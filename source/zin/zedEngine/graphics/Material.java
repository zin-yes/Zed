package zin.zedEngine.graphics;

import org.lwjgl.opengl.GL13;

import zin.shift.core.PhongShader;
import zin.zedEngine.math.Matrix4f;

public class Material {

	// TODO: Add shader and reflectivity and damping etc..
	private PhongShader shader;
	private Texture texture;

	public Material(PhongShader shader, Texture texture) {
		this.shader = shader;
		this.texture = texture;
	}
	
	public Texture getTexture() {
		return texture;
	}

	public void bindMaterial() {
		texture.bindTexture(GL13.GL_TEXTURE0);
		shader.bindShader();
	}

	public void unbindMaterial() {
		texture.unbindTexture();
		shader.unbindShader();
	}

	public void setTransformation(Matrix4f transform) {
		shader.setTransformationMatrix(transform);
	}

	public void setCamera(Matrix4f transform) {
		shader.setViewMatrix(transform);
	}
	
}
