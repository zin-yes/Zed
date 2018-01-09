package zin.zedEngine.graphics;

import static org.lwjgl.glfw.GLFW.*;

import java.util.Map;

import org.lwjgl.opengl.GL;

import zin.zedEngine.utils.OptionsFileReader;

public class Display {

	private long identifier;

	private int width, height, samples, state;
	private String title;

	public Display(int width, int height, String title, int state, int samples) {
		this.width = width;
		this.height = height;
		this.title = title;
		this.state = state;
		this.samples = samples;
	}
	
	public Display(String fileName) {
		Map<String, String> options = OptionsFileReader.readOptionsFile(fileName);
		this.width = Integer.valueOf(options.get("width"));
		this.height = Integer.valueOf(options.get("height"));
		this.title = options.get("title").toString();
		this.state = Integer.valueOf(options.get("state").toString());
		this.samples = Integer.valueOf(options.get("samples"));
	}

	public void createDisplay() {
		if (!glfwInit())
			return;

		glfwWindowHint(GLFW_VERSION_MAJOR, 3);
		glfwWindowHint(GLFW_VERSION_MINOR, 3);
		glfwWindowHint(GLFW_SAMPLES, samples);
		
		if (state == 0) {
			identifier = glfwCreateWindow(width, height, title, 0, 0);
		} else if (state == 1) {
			glfwWindowHint(GLFW_DECORATED, GLFW_FALSE);
			identifier = glfwCreateWindow(width, height, title, 0, 0);
		} else if (state == 2) {
			identifier = glfwCreateWindow(width, height, title, glfwGetPrimaryMonitor(), 0);
		}
		
		glfwMakeContextCurrent(identifier);
		GL.createCapabilities();
	}
	
	public void updateDisplay() {
		glfwPollEvents();
		glfwSwapBuffers(identifier);
	}
	
	public void destroyDisplay() {
		glfwDestroyWindow(identifier);
	}

	public boolean shouldClose() {
		return glfwWindowShouldClose(identifier);
	}
	
}
