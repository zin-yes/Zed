package zin.game.core;

import zin.zedEngine.game.Game;
import zin.zedEngine.graphics.Camera;
import zin.zedEngine.graphics.Display;
import zin.zedEngine.graphics.Entity;
import zin.zedEngine.graphics.Light;
import zin.zedEngine.graphics.Material;
import zin.zedEngine.graphics.Texture;
import zin.zedEngine.math.Vector3f;

public class TestGame extends Game {

	private static Display display;
	private static Entity entity;
	private static Light light;
	private static Material material;
	private static Camera camera;

	@Override
	public void startGame(Display display) {
		TestGame.display = display;

		display.setGrabbed(true);
		material = new Material(new PhongShader(), new Texture("concrete"));
		light = new Light(new Vector3f(0, 10, 0), new Vector3f(1), new Vector3f(1, 0, 0));
		camera = new Camera(new Vector3f(0, 0, 0), new Vector3f(1));
		entity = new Entity("knuckles", new Vector3f(), new Vector3f(), new Vector3f(1));
	}

	@Override
	public void updateGame() {
		camera.updateCamera(display);
	}

	@Override
	public void renderGame() {
		entity.render(material, camera, light);
	}

	@Override
	public void stopGame() {

	}

	@Override
	public void cleanUp() {

	}

	public static Display getDisplay() {
		return display;
	}

}
