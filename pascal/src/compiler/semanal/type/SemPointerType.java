package compiler.semanal.type;

import java.io.*;

/** Opis kazalcev. */
public class SemPointerType extends SemType {
	
	/** Tip elementa. */
	public SemType type;
	
	public SemPointerType(SemType type) {
		this.type = type;
	}
	
	@Override
	public void toXML(PrintStream xml) {
		xml.print("<semtype kind=\"POINTER\">\n");
		type.toXML(xml);
		xml.print("</semtype>\n");
	}

	@Override
	public boolean coercesTo(SemType type) {
		if (type instanceof SemPointerType) {
			SemPointerType pointerType = (SemPointerType)type;
			return (pointerType.type.coercesTo(this.type)) ||
				((this.type instanceof SemAtomType) && (((SemAtomType)this.type).type == SemAtomType.VOID)) ||
				((pointerType.type instanceof SemAtomType) && (((SemAtomType)pointerType.type).type == SemAtomType.VOID));
		} else
			return false;
	}
	
	@Override
	public int size() {
		return 4;
	}
	
}
