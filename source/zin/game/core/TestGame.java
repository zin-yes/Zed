package zin.game.core;

import zin.zedEngine.core.Game;
import zin.zedEngine.graphics.Camera;
import zin.zedEngine.graphics.DirectionalLight;
import zin.zedEngine.graphics.components.RenderComponent;
import zin.zedEngine.graphics.shaders.PhongShader;
import zin.zedEngine.math.Vector3f;

public class TestGame extends Game {

	private Camera camera;

	@Override
	public void init() {
		camera = new Camera(new Vector3f(0, 0, 0), new Vector3f(0, 0, 0));

		getRootObject().addComponent(new RenderComponent("sphere", PhongShader.getInstance(), "concrete"));
		
		getRootObject().addComponent(new DirectionalLight(new Vector3f(0, 1, 0), new Vector3f(1, 1, 1)));
	}

	@Override
	public void update() {
		camera.updateCamera(null);
	}

	@Override
	public void input() {
		
	}

	@Override
	public void render() {

	}

}
