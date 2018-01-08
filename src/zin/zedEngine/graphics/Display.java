package zin.zedEngine.graphics;

import static org.lwjgl.glfw.GLFW.*;

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
	
	public void createDisplay() {
		if(!glfwInit())
			return;
	}
	
}
