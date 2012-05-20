package compiler.imcode;

import java.io.*;

import compiler.frames.*;

public class ImcTEMP extends ImcExpr {

	/** Zacasna spremenljivka.  */
	public FrmTemp temp;

	public ImcTEMP(FrmTemp temp) {
		this.temp = temp;
	}

	@Override
	public void toXML(PrintStream xml) {
		xml.print("<imcnode kind=\"TEMP\" value=\"" + temp.name() + "\"/>\n");
	}

	@Override
	public ImcESEQ linear() {
		return new ImcESEQ(new ImcSEQ(), this);
	}

}
