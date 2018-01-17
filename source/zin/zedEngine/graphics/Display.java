package zin.zedEngine.graphics;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.glfw.GLFW.GLFW_CURSOR_DISABLED;
import static org.lwjgl.glfw.GLFW.GLFW_DECORATED;
import static org.lwjgl.glfw.GLFW.GLFW_FALSE;
import static org.lwjgl.glfw.GLFW.GLFW_SAMPLES;
import static org.lwjgl.glfw.GLFW.GLFW_VERSION_MAJOR;
import static org.lwjgl.glfw.GLFW.GLFW_VERSION_MINOR;
import static org.lwjgl.glfw.GLFW.glfwCreateWindow;
import static org.lwjgl.glfw.GLFW.glfwDestroyWindow;
import static org.lwjgl.glfw.GLFW.glfwGetCursorPos;
import static org.lwjgl.glfw.GLFW.glfwGetPrimaryMonitor;
import static org.lwjgl.glfw.GLFW.glfwInit;
import static org.lwjgl.glfw.GLFW.glfwMakeContextCurrent;
import static org.lwjgl.glfw.GLFW.glfwPollEvents;
import static org.lwjgl.glfw.GLFW.glfwSetCursorPos;
import static org.lwjgl.glfw.GLFW.glfwSetInputMode;
import static org.lwjgl.glfw.GLFW.glfwSwapBuffers;
import static org.lwjgl.glfw.GLFW.glfwWindowHint;
import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;

import java.nio.DoubleBuffer;
import java.util.Map;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL;

import zin.zedEngine.game.Game;
import zin.zedEngine.math.Vector2f;

public class Display {

	private long identifier;

	private int width, height, samples, state;
	private String title;

	private Mouse mouse;

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

		glfwSwapInterval(0);

		mouse = new Mouse(this);
	}

	public void updateDisplay() {
		mouse.updateMouse();
		
		glfwPollEvents();
		glfwSwapBuffers(identifier);
	}

	public void destroyDisplay() {
		glfwDestroyWindow(identifier);
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getSamples() {
		return samples;
	}

	public int getState() {
		return state;
	}

	public String getTitle() {
		return title;
	}

	public boolean shouldClose() {
		return glfwWindowShouldClose(identifier);
	}

	public long getIdentifier() {
		return identifier;
	}

	public double getDX() {
		return mouse.getDX();
	}

	public double getDY() {
		return mouse.getDY();
	}

	public void setGrabbed(boolean grabbed) {
		mouse.setGrabbed(grabbed);
	}

	public boolean getGrabbed() {
		return mouse.getGrabbed();
	}
	
	public Vector2f getMousePosition() {
		return mouse.getMousePosition();
	}

}

class Mouse {

	private boolean grabbed = false;

	private double newX, newY, prevX, prevY, dx, dy;
	private boolean rotX, rotY;

	private Display display;

	public Mouse(Display display) {
		this.display = display;

		newX = display.getWidth() / 2;
		newY = display.getHeight() / 2;

		prevX = 0;
		prevY = 0;

		rotX = false;
		rotY = false;
	}

	public void updateMouse() {
		DoubleBuffer x = BufferUtils.createDoubleBuffer(1);
		DoubleBuffer y = BufferUtils.createDoubleBuffer(1);

		glfwGetCursorPos(display.getIdentifier(), x, y);
		x.rewind();
		y.rewind();

		newX = x.get();
		newY = y.get();

		double deltaX = newX - display.getWidth() / 2;
		double deltaY = newY - display.getHeight() / 2;

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

		glfwSetCursorPos(display.getIdentifier(), display.getWidth() / 2, display.getHeight() / 2);
	}

	public double getDX() {
		return dx;
	}

	public double getDY() {
		return dy;
	}

	public void setGrabbed(boolean grabbed) {
		this.grabbed = grabbed;

		if (grabbed) {
			glfwSetInputMode(display.getIdentifier(), GLFW_CURSOR, GLFW_CURSOR_DISABLED);
		} else {
			glfwSetInputMode(display.getIdentifier(), GLFW_CURSOR, GLFW_CURSOR_NORMAL);
		}
	}

	public boolean getGrabbed() {
		return grabbed;
	}

	public Vector2f getMousePosition() {
		double[] x = new double[1], y = new double[1];
		glfwGetCursorPos(display.getIdentifier(), x, y);
		return new Vector2f((float) x[0], (float) y[0]);
	}

}
