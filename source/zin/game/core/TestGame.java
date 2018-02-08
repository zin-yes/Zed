package zin.game.core;

import java.util.Random;

import org.lwjgl.glfw.GLFW;

import zin.game.player.Player;
import zin.game.player.Terrain;
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
	private Terrain terrain;

	@Override
	public void init() {
		camera = new Player(new Vector3f(0, 0, 0), new Vector3f(0, 0, 0));

		terrain = new Terrain(0, 0, "terrain");

		ModelRenderer planeRenderer = new ModelRenderer(terrain.getModel(),
				new Material(PhongShader.getInstance(), new Texture("tile")));

		planeRenderer.setTextureMultiplier(0.25f);

		GameObject plane = new GameObject();

		plane.addComponent(planeRenderer);

		GameObject sphere = new GameObject().addComponent(new ModelRenderer(new Model("dragon"),
				new Material(PhongShader.getInstance(), new Texture("concrete"))));
		float x = new Random().nextInt(800);
		float z = new Random().nextInt(800);
		sphere.getTransform().setPosition(new Vector3f(x, terrain.getHeightAt(x, z), z));
		getRootObject().addChild(sphere);

		getRootObject().addComponent(new DirectionalLight(new Vector3f(0, 1, 0), new Vector3f(1, 1, 1)));
		getRootObject().addChild(plane);
	}

	@Override
	public void update() {
		camera.updateCamera(terrain);
	}

	@Override
	public void input() {
		if (Input.isKeyDown(GLFW.GLFW_KEY_G))
			if (camera.getRotation().round() != new Vector3f()) {
				camera.setRotation(camera.getRotation().lerp(new Vector3f(), 0.2f));
				System.out.println(camera.getRotation().round());
			}
	}

	@Override
	public void render() {

	}

}
