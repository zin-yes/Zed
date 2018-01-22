package zin.zedEngine.graphics;

import zin.zedEngine.math.Matrix4f;
import zin.zedEngine.math.Vector3f;

public class Transform {

	private Vector3f position, rotation, scale;

	public Transform(Vector3f position, Vector3f rotation, Vector3f scale) {
		this.position = position;
		this.rotation = rotation;
		this.scale = scale;
	}

	public Transform() {
		position = new Vector3f();
		rotation = new Vector3f();
		scale = new Vector3f(1);
	}

	public Vector3f getPosition() {
		return position;
	}

	public void setPosition(Vector3f position) {
		this.position = position;
	}

	public Vector3f getRotation() {
		return rotation;
	}

	public void setRotation(Vector3f rotation) {
		this.rotation = rotation;
	}

	public Vector3f getScale() {
		return scale;
	}

	public void setScale(Vector3f scale) {
		this.scale = scale;
	}

	public Matrix4f getTransformationMatrix() {
		Matrix4f position = new Matrix4f().translate(new Vector3f(this.position).negate());
		Matrix4f rotation = new Matrix4f().rotate(this.rotation);
		Matrix4f scale = new Matrix4f().scale(this.scale);

		return position.multiply(rotation).multiply(scale);
	}

}
