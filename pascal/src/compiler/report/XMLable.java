package compiler.report;

import java.io.*;

public interface XMLable {

	/**
	 * Izpise objekt v XML obliki na izhodni tok.
	 * 
	 * @param xml
	 *            Izhodni tok, na katerega izpise ta objekt v XML obliki.
	 */
	public void toXML(PrintStream xml);
}
