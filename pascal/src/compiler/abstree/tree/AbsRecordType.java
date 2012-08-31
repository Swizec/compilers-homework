package compiler.abstree.tree;

import compiler.abstree.AbsVisitor;

/**
 * Izrazi za opis tipov: zapisi.
 */
public class AbsRecordType extends AbsTypeExpr {

	public AbsDecls fields;

    public boolean single;

	public AbsRecordType(AbsDecls fields) {
		this.fields = fields;
                this.single = false;
	}

    public AbsRecordType(AbsDecls fields, boolean single) {
		this.fields = fields;
                this.single = single;
	}

	public void accept(AbsVisitor visitor) {
		visitor.visit(this);
	}

}
