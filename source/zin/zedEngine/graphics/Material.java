package zin.zedEngine.graphics;

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
//		texture.bindTexture();
		shader.bindShader();
	}

	public void unbindMaterial() {
//		texture.unbindTexture();
		shader.unbindShader();
	}
	
}
