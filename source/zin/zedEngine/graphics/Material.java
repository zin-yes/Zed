package zin.zedEngine.graphics;

import org.lwjgl.opengl.GL13;

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
	
}
