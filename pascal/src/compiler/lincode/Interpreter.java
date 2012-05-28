package compiler.lincode;

import java.util.*;

import compiler.report.*;
import compiler.frames.*;
import compiler.imcode.*;

public class Interpreter {

	int debug = 0;

	/** Pomnilnik.  */
	public HashMap<Integer,Integer> memory = new HashMap<Integer,Integer>();

	/** Preslikava label globalnih spremenljivk in konstant v naslove.  */
	public HashMap<String,Integer> labels = new HashMap<String,Integer>();

	/** Preslikava label funkcij v kodo.  */
	public HashMap<String,ImcCodeChunk> chunks = new HashMap<String,ImcCodeChunk>();

	/** Lokalne spremenljivke.  */
	public HashMap<String,Integer> temps = new HashMap<String,Integer>();

	/** Kazalec na sklad.  */
	public Integer SP;

	/** Kazalec na klicni zapis.  */
	public Integer FP;

	/** Kazalec na kopico.  */
	public Integer HP;

	public Interpreter(LinkedList<ImcChunk> progChunks) {
		SP = 65536;
		FP = 65536;
		HP = 1024;

		Iterator<ImcChunk> chunks = progChunks.iterator();
		while (chunks.hasNext()) {
			ImcChunk chunk = chunks.next();
			if (chunk instanceof ImcCodeChunk) {
				ImcCodeChunk codeChunk = (ImcCodeChunk)chunk;
				this.chunks.put(codeChunk.frame.label.name(), codeChunk);
			}
			if (chunk instanceof ImcDataChunk) {
				ImcDataChunk dataChunk = (ImcDataChunk)chunk;
				this.labels.put(dataChunk.label.name(), HP);
				HP = HP + dataChunk.size;
			}
		}

		execFun("_main");
	}

	public static String prefix = "";

	public Integer execFun(String label) {
		if (label.equals("_malloc")) {
			Integer addr = HP;
			HP = HP + LD(SP + 4);
			return addr;
		}
		if (label.equals("_free")) {
			return 0;
		}
		if (label.equals("_getch")) {
			return (new Scanner(System.in)).nextInt();
		}
		if (label.equals("_getint")) {
			return (new Scanner(System.in)).nextInt();
		}
		if (label.equals("_putch")) {
			System.out.print((char)(LD(SP + 4)).byteValue());
			return 0;
		}
		if (label.equals("_putint")) {
			System.out.print(LD(SP + 4));
			return 0;
		}
		if (label.equals("_ord")) {
			return (LD(SP + 4));
		}
		if (label.equals("_chr")) {
			return (LD(SP + 4));
		}

		if (debug == 1) System.err.println(prefix + "=> " + label + " SP:" + SP + " FP:" + FP);

		ImcCodeChunk chunk = chunks.get(label);
		FrmFrame frame = chunk.frame;
		HashMap<String,Integer> outerTemps = temps;
		temps = new HashMap<String,Integer>();

		// Prolog:
		ST(SP - frame.sizeLocs - 4, FP);
		FP = SP;
		SP = SP - frame.size();
		ST(frame.FP, FP);
		if (debug == 1) System.err.println(prefix + "in " + label + " SP:" + SP + " FP:" + FP);

		// jedro:
		LinkedList<ImcStmt> stmts = ((ImcSEQ)(chunk.lincode)).stmts;
		int PC = 0;
		while (PC < stmts.size()) {
			FrmLabel newLabel = execStmt(stmts.get(PC));
			if (newLabel != null) {
				// Razveljavimo cevovod :)
				PC = stmts.indexOf(new ImcLABEL(newLabel));
			}
			else PC++;
		}

		// Epilog:
		SP = SP + frame.size();
		FP = LD(SP - frame.sizeLocs - 4);
		Integer retValue = 0;
		if (frame.RV != null) retValue = LD(frame.RV);
		if (debug == 1) System.err.println(prefix + "<= " + label + " SP:" + SP + " FP:" + FP);

		temps = outerTemps;
		return retValue;
	}

	public FrmLabel execStmt(ImcStmt stmt) {
		if (debug == 1) System.err.println(prefix + stmt);

		if (stmt instanceof ImcCJUMP) {
			ImcCJUMP cjump = (ImcCJUMP)stmt;
			Integer cond = execExpr(cjump.cond);
			return cond != 0 ? cjump.trueLabel : cjump.falseLabel;
		}
		if (stmt instanceof ImcEXP) {
			ImcEXP expr = (ImcEXP)stmt;
			execExpr(expr.expr);
			return null;
		}
		if (stmt instanceof ImcJUMP) {
			return ((ImcJUMP)stmt).label;
		}
		if (stmt instanceof ImcLABEL) {
			return null;
		}
		if (stmt instanceof ImcMOVE) {
			ImcMOVE move = (ImcMOVE)stmt;
			if (move.dst instanceof ImcTEMP) {
				ImcTEMP dst = (ImcTEMP)move.dst;
				Integer src = execExpr(move.src);
				ST(dst.temp, src);
				return null;
			}
			if (move.dst instanceof ImcMEM) {
				Integer dst = execExpr(((ImcMEM)move.dst).expr);
				Integer src = execExpr(move.src);
				ST(dst, src);
				return null;
			}
			Report.error("Illegal MOVE statement.", 0);
		}
		Report.error("Unknown statement: " + stmt, 0);
		return null;
	}

	public Integer execExpr(ImcExpr expr) {
		// if (debug == 1) System.err.println(prefix + expr);

		if (expr instanceof ImcBINOP) {
			ImcBINOP binop = (ImcBINOP)expr;
			Integer lval = execExpr(binop.limc);
			Integer rval = execExpr(binop.rimc);
			switch (binop.op) {
			case ImcBINOP.ADD: return lval + rval;
			case ImcBINOP.SUB: return lval - rval;
			case ImcBINOP.MUL: return lval * rval;
			case ImcBINOP.DIV: return lval / rval;
			case ImcBINOP.EQU: return (lval == rval ? 1 : 0);
			case ImcBINOP.NEQ: return (lval != rval ? 1 : 0);
			case ImcBINOP.LTH: return (lval < rval ? 1 : 0);
			case ImcBINOP.GTH: return (lval > rval ? 1 : 0);
			case ImcBINOP.LEQ: return (lval <= rval ? 1 : 0);
			case ImcBINOP.GEQ: return (lval >= rval ? 1 : 0);
			case ImcBINOP.AND: return lval * rval;
			case ImcBINOP.OR : return (lval + rval) % 2;
			}
		}
		if (expr instanceof ImcCALL) {
			ImcCALL call = (ImcCALL)expr;
			int offset = 0;
			for (int a = 0; a < call.args.size(); a++) {
				Integer v = execExpr(call.args.get(a));
				if (call.size.get(a) == 4) {
					ST(SP + 4 * offset, v);
					offset = offset + 1;
				} else {
					for (int d = 0; d < call.size.get(a) / 4; d++) {
						Integer dv = LD(v + 4 * d);
						ST(SP + 4 * offset, dv);
						offset = offset + 1;
					}
				}
			}
			prefix = prefix + "  ";
			Integer result = execFun(call.label.name());
			prefix = prefix.substring(2);
			return result;
		}
		if (expr instanceof ImcCONST) {
			return ((ImcCONST)expr).value;
		}
		if (expr instanceof ImcMEM) {
			return LD(execExpr(((ImcMEM)expr).expr));
		}
		if (expr instanceof ImcNAME) {
			return labels.get(((ImcNAME)expr).label.name());
		}
		if (expr instanceof ImcTEMP) {
			return LD(((ImcTEMP)expr).temp);
		}
		Report.error("Unknown expression: " + expr, 0);
		return null;
	}

	public Integer LD(Integer addr) {
		Integer data = memory.get(addr / 4);
		if (debug == 1) System.err.println(prefix + "[" + addr + "]" + "->" + (data == null ? 0 : data));
		if (data == null) return 0; else return data;
	}

	public Integer LD(FrmTemp temp) {
		Integer value = temps.get(temp.name());
		if (debug == 1) System.err.println(prefix + temp.name() + "->" + (value == null ? 0 : value));
		if (value == null) return 0; else return value;
	}

	public void ST(Integer addr, Integer data) {
		if (debug == 1) System.err.println(prefix + "[" + addr + "]" + "<-" + data);
		memory.put(addr / 4, data);
	}

	public void ST(FrmTemp temp, Integer value) {
		if (debug == 1) System.err.println(prefix + temp.name() + "<-" + value);
		temps.put(temp.name(), value);
	}

}
