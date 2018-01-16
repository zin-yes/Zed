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
import static org.lwjgl.glfw.GLFW.glfwWindowHint;
import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;

import java.nio.DoubleBuffer;
import java.util.Map;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL;

import zin.zedEngine.game.Game;

public class Display {

	private long identifier;

	private int width, height, samples, state;
	private String title;

	private double newX, newY, prevX, prevY, dx, dy;
	private boolean rotX, rotY;

	public Display(int width, int height, String title, int state, int samples) {
		this.width = width;
		this.height = height;
		this.title = title;
		this.state = state;
		this.samples = samples;
	}

	public Display(String fileName) {
		Map<String, String> options = Game.readOptionsFile(fileName);
		this.width = Integer.valueOf(options.get("width"));
		this.height = Integer.valueOf(options.get("height"));
		this.title = options.get("title").toString();
		this.state = Integer.valueOf(options.get("state"));
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

		newX = width / 2;
		newY = height / 2;

		prevX = 0;
		prevY = 0;

		rotX = false;
		rotY = false;

		glfwSetInputMode(identifier, GLFW_CURSOR, GLFW_CURSOR_DISABLED);
	}

	public void updateDisplay() {
		DoubleBuffer x = BufferUtils.createDoubleBuffer(1);
		DoubleBuffer y = BufferUtils.createDoubleBuffer(1);

		glfwGetCursorPos(identifier, x, y);
		x.rewind();
		y.rewind();

		newX = x.get();
		newY = y.get();

		double deltaX = newX - width / 2;
		double deltaY = newY - height / 2;

		rotX = newX != prevX;
		rotY = newY != prevY;

		if (rotX) {
			dx = deltaX;
		}
		if (rotY) {
			dy = deltaY;
		}

		prevX = newX;
		prevY = newY;

		glfwSetCursorPos(identifier, width / 2, height / 2);

		glfwPollEvents();
		glfwSwapBuffers(identifier);
	}

	public void destroyDisplay() {
		glfwDestroyWindow(identifier);
	}

	public boolean shouldClose() {
		return glfwWindowShouldClose(identifier);
	}

	public long getIdentifier() {
		return identifier;
	}

	public double getDX() {
		return dx;
	}

	public double getDY() {
		return dy;
	}

}
