package zin.zedEngine.graphics;

import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

public class Model {

	private Material material;
	private int vaoID, vertexCount;

	public Model(float[] vertices) {
		vaoID = GL30.glGenVertexArrays();
		vertexCount = vertices.length / 3;
		GL30.glBindVertexArray(vaoID);
		int vboID = GL15.glGenBuffers();
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboID);
		FloatBuffer buffer = BufferUtils.createFloatBuffer(vertices.length);
		buffer.put(vertices);
		buffer.flip();
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
		GL20.glVertexAttribPointer(0, 3, GL11.GL_FLOAT, false, 0, 0);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
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
