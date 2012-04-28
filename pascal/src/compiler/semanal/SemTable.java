package compiler.semanal;

import java.util.*;

import compiler.report.*;
import compiler.abstree.tree.*;

public class SemTable {

	private static HashMap<String, LinkedList<AbsDecl>> mapping = new HashMap<String, LinkedList<AbsDecl>>();

	private static int scope = 0;

	public static void newScope() {
		scope++;
	}

	public static void oldScope() {
		LinkedList<String> allNames = new LinkedList<String>();
		allNames.addAll(mapping.keySet());
		for (String name : allNames) {
			try {
				SemTable.del(name);
			} catch (SemIllegalDeleteException _) {
			}
		}
		scope--;
	}

	public static void ins(String name, AbsDecl newDecl)
			throws SemIllegalInsertException {
		LinkedList<AbsDecl> allNameDecls = mapping.get(name);
		if (allNameDecls == null) {
			allNameDecls = new LinkedList<AbsDecl>();
			allNameDecls.addFirst(newDecl);
			SemDesc.setScope(newDecl, scope);
			mapping.put(name, allNameDecls);
			return;
		}
		if ((allNameDecls.size() == 0) || (SemDesc.getScope(allNameDecls.getFirst()) == null)) {
			Thread.dumpStack();
			Report.error("Internal error.", 1);
			return;
		}
		if (SemDesc.getScope(allNameDecls.getFirst()) == scope)
			throw new SemIllegalInsertException();
		allNameDecls.addFirst(newDecl);
		SemDesc.setScope(newDecl, scope);
	}

	public static void del(String name)
			throws SemIllegalDeleteException {
		LinkedList<AbsDecl> allNameDecls = mapping.get(name);
		if (allNameDecls == null)
			throw new SemIllegalDeleteException();
		if ((allNameDecls.size() == 0) || (SemDesc.getScope(allNameDecls.getFirst()) == null)) {
			Thread.dumpStack();
			Report.error("Internal error.", 1);
			return;			
		}
		if (SemDesc.getScope(allNameDecls.getFirst()) < scope)
			throw new SemIllegalDeleteException();
		allNameDecls.removeFirst();
		if (allNameDecls.size() == 0)
			mapping.remove(name);
	}

	public static AbsDecl fnd(String name) {
		LinkedList<AbsDecl> allNameDecls = mapping.get(name);
		if (allNameDecls == null)
			return null;
		if (allNameDecls.size() == 0)
			return null;
		return allNameDecls.getFirst();
	}

}
