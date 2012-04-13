package compiler.abstree.tree;

import java.util.*;

import compiler.abstree.AbsVisitor;

/**
 * Izrazi za opis vrednosti: seznam izrazov.
 */
public class AbsValExprs extends AbsTree {

	/* Izrazi. */
	public LinkedList<AbsValExpr> exprs;
	
	public AbsValExprs() {
		this.exprs = new LinkedList<AbsValExpr>();
	}
	
	public void accept(AbsVisitor visitor) {
		visitor.visit(this);
	}

}
