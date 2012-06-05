package compiler.imcode;

import java.util.LinkedList;

import compiler.abstree.AbsVisitor;
import compiler.abstree.tree.*;
import compiler.semanal.SemDesc;
import compiler.semanal.SystemFunctions;
import compiler.semanal.type.*;
import compiler.frames.*;
import compiler.report.*;

public class IMCodeGenerator implements AbsVisitor {

    public LinkedList<ImcChunk> chunks;
    public LinkedList<ImcStmt> stmts;

    private ImcCode result;
    private ImcCode result() {
        ImcCode temp = result;
        result = null;
        return temp;
    }
    private void result(ImcCode result) {
        this.result = result;
    }
    private boolean noMem = false;

    public IMCodeGenerator() {
        chunks = new LinkedList<ImcChunk>();
    }

    @Override
	public void visit(AbsAlloc acceptor) {
        SemType type  = SemDesc.getActualType(acceptor.type);
        ImcCALL call = new ImcCALL(FrmLabel.newLabel("malloc"));

        call.args.add(new ImcCONST(type.size()));
        call.size.add(4);
        call.args.add(new ImcCONST(type.size()));
        call.size.add(4);
        result(call);
    }

    @Override
	public void visit(AbsArrayType acceptor) {  }

    @Override
	public void visit(AbsAssignStmt acceptor) {
        acceptor.dstExpr.accept(this);
        ImcExpr dst = (ImcExpr)result();

        acceptor.srcExpr.accept(this);
        ImcExpr src = (ImcExpr)result();

        result(new ImcMOVE(dst, src));
    }

    @Override
	public void visit(AbsAtomConst acceptor) {
        int value = 0;
        switch (acceptor.type) {
        case AbsAtomConst.BOOL:
            value = (acceptor.value.equals("true")) ? 1 : 0;
            break;
        case AbsAtomConst.CHAR:
            value = (int)acceptor.value.charAt(1);
            break;
        case AbsAtomConst.INT:
            value = Integer.parseInt(acceptor.value);
        }
        result(new ImcCONST(value));
    }

    @Override
	public void visit(AbsAtomType acceptor) {  }

    @Override
	public void visit(AbsBinExpr acceptor) {
        switch (acceptor.oper) {
        case AbsBinExpr.RECACCESS:
            noMem = false;
            acceptor.fstExpr.accept(this);
            ImcExpr rec = (ImcExpr)result();
            SemRecordType rType = (SemRecordType)SemDesc.getActualType(acceptor.fstExpr);
            String name = ((AbsValName)acceptor.sndExpr).name;

            SemType sType = null;

            int offset = 0;
            for (int i=0; i<rType.getNumFields(); i++) {
                if (name.equals(rType.getFieldName(i).name)) {
                    sType = rType.getFieldType(i);
                    break;
                }
                offset += rType.getFieldType(i).size();
            }
            if (sType instanceof SemRecordType) {
                result(new ImcBINOP(ImcBINOP.ADD, rec, new ImcCONST(offset)));
            }else{
                result(new ImcMEM(new ImcBINOP(ImcBINOP.ADD,
                                               rec,
                                               new ImcCONST(offset))));
            }

            noMem = false;

            break;
        case AbsBinExpr.ARRACCESS:
            noMem = true;
            acceptor.fstExpr.accept(this);
            ImcExpr arr = (ImcExpr)result();
            SemArrayType aType = (SemArrayType)SemDesc.getActualType(acceptor.fstExpr);

            noMem = false;
            acceptor.sndExpr.accept(this);
            ImcExpr index = (ImcExpr)result();
            noMem = true;

            ImcBINOP tIndex = new ImcBINOP(ImcBINOP.SUB,
                                           index,
                                           new ImcCONST(aType.loBound));
            ImcBINOP tOffset = new ImcBINOP(ImcBINOP.MUL,
                                            tIndex,
                                            new ImcCONST(aType.type.size()));

            result(new ImcMEM(new ImcBINOP(ImcBINOP.ADD, arr, tOffset)));
            noMem = false;

            break;
        default:
            acceptor.fstExpr.accept(this);
            ImcExpr limc = (ImcExpr) result();

            acceptor.sndExpr.accept(this);
            ImcExpr rimc = (ImcExpr) result();

            result(new ImcBINOP(acceptor.oper, limc, rimc));
        }
    }

    @Override
	public void visit(AbsBlockStmt acceptor) {
        acceptor.stmts.accept(this);
    }

    @Override
	public void visit(AbsCallExpr acceptor) {
        ImcCALL call;

        if (SystemFunctions.isSystem(acceptor)) {
            FrmFrame frame = FrmDesc.getFrame(SemDesc.getNameDecl(acceptor.name));
            call = new ImcCALL(FrmLabel.newLabel(acceptor.name.name));

            call.args.add(new ImcCONST(SystemFunctions.FAKE_FP));
        }else{
            FrmFrame frame = FrmDesc.getFrame(SemDesc.getNameDecl(acceptor.name));
            call = new ImcCALL(frame.label);

            call.args.add(new ImcTEMP(frame.FP));
        }

        call.size.add(4);

        for (AbsValExpr expr : acceptor.args.exprs) {
            expr.accept(this);

            call.args.add((ImcExpr)result());
            call.size.add(4);
        }

        result(call);
    }

    @Override
	public void visit(AbsConstDecl acceptor) {  }

    @Override
	public void visit(AbsDeclName acceptor) {  }

    @Override
	public void visit(AbsDecls acceptor) {
        for (AbsDecl decl : acceptor.decls) {
            if (decl instanceof AbsFunDecl || decl instanceof AbsProcDecl) {
                decl.accept(this);
            }
        }
    }

    @Override
	public void visit(AbsExprStmt acceptor) {
        acceptor.expr.accept(this);
        result(new ImcEXP((ImcExpr)result()));
    }

    @Override
	public void visit(AbsForStmt acceptor) {
        ImcSEQ seq = new ImcSEQ();

        acceptor.name.accept(this);
        ImcExpr name = (ImcExpr)result();

        acceptor.loBound.accept(this);
        ImcExpr lo = (ImcExpr)result();

        acceptor.hiBound.accept(this);
        ImcExpr hi = (ImcExpr)result();

        ImcLABEL tl = new ImcLABEL(FrmLabel.newLabel());
        ImcLABEL fl = new ImcLABEL(FrmLabel.newLabel());
        ImcLABEL sl = new ImcLABEL(FrmLabel.newLabel());

        seq.stmts.add(new ImcMOVE(name, lo));
        seq.stmts.add(sl);
        seq.stmts.add(new ImcCJUMP(new ImcBINOP(ImcBINOP.LEQ, name, hi),
                                   tl.label,
                                   fl.label));
        seq.stmts.add(tl);
        acceptor.stmt.accept(this);
        seq.stmts.add((ImcStmt)result());
        seq.stmts.add(new ImcMOVE(name,
                                  new ImcBINOP(ImcBINOP.ADD,
                                               name,
                                               new ImcCONST(1))));
        seq.stmts.add(new ImcJUMP(sl.label));
        seq.stmts.add(fl);

        result(seq);
    }

    @Override
	public void visit(AbsFunDecl acceptor) {
        FrmFrame frame = FrmDesc.getFrame(acceptor);
        acceptor.stmt.accept(this);
        chunks.add(new ImcCodeChunk(frame, (ImcStmt)result()));
        acceptor.decls.accept(this);
    }

    @Override
	public void visit(AbsIfStmt acceptor) {
        ImcSEQ seq = new ImcSEQ();

        acceptor.cond.accept(this);
        ImcExpr cond = (ImcExpr)result();

        ImcLABEL tl = new ImcLABEL(FrmLabel.newLabel());
        ImcLABEL fl = new ImcLABEL(FrmLabel.newLabel());
        ImcLABEL el = new ImcLABEL(FrmLabel.newLabel());

        seq.stmts.add(new ImcCJUMP(cond, tl.label, fl.label));
        seq.stmts.add(tl);
        acceptor.thenStmt.accept(this);
        seq.stmts.add((ImcStmt)result());
        seq.stmts.add(new ImcJUMP(el.label));
        seq.stmts.add(fl);
        acceptor.elseStmt.accept(this);
        seq.stmts.add((ImcStmt)result());
        seq.stmts.add(el);

        result(seq);
    }

    @Override
	public void visit(AbsNilConst acceptor) {
        result(new ImcCONST(0));
    }

    @Override
	public void visit(AbsPointerType acceptor) {  }

    @Override
	public void visit(AbsProcDecl acceptor) {
        FrmFrame frame = FrmDesc.getFrame(acceptor);
        acceptor.stmt.accept(this);
        chunks.add(new ImcCodeChunk(frame, (ImcStmt)result()));
        acceptor.decls.accept(this);
    }

    @Override
	public void visit(AbsProgram acceptor) {

        FrmFrame frame = FrmDesc.getFrame(acceptor);
        acceptor.stmt.accept(this);
        chunks.add(new ImcCodeChunk(frame, (ImcStmt)result()));

        for (AbsDecl decl : acceptor.decls.decls) {
            if (decl instanceof AbsVarDecl) {
                AbsVarDecl var = (AbsVarDecl)decl;
                FrmVarAccess access = (FrmVarAccess)FrmDesc.getAccess(var);
                SemType type = SemDesc.getActualType(var.type);
                chunks.add(new ImcDataChunk(access.label, type.size()));
            }
        }
        acceptor.decls.accept(this);
    }

    @Override
	public void visit(AbsRecordType acceptor) {  }

    @Override
	public void visit(AbsStmts acceptor) {
        ImcSEQ seq = new ImcSEQ();
        for (AbsStmt stmt : acceptor.stmts) {
            stmt.accept(this);
            seq.stmts.add((ImcStmt)result());
        }
        result(seq);
    }

    @Override
	public void visit(AbsTypeDecl acceptor) {  }

    @Override
	public void visit(AbsTypeName acceptor) {  }

    @Override
	public void visit(AbsUnExpr acceptor) {
        acceptor.expr.accept(this);
        switch (acceptor.oper) {
        case AbsUnExpr.ADD:
            result(new ImcBINOP(ImcBINOP.ADD, new ImcCONST(0), (ImcExpr)result()));
            break;
        case AbsUnExpr.SUB:
            result(new ImcBINOP(ImcBINOP.SUB, new ImcCONST(0), (ImcExpr)result()));
            break;
        case AbsUnExpr.NOT:
            result(new ImcBINOP(ImcBINOP.EQU, new ImcCONST(0), (ImcExpr)result()));
            break;
        case AbsUnExpr.MEM:
            result(((ImcMEM)result()).expr);
            break;
        case AbsUnExpr.VAL:
            result(new ImcMEM((ImcExpr)result()));
            break;
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
        AbsDecl decl = SemDesc.getNameDecl(acceptor);
        FrmFrame frame = FrmDesc.getFrame(decl);
        FrmAccess access = FrmDesc.getAccess(decl);

        if (access instanceof FrmVarAccess) {
            visitValName((FrmVarAccess)access, decl, frame);
        }
        if (access instanceof FrmArgAccess) {
            visitValName((FrmArgAccess)access, decl, frame);
        }
        if (access instanceof FrmLocAccess) {
            visitValName((FrmLocAccess)access, decl, frame);
        }
        if (decl instanceof AbsFunDecl) {
            visitValName(access, (AbsFunDecl)decl, frame);
        }
        if (decl instanceof AbsConstDecl) {
            visitValName(access, (AbsConstDecl)decl, frame);
        }
    }

    private void visitValName(FrmVarAccess access, AbsDecl decl, FrmFrame frame) {
        if (noMem) {
            result(new ImcNAME(access.label));
        }else{
            result(new ImcMEM(new ImcNAME(access.label)));
        }
    }

    private void visitValName(FrmArgAccess access, AbsDecl decl, FrmFrame frame) {
        if (noMem) {
            result(new ImcBINOP(ImcBINOP.ADD,
                                new ImcTEMP(access.frame.FP),
                                new ImcCONST(access.offset)));
        }else{
            result(new ImcMEM(new ImcBINOP(ImcBINOP.ADD,
                                           new ImcTEMP(access.frame.FP),
                                           new ImcCONST(access.offset))));
        }

        SemType type = SemDesc.getActualType(access.var);
        if (type instanceof SemArrayType || type instanceof SemRecordType) {
            result(new ImcMEM((ImcExpr)result()));
        }
    }

    private void visitValName(FrmLocAccess access, AbsDecl decl, FrmFrame frame) {
        if (noMem) {
            result(new ImcBINOP(ImcBINOP.ADD,
                                new ImcTEMP(access.frame.FP),
                                new ImcCONST(access.offset)));
        }else{
            result(new ImcMEM(new ImcBINOP(ImcBINOP.ADD,
                                           new ImcTEMP(access.frame.FP),
                                           new ImcCONST(access.offset))));
        }
    }

    private void visitValName(FrmAccess access, AbsFunDecl decl, FrmFrame frame) {
        SemType type = SemDesc.getActualType(decl);
        if (noMem) {
            result(new ImcTEMP(frame.RV));
        }else{
            result(new ImcMEM(new ImcTEMP(frame.RV)));
        }
        if (type instanceof SemArrayType || type instanceof SemRecordType) {
            result(new ImcCONST(SemDesc.getActualConst(decl)));
        }
    }

    private void visitValName(FrmAccess access, AbsConstDecl decl, FrmFrame frame) {
        result(new ImcCONST(SemDesc.getActualConst(decl)));
    }

    @Override
	public void visit(AbsVarDecl acceptor) {  }

    @Override
	public void visit(AbsWhileStmt acceptor) {
        ImcSEQ seq = new ImcSEQ();

        acceptor.cond.accept(this);
        ImcExpr cond = (ImcExpr)result();

        ImcLABEL tl = new ImcLABEL(FrmLabel.newLabel());
        ImcLABEL fl = new ImcLABEL(FrmLabel.newLabel());
        ImcLABEL sl = new ImcLABEL(FrmLabel.newLabel());

        seq.stmts.add(sl);
        seq.stmts.add(new ImcCJUMP(cond, tl.label, fl.label));
        seq.stmts.add(tl);
        acceptor.stmt.accept(this);
        seq.stmts.add((ImcStmt)result());
        seq.stmts.add(new ImcJUMP(sl.label));
        seq.stmts.add(fl);

        result(seq);
    }

}
