package compiler.semanal;

/** Ob poskusu brisanja imena, ki ni deklarirano na trenutnem nivoju. */
public class SemIllegalDeleteException extends Exception {

	static final long serialVersionUID = 0l;

	public SemIllegalDeleteException() {
	}
}
