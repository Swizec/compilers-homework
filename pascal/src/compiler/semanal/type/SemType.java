package compiler.semanal.type;

import compiler.report.*;

/**
 * Opis podatkovnega tipa.
 * 
 * @see SemAtomType 
 * @see SemArrayType
 * @see SemRecortType
 * @see SemPointerType
 */
public abstract class SemType implements XMLable {
	
	public abstract boolean coercesTo(SemType type);
	
	public abstract int size();
}
