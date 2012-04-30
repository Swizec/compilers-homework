package compiler.abstree.tree;

import compiler.abstree.AbsVisitor;

/**
 * Deklaracije: procedura.
 */
public class AbsProcDecl extends AbsDecl {

	/** Ime. */
	public AbsDeclName name;
	
	/** Parametri. */
	public AbsDecls pars;
	
	/** Deklaracije. */
	public AbsDecls decls;
	
	/** Stavek. */
	public AbsBlockStmt stmt;
	
	public AbsProcDecl(AbsDeclName name, AbsDecls pars, AbsDecls decls, AbsBlockStmt stmt) {
		this.name = name;
		this.pars = pars;
		this.decls = decls;
		this.stmt = stmt;
	}

	public void accept(AbsVisitor visitor) {
		visitor.visit(this);
	}

}
