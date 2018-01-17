package zin.game.core;

import zin.zedEngine.graphics.Light;
import zin.zedEngine.graphics.Shader;
import zin.zedEngine.math.Matrix4f;

public class PhongShader extends Shader {

	public PhongShader() {
		super("phong");

		bindShader();
		addUniform("textureSampler");
		addUniform("transformationMatrix");
		addUniform("sunPosition");
		addUniform("sunColor");
		addUniform("sunAttenuation");
		addUniform("viewMatrix");
		addUniform("projectionMatrix");
		setUniform("projectionMatrix", new Matrix4f().perspective(120, 1280, 720, 0.1f, 1000f));
		setUniform("textureSampler", (int) 0);
		unbindShader();
	}

	public void setTransformationMatrix(Matrix4f transformation) {
		setUniform("transformationMatrix", transformation);
	}

	public void setProjectionMatrix(Matrix4f projection) {
		setUniform("projectionMatrix", projection);
	}

	public void setViewMatrix(Matrix4f view) {
		setUniform("viewMatrix", view);
	}

	public void setSunLight(Light light) {
		setUniform("sunPosition", light.getPosition());
		setUniform("sunColor", light.getColor());
		setUniform("sunAttenuation", light.getAttenuation());
	}

}
