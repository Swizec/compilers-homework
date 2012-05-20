package compiler.imcode;

import java.io.*;

import compiler.frames.*;

public class ImcCJUMP extends ImcStmt {

	/** Koda pogoja.  */
	public ImcExpr cond;

	/** Labela skoka, ce je pogoj izpolnjen.  */
	public FrmLabel trueLabel;

	/** Lanbela skoka, ce pogoj ni izpolnjen. */
	public FrmLabel falseLabel;

	public ImcCJUMP(ImcExpr cond, FrmLabel trueLabel, FrmLabel falseLabel) {
		this.cond = cond;
		this.trueLabel = trueLabel;
		this.falseLabel = falseLabel;
	}

	@Override
	public void toXML(PrintStream xml) {
		xml.print("<imcnode kind=\"CJUMP\" value=\"" + trueLabel.name() + "," + falseLabel.name() + "\">\n");
		cond.toXML(xml);
		xml.print("</imcnode>\n");
	}

	@Override
	public ImcSEQ linear() {
		ImcSEQ lin = new ImcSEQ();
		ImcESEQ linCond = cond.linear();
		FrmLabel newFalseLabel = FrmLabel.newLabel();
		lin.stmts.addAll(((ImcSEQ)linCond.stmt).stmts);
		lin.stmts.add(new ImcCJUMP(linCond.expr, trueLabel, newFalseLabel));
		lin.stmts.add(new ImcLABEL(newFalseLabel));
		lin.stmts.add(new ImcJUMP(falseLabel));
		return lin;
	}

}
