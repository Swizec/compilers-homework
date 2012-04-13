package compiler.abstree.tree;

import compiler.abstree.AbsVisitor;

/**
 * Deklaracija tipa.
 */
public class AbsTypeDecl extends AbsDecl {

	/** Ime tipa. */
	public AbsDeclName name;
	
	/** Tip tipa. */
	public AbsTypeExpr type;
	
	public AbsTypeDecl(AbsDeclName name, AbsTypeExpr type) {
		this.name = name;
		this.type = type;
	}

	public void accept(AbsVisitor visitor) {
		visitor.visit(this);
	}

}
