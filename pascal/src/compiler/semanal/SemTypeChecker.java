
package compiler.semanal;

import java.util.HashMap;

import compiler.report.*;
import compiler.abstree.AbsVisitor;
import compiler.abstree.tree.*;
import compiler.semanal.type.*;

public class SemTypeChecker implements AbsVisitor {

    public boolean error = false;
    private SemType typeInt = new SemAtomType(SemAtomType.INT);
    private SemType typeBool = new SemAtomType(SemAtomType.BOOL);
    private SemType typeVoid = new SemAtomType(SemAtomType.VOID);

    private HashMap<Integer, SemRecordType> records =
        new HashMap<Integer, SemRecordType>();
    private Integer record_depth = 0;

    @Override
	public void visit(AbsAlloc acceptor) {
        acceptor.type.accept(this);
        SemDesc.setActualType(acceptor,
                              new SemPointerType(SemDesc.getActualType(acceptor.type)));
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
        acceptor.dstExpr.accept(this);
        acceptor.srcExpr.accept(this);

        SemType ft = SemDesc.getActualType(acceptor.dstExpr);
        SemType st = SemDesc.getActualType(acceptor.srcExpr);

        if (ft instanceof SemSubprogramType) {
            ft = ((SemSubprogramType)ft).getResultType();
        }

        assert_coerces(ft, st, acceptor);

        if (ft instanceof SemAtomType || ft instanceof SemPointerType){
            SemDesc.setActualType(acceptor, ft);
        }else{
            integerPointerTypeError(acceptor);
        }
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

        acceptor.fstExpr.accept(this);
        acceptor.sndExpr.accept(this);

        SemType ft = SemDesc.getActualType(acceptor.fstExpr);
        SemType st = SemDesc.getActualType(acceptor.sndExpr);


        switch (acceptor.oper) {
        case AbsBinExpr.ADD:
        case AbsBinExpr.SUB:
        case AbsBinExpr.MUL:
        case AbsBinExpr.DIV:
            assert_coerces(ft, st, acceptor);
            if (!st.coercesTo(typeInt)) {
                integerTypeError(acceptor);
            }
            SemDesc.setActualType(acceptor, typeInt);
            break;
        case AbsBinExpr.EQU:
        case AbsBinExpr.NEQ:
        case AbsBinExpr.LTH:
        case AbsBinExpr.GTH:
        case AbsBinExpr.LEQ:
        case AbsBinExpr.GEQ:
            assert_coerces(ft, st, acceptor);
            if (!(ft instanceof SemAtomType || ft instanceof SemPointerType)) {
                integerPointerTypeError(acceptor);
            }
            SemDesc.setActualType(acceptor, typeBool);
            break;
        case AbsBinExpr.AND:
        case AbsBinExpr.OR:
            assert_coerces(ft, st, acceptor);
            assert_bool(acceptor.fstExpr, acceptor);
            SemDesc.setActualType(acceptor, typeBool);
            break;
        case AbsBinExpr.ARRACCESS:
            if (ft instanceof SemArrayType) {
                assert_int(acceptor.sndExpr, acceptor);
                SemDesc.setActualType(acceptor, ((SemArrayType) ft).type);
            }else{
                arrayTypeError(acceptor);
            }
            break;
        case AbsBinExpr.RECACCESS:
            if (ft instanceof SemRecordType) {
                SemRecordType aa = (SemRecordType)ft;
                for(int i=0; i<aa.getNumFields(); i++) {
                    if(aa.getFieldName(i).name.equals(((AbsValName)acceptor.sndExpr).name)) {
                        SemDesc.setActualType(acceptor, aa.getFieldType(i));
                    }
                }
            }else{
                recordTypeError(acceptor);
            }
            break;
        }
    }

    @Override
	public void visit(AbsBlockStmt acceptor) {
        acceptor.stmts.accept(this);
    }

    @Override
	public void visit(AbsCallExpr acceptor) {
        acceptor.name.accept(this);
        acceptor.args.accept(this);

        SemType type = SemDesc.getActualType(SemDesc.getNameDecl(acceptor.name));

        if (type instanceof SemSubprogramType) {
            SemSubprogramType thisType =
                new SemSubprogramType(((SemSubprogramType) type).getResultType());

            for (AbsValExpr args : acceptor.args.exprs) {
                SemType argType = SemDesc.getActualType(args);
                thisType.addParType(argType);
            }

            if (type.coercesTo(thisType)) {
                SemSubprogramType sub = (SemSubprogramType) type;
                SemDesc.setActualType(acceptor, sub.getResultType());
            }else{
                argumentsTypeError(acceptor);
            }
        }else{
            subprogramTypeError(acceptor.name.name, acceptor);
        }
    }

    @Override
	public void visit(AbsConstDecl acceptor) {
        acceptor.value.accept(this);
        SemDesc.setActualType(acceptor, SemDesc.getActualType(acceptor.value));
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
        acceptor.expr.accept(this);
    }

    @Override
	public void visit(AbsForStmt acceptor) {
        acceptor.name.accept(this);
        acceptor.loBound.accept(this);
        acceptor.hiBound.accept(this);
        acceptor.stmt.accept(this);

        assert_int(acceptor.loBound, acceptor);
        assert_int(acceptor.hiBound, acceptor);

        SemType var = SemDesc.getActualType(SemDesc.getNameDecl(acceptor.name));
        if (!var.coercesTo(typeInt)) {
            integerTypeError(acceptor);
        }
    }

    @Override
	public void visit(AbsFunDecl acceptor) {
        acceptor.pars.accept(this);
        acceptor.type.accept(this);

        SemType resultType = SemDesc.getActualType(acceptor.type);
        SemSubprogramType type = new SemSubprogramType(resultType);

        for (AbsDecl decl: acceptor.pars.decls){
            SemType paramType = SemDesc.getActualType(decl);
            if (paramType != null){
                type.addParType(paramType);
            }else{
                noTypeError(acceptor);
            }
        }

        SemDesc.setActualType(acceptor, type);

        acceptor.decls.accept(this);
        acceptor.stmt.accept(this);
    }

    @Override
	public void visit(AbsIfStmt acceptor) {
        acceptor.cond.accept(this);
        acceptor.thenStmt.accept(this);
        acceptor.elseStmt.accept(this);

        assert_bool(acceptor.cond, acceptor);
    }

    @Override
	public void visit(AbsNilConst acceptor) {
        SemDesc.setActualType(acceptor, typeVoid);
    }

    @Override
	public void visit(AbsPointerType acceptor) {
        acceptor.type.accept(this);
        SemDesc.setActualType(acceptor,
                              new SemPointerType(SemDesc.getActualType(acceptor.type)));
    }

    @Override
	public void visit(AbsProcDecl acceptor) {
        acceptor.pars.accept(this);

        SemSubprogramType type = new SemSubprogramType(typeVoid);

        for (AbsDecl decl: acceptor.pars.decls){
            SemType paramType = SemDesc.getActualType(decl);
            if (paramType != null){
                type.addParType(paramType);
            }else{
                noTypeError(acceptor);
            }
        }
        SemDesc.setActualType(acceptor, type);

        acceptor.decls.accept(this);
        acceptor.stmt.accept(this);
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
            if (record_depth == 0) {
                SemDesc.setActualType(acceptor, type);
            }else{
                SemRecordType aa = records.get(record_depth);

                boolean error = false;
                for (int i=0; i<aa.getNumFields(); i++) {
                    if (aa.getFieldName(i).name.equals(acceptor.name.name)) {
                        recordNameError(acceptor.name.name, acceptor);
                        error = true;
                        break;
                    }
                }

                if (!error) {
                    aa.addField(acceptor.name, SemDesc.getActualType(acceptor.type));
                }
            }
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
        acceptor.expr.accept(this);

        SemType type = SemDesc.getActualType(acceptor.expr);

        switch (acceptor.oper) {
        case AbsUnExpr.ADD:
        case AbsUnExpr.SUB:
            assert_int(acceptor.expr, acceptor);
            SemDesc.setActualType(acceptor, type);
            break;
        case AbsUnExpr.NOT:
            assert_bool(acceptor.expr, acceptor);
            SemDesc.setActualType(acceptor, type);
            break;
        case AbsUnExpr.MEM:
            SemPointerType ptr = new SemPointerType(type);
            SemDesc.setActualType(acceptor, ptr);
            break;
        case AbsUnExpr.VAL:
            if (type instanceof SemPointerType){
                SemDesc.setActualType(acceptor, ((SemPointerType) type).type);
            }else{
                pointerTypeError(acceptor);
            }
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
        SemDesc.setActualType(acceptor,
                              SemDesc.getActualType(SemDesc.getNameDecl(acceptor)));
    }

    @Override
	public void visit(AbsVarDecl acceptor) {
        acceptor.type.accept(this);
        SemDesc.setActualType(acceptor, SemDesc.getActualType(acceptor.type));
    }

    @Override
	public void visit(AbsWhileStmt acceptor) {
        acceptor.cond.accept(this);
        acceptor.stmt.accept(this);

        assert_bool(acceptor.cond, acceptor);
    }

	@Override
	public void visit(AbsBreakStmt acceptor) {
	}
	@Override
	public void visit(AbsContinueStmt acceptor) {
	}


    private void noTypeError(AbsTree loc) {
        Report.error(String.format("cannot resolve type at (%d, %d)",
                                loc.begLine, loc.begColumn), 1);
    }

    private void subprogramTypeError(String name, AbsTree loc) {
        Report.error(String.format("%s is not a subprogram at (%d, %d)",
                                   name, loc.begLine, loc.begColumn), 1);
    }

    private void recordNameError(String name, AbsTree loc) {
        Report.error(String.format("record element '%s' redeclared at (%d, %d)",
                                   name, loc.begLine, loc.begColumn), 1);
    }

    private void argumentsTypeError(AbsTree loc) {
        Report.error(String.format("calling with wrong type arguments at (%d, %d)",
                                   loc.begLine, loc.begColumn), 1);
    }

    private void booleanTypeError(AbsTree loc) {
        Report.error(String.format("boolean expected at (%d, %d)",
                                   loc.begLine, loc.begColumn), 1);
    }

    private void integerTypeError(AbsTree loc) {
        Report.error(String.format("integer expected at (%d, %d)",
                                   loc.begLine, loc.begColumn), 1);
    }

    private void integerPointerTypeError(AbsTree loc) {
        Report.error(String.format("integer or pointer expected at (%d, %d)",
                                   loc.begLine, loc.begColumn), 1);
    }
    private void arrayTypeError(AbsTree loc) {
        Report.error(String.format("array expected at (%d, %d)",
                                   loc.begLine, loc.begColumn), 1);
    }
    private void recordTypeError(AbsTree loc) {
        Report.error(String.format("record expected at (%d, %d)",
                                   loc.begLine, loc.begColumn), 1);
    }

    private void pointerTypeError(AbsTree loc) {
        Report.error(String.format("pointer expected at (%d, %d)",
                                   loc.begLine, loc.begColumn), 1);
    }

    private void missmatchError(AbsTree loc) {
        Report.error(String.format("types don't match at (%d, %d)",
                                   loc.begLine, loc.begColumn), 1);
    }


    private void assert_bool(AbsValExpr cond, AbsTree loc) {
        SemType b = SemDesc.getActualType(cond);
        if (!(b instanceof SemAtomType)
            || ((SemAtomType)b).type != SemAtomType.BOOL){
            booleanTypeError(loc);
        }
    }

    private void assert_int(AbsValExpr cond, AbsTree loc) {
        SemType i = SemDesc.getActualType(cond);
        if (!i.coercesTo(typeInt)){
            integerTypeError(loc);
        }
    }

    private void assert_coerces(SemType f, SemType s, AbsTree loc) {
        // System.out.println(String.format("%s, %s", f, s));
        if (f != null && !f.coercesTo(s)) {
            missmatchError(loc);
        }
    }

}

