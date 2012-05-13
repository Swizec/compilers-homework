package compiler.frames;

import java.io.*;
import java.util.*;

import compiler.report.*;
import compiler.abstree.tree.*;

/** Klicni zapis podprograma.  */
public class FrmFrame implements XMLable {

	/** Opis podprograma.  */
	public AbsDecl subp;

	/** Staticni nivo podprograma.  */
	public int level;

	/** Vstopna labela.  */
	public FrmLabel label;

	/** Stevilo argumentov.  */
	public int numArgs;

	/** Lokalne spremenljivke podprograma.  */
	LinkedList<FrmLocAccess> locVars;

	/** Velikost bloka lokalnih spremenljivk.  */
	public int sizeLocs;

	/** Velikost bloka za oldFP in redAddr.  */
	public int sizeFPRA = 8;

	/** Velikost bloka zacasnih spremenljivk.  */
	public int sizeTmps;

	/** Velikost bloka registrov.  */
	public int sizeRegs;

	/** Velikost izhodnih argumentov.  */
	public int sizeArgs;

	/** Kazalec FP.  */
	public FrmTemp FP;

	/** Spremenljivka z rezultatom funkcije.  */
	public FrmTemp RV;

	public FrmFrame(AbsProcDecl prc, int level) {
		this.subp = prc;
		this.level = level;
		this.label = (level == 1 ? FrmLabel.newLabel(prc.name.name) : FrmLabel.newLabel());
		this.numArgs = 0;
		this.locVars = new LinkedList<FrmLocAccess> ();
		this.sizeLocs = 0;
		this.sizeFPRA = 8;
		this.sizeTmps = 0;
		this.sizeRegs = 0;
		this.sizeArgs = 0;
		FP = new FrmTemp();
		RV = null;
	}

	public FrmFrame(AbsFunDecl fun, int level) {
		this.subp = fun;
		this.level = level;
		this.label = (level == 1 ? FrmLabel.newLabel(fun.name.name) : FrmLabel.newLabel());
		this.numArgs = 0;
		this.locVars = new LinkedList<FrmLocAccess> ();
		this.sizeLocs = 0;
		this.sizeFPRA = 8;
		this.sizeTmps = 0;
		this.sizeRegs = 0;
		this.sizeArgs = 0;
		FP = new FrmTemp();
		RV = new FrmTemp();
	}

	/** Velikost klicnega zapisa.  */
	public int size() {
		return sizeLocs + sizeFPRA + sizeTmps + sizeRegs + sizeArgs;
	}

	@Override
	public void toXML(PrintStream xml) {
		xml.print("<frmnode>\n");
		xml.print("<frm kind=\"level\" value=\"" + level + "\"/>\n");
		xml.print("<frm kind=\"label\" value=\"" + label.name() + "\"/>\n");
		xml.print("<frm kind=\"size\" value=\"" + size() + "\"/>\n");
		xml.print("<frm kind=\"FP\" value=\"" + FP.name() + "\"/>\n");
		if (RV != null) xml.print("<frm kind=\"RV\" value=\"" + RV.name() + "\"/>\n");
		xml.print("</frmnode>\n");
	}

}
