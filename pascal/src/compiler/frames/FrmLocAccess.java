package compiler.frames;

import java.io.*;

import compiler.abstree.tree.*;
import compiler.semanal.*;
import compiler.semanal.type.*;

/** Dostop do lokalne spremenljivke.  */
public class FrmLocAccess extends FrmAccess {

	/** Opis spremenljivke.  */
	public AbsVarDecl var;

	/** Klicni zapis funkcije, v kateri je spremenljivka deklarirana.  */
	public FrmFrame frame;

	/** Odmik od FPja.  */
	public int offset;

	public FrmLocAccess(AbsVarDecl var, FrmFrame frame) {
		this.var = var;
		this.frame = frame;
		
		SemType type = SemDesc.getActualType(var);
		this.offset = 0 - frame.sizeLocs - type.size();
		frame.sizeLocs = frame.sizeLocs + type.size();
	}

	public void toXML(PrintStream xml) {
		xml.print("<frmnode>\n<frm kind=\"loc offset\" value=\"" + offset + "\"/>\n</frmnode>\n");
	}

}
