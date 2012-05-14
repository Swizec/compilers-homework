package compiler.frames;

import java.io.*;

import compiler.report.*;
import compiler.lexanal.*;
import compiler.synanal.*;
import compiler.abstree.tree.*;
import compiler.semanal.*;

public class Main {

	/** Abstraktno sintaksno drevo prevajanega programa. */
	public static AbsTree absTree = null;

	/**
	 * Izvede prevajanje do faze semanticne analize.
	 */
	public static void exec() {
		/* Odpremo vhodno datoteko. */
		FileReader srcFile = null;
		String srcName = compiler.Main.prgName + ".pascal";
		try {
			srcFile = new FileReader(srcName);
		} catch (FileNotFoundException _) {
			Report.error("Source file '" + srcName + "' cannot be opened.", 1);
		}

		PascalLex lexer = new PascalLex(srcFile);
		PascalSyn parser = new PascalSyn(lexer);
		AbsProgram program = null;
		try {
			program = (AbsProgram) (parser.parse().value);
		} catch (Exception ex) {
			Report.error("Uncaught syntax error.", 1);
		}
		SemNameResolver nameResolver = new SemNameResolver();
		SemTypeChecker typeChecker = new SemTypeChecker();
		program.accept(nameResolver);
		program.accept(typeChecker);

		/* Zapremo vhodno datoteko. */
		try {
			srcFile.close();
		} catch (IOException _) {
			Report.error("Source file '" + srcName + "' cannot be closed.", 1);
		}
		if (nameResolver.error || typeChecker.error) {
			Report.error("Too many errors during semantic analysis.", 1);
		}
		
		/* Izracunamo klicne zapise in izpi"semo rezultat. */
		PrintStream xml = XML.open("frames");
		program.accept(new FrmEvaluator());
		program.accept(new FrmPrintXML(xml));
		XML.close("frames", xml);
	}
}
