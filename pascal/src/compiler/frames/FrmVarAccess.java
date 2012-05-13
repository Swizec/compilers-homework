package compiler.frames;

import java.io.*;

import compiler.abstree.tree.*;

/** Dostop do globalne spremenljivke.  */
public class FrmVarAccess extends FrmAccess {

	/** Opis spremenljivke.  */
	public AbsVarDecl var;

	/** Labela spremenljivke.  */
	public FrmLabel label;

	public FrmVarAccess(AbsVarDecl var) {
		this.var = var;
		label = FrmLabel.newLabel(var.name.name);
	}

	public void toXML(PrintStream xml) {
		xml.print("<frmnode>\n<frm kind=\"label\" value=\"" + label.name() + "\"/>\n</frmnode>\n");
	}

}
