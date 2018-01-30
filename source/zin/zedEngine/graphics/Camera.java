package zin.zedEngine.graphics;

import org.lwjgl.glfw.GLFW;

import zin.zedEngine.graphics.shaders.BasicShader;
import zin.zedEngine.math.Matrix4f;
import zin.zedEngine.math.Vector3f;

public class Camera {

	protected Vector3f position, rotation;

	public Camera(Vector3f position, Vector3f rotation) {
		this.position = position;
		this.rotation = rotation;
	}

	public Camera(Camera source) {
		position = source.getPosition();
		rotation = source.getRotation();
	}

	public Camera() {
		position = new Vector3f();
		rotation = new Vector3f();
	}

	public void updateCamera() {
		if (Input.isKeyDown(GLFW.GLFW_KEY_W))
			walkForward(0.2f);
		if (Input.isKeyDown(GLFW.GLFW_KEY_S))
			walkBackwards(0.2f);
		if (Input.isKeyDown(GLFW.GLFW_KEY_A))
			strafeRight(0.2f);
		if (Input.isKeyDown(GLFW.GLFW_KEY_D))
			strafeLeft(0.2f);
		if (Input.isKeyDown(GLFW.GLFW_KEY_SPACE))
			position.y += 0.2f;
		if (Input.isKeyDown(GLFW.GLFW_KEY_LEFT_SHIFT))
			position.y -= 0.2f;

		if (Input.isKeyDown(GLFW.GLFW_KEY_F1))
			Input.setMouseGrabbed(true);
		if (Input.isKeyDown(GLFW.GLFW_KEY_F2))
			Input.setMouseGrabbed(false);

		if (Input.getMouseGrabbed()) {
			rotation.x -= Input.getMouseDeltaY() * 0.1f;
			rotation.y += Input.getMouseDeltaX() * 0.1f;
		}

		if (rotation.x > 360)
			rotation.x = 0;
		if (rotation.x < 0)
			rotation.x = 360;
		if (rotation.y > 360)
			rotation.y = 0;
		if (rotation.y < 0)
			rotation.y = 360;
		if (rotation.z > 360)
			rotation.z = 0;
		if (rotation.z < 0)
			rotation.z = 360;

		BasicShader.getInstance().bindShader();
		BasicShader.setViewMatrix(this);
	}

	public void walkForward(float distance) {
		position.x += distance * (float) Math.sin(Math.toRadians(rotation.y));
		position.z += distance * (float) Math.cos(Math.toRadians(rotation.y));
	}

	public void walkBackwards(float distance) {
		position.x -= distance * (float) Math.sin(Math.toRadians(rotation.y));
		position.z -= distance * (float) Math.cos(Math.toRadians(rotation.y));
	}

	public void strafeRight(float distance) {
		position.x += distance * (float) Math.sin(Math.toRadians(rotation.y + 90));
		position.z += distance * (float) Math.cos(Math.toRadians(rotation.y + 90));
	}

	public void strafeLeft(float distance) {
		position.x += distance * (float) Math.sin(Math.toRadians(rotation.y - 90));
		position.z += distance * (float) Math.cos(Math.toRadians(rotation.y - 90));
	}

	public Vector3f getPosition() {
		return position;
	}

	public void setPosition(Vector3f position) {
		this.position = position;
	}

	public Vector3f getRotation() {
		return rotation;
	}

	public void setRotation(Vector3f rotation) {
		this.rotation = rotation;
	}

	public Matrix4f getTransform() {
		Matrix4f position = new Matrix4f().translate(new Vector3f(this.position).negate());
		Matrix4f rotation = new Matrix4f().rotate(this.rotation);

		return rotation.multiply(position);
	}

}