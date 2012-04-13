package compiler.abstree.tree;

import compiler.abstree.AbsVisitor;

/**
 * Izrazi za opis tipov: kazalci.
 */
public class AbsPointerType extends AbsTypeExpr {

	/** Tip podatka. */
	public AbsTypeExpr type;
	
	public AbsPointerType(AbsTypeExpr type) {
		this.type = type;
	}
	
	public void accept(AbsVisitor visitor) {
		visitor.visit(this);
	}

}
