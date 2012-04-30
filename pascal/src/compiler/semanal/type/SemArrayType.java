package compiler.semanal.type;

import java.io.*;

/** Opis tabel. */
public class SemArrayType extends SemType {
	
	/** Tip elementa. */
	public SemType type;
	
	/** Spodnja meja. */
	public int loBound;
	
	/** Zgornja meja. */
	public int hiBound;
	
	public SemArrayType(SemType type, int loBound, int hiBound) {
		this.type = type;
		this.loBound = loBound;
		this.hiBound = hiBound;
	}
	
	@Override
	public void toXML(PrintStream xml) {
		xml.print("<semtype kind=\"ARRAY\" value=\"(" + loBound + "," + hiBound + ")\">\n");
		type.toXML(xml);
		xml.print("</semtype>\n");
	}
	
	@Override
	public boolean coercesTo(SemType type) {
		if (type instanceof SemArrayType) {
			SemArrayType arrayType = (SemArrayType)type;
			return (arrayType.loBound == loBound) && (arrayType.hiBound == hiBound) && (arrayType.type.coercesTo(this.type));
		} else
			return false;
	}
	
}
