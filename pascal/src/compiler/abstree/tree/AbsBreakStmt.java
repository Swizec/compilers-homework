package compiler.abstree.tree;

import compiler.abstree.AbsVisitor;

/**
 * Stavek 'while'.
 */
public class AbsBreakStmt extends AbsStmt {

	public void accept(AbsVisitor visitor) {
		visitor.visit(this);
	}

}
