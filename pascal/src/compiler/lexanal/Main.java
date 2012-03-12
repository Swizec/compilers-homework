package compiler.lexanal;

import java.io.*;
import java.lang.reflect.*;

import compiler.report.*;
import compiler.synanal.*;

public class Main {

	public static String[] pascalTermNames;

	static {
		/* Pripravimo imena vrst koncnih simbolov.  */
		PascalTok pascalTok = new PascalTok();
		Field[] pascalToks = pascalTok.getClass().getDeclaredFields();
		pascalTermNames = new String[pascalToks.length + 5];
		for (int f = 0; f < pascalToks.length; f++) {
			try {
				int tok = pascalToks[f].getInt(pascalTok);
				String lex = pascalToks[f].toString().replaceAll("^.*\\.", "");
				pascalTermNames[tok] = lex;
			}
			catch (IllegalAccessException _) {}
		}
	}

	/** Izvede fazo leksikalne analize (ce ta ni del sintaksne analize). */
	public static void exec() {
		/* Odpremo vhodno in izhodno datoteko.  */
		FileReader srcFile = null;
		String srcName = compiler.Main.prgName + ".pascal";
		try { srcFile = new FileReader(srcName); }
		catch (FileNotFoundException _) { Report.error("Source file '" + srcName + "' cannot be opened.", 1); }
		PrintStream xml = XML.open("lexanal");

		/* Opravimo leksikalno analizo: zgolj beremo simbol za simbolom.  */
        PascalLex lexer = new PascalLex(srcFile);
        PascalSym symbol;
        try {
            while ((symbol = lexer.next_token ()).sym != PascalTok.EOF) {
            	symbol.toXML(xml);
            }
        }
        catch (IOException _) {
            Report.error("Error while testing lexical analyzer.", 1);
        }

        /* Zapremo obe datoteki.  */
        XML.close("lexanal", xml);
        try { srcFile.close(); }
		catch (IOException _) { Report.error("Source file '" + srcName + "' cannot be opened.", 1); }
	}

}
