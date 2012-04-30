package compiler.semanal.type;

import java.io.*;
import java.util.*;

import compiler.abstree.tree.*;

/** Opis zapisov. */
public class SemRecordType extends SemType {

	private Vector<AbsDeclName> fieldNames;
	
	private Vector<SemType> fieldTypes;
	
	public SemRecordType() {
		fieldNames = new Vector<AbsDeclName>();
		fieldTypes = new Vector<SemType>();
	}
	
	public void addField(AbsDeclName name, SemType type) {
		fieldNames.add(name);
		fieldTypes.add(type);
	}
	
	public int getNumFields() {
		return fieldNames.size();
	}
	
	public AbsDeclName getFieldName(int i) {
		return (i < fieldNames.size()) ? fieldNames.get(i) : null;
	}

	public SemType getFieldType(int i) {
		return (i < fieldTypes.size()) ? fieldTypes.get(i) : null;
	}

	@Override
	public void toXML(PrintStream xml) {
		xml.print("<semtype kind=\"RECORD\">\n");
		for (int i = 0; i < getNumFields(); i++) getFieldType(i).toXML(xml);
		xml.print("</semtype>\n");
	}
	
	@Override
	public boolean coercesTo(SemType type) {
		if (type instanceof SemRecordType) {
			SemRecordType recordType = (SemRecordType) type;
			if (this.getNumFields() != recordType.getNumFields()) return false;
			for (int i = 0; i < getNumFields(); i++)
				if (! this.getFieldType(i).coercesTo(recordType.getFieldType(i))) return false;
			return true;
		} else
			return false;
	}

}
