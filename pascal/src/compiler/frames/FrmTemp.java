package compiler.frames;

/** Opis zacasne spremenljivke v programu.  */
public class FrmTemp {

	private static int count = 0;

	private int num;

	public FrmTemp() {
		num = count++;
	}

	@Override
	public boolean equals(Object t) {
		return num == ((FrmTemp)t).num;
	}

	public String name() {
		return "T" + num;
	}

}
