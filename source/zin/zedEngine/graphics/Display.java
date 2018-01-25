package zin.zedEngine.graphics;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.glfw.GLFW.GLFW_FALSE;
import static org.lwjgl.glfw.GLFW.GLFW_SAMPLES;
import static org.lwjgl.glfw.GLFW.GLFW_VERSION_MAJOR;
import static org.lwjgl.glfw.GLFW.GLFW_VERSION_MINOR;
import static org.lwjgl.glfw.GLFW.glfwCreateWindow;
import static org.lwjgl.glfw.GLFW.glfwDestroyWindow;
import static org.lwjgl.glfw.GLFW.glfwGetPrimaryMonitor;
import static org.lwjgl.glfw.GLFW.glfwInit;
import static org.lwjgl.glfw.GLFW.glfwMakeContextCurrent;
import static org.lwjgl.glfw.GLFW.glfwPollEvents;
import static org.lwjgl.glfw.GLFW.glfwSwapBuffers;
import static org.lwjgl.glfw.GLFW.glfwSwapInterval;
import static org.lwjgl.glfw.GLFW.glfwWindowHint;
import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;

import org.lwjgl.opengl.GL;

public class Display {

	private static long identifier;

	private static int width, height, samples;
	private static DisplayState state;
	private static String title;

	public static void createDisplay(int width, int height, String title, DisplayState state, int samples) {
		if (!glfwInit())
			return;

		glfwWindowHint(GLFW_VERSION_MAJOR, 3);
		glfwWindowHint(GLFW_VERSION_MINOR, 3);
		glfwWindowHint(GLFW_SAMPLES, samples);

		if (state == DisplayState.WINDOWED) {
			identifier = glfwCreateWindow(width, height, title, 0, 0);
		} else if (state == DisplayState.BORDERLESS) {
			glfwWindowHint(GLFW_DECORATED, GLFW_FALSE);
			identifier = glfwCreateWindow(width, height, title, 0, 0);
		} else if (state == DisplayState.FULLSCREEN) {
			identifier = glfwCreateWindow(width, height, title, glfwGetPrimaryMonitor(), 0);
		}

		glfwMakeContextCurrent(identifier);
		GL.createCapabilities();

		glfwSwapInterval(0);

		Display.width = width;
		Display.height = height;
		Display.title = title;
		Display.state = state;
		Display.samples = samples;
	}

	public static void updateDisplay() {
		glfwPollEvents();
		glfwSwapBuffers(identifier);
	}

	public static void destroyDisplay() {
		glfwDestroyWindow(identifier);
	}

	public static int getWidth() {
		return width;
	}

	public static int getHeight() {
		return height;
	}

	public static int getSamples() {
		return samples;
	}

	public static DisplayState getState() {
		return state;
	}

	public static String getTitle() {
		return title;
	}

	public static boolean shouldClose() {
		return glfwWindowShouldClose(identifier);
	}

	public static long getIdentifier() {
		return identifier;
	}

}