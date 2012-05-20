package compiler.imcode;

import java.io.*;

import compiler.frames.*;

public class ImcCodeChunk extends ImcChunk {

	/** Klicni zapis funkcije.  */
	public FrmFrame frame;

	/** Vmesna koda funkcije.  */
	public ImcStmt imcode;

	/** Linearna vmesna koda.  */
	public ImcStmt lincode;

	public ImcCodeChunk(FrmFrame frame, ImcStmt imcode) {
		this.frame = frame;
		this.imcode = imcode;
		this.lincode = null;
	}

	@Override
	public void toXML(PrintStream xml) {
		xml.print("<codechunk value=\"" + frame.label.name() + "\">\n");
		frame.toXML(xml);
		if (lincode == null) imcode.toXML(xml); else lincode.toXML(xml);
		xml.print("</codechunk>\n");
	}

}
