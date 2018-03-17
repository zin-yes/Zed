package zin.zedEngine.core;

import zin.game.core.TestGame;
import zin.zedEngine.graphics.DisplayState;

public class Main {
	
	public static void main(String[] args) {
		CoreEngine engine = new CoreEngine(1920, 1080, 60, new TestGame());
		engine.createDisplay("Zed Engine", DisplayState.BORDERLESS, 16);
		engine.startEngine();
	}

}
