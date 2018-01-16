package zin.zedEngine.math;

import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.BufferUtils;

public class Matrix4f {

	private float[][] m;

	public Matrix4f(Matrix4f source) {
		source.m = getMatrix();
	}

	public Matrix4f() {
		m = new float[4][4];
	}

	public Matrix4f identity() {
		m[0][0] = 1;
		m[0][1] = 0;
		m[0][2] = 0;
		m[0][3] = 0;
		m[1][0] = 0;
		m[1][1] = 1;
		m[1][2] = 0;
		m[1][3] = 0;
		m[2][0] = 0;
		m[2][1] = 0;
		m[2][2] = 1;
		m[2][3] = 0;
		m[3][0] = 0;
		m[3][1] = 0;
		m[3][2] = 0;
		m[3][3] = 1;

		return this;
	}

	public Matrix4f translate(float x, float y, float z) {
		m[0][0] = 1;
		m[0][1] = 0;
		m[0][2] = 0;
		m[0][3] = x;
		m[1][0] = 0;
		m[1][1] = 1;
		m[1][2] = 0;
		m[1][3] = y;
		m[2][0] = 0;
		m[2][1] = 0;
		m[2][2] = 1;
		m[2][3] = z;
		m[3][0] = 0;
		m[3][1] = 0;
		m[3][2] = 0;
		m[3][3] = 1;

		return this;
	}

	public Matrix4f translate(Vector3f translate) {
		return translate(translate.x, translate.y, translate.z);
	}

	public Matrix4f rotate(float x, float y, float z) {
		Matrix4f rx = new Matrix4f();
		Matrix4f ry = new Matrix4f();
		Matrix4f rz = new Matrix4f();

		x = (float) Math.toRadians(x);
		y = (float) Math.toRadians(y);
		z = (float) Math.toRadians(z);

		rx.m[0][0] = 1;
		rx.m[0][1] = 0;
		rx.m[0][2] = 0;
		rx.m[0][3] = 0;
		rx.m[1][0] = 0;
		rx.m[1][1] = (float) Math.cos(x);
		rx.m[1][2] = -(float) Math.sin(x);
		rx.m[1][3] = 0;
		rx.m[2][0] = 0;
		rx.m[2][1] = (float) Math.sin(x);
		rx.m[2][2] = (float) Math.cos(x);
		rx.m[2][3] = 0;
		rx.m[3][0] = 0;
		rx.m[3][1] = 0;
		rx.m[3][2] = 0;
		rx.m[3][3] = 1;

		ry.m[0][0] = (float) Math.cos(y);
		ry.m[0][1] = 0;
		ry.m[0][2] = -(float) Math.sin(y);
		ry.m[0][3] = 0;
		ry.m[1][0] = 0;
		ry.m[1][1] = 1;
		ry.m[1][2] = 0;
		ry.m[1][3] = 0;
		ry.m[2][0] = (float) Math.sin(y);
		ry.m[2][1] = 0;
		ry.m[2][2] = (float) Math.cos(y);
		ry.m[2][3] = 0;
		ry.m[3][0] = 0;
		ry.m[3][1] = 0;
		ry.m[3][2] = 0;
		ry.m[3][3] = 1;

		rz.m[0][0] = (float) Math.cos(z);
		rz.m[0][1] = -(float) Math.sin(z);
		rz.m[0][2] = 0;
		rz.m[0][3] = 0;
		rz.m[1][0] = (float) Math.sin(z);
		rz.m[1][1] = (float) Math.cos(z);
		rz.m[1][2] = 0;
		rz.m[1][3] = 0;
		rz.m[2][0] = 0;
		rz.m[2][1] = 0;
		rz.m[2][2] = 1;
		rz.m[2][3] = 0;
		rz.m[3][0] = 0;
		rz.m[3][1] = 0;
		rz.m[3][2] = 0;
		rz.m[3][3] = 1;

		m = rx.multiply(ry.multiply(rz)).getMatrix();

		return this;
	}

	public Matrix4f rotate(Vector3f rotate) {
		return rotate(rotate.x, rotate.y, rotate.z);
	}

	public Matrix4f scale(float x, float y, float z) {
		m[0][0] = x;
		m[0][1] = 0;
		m[0][2] = 0;
		m[0][3] = 0;
		m[1][0] = 0;
		m[1][1] = y;
		m[1][2] = 0;
		m[1][3] = 0;
		m[2][0] = 0;
		m[2][1] = 0;
		m[2][2] = z;
		m[2][3] = 0;
		m[3][0] = 0;
		m[3][1] = 0;
		m[3][2] = 0;
		m[3][3] = 1;

		return this;
	}

	public Matrix4f scale(Vector3f scale) {
		return scale(scale.x, scale.y, scale.z);
	}

	public Matrix4f perspective(float fov, float width, float height, float zNear, float zFar) {
		float tanHalfFOV = (float) Math.tan(fov / 2);
		float zRange = zNear - zFar;

		m[0][0] = 1.0f / (tanHalfFOV * width / height);
		m[0][1] = 0;
		m[0][2] = 0;
		m[0][3] = 0;
		m[1][0] = 0;
		m[1][1] = 1.0f / tanHalfFOV;
		m[1][2] = 0;
		m[1][3] = 0;
		m[2][0] = 0;
		m[2][1] = 0;
		m[2][2] = (-zNear - zFar) / zRange;
		m[2][3] = 2 * zFar * zNear / zRange;
		m[3][0] = 0;
		m[3][1] = 0;
		m[3][2] = 1;
		m[3][3] = 0;

		return this;
	}

	public Matrix4f multiply(Matrix4f other) {
		Matrix4f res = new Matrix4f();

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				res.setMatrix(i, j, m[i][0] * other.getMatrix(0, j) + m[i][1] * other.getMatrix(1, j)
						+ m[i][2] * other.getMatrix(2, j) + m[i][3] * other.getMatrix(3, j));
			}
		}

		return res;
	}

	public float getMatrix(int x, int y) {
		return m[x][y];
	}

	public void setMatrix(int x, int y, float value) {
		m[x][y] = value;
	}

	public float[] getMatrixArray() {
		List<Float> list = new ArrayList<>();

		for (int i = 0; i < 4; i++)
			for (int j = 0; j < 4; j++)
				list.add(m[i][j]);

		float[] result = new float[16];

		for (int i = 0; i < 16; i++)
			result[i] = list.get(i);

		return result;
	}

	public FloatBuffer getMatrixBuffer() {
		FloatBuffer result = BufferUtils.createFloatBuffer(16);

		result.put(getMatrixArray());
		result.flip();

		return result;
	}

	public float[][] getMatrix() {
		return m;
	}

	public void setMatrix(float[][] matrix) {
		m = matrix;
	}

}
