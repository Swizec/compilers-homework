package compiler.semanal;

import java.util.*;

import compiler.abstree.tree.*;
import compiler.semanal.type.*;

public class SemDesc {

	/** Nivo vidnosti. */
	private static HashMap<AbsTree, Integer> scope = new HashMap<AbsTree, Integer>();

	public static void setScope(AbsTree node, int nodeScope) {
		scope.put(node, new Integer(nodeScope));
	}

	public static Integer getScope(AbsTree node) {
		Integer nodeScope = scope.get(node);	
		return nodeScope;
	}
	
	/** Deklaracija imena. */
	private static HashMap<AbsTree, AbsDecl> nameDecl = new HashMap<AbsTree, AbsDecl>();
	
	public static void setNameDecl(AbsTree node, AbsDecl decl) {
		nameDecl.put(node, decl);
	}
	
	public static AbsDecl getNameDecl(AbsTree node) {
		AbsDecl decl = nameDecl.get(node);
		return decl;
	}

	/** Vrednost konstante. */
	private static HashMap<AbsTree, Integer> actualConst = new HashMap<AbsTree, Integer>();
	
	public static void setActualConst(AbsTree constDecl, Integer value) {
		SemDesc.actualConst.put(constDecl, value);
	}
	
	public static Integer getActualConst(AbsTree constDecl) {
		Integer value = SemDesc.actualConst.get(constDecl);
		return value;
	}
		
	/** Opis tipa. */
	private static HashMap<AbsTree, SemType> acturalType = new HashMap<AbsTree, SemType>();

	public static void setActualType(AbsTree node, SemType type) {
		SemDesc.acturalType.put(node, type);
	}
	
	public static SemType getActualType(AbsTree node) {
		SemType type = SemDesc.acturalType.get(node);
		return type;
	}

}
