package compiler.frames;

import java.io.*;

import compiler.abstree.tree.*;

/** Dostop do argumenta funkcije.  */
public class FrmArgAccess extends FrmAccess {

	/** Opis argumenta.  */
	public AbsVarDecl var;

	/** Klicni zapis funkcije, v kateri je argument deklariran.  */
	public FrmFrame frame;

	/** Odmik od FPja.  */
	public int offset;

	public FrmArgAccess(AbsVarDecl var, FrmFrame frame) {
		this.var = var;
		this.frame = frame;
		this.offset = 4 + frame.numArgs * 4;
		frame.numArgs++;
	}

	public void toXML(PrintStream xml) {
		xml.print("<frmnode>\n<frm kind=\"arg offset\" value=\"" + offset + "\"/>\n</frmnode>\n");
	}

}
