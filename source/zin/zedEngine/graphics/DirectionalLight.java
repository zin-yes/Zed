package zin.zedEngine.graphics;

import zin.zedEngine.math.Vector3f;

public class DirectionalLight {

	private Vector3f direction, color;
	
	public DirectionalLight(Vector3f direction, Vector3f color) {
		this.direction = direction;
		this.color = color;
	}
	
	public DirectionalLight(Vector3f direction) {
		this.direction = direction;
		color = new Vector3f(1);
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
	
}
