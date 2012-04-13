package compiler.abstree.tree;

import compiler.abstree.AbsVisitor;

/**
 * Izrazi za opis vrednosti: konstante atomarnih tipov.
 */
public class AbsAtomConst extends AbsConstExpr {
	
	public final static int BOOL = 0;
	public final static int CHAR = 1;
	public final static int INT = 2;

	/** Vrednost konstante. */
	public String value;
	
	/** Tip konstante. */
	public int type;
	
	public AbsAtomConst(String value, int type) {
		this.value = value;
		this.type = type;
	}

	public void accept(AbsVisitor visitor) {
		visitor.visit(this);
	}

}
