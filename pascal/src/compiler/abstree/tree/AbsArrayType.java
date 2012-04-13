package compiler.abstree.tree;

import compiler.abstree.AbsVisitor;

/**
 * Izrazi za opis tipov: tabele.
 */
public class AbsArrayType extends AbsTypeExpr {

	/** Tip elementa. */
	public AbsTypeExpr type;
	
	/** Spodnja medja tabele. */
	public AbsValExpr loBound;
	
	/** Zgornja meja tabele. */
	public AbsValExpr hiBound;
	
	public AbsArrayType(AbsTypeExpr type, AbsValExpr loBound, AbsValExpr hiBound) {
		this.type = type;
		this.loBound = loBound;
		this.hiBound = hiBound;
	}
	
	public void accept(AbsVisitor visitor) {
		visitor.visit(this);
	}

}
