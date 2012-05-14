package compiler.frames;

import java.io.*;

import compiler.abstree.tree.*;

/** Dostop do komponente strukture.  */
public class FrmCmpAccess extends FrmAccess {

	/** Opis komponente.  */
	public AbsVarDecl cmp;

	/** Odmik od zacentega naslova strukture.  */
	public int offset;

	public FrmCmpAccess(AbsVarDecl cmp, int offset) {
		this.cmp = cmp;
		this.offset = offset;
	}

	public void toXML(PrintStream xml) {
		xml.print("<frmnode>\n<frm kind=\"cmp offset\" value=\"" + offset + "\"/>\n</frmnode>\n");
	}

}
