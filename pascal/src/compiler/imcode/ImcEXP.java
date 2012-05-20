package compiler.imcode;

import java.io.*;

public class ImcEXP extends ImcStmt {

	/** Izraz.  */
	public ImcExpr expr;

	public ImcEXP(ImcExpr expr) {
		this.expr = expr;
	}

	@Override
	public void toXML(PrintStream xml) {
		xml.print("<imcnode kind=\"EXP\">\n");
		expr.toXML(xml);
		xml.print("</imcnode>\n");
	}

	@Override
	public ImcSEQ linear() {
		ImcSEQ lin = new ImcSEQ();
		ImcESEQ linExpr = expr.linear();
		lin.stmts.addAll(((ImcSEQ)linExpr.stmt).stmts);
		lin.stmts.add(new ImcEXP(linExpr.expr));
		return lin;
	}

}

