package compiler.abstree.tree;

import compiler.abstree.AbsVisitor;

/**
 * Stavek 'while'.
 */
public class AbsWhileStmt extends AbsStmt {

	/** Pogoj. */
	public AbsValExpr cond;
	
	/** Stavek. */
	public AbsStmt stmt;
	
	public AbsWhileStmt(AbsValExpr cond, AbsStmt stmt) {
		this.cond = cond;
		this.stmt = stmt;
	}

	public void accept(AbsVisitor visitor) {
		visitor.visit(this);
	}

}
