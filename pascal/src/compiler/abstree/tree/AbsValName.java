package compiler.abstree.tree;

import compiler.abstree.AbsVisitor;

/**
 * Izrazi za opis vrednosti: ime.
 */
public class AbsValName extends AbsValExpr {
	
	/* Ime. */
	public String name;
	
	public AbsValName(String name) {
		this.name = name;
	}

	public void accept(AbsVisitor visitor) {
		visitor.visit(this);
	}

}
