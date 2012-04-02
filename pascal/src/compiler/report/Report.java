package compiler.report;

public class Report {

	/**
	 * Izpise opozorilo, a izvajanje se nadaljuje.
	 * 
	 * @param msg
	 *            Opozorilo.
	 */
	public static void warning(String msg) {
		System.err.println(":-o " + msg);
	}

	/**
	 * Izpise opozorilo, ki je vezano na vrstico in stolpec izvorne kode;
	 * izvajanje se nadaljuje.
	 * 
	 * @param msg
	 *            Opozorilo.
	 * @param begLine
	 *            Vrstica izvorne kode, kjer se zacne razlog za opozorilo.
	 * @param begColumn
	 *            Stoplec izvorne kode, kjer se zacne razlog za opozorilo.
	 */
	public static void warning(String msg, int begLine, int begColumn) {
		System.err.println(":-o [" + begLine + ":" + begColumn + "] " + msg);
	}

	/**
	 * Izpise opozorilo, ki je vezano na vrstico in stolpec izvorne kode;
	 * izvajanje se nadaljuje.
	 * 
	 * @param msg
	 *            Opozorilo.
	 * @param begLine
	 *            Vrstica izvorne kode, kjer se zacne razlog za opozorilo.
	 * @param begColumn
	 *            Stoplec izvorne kode, kjer se zacne razlog za opozorilo.
	 * @param endLine
	 *            Vrstica izvorne kode, kjer se konca razlog za opozorilo.
	 * @param endColumn
	 *            Stoplec izvorne kode, kjer se konca razlog za opozorilo.
	 */
	public static void warning(String msg, int begLine, int begColumn,
			int endLine, int endColumn) {
		System.err.println(":-o [" + begLine + ":" + begColumn + "-" + endLine
				+ ":" + endColumn + "] " + msg);
	}

	/**
	 * Izpise obvestilo o napaki; program se ustavi in konca s podano izhodno
	 * kodo.
	 * 
	 * @param msg
	 *            Obvestilo o napaki.
	 * @param exitCode
	 *            Izhodna koda programa.
	 */
	public static void error(String msg, int exitCode) {
		System.err.println(":-( " + msg);
		System.exit(exitCode);
	}

	/**
	 * Izpise obvestilo o napaki, ki je vezana na vrstico in stolpec izvorne
	 * kode; program se ustavi in konca s podano izhodno kodo.
	 * 
	 * @param msg
	 *            Obvestilo o napaki.
	 * @param begLine
	 *            Vrstica izvorne kode, kjer se zacne razlog za opozorilo.
	 * @param begColumn
	 *            Stoplec izvorne kode, kjer se zacne razlog za opozorilo.
	 * @param exitCode
	 *            Izhodna koda programa.
	 */
	public static void error(String msg, int begLine, int begColumn,
			int exitCode) {
		System.err.println(":-( [" + begLine + ":" + begColumn + "] " + msg);
		System.exit(exitCode);
	}

	/**
	 * Izpise obvestilo o napaki, ki je vezana na vrstico in stolpec izvorne
	 * kode; program se ustavi in konca s podano izhodno kodo.
	 * 
	 * @param msg
	 *            Obvestilo o napaki.
	 * @param begLine
	 *            Vrstica izvorne kode, kjer se zacne razlog za opozorilo.
	 * @param begColumn
	 *            Stoplec izvorne kode, kjer se zacne razlog za opozorilo.
	 * @param endLine
	 *            Vrstica izvorne kode, kjer se konca razlog za opozorilo.
	 * @param endColumn
	 *            Stoplec izvorne kode, kjer se konca razlog za opozorilo.
	 * @param exitCode
	 *            Izhodna koda programa.
	 */
	public static void error(String msg, int begLine, int begColumn,
			int endLine, int endColumn, int exitCode) {
		System.err.println(":-( [" + begLine + ":" + begColumn + "-" + endLine
				+ ":" + endColumn + "] " + msg);
		System.exit(exitCode);
	}
}
