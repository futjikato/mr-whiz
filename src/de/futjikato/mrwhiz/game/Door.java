package de.futjikato.mrwhiz.game;

public class Door extends Block {

	public Door(int bx, int by, char type, BlockDefinitions defines) {
		super(bx, by, type, defines);
		// TODO Auto-generated constructor stub
	}

	public void openDoor() {
		setDoRender(false);
	}

	public void closeDoor() {
		setDoRender(true);
	}
}
