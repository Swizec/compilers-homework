package compiler.abstree;

import compiler.report.*;
import compiler.abstree.tree.*;

public class AbsEmptyVisitor implements AbsVisitor {
	
	@Override
	public void visit(AbsAlloc acceptor) {
		Thread.dumpStack();
		Report.error("Unimplemented visitor method.", 1);
	}

	@Override
	public void visit(AbsArrayType acceptor) {
		Thread.dumpStack();
		Report.error("Unimplemented visitor method.", 1);
	}

	@Override
	public void visit(AbsAssignStmt acceptor) {
		Thread.dumpStack();
		Report.error("Unimplemented visitor method.", 1);
	}

	@Override
	public void visit(AbsAtomConst acceptor) {
		Thread.dumpStack();
		Report.error("Unimplemented visitor method.", 1);
	}

	@Override
	public void visit(AbsAtomType acceptor) {
		Thread.dumpStack();
		Report.error("Unimplemented visitor method.", 1);
	}

	@Override
	public void visit(AbsBinExpr acceptor) {
		Thread.dumpStack();
		Report.error("Unimplemented visitor method.", 1);
	}

	@Override
	public void visit(AbsBlockStmt acceptor) {
		Thread.dumpStack();
		Report.error("Unimplemented visitor method.", 1);
	}

	@Override
	public void visit(AbsCallExpr acceptor) {
		Thread.dumpStack();
		Report.error("Unimplemented visitor method.", 1);
	}

	@Override
	public void visit(AbsConstDecl acceptor) {
		Thread.dumpStack();
		Report.error("Unimplemented visitor method.", 1);
	}

	@Override
	public void visit(AbsDeclName acceptor) {
		Thread.dumpStack();
		Report.error("Unimplemented visitor method.", 1);
	}

	@Override
	public void visit(AbsDecls acceptor) {
		Thread.dumpStack();
		Report.error("Unimplemented visitor method.", 1);
	}

	@Override
	public void visit(AbsExprStmt acceptor) {
		Thread.dumpStack();
		Report.error("Unimplemented visitor method.", 1);
	}

	@Override
	public void visit(AbsForStmt acceptor) {
		Thread.dumpStack();
		Report.error("Unimplemented visitor method.", 1);
	}

	@Override
	public void visit(AbsFunDecl acceptor) {
		Thread.dumpStack();
		Report.error("Unimplemented visitor method.", 1);
	}

	@Override
	public void visit(AbsIfStmt acceptor) {
		Thread.dumpStack();
		Report.error("Unimplemented visitor method.", 1);
	}

	@Override
	public void visit(AbsNilConst acceptor) {
		Thread.dumpStack();
		Report.error("Unimplemented visitor method.", 1);
	}

	@Override
	public void visit(AbsPointerType acceptor) {
		Thread.dumpStack();
		Report.error("Unimplemented visitor method.", 1);
	}

	@Override
	public void visit(AbsProcDecl acceptor) {
		Thread.dumpStack();
		Report.error("Unimplemented visitor method.", 1);
	}

	@Override
	public void visit(AbsProgram acceptor) {
		Thread.dumpStack();
		Report.error("Unimplemented visitor method.", 1);
	}

	@Override
	public void visit(AbsRecordType acceptor) {
		Thread.dumpStack();
		Report.error("Unimplemented visitor method.", 1);
	}

	@Override
	public void visit(AbsStmts acceptor) {
		Thread.dumpStack();
		Report.error("Unimplemented visitor method.", 1);
	}

	@Override
	public void visit(AbsTypeDecl acceptor) {
		Thread.dumpStack();
		Report.error("Unimplemented visitor method.", 1);
	}

	@Override
	public void visit(AbsTypeName acceptor) {
		Thread.dumpStack();
		Report.error("Unimplemented visitor method.", 1);
	}

	@Override
	public void visit(AbsUnExpr acceptor) {
		Thread.dumpStack();
		Report.error("Unimplemented visitor method.", 1);
	}

	@Override
	public void visit(AbsValExprs acceptor) {
		Thread.dumpStack();
		Report.error("Unimplemented visitor method.", 1);
	}

	@Override
	public void visit(AbsValName acceptor) {
		Thread.dumpStack();
		Report.error("Unimplemented visitor method.", 1);
	}

	@Override
	public void visit(AbsVarDecl acceptor) {
		Thread.dumpStack();
		Report.error("Unimplemented visitor method.", 1);
	}

	@Override
	public void visit(AbsWhileStmt acceptor) {
		Thread.dumpStack();
		Report.error("Unimplemented visitor method.", 1);
	}
	
}
