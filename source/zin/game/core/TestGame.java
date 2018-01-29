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
import zin.zedEngine.physics.BoundingSphere;

public class TestGame extends Game {

	private Player camera;

	private GameObject plane;
	private GameObject sphere;

	private ModelRenderer sphereRenderer;
	private BoundingSphere boundingSphere;
	private BoundingSphere cameraSphere;

	@Override
	public void init() {
		camera = new Player(new Vector3f(0, 100, 0), new Vector3f(0, 0, 0));
		
		ModelRenderer planeRenderer = new ModelRenderer(new Model("plane"),
				new Material(PhongShader.getInstance(), new Texture("tile")));
		planeRenderer.setTextureMultiplier(0.05f);

		sphereRenderer = new ModelRenderer(new Model("sphere"),
				new Material(PhongShader.getInstance(), new Texture("concrete")));
		boundingSphere = new BoundingSphere(new Vector3f(), 1);
		cameraSphere = new BoundingSphere(new Vector3f(camera.getPosition()), 0.1f);

		plane = new GameObject();

		sphere = new GameObject();
		sphere.addComponent(sphereRenderer);

		sphere.getTransform().setPosition(new Vector3f(0, 2, 0));

		plane.addComponent(planeRenderer);

		plane.getTransform().setScale(new Vector3f(5));

		getRootObject().addComponent(new DirectionalLight(new Vector3f(1, 5f, 0)));
		getRootObject().addChild(sphere);
		getRootObject().addChild(plane);
	}

	@Override
	public void update() {
		camera.updateCamera();
		boundingSphere.setCenter(sphere.getTransform().getPosition());
		cameraSphere.setCenter(new Vector3f(camera.getPosition()));
		if (boundingSphere.getIntersection(cameraSphere).isIntersecting()) {
			System.out.println(boundingSphere.getIntersection(cameraSphere).getPenetrationDistance());
		}
	}

	@Override
	public void input() {
		if(Input.isKeyDown(GLFW.GLFW_KEY_G))
			camera.setRotation(camera.getRotation().lerp(new Vector3f(), 0.02f));
	}

	@Override
	public void render() {

	}

}
