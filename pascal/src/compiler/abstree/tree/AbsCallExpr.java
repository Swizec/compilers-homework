package compiler.abstree.tree;

import compiler.abstree.AbsVisitor;

/**
 * Izrazi za opis vrednosti: klic podprograma.
 */
public class AbsCallExpr extends AbsValExpr {
	
	/* Ime podprograma. */
	public AbsValName name;
	
	/* Argumenti. */
	public AbsValExprs args;
	
	public AbsCallExpr(AbsValName name, AbsValExprs args) {
		this.name = name;
		this.args = args;
	}

	public void accept(AbsVisitor visitor) {
		visitor.visit(this);
	}

}
