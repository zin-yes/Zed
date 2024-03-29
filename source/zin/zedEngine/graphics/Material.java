package zin.zedEngine.graphics;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;

import zin.zedEngine.graphics.shaders.PhongShader;

public class Material {

	// TODO: Add shader and reflectivity and damping etc..
	private Shader shader;
	private Texture texture;

	public Material(Shader shader, Texture texture) {
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

	public void updateUniforms(Transform transform) {
		shader.updateUniforms(transform.getTransformationMatrix());
	}

	public void cleanUp() {
		GL11.glDeleteTextures(texture.getTextureID());
		shader.cleanUp();
	}

	public void setTextureMultiplier(float multiplier) {
		shader.bindShader();
		try {
			((PhongShader) shader).setTextureMultiplier(multiplier);
		} catch (Exception e) {
			System.err.println("Can't apply texture multiplier!");
		}
	}

}
