package compiler.abstree.tree;

import compiler.abstree.AbsVisitor;

/**
 * Sestavljeni stavek.
 */
public class AbsBlockStmt extends AbsStmt {

	/** Stavki. */
	public AbsStmts stmts;
	
	public AbsBlockStmt(AbsStmts stmts) {
		this.stmts = stmts;
	}

	public void accept(AbsVisitor visitor) {
		visitor.visit(this);
	}

}
