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
	private Terrain terrain1;
	private Terrain terrain2;
	private Terrain terrain3;

	@Override
	public void init() {
		camera = new Player(new Vector3f(0, 0, 0), new Vector3f(0, 0, 0));

		terrain = new Terrain(0, 0, "terrain");
		terrain1 = new Terrain(0, -1, "terrain");
		terrain2 = new Terrain(-1, -1, "terrain");
		terrain3 = new Terrain(-1, 0, "terrain");

		ModelRenderer planeRenderer = new ModelRenderer(terrain.getModel(),
				new Material(PhongShader.getInstance(), new Texture("tile")));

		ModelRenderer plane1Renderer = new ModelRenderer(terrain1.getModel(),
				new Material(PhongShader.getInstance(), new Texture("tile")));

		ModelRenderer plane2Renderer = new ModelRenderer(terrain2.getModel(),
				new Material(PhongShader.getInstance(), new Texture("tile")));

		ModelRenderer plane3Renderer = new ModelRenderer(terrain3.getModel(),
				new Material(PhongShader.getInstance(), new Texture("tile")));
		planeRenderer.setTextureMultiplier(0.25f);

		GameObject plane = new GameObject();
		GameObject plane1 = new GameObject();
		GameObject plane2 = new GameObject();
		GameObject plane3 = new GameObject();

		plane.addComponent(planeRenderer);
		plane1.addComponent(plane1Renderer);
		plane2.addComponent(plane2Renderer);
		plane3.addComponent(plane3Renderer);

		GameObject sphere = new GameObject().addComponent(new ModelRenderer(new Model("dragon"),
				new Material(PhongShader.getInstance(), new Texture("concrete"))));
		float x = new Random().nextInt(800);
		float z = new Random().nextInt(800);
		sphere.getTransform().setPosition(new Vector3f(x, terrain.getHeightAt(x, z), z));
		getRootObject().addChild(sphere);

		getRootObject().addComponent(new DirectionalLight(new Vector3f(0, 1, 0), new Vector3f(1, 1, 1)));
		getRootObject().addChild(plane);
		getRootObject().addChild(plane1);
		getRootObject().addChild(plane2);
		getRootObject().addChild(plane3);
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
