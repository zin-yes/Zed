package zin.zedEngine.graphics;

import static org.lwjgl.glfw.GLFW.GLFW_CURSOR;
import static org.lwjgl.glfw.GLFW.GLFW_CURSOR_DISABLED;
import static org.lwjgl.glfw.GLFW.GLFW_CURSOR_NORMAL;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_LAST;
import static org.lwjgl.glfw.GLFW.glfwGetCursorPos;
import static org.lwjgl.glfw.GLFW.glfwGetKey;
import static org.lwjgl.glfw.GLFW.glfwSetCursorPos;
import static org.lwjgl.glfw.GLFW.glfwSetInputMode;

import java.nio.DoubleBuffer;

import org.lwjgl.BufferUtils;

import zin.zedEngine.math.Vector2f;

public class Input {

	private static Keyboard keyboard;
	private static Mouse mouse;

	public static void init() {
		keyboard = new Keyboard();
		mouse = new Mouse();
	}

	public static void updateInput() {
		keyboard.updateKeyboard();
		mouse.updateMouse();
	}

	public static boolean isKeyDown(int keyCode) {
		return keyboard.isKeyDown(keyCode);
	}

	public static boolean isKeyPressed(int keyCode) {
		return keyboard.isKeyPressed(keyCode);
	}

	public static boolean isKeyReleased(int keyCode) {
		return keyboard.isKeyReleased(keyCode);
	}

	public static double getMouseDeltaX() {
		return mouse.getDX();
	}

	public static double getMouseDeltaY() {
		return mouse.getDY();
	}

	public static void setMouseGrabbed(boolean grabbed) {
		mouse.setGrabbed(grabbed);
	}

	public static boolean getMouseGrabbed() {
		return mouse.getGrabbed();
	}

	public static Vector2f getMousePosition() {
		return mouse.getMousePosition();
	}

}

class Keyboard {

	private boolean currentKeys[];

	public Keyboard() {
		this.currentKeys = new boolean[GLFW_KEY_LAST];
		for (int i = 0; i < GLFW_KEY_LAST; i++)
			currentKeys[i] = false;
	}

	public boolean isKeyDown(int keyCode) {
		return glfwGetKey(Display.getIdentifier(), keyCode) == 1;
	}

	public boolean isKeyPressed(int keyCode) {
		return (isKeyDown(keyCode) && !currentKeys[keyCode]);
	}

	public boolean isKeyReleased(int keyCode) {
		return (!isKeyDown(keyCode) && currentKeys[keyCode]);
	}

	public void updateKeyboard() {
		for (int i = 32; i < GLFW_KEY_LAST; i++)
			currentKeys[i] = isKeyDown(i);
	}

}

class Mouse {

	private boolean grabbed = false;

	private double newX, newY, prevX, prevY, dx, dy;
	private boolean rotX, rotY;

	public Mouse() {
		newX = Display.getWidth() / 2;
		newY = Display.getHeight() / 2;

		prevX = 0;
		prevY = 0;

		rotX = false;
		rotY = false;
	}

	public void updateMouse() {
		if (grabbed) {
			DoubleBuffer x = BufferUtils.createDoubleBuffer(1);
			DoubleBuffer y = BufferUtils.createDoubleBuffer(1);

			glfwGetCursorPos(Display.getIdentifier(), x, y);
			x.rewind();
			y.rewind();

			newX = x.get();
			newY = y.get();

			double deltaX = newX - Display.getWidth() / 2;
			double deltaY = newY - Display.getHeight() / 2;

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

			glfwSetCursorPos(Display.getIdentifier(), Display.getWidth() / 2, Display.getHeight() / 2);
		}
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
			glfwSetInputMode(Display.getIdentifier(), GLFW_CURSOR, GLFW_CURSOR_DISABLED);
		} else {
			glfwSetInputMode(Display.getIdentifier(), GLFW_CURSOR, GLFW_CURSOR_NORMAL);
		}
	}

	public boolean getGrabbed() {
		return grabbed;
	}

	public Vector2f getMousePosition() {
		double[] x = new double[1], y = new double[1];
		glfwGetCursorPos(Display.getIdentifier(), x, y);
		return new Vector2f((float) x[0], (float) y[0]);
	}

}