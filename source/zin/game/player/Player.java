package zin.game.player;

import org.lwjgl.glfw.GLFW;

import zin.zedEngine.graphics.Camera;
import zin.zedEngine.graphics.Input;
import zin.zedEngine.graphics.shaders.BasicShader;
import zin.zedEngine.math.Vector3f;

public class Player extends Camera {

	public Player(Vector3f position, Vector3f rotation) {
		super(position, rotation);
	}

	@Override
	public void updateCamera() {
		if (Input.isKeyDown(GLFW.GLFW_KEY_F1))
			Input.setMouseGrabbed(true);
		if (Input.isKeyDown(GLFW.GLFW_KEY_F2))
			Input.setMouseGrabbed(false);

		if (Input.getMouseGrabbed()) {
			rotation.x -= Input.getMouseDeltaY() * 0.1f;
			rotation.y += Input.getMouseDeltaX() * 0.1f;
		}

		if (Input.isKeyDown(GLFW.GLFW_KEY_W))
			walkForward(0.2f);
		if (Input.isKeyDown(GLFW.GLFW_KEY_A))
			strafeLeft(0.2f);
		if (Input.isKeyDown(GLFW.GLFW_KEY_S))
			walkBackwards(0.2f);
		if (Input.isKeyDown(GLFW.GLFW_KEY_D))
			strafeRight(0.2f);
		if (Input.isKeyDown(GLFW.GLFW_KEY_LEFT_SHIFT))
			position.y -= 0.2f;
		if (Input.isKeyDown(GLFW.GLFW_KEY_SPACE))
			position.y += 0.2f;

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

}
