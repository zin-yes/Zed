package zin.zedEngine.game;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public abstract class Game {

	public abstract void startGame();

	public abstract void updateGame();

	public abstract void stopGame();

	public abstract boolean shouldClose();
	
	public static Map<String, String> readOptionsFile(String fileName) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(Class.class.getResourceAsStream(fileName)));

		Map<String, String> values = new HashMap<>();
		
		try {
			String line = reader.readLine();
			while (line != null) {
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
