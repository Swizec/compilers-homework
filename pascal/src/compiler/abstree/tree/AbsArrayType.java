package compiler.abstree.tree;

import compiler.abstree.AbsVisitor;

/**
 * Izrazi za opis tipov: tabele.
 */
public class AbsArrayType extends AbsTypeExpr {

	/** Tip elementa. */
	public AbsTypeExpr type;

	/** Spodnja medja tabele. */
	public AbsValExpr loBound;

	/** Zgornja meja tabele. */
	public AbsValExpr hiBound;

    public boolean single;

    public AbsArrayType(AbsTypeExpr type, AbsValExpr loBound, AbsValExpr hiBound) {
		this.type = type;
		this.loBound = loBound;
		this.hiBound = hiBound;
                this.single = false;
	}

    public AbsArrayType(AbsTypeExpr type, AbsValExpr loBound, AbsValExpr hiBound, boolean single) {
		this.type = type;
		this.loBound = loBound;
		this.hiBound = hiBound;
                this.single = single;
	}

	public void accept(AbsVisitor visitor) {
		visitor.visit(this);
	}

}
