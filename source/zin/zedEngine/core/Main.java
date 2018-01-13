package zin.zedEngine.core;

import zin.shift.core.ShiftGame;
import zin.zedEngine.game.Game;

public class Main {

	private static Game game;

	public static void main(String[] args) {
		game = new ShiftGame();

		game.startGame();
		while (!game.shouldClose()) {
			game.updateGame();
		}
		game.stopGame();
	}

}
