package compiler.synanal;

import java.io.*;
import java.lang.reflect.*;

import compiler.lexanal.*;
import compiler.report.*;

public class Main {

	public static String[] pascalNontNames;

	static {
		/* Pripravimo imena vrst vmesnih simbolov.  */
		PascalTok pascalTok = new PascalTok();
		Field[] pascalToks = pascalTok.getClass().getDeclaredFields();
		pascalNontNames = new String[pascalToks.length];
		for (int f = 0; f < pascalToks.length; f++) {
			try {
				int tok = pascalToks[f].getInt(pascalTok);
				String lex = pascalToks[f].toString().replaceAll("^.*\\.", "");
				if (! ((tok < compiler.lexanal.Main.pascalTermNames.length) &&
					   (lex.equals(compiler.lexanal.Main.pascalTermNames[tok])))) {
					pascalNontNames[tok] = lex;
				}
			}
			catch (IllegalAccessException _) {}
		}
	}

	/** Izvede prevajanje do vkljucno faze sintaksne analize. */
	public static void exec() {
		/* Odpremo vhodno in izhodno datoteko.  */
		FileReader srcFile = null;
		String srcName = compiler.Main.prgName + ".pascal";
		try { srcFile = new FileReader(srcName); }
		catch (FileNotFoundException _) { Report.error("Source file '" + srcName + "' cannot be opened.", 1); }
		PrintStream xml = XML.open("synanal");

		PascalLex lexer = new PascalLex(srcFile);
		PascalSyn parser = new PascalSyn(lexer);
		try {
			parser.debug_parse(xml);
		}
		catch (Exception ex) {
			XML.close("synanal", xml);
			Report.error("Error while testing syntax analyzer.", 1);
		}

		/* Zapremo obe datoteki.  */
        XML.close("synanal", xml);
        try { srcFile.close(); }
		catch (IOException _) { Report.error("Source file '" + srcName + "' cannot be opened.", 1); }
	}
}
