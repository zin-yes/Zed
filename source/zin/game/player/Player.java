package zin.game.player;

import org.lwjgl.glfw.GLFW;

import zin.zedEngine.graphics.Camera;
import zin.zedEngine.graphics.Input;
import zin.zedEngine.graphics.shaders.PhongShader;
import zin.zedEngine.math.Vector3f;

public class Player extends Camera {

	public Player(Vector3f position, Vector3f rotation) {
		super(position, rotation);
	}

	private static final float MOVE_SPEED = 0.2f;
	private static final float JUMP_HEIGHT = 0.5f;
	private static final float CROUCH_LENGTH = 4f;

	@Override
	public void updateCamera(Terrain terrain) {
		if (Input.isKeyDown(GLFW.GLFW_KEY_F1))
			Input.setMouseGrabbed(true);
		if (Input.isKeyDown(GLFW.GLFW_KEY_F2))
			Input.setMouseGrabbed(false);

		if (Input.getMouseGrabbed()) {
			rotation.x -= Input.getMouseDeltaY() * 0.1f;
			rotation.y += Input.getMouseDeltaX() * 0.1f;
		}

		if (terrain != null) {
			if (position.y < terrain.getHeightAt(position.x, position.z) + 4) {
				position.y = terrain.getHeightAt(position.x, position.z) + 4;
				if (Input.isKeyDown(GLFW.GLFW_KEY_SPACE))
					velocity.y = JUMP_HEIGHT;
			} else {
				velocity.y += -0.02f;
			}
		} else {
			if (position.y < 0 + 4) {
				position.y = 0 + 4;
				if (Input.isKeyDown(GLFW.GLFW_KEY_SPACE))
					velocity.y = JUMP_HEIGHT;
			} else {
				velocity.y += -0.02f;
			}
		}

		position.y += velocity.y;

		if (Input.isKeyDown(GLFW.GLFW_KEY_W))
			walkForward(MOVE_SPEED);
		if (Input.isKeyDown(GLFW.GLFW_KEY_A))
			strafeRight(MOVE_SPEED);
		if (Input.isKeyDown(GLFW.GLFW_KEY_S))
			walkBackwards(MOVE_SPEED);
		if (Input.isKeyDown(GLFW.GLFW_KEY_D))
			strafeLeft(MOVE_SPEED);
		if (Input.isKeyDown(GLFW.GLFW_KEY_LEFT_SHIFT))
			position.y -= CROUCH_LENGTH;

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

		position.x += velocity.x;
		position.z += velocity.z;

		velocity = velocity.lerp(new Vector3f(0, velocity.y, 0), 0.2f);

		PhongShader.getInstance().bindShader();
		PhongShader.setViewMatrix(this);
	}

	public void walkForward(float distance) {
		velocity.x = distance * (float) Math.sin(Math.toRadians(rotation.y));
		velocity.z = distance * (float) Math.cos(Math.toRadians(rotation.y));
	}

	public void walkBackwards(float distance) {
		velocity.x = distance * (float) Math.sin(Math.toRadians(rotation.y - 180));
		velocity.z = distance * (float) Math.cos(Math.toRadians(rotation.y - 180));
	}

	public void strafeLeft(float distance) {
		velocity.x = distance * (float) Math.sin(Math.toRadians(rotation.y + 90));
		velocity.z = distance * (float) Math.cos(Math.toRadians(rotation.y + 90));
	}

	public void strafeRight(float distance) {
		velocity.x = distance * (float) Math.sin(Math.toRadians(rotation.y - 90));
		velocity.z = distance * (float) Math.cos(Math.toRadians(rotation.y - 90));
	}

}
