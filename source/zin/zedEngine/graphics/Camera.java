package zin.zedEngine.graphics;

import org.lwjgl.glfw.GLFW;

import zin.zedEngine.math.Matrix4f;
import zin.zedEngine.math.Vector3f;

public class Camera {

	private Vector3f position, rotation;

	public Camera(Vector3f position, Vector3f rotation) {
		this.position = position;
		this.rotation = rotation;
	}

	public Camera(Camera source) {
		position = source.getPosition();
		rotation = source.getRotation();
	}

	public void updateCamera(Display display) {
		if (GLFW.glfwGetKey(display.getIdentifier(), GLFW.GLFW_KEY_W) == GLFW.GLFW_TRUE)
			walkForward(0.2f);
		if (GLFW.glfwGetKey(display.getIdentifier(), GLFW.GLFW_KEY_S) == GLFW.GLFW_TRUE)
			walkBackwards(0.2f);
		if (GLFW.glfwGetKey(display.getIdentifier(), GLFW.GLFW_KEY_A) == GLFW.GLFW_TRUE)
			strafeRight(0.2f);
		if (GLFW.glfwGetKey(display.getIdentifier(), GLFW.GLFW_KEY_D) == GLFW.GLFW_TRUE)
			strafeLeft(0.2f);

		rotation.x -= display.getDY() * 0.2f;
		rotation.y += display.getDX() * 0.2f;

		if(rotation.x > 360)
			rotation.x = 0;
		if(rotation.x < 0)
			rotation.x = 360;
		if(rotation.y > 360)
			rotation.y = 0;
		if(rotation.y < 0)
			rotation.y = 360;
		if(rotation.z > 360)
			rotation.z = 0;
		if(rotation.z < 0)
			rotation.z = 360;
	}

	public void walkForward(float distance) {
		position.x -= distance * (float) Math.sin(Math.toRadians(rotation.y));
		position.z -= distance * (float) Math.cos(Math.toRadians(rotation.y));
	}

	public void walkBackwards(float distance) {
		position.x += distance * (float) Math.sin(Math.toRadians(rotation.y));
		position.z += distance * (float) Math.cos(Math.toRadians(rotation.y));
	}

	public void strafeLeft(float distance) {
		position.x += distance * (float) Math.sin(Math.toRadians(rotation.y - 90));
		position.z += distance * (float) Math.cos(Math.toRadians(rotation.y - 90));
	}

	public void strafeRight(float distance) {
		position.x += distance * (float) Math.sin(Math.toRadians(rotation.y + 90));
		position.z += distance * (float) Math.cos(Math.toRadians(rotation.y + 90));
	}

	public Vector3f getPosition() {
		return position;
	}

	public Vector3f getRotation() {
		return rotation;
	}

	public Matrix4f getTransform() {
		Matrix4f position = new Matrix4f().translate(this.position);
		Matrix4f rotation = new Matrix4f().rotate(this.rotation);

		return rotation.multiply(position);
	}

}
