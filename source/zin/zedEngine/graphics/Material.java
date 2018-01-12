package zin.zedEngine.graphics;

public class Material {

	// TODO: Add shader and reflectivity and damping etc..
	private Texture texture;

	public Material(Texture texture) {
		this.texture = texture;
	}
	
	public Texture getTexture() {
		return texture;
	}
	
}
