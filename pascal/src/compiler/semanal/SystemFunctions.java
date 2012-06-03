
package compiler.semanal;

import java.util.ArrayList;
import java.util.Iterator;

import compiler.abstree.tree.*;
import compiler.semanal.type.SemAtomType;
import compiler.semanal.type.SemSubprogramType;

public class SystemFunctions {
    public static final int FAKE_FP = 2011988;
    private static ArrayList<String> names = new ArrayList<String>();

    public static void fillData() {
        try {
            subprogram("putch", params("putch", AbsAtomType.CHAR));
            subprogram("putint", params("putint", AbsAtomType.INT));
            subprogram("getch", AbsAtomType.CHAR);
            subprogram("getint", AbsAtomType.INT);
            subprogram("ord", params("ord", AbsAtomType.CHAR), AbsAtomType.INT);
            subprogram("chr", params("chr", AbsAtomType.INT), AbsAtomType.CHAR);
            //subprogram("free", params("free",
        }catch (SemIllegalInsertException e) { }
    }

    // creates a params definition
    private static AbsDecls params(String name, int paramType) {
        AbsVarDecl param = new AbsVarDecl(new AbsDeclName(name),
                                          new AbsAtomType(paramType));
        SemAtomType _paramType = new SemAtomType(paramType);

        SemDesc.setActualType(param, _paramType);

        AbsDecls params = new AbsDecls();
        params.decls.add(param);

        return params;
    }

    // defines a procedure with params
    private static void subprogram(String name, AbsDecls params)
        throws SemIllegalInsertException {

        AbsProcDecl node = new AbsProcDecl(new AbsDeclName(name), params, null, null);

        subprogram(node, SemAtomType.VOID);
    }

    // defines a function without params
    private static void subprogram(String name, int returnType)
        throws SemIllegalInsertException {

        AbsFunDecl node = new AbsFunDecl(new AbsDeclName(name),
                                         new AbsDecls(),
                                         new AbsAtomType(returnType),
                                         null,
                                         null);

        subprogram(node, returnType);
    }

    // defines a function with params
    private static void subprogram(String name, AbsDecls params, int returnType)
        throws SemIllegalInsertException {

        AbsFunDecl node = new AbsFunDecl(new AbsDeclName(name),
                                         params,
                                         new AbsAtomType(returnType),
                                         null,
                                         null);

        subprogram(node, returnType);
    }

    // defines a subprogram
    private static void subprogram(AbsSubprogramDecl node, int returnType)
        throws SemIllegalInsertException {

        SemSubprogramType type = new SemSubprogramType(new SemAtomType(returnType));

        for (Iterator<AbsDecl> it = node.pars.decls.iterator(); it.hasNext();) {
            type.addParType(SemDesc.getActualType(it.next()));
        }

        SemTable.ins(node.name.name, node);
        SemDesc.setActualType(node, type);

        names.add(node.name.name);
    }

    public static boolean isSystem(AbsCallExpr call) {
        return names.contains(call.name.name);
    }
}
