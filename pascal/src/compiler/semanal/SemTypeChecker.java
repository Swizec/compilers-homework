
package compiler.semanal;

import compiler.report.*;
import compiler.abstree.AbsVisitor;
import compiler.abstree.tree.*;
import compiler.semanal.type.*;

public class SemTypeChecker implements AbsVisitor {

    public boolean error = false;

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
        SemAtomType type = new SemAtomType(acceptor.type);
        SemDesc.setActualType(acceptor, type);
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
        acceptor.stmts.accept(this);
    }

    @Override
	public void visit(AbsCallExpr acceptor) {
        Thread.dumpStack();
        Report.error("Unimplemented visitor method.", 1);
    }

    @Override
	public void visit(AbsConstDecl acceptor) {
        acceptor.value.accept(this);
        SemType type = SemDesc.getActualType(acceptor.value);
        if (type != null) {
            SemDesc.setActualType(acceptor, type);
        }
    }

    @Override
	public void visit(AbsDeclName acceptor) {
        Thread.dumpStack();
        Report.error("Unimplemented visitor method.", 1);
    }

    @Override
	public void visit(AbsDecls acceptor) {
        for (AbsDecl decl : acceptor.decls) {
            decl.accept(this);
        }
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
        acceptor.decls.accept(this);
        acceptor.stmt.accept(this);
    }

    @Override
	public void visit(AbsRecordType acceptor) {
        Thread.dumpStack();
        Report.error("Unimplemented visitor method.", 1);
    }

    @Override
	public void visit(AbsStmts acceptor) {
        for (AbsStmt stmt : acceptor.stmts) {
            stmt.accept(this);
        }
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

