package compiler.imcode;

import java.io.*;

public class ImcMOVE extends ImcStmt {

	/** Ponor.  */
	public ImcExpr dst;

	/** Izvor.  */
	public ImcExpr src;

	public ImcMOVE(ImcExpr dst, ImcExpr src) {
		this.dst = dst;
		this.src = src;
	}

	@Override
	public void toXML(PrintStream xml) {
		xml.print("<imcnode kind=\"MOVE\">\n");
		dst.toXML(xml);
		src.toXML(xml);
		xml.print("</imcnode>\n");
	}

	@Override
	public ImcSEQ linear() {
		ImcSEQ lin = new ImcSEQ();
		ImcESEQ dst = this.dst.linear();
		ImcESEQ src = this.src.linear();
		lin.stmts.addAll(((ImcSEQ)dst.stmt).stmts);
		lin.stmts.addAll(((ImcSEQ)src.stmt).stmts);
		lin.stmts.add(new ImcMOVE(dst.expr, src.expr));
		return lin;
	}

}
