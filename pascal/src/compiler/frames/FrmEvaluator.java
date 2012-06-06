package compiler.frames;

import compiler.abstree.*;
import compiler.abstree.tree.*;
import compiler.semanal.*;

public class FrmEvaluator implements AbsVisitor {

    private boolean debug = false;
    private FrmFrame curFrame;

    public void visit(AbsAtomType acceptor) {
    }

    public void visit(AbsConstDecl acceptor) {
    }

    public void visit(AbsFunDecl acceptor) {
        FrmFrame frame = new FrmFrame(acceptor, SemDesc.getScope(acceptor));
        for (AbsDecl decl : acceptor.pars.decls) {
            if (decl instanceof AbsVarDecl) {
                AbsVarDecl varDecl = (AbsVarDecl)decl;
                FrmArgAccess access = new FrmArgAccess(varDecl, frame);
                FrmDesc.setAccess(varDecl, access);
            }
            decl.accept(this);
        }
        for (AbsDecl decl : acceptor.decls.decls) {
            if (decl instanceof AbsVarDecl) {
                AbsVarDecl varDecl = (AbsVarDecl)decl;
                FrmLocAccess access = new FrmLocAccess(varDecl, frame);
                frame.locVars.add(access);
                FrmDesc.setAccess(varDecl, access);
            }
            decl.accept(this);
        }
        curFrame = frame;
        curFrame.sizeArgs = 4;
        acceptor.stmt.accept(this);
        FrmDesc.setFrame(acceptor, frame);
    }

    public void visit(AbsProgram acceptor) {
        FrmFrame frame = new FrmFrame(acceptor, -1);
        for (AbsDecl decl : acceptor.decls.decls) {
            if (decl instanceof AbsVarDecl) {
                AbsVarDecl varDecl = (AbsVarDecl)decl;
                FrmVarAccess access = new FrmVarAccess(varDecl);
                FrmDesc.setAccess(varDecl, access);
            }
            decl.accept(this);
        }
        curFrame = frame;
        acceptor.stmt.accept(this);
        FrmDesc.setFrame(acceptor, frame);
    }

    public void visit(AbsProcDecl acceptor) {
        FrmFrame frame = new FrmFrame(acceptor, SemDesc.getScope(acceptor));
        for (AbsDecl decl : acceptor.pars.decls) {
            if (decl instanceof AbsVarDecl) {
                AbsVarDecl varDecl = (AbsVarDecl)decl;
                FrmArgAccess access = new FrmArgAccess(varDecl, frame);
                FrmDesc.setAccess(varDecl, access);
            }
            decl.accept(this);
        }
        for (AbsDecl decl : acceptor.decls.decls) {
            if (decl instanceof AbsVarDecl) {
                AbsVarDecl varDecl = (AbsVarDecl)decl;
                FrmLocAccess access = new FrmLocAccess(varDecl, frame);
                frame.locVars.add(access);
                FrmDesc.setAccess(varDecl, access);
            }
            decl.accept(this);
        }
        curFrame = frame;
        acceptor.stmt.accept(this);
        if(debug) System.out.println(frame.sizeArgs);
        FrmDesc.setFrame(acceptor, frame);
    }

    public void visit(AbsRecordType acceptor) {
        int offset = 0;
        for (AbsDecl decl : acceptor.fields.decls) {
            if (decl instanceof AbsVarDecl) {
                AbsVarDecl varDecl = (AbsVarDecl)decl;
                FrmCmpAccess access = new FrmCmpAccess(varDecl, offset);
                FrmDesc.setAccess(varDecl, access);
                offset = offset + SemDesc.getActualType(varDecl.type).size();
            }
            decl.accept(this);
        }
    }

    public void visit(AbsTypeDecl acceptor) {
        acceptor.type.accept(this);
    }

    public void visit(AbsVarDecl acceptor) {
        acceptor.type.accept(this);
    }

    @Override
	public void visit(AbsAlloc acceptor) {
        acceptor.type.accept(this);
    }

    @Override
	public void visit(AbsArrayType acceptor) {
        acceptor.type.accept(this);
    }

    @Override
	public void visit(AbsAssignStmt acceptor) {
        acceptor.srcExpr.accept(this);
        acceptor.dstExpr.accept(this);
    }

    @Override
	public void visit(AbsAtomConst acceptor) {
    }

    @Override
	public void visit(AbsBinExpr acceptor) {
        acceptor.fstExpr.accept(this);
        acceptor.sndExpr.accept(this);
    }

    @Override
	public void visit(AbsBlockStmt acceptor) {
        acceptor.stmts.accept(this);
    }

    @Override
	public void visit(AbsCallExpr acceptor) {
        int callArgsSize = 4 * acceptor.args.exprs.size() + 4;
        if(curFrame.sizeArgs < callArgsSize)
            curFrame.sizeArgs = callArgsSize;
        acceptor.args.accept(this);
    }

    @Override
	public void visit(AbsDeclName acceptor) {
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
        acceptor.name.accept(this);
        acceptor.loBound.accept(this);
        acceptor.hiBound.accept(this);
    }

    @Override
	public void visit(AbsIfStmt acceptor) {
        acceptor.cond.accept(this);
        acceptor.thenStmt.accept(this);
        acceptor.elseStmt.accept(this);
    }

    @Override
	public void visit(AbsNilConst acceptor) {
    }

    @Override
	public void visit(AbsPointerType acceptor) {
        acceptor.type.accept(this);
    }

    @Override
	public void visit(AbsStmts acceptor) {
        for (AbsStmt stmt : acceptor.stmts) {
            stmt.accept(this);
        }
    }

    @Override
	public void visit(AbsTypeName acceptor) {
        SemDesc.getNameDecl(acceptor).accept(this);
    }

    @Override
	public void visit(AbsUnExpr acceptor) {
        acceptor.expr.accept(this);
    }

    @Override
	public void visit(AbsValExprs acceptor) {
        for (AbsValExpr expr : acceptor.exprs) {
            expr.accept(this);
        }
    }

    @Override
	public void visit(AbsValName acceptor) {
        // TODO poglej
    }

    @Override
	public void visit(AbsWhileStmt acceptor) {
        acceptor.cond.accept(this);
        acceptor.stmt.accept(this);
    }
}
