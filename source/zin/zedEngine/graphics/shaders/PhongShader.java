package zin.zedEngine.graphics.shaders;

import zin.zedEngine.graphics.Shader;
import zin.zedEngine.math.Matrix4f;

public class PhongShader extends Shader {

	private static PhongShader shader;

	public PhongShader() {
		super("phong");
	}

	public static void init(float fov, int width, int height, float zNear, float zFar) {
		shader = new PhongShader();

		shader.bindShader();
		addUniform("transformationMatrix");
		addUniform("projectionMatrix");
		addUniform("viewMatrix");
		addUniform("textureSampler");

		setProjectionMatrix(fov, width, height, zNear, zFar);
		setUniform("textureSampler", (int) 0);
		shader.unbindShader();
	}

	public static void setTransformationMatrix(Matrix4f transformation) {
		setUniform("transformationMatrix", transformation);
	}

	public static void setProjectionMatrix(float fov, int width, int height, float zNear, float zFar) {
		setUniform("projectionMatrix", new Matrix4f().perspective(fov, width, height, zNear, zFar));
	}

	public static void setViewMatrix(Matrix4f view) {
		setUniform("viewMatrix", view);
	}

	public static PhongShader getInstance() {
		return shader;
	}

}
