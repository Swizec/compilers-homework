package compiler.imcode;

import java.io.*;

public class ImcBINOP extends ImcExpr {

	public static final int ADD = 0;
	public static final int SUB = 1;
	public static final int MUL = 2;
	public static final int DIV = 3;
	public static final int EQU = 4;
	public static final int NEQ = 5;
	public static final int LTH = 6;
	public static final int GTH = 7;
	public static final int LEQ = 8;
	public static final int GEQ = 9;
	public static final int AND = 10;
	public static final int OR  = 11;

	/** Operator.  */
	public int op;

	/** Koda levega podizraza.  */
	public ImcExpr limc;

	/** Koda desnega podizraza.  */
	public ImcExpr rimc;

	public ImcBINOP(int op, ImcExpr limc, ImcExpr rimc) {
		this.op = op;
		this.limc = limc;
		this.rimc = rimc;
	}

	@Override
	public void toXML(PrintStream xml) {
		String op = null;
		switch (this.op) {
		case ADD: op = "+" ; break;
		case SUB: op = "-" ; break;
		case MUL: op = "*" ; break;
		case DIV: op = "/" ; break;
		case EQU: op = "=="; break;
		case NEQ: op = "!="; break;
		case LTH: op = "&#60;" ; break;
		case GTH: op = "&#62;" ; break;
		case LEQ: op = "&#60;="; break;
		case GEQ: op = "&#62;="; break;
		case AND: op = "&#38;" ; break;
		case OR : op = "|" ; break;
		}
		xml.print("<imcnode kind=\"BINOP\" value=\"" + op + "\">\n");
		limc.toXML(xml);
		rimc.toXML(xml);
		xml.print("</imcnode>\n");
	}

	@Override
	public ImcESEQ linear() {
		ImcESEQ limc = this.limc.linear();
		ImcESEQ rimc = this.rimc.linear();
		ImcSEQ stmt = new ImcSEQ();
		stmt.stmts.addAll(((ImcSEQ)limc.stmt).stmts);
		stmt.stmts.addAll(((ImcSEQ)rimc.stmt).stmts);
		ImcESEQ lin = new ImcESEQ(stmt, new ImcBINOP(op, limc.expr, rimc.expr));
		return lin;
	}

}
