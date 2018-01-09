package zin.shift.core;

import zin.zedEngine.game.Game;
import zin.zedEngine.graphics.Display;
import zin.zedEngine.utils.OptionsFileReader;

public class ShiftGame extends Game {

	private static final int WIDTH = 1280;
	private static final int HEIGHT = 720;
	private static final String TITLE = "";
	private static final int SAMPLES = 16;
	private static final int STATE = 0;

	private Display display;

	@Override
	public void startGame() {
		display = new Display("/display_settings.zof");
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
