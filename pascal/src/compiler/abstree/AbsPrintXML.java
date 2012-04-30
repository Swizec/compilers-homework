package compiler.abstree;

import java.io.*;

import compiler.abstree.tree.*;

public class AbsPrintXML implements AbsVisitor {

	PrintStream xml;

	public AbsPrintXML(PrintStream xml) {
		this.xml = xml;
	}

	public String printPos(AbsTree tree) {
		return "pos=\"(" + tree.begLine + ":" + tree.begColumn + "-"
				+ tree.endLine + ":" + tree.endColumn + ")\"";
	}

	public void visit(AbsAlloc acceptor) {
		if (acceptor.error) { xml.println("<abserror kind=\"Alloc\"/>"); return; }
		xml.print("<absnode " + printPos(acceptor) + " kind=\"Alloc\">");
		acceptor.type.accept(this);
		xml.print("</absnode>\n");
	}

	public void visit(AbsArrayType acceptor) {
		if (acceptor.error) { xml.println("<abserror kind=\"ArrayType\"/>"); return; }
		xml.print("<absnode " + printPos(acceptor) + " kind=\"ArrayType\">");
		acceptor.loBound.accept(this);
		acceptor.hiBound.accept(this);
		acceptor.type.accept(this);
		xml.print("</absnode>\n");
	}

	public void visit(AbsAssignStmt acceptor) {
		if (acceptor.error) { xml.println("<abserror kind=\"AssignStmt\"/>"); return; }
		xml.print("<absnode " + printPos(acceptor) + " kind=\"AssignStmt\">");
		acceptor.dstExpr.accept(this);
		acceptor.srcExpr.accept(this);
		xml.print("</absnode>\n");
	}

	public void visit(AbsAtomConst acceptor) {
		if (acceptor.error) { xml.println("<abserror kind=\"AtomExpr\"/>"); return; }
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
		xml.print("<absnode " + printPos(acceptor) + " kind=\"AtomExpr(" + type
				+ ")\" value=\"" + value + "\">");
		xml.print("</absnode>\n");
	}

	public void visit(AbsAtomType acceptor) {
		if (acceptor.error) { xml.println("<abserror kind=\"AtomType\"/>"); return; }
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
		xml.print("<absnode " + printPos(acceptor) + " kind=\"AtomType(" + type
				+ ")\">");
		xml.print("</absnode>\n");
	}

	public void visit(AbsBinExpr acceptor) {
		if (acceptor.error) { xml.println("<abserror kind=\"BinExpr\"/>"); return; }
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
		xml.print("<absnode " + printPos(acceptor)
				+ " kind=\"BinExpr\" value=\"" + oper + "\">");
		acceptor.fstExpr.accept(this);
		acceptor.sndExpr.accept(this);
		xml.print("</absnode>\n");
	}

	public void visit(AbsBlockStmt acceptor) {
		if (acceptor.error) { xml.println("<abserror kind=\"BlockStmt\"/>"); return; }
		xml.print("<absnode " + printPos(acceptor) + " kind=\"BlockStmt\">");
		acceptor.stmts.accept(this);
		xml.print("</absnode>\n");
	}

	public void visit(AbsCallExpr acceptor) {
		if (acceptor.error) { xml.println("<abserror kind=\"CallExpr\"/>"); return; }
		xml.print("<absnode " + printPos(acceptor) + " kind=\"CallStmt\">\n");
		acceptor.name.accept(this);
		acceptor.args.accept(this);
		xml.print("</absnode>\n");
	}

	public void visit(AbsConstDecl acceptor) {
		if (acceptor.error) { xml.println("<abserror kind=\"ConstDecl\"/>"); return; }
		xml.print("<absnode " + printPos(acceptor) + " kind=\"ConstDecl\">\n");
		acceptor.name.accept(this);
		acceptor.value.accept(this);
		xml.print("</absnode>\n");
	}

	public void visit(AbsDeclName acceptor) {
		if (acceptor.error) { xml.println("<abserror kind=\"DeclName\"/>"); return; }
		xml.print("<absnode " + printPos(acceptor)
				+ " kind=\"DeclName\" value=\"" + acceptor.name + "\">");
		xml.print("</absnode>\n");
	}

	public void visit(AbsDecls acceptor) {
		if (acceptor.error) { xml.println("<abserror kind=\"Decls\"/>"); return; }
		xml.print("<absnode " + printPos(acceptor) + " kind=\"Decls\">\n");
		for (AbsDecl decl : acceptor.decls) {
			decl.accept(this);
		}
		xml.print("</absnode>\n");
	}

	public void visit(AbsExprStmt acceptor) {
		if (acceptor.error) { xml.println("<abserror kind=\"ExprStmt\"/>"); return; }
		xml.print("<absnode " + printPos(acceptor) + " kind=\"ExprStmt\">\n");
		acceptor.expr.accept(this);
		xml.print("</absnode>\n");
	}

	public void visit(AbsForStmt acceptor) {
		if (acceptor.error) { xml.println("<abserror kind=\"ForStmt\"/>"); return; }
		xml.print("<absnode " + printPos(acceptor) + " kind=\"ForStmt\">");
		acceptor.name.accept(this);
		acceptor.loBound.accept(this);
		acceptor.hiBound.accept(this);
		acceptor.stmt.accept(this);
		xml.print("</absnode>\n");
	}

	public void visit(AbsFunDecl acceptor) {
		if (acceptor.error) { xml.println("<abserror kind=\"FunDecl\"/>"); return; }
		xml.print("<absnode " + printPos(acceptor) + " kind=\"FunDecl\">\n");
		acceptor.name.accept(this);
		acceptor.pars.accept(this);
		acceptor.type.accept(this);
		acceptor.decls.accept(this);
		acceptor.stmt.accept(this);
		xml.print("</absnode>\n");
	}

	public void visit(AbsIfStmt acceptor) {
		if (acceptor.error) { xml.println("<abserror kind=\"IfStmt\"/>"); return; }
		xml.print("<absnode " + printPos(acceptor) + " kind=\"IfStmt\">\n");
		acceptor.cond.accept(this);
		acceptor.thenStmt.accept(this);
		acceptor.elseStmt.accept(this);
		xml.print("</absnode>\n");
	}

	public void visit(AbsNilConst acceptor) {
		if (acceptor.error) { xml.println("<abserror kind=\"NilConst\"/>"); return; }
		xml.print("<absnode " + printPos(acceptor) + " kind=\"NilConst\">\n");
		xml.print("</absnode>\n");
	}

	public void visit(AbsPointerType acceptor) {
		if (acceptor.error) { xml.println("<abserror kind=\"PointerType\"/>"); return; }
		xml.print("<absnode " + printPos(acceptor) + " kind=\"PointerType\">");
		acceptor.type.accept(this);
		xml.print("</absnode>\n");
	}

	public void visit(AbsProcDecl acceptor) {
		if (acceptor.error) { xml.println("<abserror kind=\"ProcDecl\"/>"); return; }
		xml.print("<absnode " + printPos(acceptor) + " kind=\"ProcDecl\">\n");
		acceptor.name.accept(this);
		acceptor.pars.accept(this);
		acceptor.decls.accept(this);
		acceptor.stmt.accept(this);
		xml.print("</absnode>\n");
	}

	public void visit(AbsProgram acceptor) {
		if (acceptor.error) { xml.println("<abserror kind=\"Program\"/>"); return; }
		xml.print("<absnode " + printPos(acceptor) + " kind=\"Program\">\n");
		acceptor.name.accept(this);
		acceptor.decls.accept(this);
		acceptor.stmt.accept(this);
		xml.print("</absnode>\n");
	}

	public void visit(AbsRecordType acceptor) {
		if (acceptor.error) { xml.println("<abserror kind=\"RecordType\"/>"); return; }
		xml.print("<absnode " + printPos(acceptor) + " kind=\"RecordType\">");
		acceptor.fields.accept(this);
		xml.print("</absnode>\n");
	}

	public void visit(AbsStmts acceptor) {
		if (acceptor.error) { xml.println("<abserror kind=\"Stmts\"/>"); return; }
		xml.print("<absnode " + printPos(acceptor) + " kind=\"Stmts\">\n");
		for (AbsStmt stmt : acceptor.stmts) {
			stmt.accept(this);
		}
		xml.print("</absnode>\n");
	}

	public void visit(AbsTypeDecl acceptor) {
		if (acceptor.error) { xml.println("<abserror kind=\"TypeDecl\"/>"); return; }
		xml.print("<absnode " + printPos(acceptor) + " kind=\"TypeDecl\">\n");
		acceptor.name.accept(this);
		acceptor.type.accept(this);
		xml.print("</absnode>\n");
	}

	public void visit(AbsTypeName acceptor) {
		if (acceptor.error) { xml.println("<abserror kind=\"TypeName\"/>"); return; }
		xml.print("<absnode " + printPos(acceptor)
				+ " kind=\"TypeName\" value=\"" + acceptor.name + "\">");
		xml.print("</absnode>\n");
	}

	public void visit(AbsUnExpr acceptor) {
		if (acceptor.error) { xml.println("<abserror kind=\"UnExpr\"/>"); return; }
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
		xml.print("<absnode " + printPos(acceptor)
				+ " kind=\"UnExpr\" value=\"" + oper + "\">");
		acceptor.expr.accept(this);
		xml.print("</absnode>\n");
	}

	public void visit(AbsValExprs acceptor) {
		if (acceptor.error) { xml.println("<abserror kind=\"ValExprs\"/>"); return; }
		xml.print("<absnode " + printPos(acceptor) + " kind=\"ValExprs\">\n");
		for (AbsValExpr expr : acceptor.exprs) {
			expr.accept(this);
		}
		xml.print("</absnode>\n");
	}

	public void visit(AbsValName acceptor) {
		if (acceptor.error) { xml.println("<abserror kind=\"ValName\"/>"); return; }
		xml.print("<absnode " + printPos(acceptor)
				+ " kind=\"ValName\" value=\"" + acceptor.name + "\">");
		xml.print("</absnode>\n");
	}

	public void visit(AbsVarDecl acceptor) {
		if (acceptor.error) { xml.println("<abserror kind=\"VarDecl\"/>"); return; }
		xml.print("<absnode " + printPos(acceptor) + " kind=\"VarDecl\">\n");
		acceptor.name.accept(this);
		acceptor.type.accept(this);
		xml.print("</absnode>\n");
	}

	public void visit(AbsWhileStmt acceptor) {
		if (acceptor.error) { xml.println("<abserror kind=\"WhileStmt\"/>"); return; }
		xml.print("<absnode " + printPos(acceptor) + " kind=\"WhileStmt\">");
		acceptor.cond.accept(this);
		acceptor.stmt.accept(this);
		xml.print("</absnode>\n");
	}

}
