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
	private Camera camera;

	@Override
	public void init() {
		root = new GameObject();
		camera = new Camera();
		
		Material material = new Material(BasicShader.getInstance(), new Texture("concrete"));
		Model model = new Model("dragon");
		
		ModelRenderer renderer = new ModelRenderer(model, material);
		root.addComponent(renderer);
	}

	@Override
	public void update() {
		root.update();
		camera.updateCamera();
	}

	@Override
	public void input() {
		root.input();
	}

	@Override
	public void render() {
		root.render();
	}

}
