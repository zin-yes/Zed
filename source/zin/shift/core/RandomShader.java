package zin.shift.core;

import org.lwjgl.opengl.GL20;

import zin.zedEngine.graphics.Shader;

public class RandomShader extends Shader {

	public RandomShader() {
		super("test");
	}

	@Override
	protected void getAllUniformLocations() {
		
	}

	@Override
	protected void bindAttributes() {
		GL20.glBindAttribLocation(super.programID, 0, "position");
	}

}
