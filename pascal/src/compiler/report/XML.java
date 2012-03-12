package compiler.report;

import java.io.*;

public class XML {

	/**
	 * Odpre izhodno datoteko za izpis rezultatov posamezne faze prevajanja v
	 * XML formatu.
	 * 
	 * Ime vhodne datoteke je enako imenu faze prevajanja s koncnico
	 * <tt>.xml</tt>. V datoteko se izpise tudi glava XML dokumenta vkljucno z
	 * (odprto) oznako glavnega elementa, ki je znova enak imenu faze.
	 * 
	 * Ce je v lupini nastavljena spremenljivka <tt>PASCALXSL</tt>, je njena
	 * vrednost uporabljena kot direktorij, na katerem so shranjene pripadajoce
	 * <tt>.xsl</tt> datoteke. V tem primeru se v glavo XML dokumenta izpise
	 * tudi referenca na pripadajoco <tt>.xsl</tt> datoteko.
	 * 
	 * @param phase
	 *            Ime faze prevajanja.
	 * @return Odprta izhodna datoteka.
	 */
	public static PrintStream open(String phase) {
		PrintStream stream = null;
		try {
			stream = new PrintStream(phase + ".xml");
			stream.println("<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>");
			try {
				String xslDir = System.getenv("PASCALXSL");
				if (xslDir != null) {
					stream.println("<?xml-stylesheet type=\"text/xsl\" href=\""
							+ xslDir + "/" + phase + ".xsl\"?>");
				}
			} catch (Exception _) {
			}
			stream.println("<" + phase + ">");
		} catch (IOException _) {
			Report.error("Cannot open XML file '" + phase + ".xml'.", 1);
		}
		return stream;
	}

	/**
	 * Zapre izhodno datoteko za izpis rezultatov posamezne faze prevajanja v
	 * XML formatu.
	 * 
	 * Pri zapiranju datoteke se izpise tudi (zaprta) oznaka glavnega elementa,
	 * ki je enak imenu faze.
	 * 
	 * @param phase
	 *            Ime faze prevajanja.
	 * @param stream
	 *            Odprta izhodna datoteka.
	 */
	public static void close(String phase, PrintStream stream) {
		stream.println("</" + phase + ">");
		stream.close();
	}
}
