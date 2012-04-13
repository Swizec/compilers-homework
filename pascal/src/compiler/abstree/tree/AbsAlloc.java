package compiler.abstree.tree;

import compiler.abstree.AbsVisitor;

public class AbsAlloc extends AbsValExpr {

	/** Tip dodoeljenega podatka, */
	public AbsTypeExpr type;
	
	public AbsAlloc(AbsTypeExpr type) {
		this.type = type;
	}
	
	public void accept(AbsVisitor visitor) {
		visitor.visit(this);
	}

}
