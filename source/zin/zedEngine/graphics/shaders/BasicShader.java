package zin.zedEngine.graphics.shaders;

import zin.zedEngine.graphics.Camera;
import zin.zedEngine.graphics.Shader;
import zin.zedEngine.math.Matrix4f;

public class BasicShader extends Shader {

	private static BasicShader shader;
	
	public BasicShader() {
		super("basic");
	}
	
	public static void init() {
		shader = new BasicShader();

		shader.bindShader();
		addUniform("textureSampler");
		addUniform("transformationMatrix");
		addUniform("projectionMatrix");
		addUniform("viewMatrix");
		setUniform("projectionMatrix", new Matrix4f().perspective(120, 1280, 720, 0.1f, 1000));
		setUniform("textureSampler", (int) 0);
		shader.unbindShader();
	}

	public static void setTransformationMatrix(Matrix4f transformation) {
		setUniform("transformationMatrix", transformation);
	}

	public static void setProjectionMatrix(float fov, int width, int height, float zNear, float zFar) {
		setUniform("projectionMatrix", new Matrix4f().perspective(fov, width, height, zNear, zFar));
		}

	public static void setViewMatrix(Camera camera) {
		setUniform("viewMatrix", camera.getTransform());
	}
	
	public static BasicShader getInstance() {
		return shader;
	}

}
