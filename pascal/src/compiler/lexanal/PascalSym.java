package compiler.lexanal;

import java.io.*;

import java_cup.runtime.*;

import compiler.report.*;
import compiler.synanal.*;

/**
 * Opis posameznega osnovnega simbola.
 * 
 * Ta razred je izpeljan iz razreda java_cup.runtime.Symbol. Edini razlog za
 * izpeljavo je dodatek metode <code>toXML</code> za izpis simbola v XML obliki.
 * 
 * Vsak osnovni simbol je opisan s stirimi podatki: - <code>int sym</code> :
 * vrsta osnovnega simbola (token); - <code>int left</code> : vrstica izvorne
 * kode, v kateri se osnovni simbol zacne (line); - <code>int right</code> :
 * stolpec izvorne kode, v katerem se osnovni simbol zacne (column); -
 * <code>Object value</code>: znakovna predstavitev osnovnega simbola (lexeme).
 * (Cudno poimenovanje izvira iz razreda java_cup.runtime.Symbol.)
 * 
 * @see <a
 *      href="https://www2.in.tum.de/repos/cup/develop/src/java_cup/runtime/Symbol.java"><code>java_cup.runtime.Symbol</code>
 *      source</a>
 */
public class PascalSym extends Symbol implements XMLable {

	/**
	 * Konstruktor, ki se uporablja za osnovni simbol <code>EOF</code>.
	 * 
	 * @param sym
	 *            Vrsta osnovnega simbola.
	 */
	public PascalSym(int sym) {
		this(sym, -1, -1, "");
	}

	/**
	 * Konstruktor, ki se uporablja le pri testnem izpisu med sintaksno analizo.
	 * 
	 * @param symb
	 *            Simbol razreda <code>Symbol<code>.
	 */
	public PascalSym(Symbol symb) {
		this(symb.sym, symb.left, symb.right, symb.value);
	}

	/**
	 * Konstruktor, ki se uporablja za vse osnovne simbole razen za
	 * <code>EOF</code>.
	 * 
	 * @param sym
	 *            Vrsta osnovnega simbola.
	 * @param left
	 *            Vrstica izvorne kode, v kateri se osnovni simbol zacne.
	 * @param right
	 *            Stolpec izvorne kode, v katerem se osnovni simbol zacne.
	 * @param value
	 *            Znakovna predstavitev osnovnega simbola.
	 */
	public PascalSym(int sym, int left, int right, Object value) {
		super(sym, left, right, value);
	}

	public void toXML(PrintStream xml) {
		switch (sym) {
		case PascalTok.BOOL_CONST:
		case PascalTok.CHAR_CONST:
		case PascalTok.INT_CONST:
		case PascalTok.IDENTIFIER:
			xml.print("<terminal token=\"" + Main.pascalTermNames[sym]
					+ "\" lexeme=\"" + toXML((String) value) + "\" line=\""
					+ left + "\" column=\"" + right + "\"" + "/>\n");
			break;
		default:
			xml.print("<terminal token=\"" + Main.pascalTermNames[sym]
					+ "\" line=\"" + left + "\" column=\"" + right + "\""
					+ "/>\n");
			break;
		}
	}

	private String toXML(String lexeme) {
		StringBuffer lex = new StringBuffer();
		for (int i = 0; i < lexeme.length(); i++)
			switch (lexeme.charAt(i)) {
			case '\'':
				lex.append("&#39;");
				break;
			case '\"':
				lex.append("&#34;");
				break;
			case '&':
				lex.append("&#38;");
				break;
			case '<':
				lex.append("&#60;");
				break;
			case '>':
				lex.append("&#62;");
				break;
			default:
				lex.append(lexeme.charAt(i));
				break;
			}
		return lex.toString();
	}
}
