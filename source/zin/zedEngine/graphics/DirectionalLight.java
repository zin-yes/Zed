package zin.zedEngine.graphics;

import zin.zedEngine.core.GameComponent;
import zin.zedEngine.graphics.shaders.PhongShader;
import zin.zedEngine.math.Vector3f;

public class DirectionalLight extends GameComponent {

	private Vector3f direction, color;
	
	public DirectionalLight(Vector3f direction, Vector3f color) {
		this.direction = direction;
		this.color = color;
		if(!PhongShader.addLight(this))
			System.out.println("Too many lights failed to add light: " + direction);
	}
	
	public DirectionalLight(Vector3f direction) {
		this.direction = direction;
		color = new Vector3f(1);
		if(!PhongShader.addLight(this))
			System.out.println("Too many lights failed to add light: " + direction);
	}

	@Override
	public void init(Transform transform) {
		
	}

	public Vector3f getDirection() {
		return direction;
	}

	public void setDirection(Vector3f direction) {
		this.direction = direction;
	}

	public Vector3f getColor() {
		return color;
	}

	public void setColor(Vector3f color) {
		this.color = color;
	}

	@Override
	public void update(Transform transform) {
		
	}

	@Override
	public void input(Transform transform) {
		
	}

	@Override
	public void render(Transform transform) {
		
	}

	@Override
	public void cleanUp() {
		
	}
	
}
