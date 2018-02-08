package zin.game.player;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import zin.zedEngine.graphics.Model;
import zin.zedEngine.math.Vector2f;
import zin.zedEngine.math.Vector3f;

public class Terrain {

	private static final float SIZE = 800;

	private float x;
	private float z;
	private Model model;

	private float[][] heights;

	public Terrain(int gridX, int gridZ, String heightMap) {
		this.x = gridX * SIZE;
		this.z = gridZ * SIZE;
		this.model = generateTerrain(heightMap);
	}

	private Model generateTerrain(String heightMap) {
		BufferedImage heightMapImage = null;

		try {
			heightMapImage = ImageIO.read(Class.class.getResourceAsStream("/heightmaps/" + heightMap + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		int VERTEX_COUNT = heightMapImage.getHeight();
		heights = new float[VERTEX_COUNT][VERTEX_COUNT];

		int count = VERTEX_COUNT * VERTEX_COUNT;
		float[] vertices = new float[count * 3];
		float[] normals = new float[count * 3];
		float[] textureCoords = new float[count * 2];
		int[] indices = new int[6 * (VERTEX_COUNT - 1) * (VERTEX_COUNT - 1)];
		int vertexPointer = 0;
		for (int i = 0; i < VERTEX_COUNT; i++) {
			for (int j = 0; j < VERTEX_COUNT; j++) {
				vertices[vertexPointer * 3] = (float) j / ((float) VERTEX_COUNT - 1) * SIZE;
				float height = getHeight(j, i, heightMapImage);
				heights[j][i] = height;
				vertices[vertexPointer * 3 + 1] = height;
				vertices[vertexPointer * 3 + 2] = (float) i / ((float) VERTEX_COUNT - 1) * SIZE;
				normals[vertexPointer * 3] = 0;
				normals[vertexPointer * 3 + 1] = 1;
				normals[vertexPointer * 3 + 2] = 0;
				textureCoords[vertexPointer * 2] = (float) j / ((float) VERTEX_COUNT - 1);
				textureCoords[vertexPointer * 2 + 1] = (float) i / ((float) VERTEX_COUNT - 1);
				vertexPointer++;
			}
		}
		int pointer = 0;
		for (int gz = 0; gz < VERTEX_COUNT - 1; gz++) {
			for (int gx = 0; gx < VERTEX_COUNT - 1; gx++) {
				int topLeft = (gz * VERTEX_COUNT) + gx;
				int topRight = topLeft + 1;
				int bottomLeft = ((gz + 1) * VERTEX_COUNT) + gx;
				int bottomRight = bottomLeft + 1;
				indices[pointer++] = topLeft;
				indices[pointer++] = bottomLeft;
				indices[pointer++] = topRight;
				indices[pointer++] = topRight;
				indices[pointer++] = bottomLeft;
				indices[pointer++] = bottomRight;
			}
		}
		return new Model(vertices, textureCoords, normals, indices);
	}

	public float getHeightAt(float x, float z) {
		float terrainX = x - this.x;
		float terrainZ = z - this.z;

		float gridSquare = SIZE / ((float) heights.length - 1);

		int gridX = (int) Math.floor(terrainX / gridSquare);
		int gridZ = (int) Math.floor(terrainZ / gridSquare);

		if (gridX >= heights.length - 1 || gridX < 0 || gridZ >= heights.length - 1 || gridZ < 0)
			return 0;

		float xCoord = (terrainX % gridSquare) / gridSquare;
		float zCoord = (terrainZ % gridSquare) / gridSquare;
		
		if (xCoord <= (1-zCoord)) {
			return barryCentric(new Vector3f(0, heights[gridX][gridZ], 0), new Vector3f(1,
							heights[gridX + 1][gridZ], 0), new Vector3f(0,
							heights[gridX][gridZ + 1], 1), new Vector2f(xCoord, zCoord));
		} else {
			return barryCentric(new Vector3f(1, heights[gridX + 1][gridZ], 0), new Vector3f(1,
							heights[gridX + 1][gridZ + 1], 1), new Vector3f(0,
							heights[gridX][gridZ + 1], 1), new Vector2f(xCoord, zCoord));
		}
	}
	
	private float barryCentric(Vector3f p1, Vector3f p2, Vector3f p3, Vector2f pos) {
		float det = (p2.z - p3.z) * (p1.x - p3.x) + (p3.x - p2.x) * (p1.z - p3.z);
		float l1 = ((p2.z - p3.z) * (pos.x - p3.x) + (p3.x - p2.x) * (pos.y - p3.z)) / det;
		float l2 = ((p3.z - p1.z) * (pos.x - p3.x) + (p1.x - p3.x) * (pos.y - p3.z)) / det;
		float l3 = 1.0f - l1 - l2;
		return l1 * p1.y + l2 * p2.y + l3 * p3.y;
	}

	public float getHeight(int x, int z, BufferedImage heightMapImage) {
		if (x < 0 || x > heightMapImage.getHeight() || z < 0 || z > heightMapImage.getHeight())
			return 0;

		float height = heightMapImage.getRGB(x, z);

		height += (256 * 256 * 256) / 2;
		height /= (256 * 256 * 256) / 2;
		height *= 80;

		return height;
	}

	public float getX() {
		return x;
	}

	public float getZ() {
		return z;
	}

	public Model getModel() {
		return model;
	}

}
