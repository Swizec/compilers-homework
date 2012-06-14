package compiler.abstree.tree;

import compiler.abstree.AbsVisitor;

/**
 * Stavek '?:'.
 */

public class AbsInIfStmt extends AbsValExpr {

	/** Pogoj. */
	public AbsValExpr cond;

	/** Vrednost ob izpolnjenem pogoju. */
	public AbsValExpr thenVal;

	/** Vrednost ob neizpolnjenem pogoju. */
	public AbsValExpr elseVal;

	public AbsInIfStmt(AbsValExpr cond, AbsValExpr thenVal, AbsValExpr elseVal) {
		this.cond = cond;
		this.thenVal = thenVal;
		this.elseVal = elseVal;
        }

	public void accept(AbsVisitor visitor) {
		visitor.visit(this);
	}

}
