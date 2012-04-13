package compiler;

import compiler.report.*;

public class Main {

	/** Ime programa, ki ga prevajamo. */
	public static String prgName;

	/**
	 * Zaganjac prevajalnika.
	 * 
	 * Prvi argument v ukazni vrstici vsebuje ime vhodne datoteke z izvorno kodo
	 * v programskem jeziku Pascal. Drugi argument (ce je prisoten) doloca
	 * zadnjo fazo prevajanja, ki jo se opravimo. Ostale argumente ignoriramo.
	 * 
	 * @param argv
	 *            Argumenti ukazne vrstice.
	 */
	public static void main(String[] args) {
		System.out.println("This is Pascal compiler:");

		/* Dolocimo ime programa, ki ga prevajamo. */
		if (args.length < 1) {
			Report.error("Source file is not specified.", 1);
		} else
			prgName = args[0];

		/* Dolocimo zadnjo fazo prevajanja. */
		String phase = args.length < 2 ? "" : args[1];
		/* Opravimo izbrano fazo prevajanja (in vse predhodne faze). */
		if (phase.equals("lexanal"))
			compiler.lexanal.Main.exec();
		else if (phase.equals("synanal"))
			compiler.synanal.Main.exec();
		else if (phase.equals("abstree"))
			compiler.abstree.Main.exec();
		else
			compiler.abstree.Main.exec();

		System.out.print(":-) Done.\n");
		System.exit(0);
	}
}
