package compiler.frames;

import java.io.*;

import compiler.abstree.*;
import compiler.abstree.tree.*;
import compiler.semanal.*;
import compiler.semanal.type.*;

public class FrmPrintXML implements AbsVisitor {

	PrintStream xml;

	public FrmPrintXML(PrintStream xml) {
		this.xml = xml;
	}

	public String printPos(AbsTree tree) {
		return "pos=\"(" + tree.begLine + ":" + tree.begColumn + "-" + tree.endLine + ":" + tree.endColumn + ")\"";
	}

	public void visit(AbsAlloc acceptor) {
		if (acceptor.error) { xml.print("<abserror kind=\"Alloc\"/>\n"); return; }
		xml.print("<absnode " + printPos(acceptor) + " kind=\"Alloc\">\n");
		{
			SemType actualType = SemDesc.getActualType(acceptor); if (actualType != null) actualType.toXML(xml);
		}
		acceptor.type.accept(this);
		xml.print("</absnode>\n");
	}

	public void visit(AbsArrayType acceptor) {
		if (acceptor.error) { xml.print("<abserror kind=\"ArrayType\"/>\n"); return; }
		xml.print("<absnode " + printPos(acceptor) + " kind=\"ArrayType\">\n");
		{
			SemType actualType = SemDesc.getActualType(acceptor); if (actualType != null) actualType.toXML(xml);
		}
		acceptor.loBound.accept(this);
		acceptor.hiBound.accept(this);
		acceptor.type.accept(this);
		xml.print("</absnode>\n");
	}

	public void visit(AbsAssignStmt acceptor) {
		if (acceptor.error) { xml.print("<abserror kind=\"AssignStmt\"/>\n"); return; }
		xml.print("<absnode " + printPos(acceptor) + " kind=\"AssignStmt\">\n");
		acceptor.dstExpr.accept(this);
		acceptor.srcExpr.accept(this);
		xml.print("</absnode>\n");
	}

	public void visit(AbsAtomConst acceptor) {
		if (acceptor.error) { xml.print("<abserror kind=\"AtomExpr\"/>\n"); return; }
		String type = null;
		String value = null;
		switch (acceptor.type) {
		case AbsAtomConst.BOOL:
			type = "BOOL";
			value = acceptor.value;
			break;
		case AbsAtomConst.CHAR:
			type = "CHAR";
			switch (acceptor.value.charAt(1)) {
			case '\'':
				value = "'" + "&#39;" + "'";
				break;
			case '\"':
				value = "'" + "&#34;" + "'";
				break;
			case '&':
				value = "'" + "&#38;" + "'";
				break;
			case '<':
				value = "'" + "&#60;" + "'";
				break;
			case '>':
				value = "'" + "&#62;" + "'";
				break;
			default:
				value = acceptor.value;
			}
			break;
		case AbsAtomConst.INT:
			type = "INT";
			value = acceptor.value;
		}
		xml.print("<absnode " + printPos(acceptor) + " kind=\"AtomExpr(" + type + ")\" value=\"" + value + "\">\n");
		{
			xml.print("<seminfo kind=\"VALUE\" value=\"" + SemDesc.getActualConst(acceptor) + "\"/>\n");
			SemType actualType = SemDesc.getActualType(acceptor); if (actualType != null) actualType.toXML(xml);
		}
		xml.print("</absnode>\n");
	}

	public void visit(AbsAtomType acceptor) {
		if (acceptor.error) { xml.print("<abserror kind=\"AtomType\"/>\n"); return; }
		String type = null;
		switch (acceptor.type) {
		case AbsAtomType.BOOL:
			type = "BOOL";
			break;
		case AbsAtomType.CHAR:
			type = "CHAR";
			break;
		case AbsAtomType.INT:
			type = "INT";
			break;
		}
		xml.print("<absnode " + printPos(acceptor) + " kind=\"AtomType(" + type + ")\">\n");
		{
			SemType actualType = SemDesc.getActualType(acceptor); if (actualType != null) actualType.toXML(xml);
		}
		xml.print("</absnode>\n");
	}

	public void visit(AbsBinExpr acceptor) {
		if (acceptor.error) { xml.print("<abserror kind=\"BinExpr\"/>\n"); return; }
		String oper = null;
		switch (acceptor.oper) {
		case AbsBinExpr.ADD:
			oper = "ADD";
			break;
		case AbsBinExpr.SUB:
			oper = "SUB";
			break;
		case AbsBinExpr.MUL:
			oper = "MUL";
			break;
		case AbsBinExpr.DIV:
			oper = "DIV";
			break;
		case AbsBinExpr.EQU:
			oper = "EQU";
			break;
		case AbsBinExpr.NEQ:
			oper = "NEQ";
			break;
		case AbsBinExpr.LTH:
			oper = "LTH";
			break;
		case AbsBinExpr.GTH:
			oper = "GTH";
			break;
		case AbsBinExpr.LEQ:
			oper = "LEQ";
			break;
		case AbsBinExpr.GEQ:
			oper = "GEQ";
			break;
		case AbsBinExpr.AND:
			oper = "AND";
			break;
		case AbsBinExpr.OR:
			oper = "OR";
			break;
		case AbsBinExpr.ARRACCESS:
			oper = "ARRACCESS";
			break;
		case AbsBinExpr.RECACCESS:
			oper = "RECACCESS";
			break;
		}
		xml.print("<absnode " + printPos(acceptor) + " kind=\"BinExpr\" value=\"" + oper + "\">\n");
		{
			xml.print("<seminfo kind=\"VALUE\" value=\"" + SemDesc.getActualConst(acceptor) + "\"/>\n");
			SemType actualType = SemDesc.getActualType(acceptor); if (actualType != null) actualType.toXML(xml);
		}
		acceptor.fstExpr.accept(this);
		acceptor.sndExpr.accept(this);
		xml.print("</absnode>\n");
	}

	public void visit(AbsBlockStmt acceptor) {
		if (acceptor.error) { xml.print("<abserror kind=\"BlockStmt\"/>\n"); return; }
		xml.print("<absnode " + printPos(acceptor) + " kind=\"BlockStmt\">\n");
		acceptor.stmts.accept(this);
		xml.print("</absnode>\n");
	}

	public void visit(AbsCallExpr acceptor) {
		if (acceptor.error) { xml.print("<abserror kind=\"CallExpr\"/>\n"); return; }
		xml.print("<absnode " + printPos(acceptor) + " kind=\"CallExpr\">\n");
		{
			SemType actualType = SemDesc.getActualType(acceptor); if (actualType != null) actualType.toXML(xml);
		}
		acceptor.name.accept(this);
		acceptor.args.accept(this);
		xml.print("</absnode>\n");
	}

	public void visit(AbsConstDecl acceptor) {
		if (acceptor.error) { xml.print("<abserror kind=\"ConstDecl\"/>\n"); return; }
		xml.print("<absnode " + printPos(acceptor) + " kind=\"ConstDecl\">\n");
		{
			xml.print("<seminfo kind=\"DECL\" value=\"" + acceptor.hashCode() + "\"/>\n");
			xml.print("<seminfo kind=\"SCOPE\" value=\"" + SemDesc.getScope(acceptor) + "\"/>\n");
			xml.print("<seminfo kind=\"VALUE\" value=\"" + SemDesc.getActualConst(acceptor) + "\"/>\n");
			SemType actualType = SemDesc.getActualType(acceptor); if (actualType != null) actualType.toXML(xml);
		}
		acceptor.name.accept(this);
		acceptor.value.accept(this);
		xml.print("</absnode>\n");
	}

	public void visit(AbsDeclName acceptor) {
		if (acceptor.error) { xml.print("<abserror kind=\"DeclName\"/>\n"); return; }
		xml.print("<absnode " + printPos(acceptor) + " kind=\"DeclName\" value=\"" + acceptor.name + "\">\n");
		{
			AbsDecl decl = SemDesc.getNameDecl(acceptor);
			if (decl != null) xml.print("<seminfo kind=\"DECL\" value=\"" + decl.hashCode() + "\"/>\n");
			if (decl != null) xml.print("<seminfo kind=\"SCOPE\" value=\""+ SemDesc.getScope(decl) + "\"/>\n");
		}
		xml.print("</absnode>\n");
	}

	public void visit(AbsDecls acceptor) {
		if (acceptor.error) { xml.print("<abserror kind=\"Decls\"/>\n"); return; }
		xml.print("<absnode " + printPos(acceptor) + " kind=\"Decls\">\n");
		for (AbsDecl decl : acceptor.decls) decl.accept(this);
		xml.print("</absnode>\n");
	}

	public void visit(AbsExprStmt acceptor) {
		if (acceptor.error) { xml.print("<abserror kind=\"ExprStmt\"/>\n"); return; }
		xml.print("<absnode " + printPos(acceptor) + " kind=\"ExprStmt\">\n");
		{
			SemType actualType = SemDesc.getActualType(acceptor); if (actualType != null) actualType.toXML(xml);
		}
		acceptor.expr.accept(this);
		xml.print("</absnode>\n");
	}

	public void visit(AbsForStmt acceptor) {
		if (acceptor.error) { xml.print("<abserror kind=\"ForStmt\"/>\n"); return; }
		xml.print("<absnode " + printPos(acceptor) + " kind=\"ForStmt\">\n");
		acceptor.name.accept(this);
		acceptor.loBound.accept(this);
		acceptor.hiBound.accept(this);
		acceptor.stmt.accept(this);
		xml.print("</absnode>\n");
	}

	public void visit(AbsFunDecl acceptor) {
		if (acceptor.error) { xml.print("<abserror kind=\"FunDecl\"/>\n"); return; }
		xml.print("<absnode " + printPos(acceptor) + " kind=\"FunDecl\">\n");
		{
			xml.print("<seminfo kind=\"DECL\" value=\"" + acceptor.hashCode() + "\"/>\n");
			xml.print("<seminfo kind=\"SCOPE\" value=\"" + SemDesc.getScope(acceptor) + "\"/>\n");
			SemType actualType = SemDesc.getActualType(acceptor); if (actualType != null) actualType.toXML(xml);
		}
		{
			FrmDesc.getFrame(acceptor).toXML(xml);
		}
		acceptor.name.accept(this);
		acceptor.pars.accept(this);
		acceptor.type.accept(this);
		acceptor.decls.accept(this);
		acceptor.stmt.accept(this);
		xml.print("</absnode>\n");
	}

	public void visit(AbsIfStmt acceptor) {
		if (acceptor.error) { xml.print("<abserror kind=\"IfStmt\"/>\n"); return; }
		xml.print("<absnode " + printPos(acceptor) + " kind=\"IfStmt\">\n");
		acceptor.cond.accept(this);
		acceptor.thenStmt.accept(this);
		acceptor.elseStmt.accept(this);
		xml.print("</absnode>\n");
	}

	public void visit(AbsNilConst acceptor) {
		if (acceptor.error) { xml.print("<abserror kind=\"NilConst\"/>\n"); return; }
		xml.print("<absnode " + printPos(acceptor) + " kind=\"NilConst\">\n");
		{
			SemType actualType = SemDesc.getActualType(acceptor); if (actualType != null) actualType.toXML(xml);
		}
		xml.print("</absnode>\n");
	}

	public void visit(AbsPointerType acceptor) {
		if (acceptor.error) { xml.print("<abserror kind=\"PointerType\"/>\n"); return; }
		xml.print("<absnode " + printPos(acceptor) + " kind=\"PointerType\">\n");
		{
			SemType actualType = SemDesc.getActualType(acceptor); if (actualType != null) actualType.toXML(xml);
		}
		acceptor.type.accept(this);
		xml.print("</absnode>\n");
	}

	public void visit(AbsProcDecl acceptor) {
		if (acceptor.error) { xml.print("<abserror kind=\"ProcDecl\"/>\n"); return; }
		xml.print("<absnode " + printPos(acceptor) + " kind=\"ProcDecl\">\n");
		{
			xml.print("<seminfo kind=\"DECL\" value=\"" + acceptor.hashCode() + "\"/>\n");
			xml.print("<seminfo kind=\"SCOPE\" value=\"" + SemDesc.getScope(acceptor) + "\"/>\n");
			SemType actualType = SemDesc.getActualType(acceptor); if (actualType != null) actualType.toXML(xml);
		}
		{
			FrmDesc.getFrame(acceptor).toXML(xml);
		}
		acceptor.name.accept(this);
		acceptor.pars.accept(this);
		acceptor.decls.accept(this);
		acceptor.stmt.accept(this);
		xml.print("</absnode>\n");
	}

	public void visit(AbsProgram acceptor) {
		if (acceptor.error) { xml.print("<abserror kind=\"Program\"/>\n"); return; }
		xml.print("<absnode " + printPos(acceptor) + " kind=\"Program\">\n");
		acceptor.name.accept(this);
		acceptor.decls.accept(this);
		acceptor.stmt.accept(this);
		xml.print("</absnode>\n");
	}

	public void visit(AbsRecordType acceptor) {
		if (acceptor.error) { xml.print("<abserror kind=\"RecordType\"/>\n"); return; }
		xml.print("<absnode " + printPos(acceptor) + " kind=\"RecordType\">\n");
		{
			SemType actualType = SemDesc.getActualType(acceptor); if (actualType != null) actualType.toXML(xml);
		}
		acceptor.fields.accept(this);
		xml.print("</absnode>\n");
	}

	public void visit(AbsStmts acceptor) {
		if (acceptor.error) { xml.print("<abserror kind=\"Stmts\"/>\n"); return; }
		xml.print("<absnode " + printPos(acceptor) + " kind=\"Stmts\">\n");
		for (AbsStmt stmt : acceptor.stmts) stmt.accept(this);
		xml.print("</absnode>\n");
	}

	public void visit(AbsTypeDecl acceptor) { //+
		if (acceptor.error) { xml.print("<abserror kind=\"TypeDecl\"/>\n"); return; }
		xml.print("<absnode " + printPos(acceptor) + " kind=\"TypeDecl\">\n");
		{
			xml.print("<seminfo kind=\"DECL\" value=\"" + acceptor.hashCode() + "\"/>\n");
			xml.print("<seminfo kind=\"SCOPE\" value=\"" + SemDesc.getScope(acceptor) + "\"/>\n");
			SemType actualType = SemDesc.getActualType(acceptor); if (actualType != null) actualType.toXML(xml);
		}
		acceptor.name.accept(this);
		acceptor.type.accept(this);
		xml.print("</absnode>\n");
	}

	public void visit(AbsTypeName acceptor) {
		if (acceptor.error) { xml.print("<abserror kind=\"TypeName\"/>\n"); return; }
		xml.print("<absnode " + printPos(acceptor) + " kind=\"TypeName\" value=\"" + acceptor.name + "\">\n");
		{
			AbsDecl decl = SemDesc.getNameDecl(acceptor); if (decl != null) xml.print("<seminfo kind=\"DECL\" value=\"" + decl.hashCode() + "\"/>\n");
			SemType actualType = SemDesc.getActualType(acceptor); if (actualType != null) actualType.toXML(xml);
		}
		xml.print("</absnode>\n");
	}

	public void visit(AbsUnExpr acceptor) {
		if (acceptor.error) { xml.print("<abserror kind=\"UnExpr\"/>\n"); return; }
		String oper = null;
		switch (acceptor.oper) {
		case AbsUnExpr.VAL:
			oper = "VAL";
			break;
		case AbsUnExpr.MEM:
			oper = "MEM";
			break;
		case AbsUnExpr.ADD:
			oper = "ADD";
			break;
		case AbsUnExpr.SUB:
			oper = "SUB";
			break;
		case AbsUnExpr.NOT:
			oper = "NOT";
			break;
		}
		xml.print("<absnode " + printPos(acceptor) + " kind=\"UnExpr\" value=\"" + oper + "\">\n");
		{
			xml.print("<seminfo kind=\"VALUE\" value=\"" + SemDesc.getActualConst(acceptor) + "\"/>\n");
			SemType actualType = SemDesc.getActualType(acceptor); if (actualType != null) actualType.toXML(xml);
		}
		acceptor.expr.accept(this);
		xml.print("</absnode>\n");
	}

	public void visit(AbsValExprs acceptor) {
		if (acceptor.error) { xml.print("<abserror kind=\"ValExprs\"/>\n"); return; }
		xml.print("<absnode " + printPos(acceptor) + " kind=\"ValExprs\">\n");
		for (AbsValExpr expr : acceptor.exprs) expr.accept(this);
		xml.print("</absnode>\n");
	}

	public void visit(AbsValName acceptor) {
		if (acceptor.error) { xml.print("<abserror kind=\"ValName\"/>\n"); return; }
		xml.print("<absnode " + printPos(acceptor) + " kind=\"ValName\" value=\"" + acceptor.name + "\">\n");
		{
			AbsDecl decl = SemDesc.getNameDecl(acceptor); if (decl != null) xml.print("<seminfo kind=\"DECL\" value=\"" + decl.hashCode() + "\"/>\n");
			SemType actualType = SemDesc.getActualType(acceptor); if (actualType != null) actualType.toXML(xml);
		}
		xml.print("</absnode>\n");
	}

	public void visit(AbsVarDecl acceptor) {
		if (acceptor.error) { xml.print("<abserror kind=\"VarDecl\"/>\n"); return; }
		xml.print("<absnode " + printPos(acceptor) + " kind=\"VarDecl\">\n");
		{
			xml.print("<seminfo kind=\"DECL\" value=\"" + acceptor.hashCode() + "\"/>\n");
			xml.print("<seminfo kind=\"SCOPE\" value=\"" + SemDesc.getScope(acceptor) + "\"/>\n");
			SemType actualType = SemDesc.getActualType(acceptor); if (actualType != null) actualType.toXML(xml);
		}
		{
			FrmDesc.getAccess(acceptor).toXML(xml);
		}
		acceptor.name.accept(this);
		acceptor.type.accept(this);
		xml.print("</absnode>\n");
	}

	public void visit(AbsWhileStmt acceptor) {
		if (acceptor.error) { xml.print("<abserror kind=\"WhileStmt\"/>\n"); return; }
		xml.print("<absnode " + printPos(acceptor) + " kind=\"WhileStmt\">\n");
		acceptor.cond.accept(this);
		acceptor.stmt.accept(this);
		xml.print("</absnode>\n");
	}

}
