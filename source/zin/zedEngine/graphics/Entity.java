package zin.zedEngine.graphics;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import zin.zedEngine.math.Matrix4f;
import zin.zedEngine.math.Vector3f;

public class Entity {

	private Model model;
	private Vector3f position, rotation, scale;

	public Entity(String fileName, Vector3f position, Vector3f rotation, Vector3f scale) {
		this.model = new Model(fileName);
		this.position = position;
		this.rotation = rotation;
		this.scale = scale;
	}

	public Entity(Model model, Vector3f position, Vector3f rotation, Vector3f scale) {
		this.model = model;
		this.position = position;
		this.rotation = rotation;
		this.scale = scale;
	}

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
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

	public Matrix4f getTransform() {
		Matrix4f position = new Matrix4f().translate(this.position);
		Matrix4f rotation = new Matrix4f().rotate(this.rotation);
		Matrix4f scale = new Matrix4f().scale(this.scale);

		return position.multiply(rotation).multiply(scale);
	}

	public void render(Material material, Camera camera, Light light) {
		material.bindMaterial();
		GL30.glBindVertexArray(model.getVaoID());
		GL20.glEnableVertexAttribArray(0);
		GL20.glEnableVertexAttribArray(1);
		GL20.glEnableVertexAttribArray(2);
		material.setTransformation(getTransform());
		material.setCamera(camera.getTransform());
		material.setSunLight(light);
		GL11.glDrawElements(GL11.GL_TRIANGLES, model.getVertexCount(), GL11.GL_UNSIGNED_INT, 0);
		GL20.glDisableVertexAttribArray(0);
		GL20.glDisableVertexAttribArray(1);
		GL20.glDisableVertexAttribArray(2);
		GL30.glBindVertexArray(0);
		material.unbindMaterial();
	}

}
