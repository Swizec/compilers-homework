package compiler.abstree.tree;

import compiler.abstree.AbsVisitor;

/**
 * Deklaracije: funkcija.
 */
public class AbsFunDecl extends AbsSubprogramDecl {

	/** Tip. */
	public AbsTypeExpr type;

	public AbsFunDecl(AbsDeclName name, AbsDecls pars, AbsTypeExpr type, AbsDecls decls, AbsBlockStmt stmt) {
		this.name = name;
		this.pars = pars;
		this.type = type;
		this.decls = decls;
		this.stmt = stmt;
	}

	public void accept(AbsVisitor visitor) {
		visitor.visit(this);
	}

}
