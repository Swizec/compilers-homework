package compiler.imcode;

import java.io.*;

import compiler.frames.*;

public class ImcNAME extends ImcExpr {

	/** Labela imenovane lokacije.  */
	public FrmLabel label;

	public ImcNAME(FrmLabel label) {
		this.label = label;
	}

	@Override
	public void toXML(PrintStream xml) {
		xml.print("<imcnode kind=\"NAME\" value=\"" + label.name() + "\"/>\n");
	}

	@Override
	public ImcESEQ linear() {
		return new ImcESEQ(new ImcSEQ(), this);
	}

}
