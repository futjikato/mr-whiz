package de.futjikato.mrwhiz.game;

public class UnknownType extends Exception {

	private char type;

	public UnknownType() {
		// TODO Auto-generated constructor stub
	}

	public UnknownType(char type) {
		this.type = type;
	}

	public char getUnknownType() {
		return type;
	}

	public UnknownType(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public UnknownType(Throwable arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public UnknownType(String arg0, Throwable arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

}
