package compiler.abstree.tree;

import compiler.abstree.AbsVisitor;

/** Stavek for.
 */
public class AbsForStmt extends AbsStmt {

	/** Ime zancne spremenljivke. */
	public AbsValName name;
	
	/** Spodnja meja. */
	public AbsValExpr loBound;
	
	/** Zgornja meja. */
	public AbsValExpr hiBound;
	
	/** Stavek. */
	public AbsStmt stmt;
	
	public AbsForStmt(AbsValName name, AbsValExpr loBound, AbsValExpr hiBound, AbsStmt stmt) {
		this.name = name;
		this.loBound = loBound;
		this.hiBound = hiBound;
		this.stmt = stmt;
	}
	
	public void accept(AbsVisitor visitor) {
		visitor.visit(this);
	}

}
