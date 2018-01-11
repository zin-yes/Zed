package zin.shift.core;

import zin.zedEngine.game.Game;
import zin.zedEngine.graphics.Display;

public class ShiftGame extends Game {

	private Display display;

	@Override
	public void startGame() {
		display = new Display("display_settings");
		display.createDisplay();
	}

	@Override
	public void updateGame() {
		display.updateDisplay();
	}

	@Override
	public void stopGame() {
		display.destroyDisplay();
	}

	@Override
	public boolean shouldClose() {
		return display.shouldClose();
	}

}
