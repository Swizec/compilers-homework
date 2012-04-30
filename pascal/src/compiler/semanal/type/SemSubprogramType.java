package compiler.semanal.type;

import java.io.*;
import java.util.*;

/** Opis podprogramov. */
public class SemSubprogramType extends SemType {
	
	private Vector<SemType> parTypes;
	
	private SemType resultType;
		
	public SemSubprogramType(SemType resultType) {
		parTypes = new Vector<SemType>();
		this.resultType = resultType;
	}
	
	public void addParType(SemType type) {
		parTypes.add(type);
	}
	
	public int getNumPars() {
		return parTypes.size();
	}
	
	public SemType getParType(int i) {
		return (i < parTypes.size()) ? parTypes.get(i) : null;
	}
	
	public SemType getResultType() {
		return resultType;
	}

	@Override
	public void toXML(PrintStream xml) {
		xml.print("<semtype kind=\"SUBPROGRAM\">\n");
		for (int i = 0; i < getNumPars(); i++) getParType(i).toXML(xml);
		getResultType().toXML(xml);
		xml.print("</semtype>\n");
	}
	
	@Override
	public boolean coercesTo(SemType type) {
		if (type instanceof SemSubprogramType) {
			SemSubprogramType subprogramType = (SemSubprogramType) type;
			if (this.getNumPars() != subprogramType.getNumPars()) return false;
			for (int i = 0; i < getNumPars(); i++)
				if (! this.getParType(i).coercesTo(subprogramType.getParType(i))) return false;
			if (! this.getResultType().coercesTo(subprogramType.getResultType())) return false;
			return true;
		} else
			return false;
	}

}
