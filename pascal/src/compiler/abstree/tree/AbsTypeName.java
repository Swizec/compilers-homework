package compiler.abstree.tree;

import compiler.abstree.AbsVisitor;

/**
 * Izrazi za opis tipov: ime.
 */
public class AbsTypeName extends AbsTypeExpr {
	
	/* Ime. */
	public String name;
	
	public AbsTypeName(String name) {
		this.name = name;
	}

	public void accept(AbsVisitor visitor) {
		visitor.visit(this);
	}

}
