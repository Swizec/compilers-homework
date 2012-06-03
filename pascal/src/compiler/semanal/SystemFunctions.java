
package compiler.semanal;

import java.util.ArrayList;

import compiler.abstree.tree.*;
import compiler.semanal.type.SemAtomType;
import compiler.semanal.type.SemSubprogramType;

public class SystemFunctions {
    public static final int FAKE_FP = 2011988;
    private static ArrayList<String> names = new ArrayList<String>();

    public static void fillData() {
        names.add("putch");

        try {
            putch();
        }catch (SemIllegalInsertException e) { }
    }

    private static void putch() throws SemIllegalInsertException {
        AbsDeclName name = new AbsDeclName("putch");
        AbsVarDecl param = new AbsVarDecl(name, new AbsAtomType(AbsAtomType.CHAR));

        SemDesc.setActualType(param, new SemAtomType(SemAtomType.CHAR));

        AbsDecls params = new AbsDecls();
        params.decls.add(param);

        AbsProcDecl node = new AbsProcDecl(name, params, null, null);
        SemSubprogramType type = new SemSubprogramType(new SemAtomType(SemAtomType.VOID));
        type.addParType(new SemAtomType(SemAtomType.CHAR));

        SemTable.ins(name.name, node);
        SemDesc.setActualType(node, type);
    }

    public static boolean isSystem(AbsCallExpr call) {
        return names.contains(call.name.name);
    }
}
