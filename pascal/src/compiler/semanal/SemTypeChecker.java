
package compiler.semanal;

import java.util.HashMap;

import compiler.report.*;
import compiler.abstree.AbsVisitor;
import compiler.abstree.tree.*;
import compiler.semanal.type.*;

public class SemTypeChecker implements AbsVisitor {

    public boolean error = false;

    private HashMap<Integer, SemRecordType> records =
        new HashMap<Integer, SemRecordType>();
    private Integer record_depth = 0;

    @Override
	public void visit(AbsAlloc acceptor) {
        SemAtomType type = new SemAtomType(SemAtomType.INT);
        SemDesc.setActualType(acceptor, type);
    }

    @Override
	public void visit(AbsArrayType acceptor) {
        acceptor.type.accept(this);
        acceptor.loBound.accept(this);
        acceptor.hiBound.accept(this);

        SemType type = SemDesc.getActualType(acceptor.type);
        Integer loBound = SemDesc.getActualConst(acceptor.loBound);
        Integer hiBound = SemDesc.getActualConst(acceptor.hiBound);

        if (type != null) {
            SemDesc.setActualType(acceptor,
                                  new SemArrayType(type, loBound, hiBound));
        }else{
            noTypeError(acceptor);
        }
    }

    @Override
	public void visit(AbsAssignStmt acceptor) {
        // TODO
    }

    @Override
	public void visit(AbsAtomConst acceptor) {
        SemAtomType type = new SemAtomType(acceptor.type);
        SemDesc.setActualType(acceptor, type);
    }

    @Override
	public void visit(AbsAtomType acceptor) {
        SemAtomType type = new SemAtomType(acceptor.type);
        SemDesc.setActualType(acceptor, type);
    }

    @Override
	public void visit(AbsBinExpr acceptor) {
        // TODO: ensure the two types match
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
        // TODO
    }

    @Override
	public void visit(AbsForStmt acceptor) {
        // TODO
    }

    @Override
	public void visit(AbsFunDecl acceptor) {
        // TODO
    }

    @Override
	public void visit(AbsIfStmt acceptor) {
        // TODO
    }

    @Override
	public void visit(AbsNilConst acceptor) {
        Thread.dumpStack();
        Report.error("Unimplemented visitor method.", 1);
    }

    @Override
	public void visit(AbsPointerType acceptor) {
        acceptor.type.accept(this);
        SemDesc.setActualType(acceptor,
                              new SemPointerType(SemDesc.getActualType(acceptor.type)));
    }

    @Override
	public void visit(AbsProcDecl acceptor) {
        // TODO
    }

    @Override
	public void visit(AbsProgram acceptor) {
        acceptor.decls.accept(this);
        acceptor.stmt.accept(this);
    }

    @Override
	public void visit(AbsRecordType acceptor) {
        record_depth++;
        records.put(record_depth, new SemRecordType());
        acceptor.fields.accept(this);
        SemDesc.setActualType(acceptor, records.get(record_depth));
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
        acceptor.type.accept(this);
        SemType type = SemDesc.getActualType(acceptor.type);
        if (type != null) {
            SemDesc.setActualType(acceptor, type);
        }else{
            noTypeError(acceptor);
        }
    }

    @Override
	public void visit(AbsTypeName acceptor) {
        SemType type = SemDesc.getActualType((AbsTree)SemDesc.getNameDecl(acceptor));
        if (type != null) {
            SemDesc.setActualType(acceptor, type);
        }else{
            noTypeError(acceptor);
        }
    }

    @Override
	public void visit(AbsUnExpr acceptor) {
        // TODO: wat?
    }

    @Override
	public void visit(AbsValExprs acceptor) {
        Thread.dumpStack();
        Report.error("Unimplemented visitor method.", 1);
    }

    @Override
	public void visit(AbsValName acceptor) {
        // TODO: stuff!
    }

    @Override
	public void visit(AbsVarDecl acceptor) {
        acceptor.type.accept(this);
        SemDesc.setActualType(acceptor, SemDesc.getActualType(acceptor.type));
    }

    @Override
	public void visit(AbsWhileStmt acceptor) {
        // TODO
    }

    private void noTypeError(AbsTree loc) {
        Report.error(String.format("cannot resolve type at (%d, %d)",
                                   loc.begLine, loc.begColumn), 1);
    }

}

