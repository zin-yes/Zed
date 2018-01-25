package zin.zedEngine.graphics.shaders;

import java.util.ArrayList;
import java.util.List;

import zin.zedEngine.graphics.Camera;
import zin.zedEngine.graphics.DirectionalLight;
import zin.zedEngine.graphics.Shader;
import zin.zedEngine.graphics.PointLight;
import zin.zedEngine.math.Matrix4f;
import zin.zedEngine.math.Vector3f;

public class PhongShader extends Shader {

	private static PhongShader shader;
	private static List<PointLight> spotLights;
	private static List<DirectionalLight> directionalLights;

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
		}
		spotLights = new ArrayList<>();

		for (int i = 0; i < 4; i++) {
			addUniform("directionalLightDirection[" + i + "]");
			addUniform("directionalLightColor[" + i + "]");
		}
		directionalLights = new ArrayList<>();

		for (int i = 0; i < 14; i++) {
			setUniform("spotLightPosition[" + i + "]", new Vector3f(0));
			setUniform("spotLightColor[" + i + "]", new Vector3f(0));
			setUniform("spotLightAttenuation[" + i + "]", new Vector3f(1, 0, 0));
		}

		for (int i = 0; i < 4; i++) {
			setUniform("directionalLightDirection[" + i + "]", new Vector3f(0));
			setUniform("directionalLightColor[" + i + "]", new Vector3f(0));
		}

		addUniform("textureMultiplier");
		addUniform("reflectivity");
		addUniform("specularDampening");
		setUniform("reflectivity", 10);
		setUniform("specularDampening", 1);
		setProjectionMatrix(fov, width, height, zNear, zFar);
		setUniform("textureSampler", (int) 0);
		shader.unbindShader();
	}

	public static void setLights(List<PointLight> spotLights, List<DirectionalLight> directionalLights) {
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
		}
		PhongShader.spotLights = spotLights;

		for (int i = 0; i < 4; i++) {
			if (i < directionalLights.size()) {
				setUniform("directionalLightDirection[" + i + "]", directionalLights.get(i).getDirection());
				setUniform("directionalLightColor[" + i + "]", directionalLights.get(i).getColor());
			} else {
				setUniform("directionalLightDirection[" + i + "]", new Vector3f(0));
				setUniform("directionalLightColor[" + i + "]", new Vector3f(0));
			}
		}
		PhongShader.directionalLights = directionalLights;
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

	public static PhongShader getInstance() {
		return shader;
	}

	public static boolean addLight(PointLight spotLight) {
		if (spotLights.size() > 14)
			return false;

		spotLights.add(spotLight);
		
		shader.bindShader();
		setLights(spotLights, directionalLights);
		return true;
	}
	
	public static boolean addLight(DirectionalLight directionalLight) {
		if (directionalLights.size() > 14)
			return false;

		directionalLights.add(directionalLight);

		shader.bindShader();
		setLights(spotLights, directionalLights);
		return true;
	}
	
	public void setTextureMultiplier(float multiplier) {
		setUniform("textureMultiplier", multiplier);
	}

}
