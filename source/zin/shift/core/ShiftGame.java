package zin.shift.core;

import org.lwjgl.opengl.GL11;

import zin.zedEngine.game.Game;
import zin.zedEngine.graphics.Camera;
import zin.zedEngine.graphics.Display;
import zin.zedEngine.graphics.Entity;
import zin.zedEngine.graphics.Material;
import zin.zedEngine.graphics.Texture;
import zin.zedEngine.math.Vector3f;

public class ShiftGame extends Game {

	private Display display;
	private Entity entity;
	private Material entityMaterial;
	private Camera camera;

	@Override
	public void startGame() {
		display = new Display("display_settings");
		display.createDisplay();
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		entity = new Entity("knuckles", new Vector3f(0, 0, 0), new Vector3f(0, 0, 0), new Vector3f(1, 1, 1));
		camera = new Camera(new Vector3f(0, 0, 0), new Vector3f(0, 0, 0));
		entityMaterial = new Material(new PhongShader(), new Texture("knuckles"));
	}

	@Override
	public void updateGame() {
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		entity.render(entityMaterial, camera);
		display.updateDisplay();
		camera.updateCamera(display);
	}

	@Override
	public void stopGame() {
		display.destroyDisplay();
	}

	@Override
	public boolean shouldClose() {
		return display.shouldClose();
	}

}
