package compiler.imcode;

import java.io.*;
import java.util.*;

import compiler.frames.*;

public class ImcCALL extends ImcExpr {

	/** Labela funkcije.  */
	public FrmLabel label;

	/** Argumenti funkcijskega klica (vkljucno s FP).  */
	public LinkedList<ImcExpr> args;
	
	/** Velikost argumentov (vklju"cno s FP).  */
	public LinkedList<Integer> size;

	public ImcCALL(FrmLabel label) {
		this.label = label;
		this.args = new LinkedList<ImcExpr>();
		this.size = new LinkedList<Integer>();
	}

	@Override
	public void toXML(PrintStream xml) {
		xml.print("<imcnode kind=\"CALL\" value=\"" + label.name() + "\">\n");
		Iterator<ImcExpr> args = this.args.iterator();
		while (args.hasNext()) {
			ImcExpr arg = args.next();
			arg.toXML(xml);
		}
		xml.print("</imcnode>\n");
	}

	@Override
	public ImcESEQ linear() {
		ImcSEQ linStmt = new ImcSEQ();
		ImcCALL linCall = new ImcCALL(label);
		Iterator<ImcExpr> args = this.args.iterator();
		while (args.hasNext()) {
			FrmTemp temp = new FrmTemp();
			ImcExpr arg = args.next();
			ImcESEQ linArg = arg.linear();
			linStmt.stmts.addAll(((ImcSEQ)linArg.stmt).stmts);
			linStmt.stmts.add(new ImcMOVE(new ImcTEMP(temp), linArg.expr));
			linCall.args.add(new ImcTEMP(temp));
		}
		linCall.size = this.size;
		FrmTemp temp = new FrmTemp();
		linStmt.stmts.add(new ImcMOVE(new ImcTEMP(temp), linCall));
		return new ImcESEQ(linStmt, new ImcTEMP(temp));
	}

}
