package compiler.abstree.tree;

import compiler.abstree.AbsVisitor;

/**
 * Izrazi za opis vrednosti: izrazi z binarnim operatorjem.
 */
public class AbsBinExpr extends AbsValExpr {

	public static final int ADD = 0;
	public static final int SUB = 1;
	public static final int MUL = 2;
	public static final int DIV = 3;
	public static final int EQU = 4;
	public static final int NEQ = 5;
	public static final int LTH = 6;
	public static final int GTH = 7;
	public static final int LEQ = 8;
	public static final int GEQ = 9;
	public static final int AND = 10;
	public static final int OR = 11;
	public static final int ARRACCESS = 12;
	public static final int RECACCESS = 13;
	
	/* Binarni operator. */
	public int oper;
	
	/* Levi podizraz. */
	public AbsValExpr fstExpr;
	
	/* Desni podizraz. */
	public AbsValExpr sndExpr;
	
	public AbsBinExpr(int oper, AbsValExpr fstExpr, AbsValExpr sndExpr) {
		this.oper = oper;
		this.fstExpr = fstExpr;
		this.sndExpr = sndExpr;
	}
	
	public void accept(AbsVisitor visitor) {
		visitor.visit(this);
	}

}
