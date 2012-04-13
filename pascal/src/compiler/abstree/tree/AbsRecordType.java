package compiler.abstree.tree;

import compiler.abstree.AbsVisitor;

/**
 * Izrazi za opis tipov: zapisi.
 */
public class AbsRecordType extends AbsTypeExpr {
	
	public AbsDecls fields;
	
	public AbsRecordType(AbsDecls fields) {
		this.fields = fields;
	}

	public void accept(AbsVisitor visitor) {
		visitor.visit(this);
	}

}
