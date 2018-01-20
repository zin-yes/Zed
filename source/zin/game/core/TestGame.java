package zin.game.core;

import zin.zedEngine.core.Game;
import zin.zedEngine.graphics.Camera;
import zin.zedEngine.graphics.Material;
import zin.zedEngine.graphics.Model;
import zin.zedEngine.graphics.ModelRenderer;
import zin.zedEngine.graphics.Texture;
import zin.zedEngine.graphics.shaders.PhongShader;

public class TestGame extends Game {

	private Camera camera;

	@Override
	public void init() {
		camera = new Camera();
		
		getRootObject().addComponent(new ModelRenderer(new Model("dragon"),
				new Material(PhongShader.getInstance(), new Texture("concrete"))));
	}

	@Override
	public void update() {
		
	}

	@Override
	public void input() {
		camera.updateCamera();
	}

	@Override
	public void render() {
		
	}

	@Override
	public void cleanUp() {
		
	}

}
