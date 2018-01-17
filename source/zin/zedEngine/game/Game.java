package zin.zedEngine.game;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import zin.zedEngine.graphics.Display;

public abstract class Game {

	public static final int WIDTH = 1280;
	public static final int HEIGHT = 720;
	public static final String TITLE = "";
	public static final int SAMPLES = 16;
	public static final int STATE = 0;
	public static final int FPS_LIMIT = 60;

	public abstract void startGame(Display display);

	public abstract void updateGame();

	public abstract void renderGame();

	public abstract void stopGame();

	public abstract void cleanUp();

	public static Map<String, String> readOptionsFile(String fileName) {
		BufferedReader reader = new BufferedReader(
				new InputStreamReader(Class.class.getResourceAsStream("/configs/" + fileName + ".zof")));

		Map<String, String> values = new HashMap<>();

		try {
			String line = reader.readLine();
			while (line != null) {
				if (line.startsWith("#break"))
					break;
				String[] pointersAndValues = line.split(": ");
				values.put(pointersAndValues[0], pointersAndValues[1]);
				line = reader.readLine();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return values;
	}

}
