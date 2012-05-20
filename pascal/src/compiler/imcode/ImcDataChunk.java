package compiler.imcode;

import java.io.*;

import compiler.frames.*;

public class ImcDataChunk extends ImcChunk {

	/** Naslov spremenljivke v pomnilniku.  */
	public FrmLabel label;

	/** Velikost spremenljivke v pomnilniku.  */
	public int size;

	public ImcDataChunk(FrmLabel label, int size) {
		this.label = label;
		this.size = size;
	}

	@Override
	public void toXML(PrintStream xml) {
		xml.print("<datachunk label=\"" + label.name() + "\" size=\"" + size + "\"/>\n");
	}

}
