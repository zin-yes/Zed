package zin.zedEngine.graphics;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

public abstract class Shader {

	protected int programID, vertexShaderID, fragmentShaderID;

	public Shader(String fileName) {
		StringBuilder vertexShaderSource = new StringBuilder();
		try {
			InputStream in = Class.class.getResourceAsStream("/shaders/" + fileName + ".vsh");
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			String line;
			while ((line = reader.readLine()) != null) {
				vertexShaderSource.append(line).append("\n");
			}
			reader.close();
		} catch(IOException e) {
			System.err.println("Count not read file!");
			e.printStackTrace();
			System.exit(-1);
		}
		vertexShaderID = GL20.glCreateShader(GL20.GL_VERTEX_SHADER);
		GL20.glShaderSource(vertexShaderID, vertexShaderSource);
		GL20.glCompileShader(vertexShaderID);
		if(GL20.glGetShaderi(vertexShaderID, GL20.GL_COMPILE_STATUS) == GL11.GL_FALSE) {
			System.out.println(GL20.glGetShaderInfoLog(vertexShaderID, 500));
			System.err.println("Could not compile shader.");
			System.exit(-1);
		}
		
		//f
		StringBuilder fragmentShaderSource = new StringBuilder();
		try {
			InputStream in = Class.class.getResourceAsStream("/shaders/" + fileName + ".fsh");
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			String line;
			while ((line = reader.readLine()) != null) {
				fragmentShaderSource.append(line).append("\n");
			}
			reader.close();
		} catch(IOException e) {
			System.err.println("Count not read file!");
			e.printStackTrace();
			System.exit(-1);
		}
		fragmentShaderID = GL20.glCreateShader(GL20.GL_FRAGMENT_SHADER);
		GL20.glShaderSource(fragmentShaderID, fragmentShaderSource);
		GL20.glCompileShader(fragmentShaderID);
		if(GL20.glGetShaderi(fragmentShaderID, GL20.GL_COMPILE_STATUS) == GL11.GL_FALSE) {
			System.out.println(GL20.glGetShaderInfoLog(fragmentShaderID, 500));
			System.err.println("Could not compile shader.");
			System.exit(-1);
		}
		
		programID = GL20.glCreateProgram();
		GL20.glAttachShader(programID, vertexShaderID);
		GL20.glAttachShader(programID, fragmentShaderID);
		bindAttributes();
		GL20.glLinkProgram(programID);
		GL20.glValidateProgram(programID);
		getAllUniformLocations();
	}

	protected abstract void getAllUniformLocations();

	protected abstract void bindAttributes();

	public void bindShader() {
		GL20.glUseProgram(programID);
	}

	public void unbindShader() {
		GL20.glUseProgram(0);
	}

}
