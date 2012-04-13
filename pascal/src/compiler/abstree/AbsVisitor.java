package compiler.abstree;

import compiler.abstree.tree.*;

public interface AbsVisitor {

	public void visit(AbsAlloc acceptor);
	public void visit(AbsArrayType acceptor);
	public void visit(AbsAssignStmt acceptor);
	public void visit(AbsAtomConst acceptor);
	public void visit(AbsAtomType acceptor);
	public void visit(AbsBinExpr acceptor);
	public void visit(AbsBlockStmt acceptor);
	public void visit(AbsCallExpr acceptor);
	public void visit(AbsConstDecl acceptor);
	public void visit(AbsDeclName acceptor);
	public void visit(AbsDecls acceptor);
	public void visit(AbsExprStmt acceptor);
	public void visit(AbsForStmt acceptor);
	public void visit(AbsFunDecl acceptor);
	public void visit(AbsIfStmt acceptor);
	public void visit(AbsNilConst acceptor);
	public void visit(AbsPointerType acceptor);
	public void visit(AbsProcDecl acceptor);
	public void visit(AbsProgram acceptor);
	public void visit(AbsRecordType acceptor);
	public void visit(AbsStmts acceptor);
	public void visit(AbsTypeDecl acceptor);
	public void visit(AbsTypeName acceptor);
	public void visit(AbsUnExpr acceptor);
	public void visit(AbsValExprs acceptor);
	public void visit(AbsValName acceptor);
	public void visit(AbsVarDecl acceptor);
	public void visit(AbsWhileStmt acceptor);

}
