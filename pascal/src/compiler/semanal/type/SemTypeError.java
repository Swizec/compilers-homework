package compiler.semanal.type;

import java.io.*;

/** Opis napake. */
public class SemTypeError extends SemType {

	@Override
	public void toXML(PrintStream xml) {
		xml.print("<semtypeerror/>\n");
	}
	
	@Override
	public boolean coercesTo(SemType type) {
		return false;
	}

}
