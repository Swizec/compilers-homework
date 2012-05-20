package compiler.imcode;

import java.io.*;

public class ImcCONST extends ImcExpr {

	/** Vrednost.  */
	public int value;

	public ImcCONST(int value) {
		this.value = value;
	}

	@Override
	public void toXML(PrintStream xml) {
		xml.print("<imcnode kind=\"CONST\" value=\"" + value + "\"/>\n");
	}

	@Override
	public ImcESEQ linear() {
		return new ImcESEQ(new ImcSEQ(), this);
	}

}
