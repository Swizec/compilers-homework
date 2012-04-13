package compiler.abstree.tree;

import compiler.abstree.AbsVisitor;

/**
 * Deklaracija konstante.
 */
public class AbsConstDecl extends AbsDecl {

	/** Ime konstante. */
	public AbsDeclName name;
	
	/** Vrednost konstante. */
	public AbsValExpr value;
	
	public AbsConstDecl(AbsDeclName name, AbsValExpr value) {
		this.name = name;
		this.value = value;
	}

	public void accept(AbsVisitor visitor) {
		visitor.visit(this);
	}

}
