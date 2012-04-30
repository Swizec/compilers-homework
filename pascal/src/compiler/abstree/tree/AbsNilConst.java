package compiler.abstree.tree;

import compiler.abstree.AbsVisitor;

/**
 * Izrazi za opis vrednosti: konstanta 'nil'.
 */
public class AbsNilConst extends AbsConstExpr {

	public AbsNilConst() {
	}

	public void accept(AbsVisitor visitor) {
		visitor.visit(this);
	}

}
