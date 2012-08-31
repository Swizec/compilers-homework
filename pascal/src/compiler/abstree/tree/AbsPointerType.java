package compiler.abstree.tree;

import compiler.abstree.AbsVisitor;

/**
 * Izrazi za opis tipov: kazalci.
 */
public class AbsPointerType extends AbsTypeExpr {

	/** Tip podatka. */
	public AbsTypeExpr type;

    public boolean single;

    public AbsPointerType(AbsTypeExpr type) {
		this.type = type;
                this.single = false;
	}

    public AbsPointerType(AbsTypeExpr type, boolean single) {
		this.type = type;
                this.single = single;
	}

	public void accept(AbsVisitor visitor) {
		visitor.visit(this);
	}

}
