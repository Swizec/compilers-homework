package compiler.imcode;

import java.io.*;

import compiler.frames.*;

public class ImcLABEL extends ImcStmt {

	/** Labela imenovane lokacije.  */
	public FrmLabel label;

	public ImcLABEL(FrmLabel label) {
		this.label = label;
	}

	@Override
	public void toXML(PrintStream xml) {
		xml.print("<imcnode kind=\"LABEL\" value=\"" + label.name() + "\"/>\n");
	}

	@Override
	public ImcSEQ linear() {
		ImcSEQ lin = new ImcSEQ();
		lin.stmts.add(this);
		return lin;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof ImcLABEL) {
			return ((ImcLABEL)obj).label.name().equals(label.name());
		}
		return false;
	}

}
