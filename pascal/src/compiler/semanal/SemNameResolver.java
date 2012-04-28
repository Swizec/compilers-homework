
package compiler.semanal;

import compiler.report.*;
import compiler.abstree.AbsVisitor;
import compiler.abstree.tree.*;

public class SemNameResolver implements AbsVisitor {

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
        if (acceptor.type == AbsAtomConst.INT) {
            try {
                SemDesc.setActualConst(acceptor, Integer.parseInt(acceptor.value));
            }catch (Exception e) {
                System.out.println(String.format("int parse error at (%d, %d)",
                                                 acceptor.begLine, acceptor.endLine));
            }
        }
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
        try {
            SemTable.ins(acceptor.name.name, acceptor);
        }catch(SemIllegalInsertException e) {
            isDeclaredError(acceptor.name.name, acceptor.begLine, acceptor.begColumn);
        }

        acceptor.value.accept(this);

        Integer val = SemDesc.getActualConst(acceptor.value);
        if (val != null) {
            SemDesc.setActualConst(acceptor, val);
        }
    }

    @Override
	public void visit(AbsDeclName acceptor) {
        Thread.dumpStack();
        System.out.println("Hello World");
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

    private void isDeclaredError(String name, int line, int col){
        System.out.println(String.format("var %s is redefined at (%d,%d)", name, line, col));
        error = true;
    }

    private void notAValueError(String name, int line, int col){
        System.out.println(String.format("const %s can not be evalueted at (%d,%d)", name, line, col));
        error = true;
    }

    private void notDeclaredError(String name, int line, int col){
        System.out.println(String.format("var %s is undefined at (%d,%d)", name, line, col));
        error = true;
    }

}

