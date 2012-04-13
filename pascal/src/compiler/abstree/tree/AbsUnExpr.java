package compiler.abstree.tree;

import compiler.abstree.AbsVisitor;

/**
 * Izrazi za opis vrednosti: izrazi z unarnim operatorjem.
 */
public class AbsUnExpr extends AbsValExpr {

	public static final int VAL = 0;
	public static final int MEM = 1;
	public static final int ADD = 2;
	public static final int SUB = 3;
	public static final int NOT = 4;
	
	/** Unarni operator. */
	public int oper;
	
	/** Podizraz. */
	public AbsValExpr expr;
	
	public AbsUnExpr(int oper, AbsValExpr expr) {
		this.oper = oper;
		this.expr = expr;
	}
	
	public void accept(AbsVisitor visitor) {
		visitor.visit(this);
	}

}
