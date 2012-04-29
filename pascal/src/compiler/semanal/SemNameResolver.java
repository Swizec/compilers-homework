
package compiler.semanal;

import compiler.report.*;
import compiler.abstree.AbsVisitor;
import compiler.abstree.tree.*;

public class SemNameResolver implements AbsVisitor {

    public boolean error = false;
    private int record_depth = 0;

    @Override
	public void visit(AbsAlloc acceptor) {
        Thread.dumpStack();
        Report.error("Unimplemented visitor method.", 1);
    }

    @Override
	public void visit(AbsArrayType acceptor) {
        acceptor.loBound.accept(this);
        acceptor.hiBound.accept(this);

        Integer hval = SemDesc.getActualConst(acceptor.hiBound);
        Integer lval = SemDesc.getActualConst(acceptor.loBound);

        if (lval == null) {
            notAValueError("loBound", acceptor);
        }
        if (hval == null) {
            notAValueError("hiBound", acceptor);
        }

        acceptor.type.accept(this);
    }

    @Override
	public void visit(AbsAssignStmt acceptor) {
        acceptor.dstExpr.accept(this);
        acceptor.srcExpr.accept(this);
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
        // nothing
    }

    @Override
	public void visit(AbsBinExpr acceptor) {
        acceptor.fstExpr.accept(this);
        acceptor.sndExpr.accept(this);

        Integer fval = SemDesc.getActualConst(acceptor.fstExpr);
        Integer sval = SemDesc.getActualConst(acceptor.sndExpr);

        if (fval != null && sval != null) {
            switch (acceptor.oper) {
            case AbsBinExpr.ADD:
                SemDesc.setActualConst(acceptor, fval+sval);
                break;
            case AbsBinExpr.SUB:
                SemDesc.setActualConst(acceptor, fval-sval);
                break;
            case AbsBinExpr.MUL:
                SemDesc.setActualConst(acceptor, fval*sval);
                break;
            case AbsBinExpr.DIV:
                if (sval != 0) {
                    SemDesc.setActualConst(acceptor, fval/sval);
                }
                break;
            }
        }
    }

    @Override
	public void visit(AbsBlockStmt acceptor) {
        acceptor.stmts.accept(this);
    }

    @Override
	public void visit(AbsCallExpr acceptor) {
        acceptor.args.accept(this);
        AbsDecl decl = SemTable.fnd(acceptor.name.name);
        if (decl == null) {
            notDeclaredError(acceptor.name.name, acceptor);
        }
    }

    @Override
	public void visit(AbsConstDecl acceptor) {
        if (record_depth == 0) {
            try {
                SemTable.ins(acceptor.name.name, acceptor);
            }catch(SemIllegalInsertException e) {
                isDeclaredError(acceptor.name.name, acceptor);
            }
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
        Report.error("This shouldn't happen");
    }

    @Override
	public void visit(AbsDecls acceptor) {
        for (AbsDecl decl : acceptor.decls) {
            decl.accept(this);
        }
    }

    @Override
	public void visit(AbsExprStmt acceptor) {
        acceptor.expr.accept(this);
    }

    @Override
	public void visit(AbsForStmt acceptor) {
        AbsDecl decl = SemTable.fnd(acceptor.name.name);
        if (decl == null) {
            notDeclaredError(acceptor.name.name, acceptor);
        }
        acceptor.loBound.accept(this);
        acceptor.hiBound.accept(this);
        acceptor.stmt.accept(this);
    }

    @Override
	public void visit(AbsFunDecl acceptor) {
        SemTable.newScope();
        try {
            SemTable.ins(acceptor.name.name, acceptor);
        }catch (SemIllegalInsertException e) {
            isDeclaredError(acceptor.name.name, acceptor);
        }
        acceptor.pars.accept(this);
        acceptor.type.accept(this);
        acceptor.decls.accept(this);
        acceptor.stmt.accept(this);

        SemTable.oldScope();

        try {
            SemTable.ins(acceptor.name.name, acceptor);
        }catch (SemIllegalInsertException e) {
            isDeclaredError(acceptor.name.name, acceptor);
        }
    }

    @Override
	public void visit(AbsIfStmt acceptor) {
        acceptor.cond.accept(this);
        acceptor.thenStmt.accept(this);
        acceptor.elseStmt.accept(this);
    }

    @Override
	public void visit(AbsNilConst acceptor) {
        // not a thing to do
    }

    @Override
	public void visit(AbsPointerType acceptor) {
        Thread.dumpStack();
        Report.error("Unimplemented visitor method.", 1);
    }

    @Override
	public void visit(AbsProcDecl acceptor) {
        SemTable.newScope();
        try {
            SemTable.ins(acceptor.name.name, acceptor);
        }catch(SemIllegalInsertException e) {
            isDeclaredError(acceptor.name.name, acceptor);
        }

        acceptor.pars.accept(this);
        acceptor.decls.accept(this);
        acceptor.stmt.accept(this);

        SemTable.oldScope();

        try {
            SemTable.ins(acceptor.name.name, acceptor);
        }catch(SemIllegalInsertException e) {
            isDeclaredError(acceptor.name.name, acceptor);
        }
    }

    @Override
	public void visit(AbsProgram acceptor) {
        acceptor.decls.accept(this);
        acceptor.stmt.accept(this);
    }

    @Override
	public void visit(AbsRecordType acceptor) {
        record_depth++;
        acceptor.fields.accept(this);
        record_depth--;
    }

    @Override
	public void visit(AbsStmts acceptor) {
        for (AbsStmt stmt : acceptor.stmts) {
            stmt.accept(this);
        }
    }

    @Override
	public void visit(AbsTypeDecl acceptor) {
        if (record_depth == 0) {
            try {
                SemTable.ins(acceptor.name.name, acceptor);
            } catch (SemIllegalInsertException e) {
                isDeclaredError(acceptor.name.name, acceptor);
            }
        }
        acceptor.type.accept(this);
        SemDesc.setNameDecl(acceptor.name, acceptor);
    }

    @Override
	public void visit(AbsTypeName acceptor) {
        AbsDecl decl = SemTable.fnd(acceptor.name);
        if (decl == null) {
            notDeclaredError(acceptor.name, acceptor);
        }else{
            SemDesc.setNameDecl(acceptor, decl);
        }
    }

    @Override
	public void visit(AbsUnExpr acceptor) {
        acceptor.expr.accept(this);

        Integer val = SemDesc.getActualConst(acceptor.expr);
        if (val != null) {
            if (acceptor.oper == AbsUnExpr.SUB) {
                val = -val;
            }
            SemDesc.setActualConst(acceptor, val);
        }
    }

    @Override
	public void visit(AbsValExprs acceptor) {
        for (AbsValExpr expr : acceptor.exprs) {
            expr.accept(this);
        }
    }

    @Override
        public void visit(AbsValName acceptor) {
        AbsDecl decl = SemTable.fnd(acceptor.name);
        if (decl == null) {
            notDeclaredError(acceptor.name, acceptor);
        }else{
            SemDesc.setNameDecl(acceptor, decl);
            Integer val = SemDesc.getActualConst(decl);
            if (val != null) {
                SemDesc.setActualConst(acceptor, val);
            }
        }
    }

    @Override
	public void visit(AbsVarDecl acceptor) {
        if (record_depth == 0){
            try {
                SemTable.ins(acceptor.name.name, acceptor);
            } catch (SemIllegalInsertException e) {
                isDeclaredError(acceptor.name.name, acceptor);
            }
        }
        acceptor.type.accept(this);
    }

    @Override
	public void visit(AbsWhileStmt acceptor) {
        acceptor.cond.accept(this);
        acceptor.stmt.accept(this);
    }

    private void isDeclaredError(String name, AbsTree loc){
        System.out.println(String.format("var %s is redefined at (%d,%d)",
                                         name, loc.begLine, loc.endLine));
        error = true;
    }

    private void notAValueError(String name, AbsTree loc){
        System.out.println(String.format("const %s can not be evaluated at (%d,%d)",
                                         name, loc.begLine, loc.endLine));
        error = true;
    }

    private void notDeclaredError(String name, AbsTree loc){
        System.out.println(String.format("var %s is undefined at (%d,%d)",
                                         name, loc.begLine, loc.endLine));
        error = true;
    }

}

