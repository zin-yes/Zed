package zin.zedEngine.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/* The files that this class reads are only in the engine. 
 * If you want to use this for yourself, you'll have to follow the syntax. 
 * Ill make a wiki for it soon.
 */
public class OptionsFileReader {

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
