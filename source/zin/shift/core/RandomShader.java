package zin.shift.core;

import zin.zedEngine.graphics.Shader;

public class RandomShader extends Shader {

	public RandomShader() {
		super("test");

//		addUniform("model");
//		addUniform("project");
//		addUniform("view");
		addUniform("textureSampler");
		setUniform("textureSampler", (int) 0);
	}
	
	

}
