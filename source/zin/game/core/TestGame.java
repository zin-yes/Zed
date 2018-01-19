package zin.game.core;

import zin.zedEngine.core.Game;
import zin.zedEngine.core.GameObject;
import zin.zedEngine.graphics.Camera;
import zin.zedEngine.graphics.Material;
import zin.zedEngine.graphics.Model;
import zin.zedEngine.graphics.ModelRenderer;
import zin.zedEngine.graphics.Texture;
import zin.zedEngine.graphics.shaders.BasicShader;

public class TestGame extends Game {

	private GameObject root;
	private GameObject object;
	private Camera camera;

	@Override
	public void init() {
		root = new GameObject();
		camera = new Camera();

		object = new GameObject();
		object.addComponent(new ModelRenderer(new Model("dragon"),
				new Material(BasicShader.getInstance(), new Texture("concrete"))));

		root.addChild(object);
	}

	@Override
	public void update() {
		root.update();
	}

	@Override
	public void input() {
		camera.updateCamera();
		root.input();
	}

	@Override
	public void render() {
		root.render();
	}

	@Override
	public void cleanUp() {
		root.cleanUp();
	}

}
