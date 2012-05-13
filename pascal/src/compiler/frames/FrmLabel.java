package compiler.frames;

/** Opis labele v programu.  */
public class FrmLabel {

	/** Ime labele.  */
	private String name;

	private FrmLabel(String name) {
		this.name = name;
	}

	@Override
	public boolean equals(Object l) {
		return name == ((FrmLabel)l).name;
	}

	/** Vrne ime labele.  */
	public String name() {
		return name;
	}

	private static int label_count = 0;

	/** Vrne novo anonimno labelo.  */
	public static FrmLabel newLabel() {
		return new FrmLabel("L" + (label_count++));
	}

	/** Vrne novo poimenovano labelo.  */
	public static FrmLabel newLabel(String name) {
		return new FrmLabel("_" + name);
	}

}
