package compiler.imcode;

import java.io.*;

import compiler.frames.*;

public class ImcJUMP extends ImcStmt {

	/** Labela skoka.  */
	public FrmLabel label;

	public ImcJUMP(FrmLabel label) {
		this.label = label;
	}

	@Override
	public void toXML(PrintStream xml) {
		xml.print("<imcnode kind=\"JUMP\" value=\"" + label.name() + "\"/>\n");
	}

	@Override
	public ImcSEQ linear() {
		ImcSEQ lin = new ImcSEQ();
		lin.stmts.add(this);
		return lin;
	}

}
