package compiler.imcode;

import java.io.*;

public class ImcMEM extends ImcExpr {

	/** Opis dostopa do pomnilnika.  */
	public ImcExpr expr;

	public ImcMEM(ImcExpr expr) {
		this.expr = expr;
	}

	@Override
	public void toXML(PrintStream xml) {
		xml.print("<imcnode kind=\"MEM\">\n");
		expr.toXML(xml);
		xml.print("</imcnode>\n");
	}

	@Override
	public ImcESEQ linear() {
		ImcESEQ lin = expr.linear();
		lin.expr = new ImcMEM(lin.expr);
		return lin;
	}

}
