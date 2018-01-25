package zin.game.core;

import zin.zedEngine.core.Game;
import zin.zedEngine.graphics.Camera;
import zin.zedEngine.graphics.Material;
import zin.zedEngine.graphics.Model;
import zin.zedEngine.graphics.ModelRenderer;
import zin.zedEngine.graphics.PointLight;
import zin.zedEngine.graphics.Texture;
import zin.zedEngine.graphics.shaders.PhongShader;
import zin.zedEngine.math.Vector3f;

public class TestGame extends Game {

	private Camera camera;

	@Override
	public void init() {
		camera = new Camera(new Vector3f(), new Vector3f());

		getRootObject().addComponent(new PointLight(new Vector3f(0, 100, 0)));
		ModelRenderer modelRenderer = new ModelRenderer(new Model("sphere"),
				new Material(PhongShader.getInstance(), new Texture("concrete")));
		modelRenderer.setTextureMultiplier(0.11f);
		getRootObject().addComponent(modelRenderer);
	}

	@Override
	public void update() {
		camera.updateCamera();
	}

	@Override
	public void input() {
		
	}

	@Override
	public void render() {

	}

}
