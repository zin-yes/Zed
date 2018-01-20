package zin.zedEngine.graphics.shaders;

import java.util.List;

import zin.zedEngine.graphics.DirectionalLight;
import zin.zedEngine.graphics.Shader;
import zin.zedEngine.graphics.SpotLight;
import zin.zedEngine.math.Matrix4f;
import zin.zedEngine.math.Vector3f;

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
		for (int i = 0; i < 14; i++) {
			addUniform("spotLightPosition[" + i + "]");
			addUniform("spotLightColor[" + i + "]");
			addUniform("spotLightAttenuation[" + i + "]");
			addUniform("directionalLightDirection[" + i + "]");
			addUniform("directionalLightColor[" + i + "]");
		}
		setProjectionMatrix(fov, width, height, zNear, zFar);
		setUniform("textureSampler", (int) 0);
		shader.unbindShader();
	}

	public static void setLights(List<SpotLight> spotLights, List<DirectionalLight> directionalLights) {
		for (int i = 0; i < 14; i++) {
			if (i < spotLights.size()) {
				setUniform("spotLightPosition[" + i + "]", spotLights.get(i).getPosition());
				setUniform("spotLightColor[" + i + "]", spotLights.get(i).getColor());
				setUniform("spotLightAttenuation[" + i + "]", spotLights.get(i).getAttenuation());
			} else {
				setUniform("spotLightPosition[" + i + "]", new Vector3f(0));
				setUniform("spotLightColor[" + i + "]", new Vector3f(0));
				setUniform("spotLightAttenuation[" + i + "]", new Vector3f(1, 0, 0));
			}
			if (i < directionalLights.size()) {
				setUniform("directionalLightDirection[" + i + "]", directionalLights.get(i).getDirection());
				setUniform("directionalLightColor[" + i + "]", directionalLights.get(i).getColor());
			} else {
				setUniform("directionalLightDirection[" + i + "]", new Vector3f(0));
				setUniform("directionalLightColor[" + i + "]", new Vector3f(0));
			}
		}
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
