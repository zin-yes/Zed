package zin.zedEngine.graphics;

public class Model {

	private Material material;
	private int vaoID, vertexCount;

	public Model(int vaoID, int vertexCount) {
		this.vaoID = vaoID;
		this.vertexCount = vertexCount;
	}
	
	public Texture getTexture() {
		return material.getTexture();
	}
	
	public int getTextureID() {
		return getTexture().getTextureID();
	}
	
	public int getVaoID() {
		return vaoID;
	}
	
	public int getVertexCount() {
		return vertexCount;
	}
	
}
