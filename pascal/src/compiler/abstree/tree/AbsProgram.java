package compiler.abstree.tree;

import compiler.abstree.AbsVisitor;

/**
 * Opis programa.
 */
public class AbsProgram extends AbsTree {

	/** Ime programa. */
	public AbsDeclName name;
	
	/** Deklaracije. */
	public AbsDecls decls;
	
	/** Blok. */
	public AbsBlockStmt stmt;
	
	public AbsProgram(AbsDeclName name, AbsDecls decls, AbsBlockStmt stmt) {
		this.name = name;
		this.decls = decls;
		this.stmt = stmt;
	}
	
	public void accept(AbsVisitor visitor) {
		visitor.visit(this);
	}

}
