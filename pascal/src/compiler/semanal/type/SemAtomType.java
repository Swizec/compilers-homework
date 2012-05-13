package compiler.semanal.type;

import java.io.*;

/** Opis atomarnih tipov. */
public class SemAtomType extends SemType {
	
	public static final int BOOL = 0;
	public static final int CHAR = 1;
	public static final int INT = 2;
	public static final int VOID = 3;
	
	/* Tip. */
	public int type;
	
	public SemAtomType(int type) {
		this.type = type;
	}
	
	@Override
	public void toXML(PrintStream xml) {
		switch (type) {
		case SemAtomType.BOOL:
			xml.print("<semtype kind=\"BOOL\"/>\n");
			break;
		case SemAtomType.CHAR:
			xml.print("<semtype kind=\"CHAR\"/>\n");
			break;
		case SemAtomType.INT:
			xml.print("<semtype kind=\"INT\"/>\n");
			break;
		case SemAtomType.VOID:
			xml.print("<semtype kind=\"VOID\"/>");
			break;
		}
	}

	@Override
	public boolean coercesTo(SemType type) {
		if (type instanceof SemAtomType) {
			SemAtomType atomType = (SemAtomType)type;
			return this.type == atomType.type;
		} else
			return false;
	}
	
	@Override
	public int size() {
		return 4;
	}
	
}
