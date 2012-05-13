package compiler.frames;

import compiler.abstree.*;
import compiler.abstree.tree.*;
import compiler.semanal.*;

public class FrmEvaluator extends AbsEmptyVisitor {
	
	public void visit(AbsAtomType acceptor) {
	}
	
	public void visit(AbsConstDecl acceptor) {
	}
	
	public void visit(AbsFunDecl acceptor) {
		FrmFrame frame = new FrmFrame(acceptor, SemDesc.getScope(acceptor));
		for (AbsDecl decl : acceptor.decls.decls) {
			if (decl instanceof AbsVarDecl) {
				AbsVarDecl varDecl = (AbsVarDecl)decl;
				FrmLocAccess access = new FrmLocAccess(varDecl, frame);
				FrmDesc.setAccess(varDecl, access);
			}
			decl.accept(this);
		}
		FrmDesc.setFrame(acceptor, frame);
	}
	
	public void visit(AbsProgram acceptor) {
		for (AbsDecl decl : acceptor.decls.decls) {
			if (decl instanceof AbsVarDecl) {
				AbsVarDecl varDecl = (AbsVarDecl)decl;
				FrmVarAccess access = new FrmVarAccess(varDecl);
				FrmDesc.setAccess(varDecl, access);
			}
			decl.accept(this);
		}
	}
	
	public void visit(AbsProcDecl acceptor) {
		FrmFrame frame = new FrmFrame(acceptor, SemDesc.getScope(acceptor));
		for (AbsDecl decl : acceptor.pars.decls) {
			if (decl instanceof AbsVarDecl) {
				AbsVarDecl varDecl = (AbsVarDecl)decl;
				FrmArgAccess access = new FrmArgAccess(varDecl, frame);
				FrmDesc.setAccess(varDecl, access);
			}
		}
		for (AbsDecl decl : acceptor.decls.decls) {
			if (decl instanceof AbsVarDecl) {
				AbsVarDecl varDecl = (AbsVarDecl)decl;
				FrmLocAccess access = new FrmLocAccess(varDecl, frame);
				FrmDesc.setAccess(varDecl, access);
			}
			decl.accept(this);
		}
		FrmDesc.setFrame(acceptor, frame);
	}
	
	public void visit(AbsRecordType acceptor) {
		int offset = 0;
		for (AbsDecl decl : acceptor.fields.decls)
			if (decl instanceof AbsVarDecl) {
				AbsVarDecl varDecl = (AbsVarDecl)decl;
				FrmCmpAccess access = new FrmCmpAccess(varDecl, offset);
				FrmDesc.setAccess(varDecl, access);
				offset = offset + SemDesc.getActualType(varDecl.type).size();
			}
	}
	
	public void visit(AbsTypeDecl acceptor) {
	}
	
	public void visit(AbsVarDecl acceptor) {
		acceptor.type.accept(this);
	}
	
}
