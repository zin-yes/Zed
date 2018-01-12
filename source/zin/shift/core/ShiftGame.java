package zin.shift.core;

import org.lwjgl.opengl.GL11;

import zin.zedEngine.game.Game;
import zin.zedEngine.graphics.Display;
import zin.zedEngine.graphics.Entity;
import zin.zedEngine.graphics.Material;
import zin.zedEngine.graphics.Model;
import zin.zedEngine.math.Vector3f;

public class ShiftGame extends Game {

	private Display display;
	private Entity entity;
	private Material entityMaterial;

	@Override
	public void startGame() {
		display = new Display("display_settings");
		display.createDisplay();
		float[] vertices = new float[] { -1.0f, -1.0f, 0.0f, 1.0f, -1.0f, 0.0f, 0.0f, 1.0f, 0.0f };
		entity = new Entity(new Model(vertices), new Vector3f(0, 0, 0), new Vector3f(0, 0, 0), new Vector3f(1, 1, 1));
		entityMaterial = new Material(new RandomShader(), null);
	}

	@Override
	public void updateGame() {
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
		entity.render(entityMaterial);
		display.updateDisplay();
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
