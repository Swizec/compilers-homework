package compiler.abstree.tree;

import compiler.abstree.AbsVisitor;

/**
 * Deklaracije: funkcija.
 */
public class AbsFunDecl extends AbsDecl {

	/** Ime. */
	public AbsDeclName name;
	
	/** Parametri. */
	public AbsDecls pars;
	
	/** Tip. */
	public AbsTypeExpr type;
	
	/** Deklaracije. */
	public AbsDecls decls;
	
	/** Stavek. */
	public AbsBlockStmt stmt;
	
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
