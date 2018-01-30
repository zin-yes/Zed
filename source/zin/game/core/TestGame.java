package zin.game.core;

import org.lwjgl.glfw.GLFW;

import zin.game.player.Player;
import zin.zedEngine.core.Game;
import zin.zedEngine.core.GameObject;
import zin.zedEngine.graphics.DirectionalLight;
import zin.zedEngine.graphics.Input;
import zin.zedEngine.graphics.Material;
import zin.zedEngine.graphics.Model;
import zin.zedEngine.graphics.ModelRenderer;
import zin.zedEngine.graphics.Texture;
import zin.zedEngine.graphics.shaders.PhongShader;
import zin.zedEngine.math.Vector3f;

public class TestGame extends Game {

	private Player camera;

	@Override
	public void init() {
		camera = new Player(new Vector3f(0, 0, 0), new Vector3f(0, 0, 0));

		ModelRenderer planeRenderer = new ModelRenderer(new Model("plane"),
				new Material(PhongShader.getInstance(), new Texture("tile")));
		planeRenderer.setTextureMultiplier(0.2f);

		GameObject plane = new GameObject();

		plane.addComponent(planeRenderer);

		getRootObject().addComponent(new DirectionalLight(new Vector3f(0, 1, 0)));
		getRootObject().addChild(plane);
	}

	@Override
	public void update() {
		camera.updateCamera();
	}

	@Override
	public void input() {
		if (Input.isKeyDown(GLFW.GLFW_KEY_G))
			if(camera.getRotation().round() != new Vector3f()) {
				camera.setRotation(camera.getRotation().lerp(new Vector3f(), 0.2f));
				System.out.println(camera.getRotation().round());
			}
	}

	@Override
	public void render() {

	}

}
