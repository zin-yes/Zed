package zin.zedEngine.graphics.components;

import zin.zedEngine.core.GameComponent;
import zin.zedEngine.graphics.Material;
import zin.zedEngine.graphics.Model;
import zin.zedEngine.graphics.Shader;
import zin.zedEngine.graphics.Texture;
import zin.zedEngine.graphics.Transform;

public class RenderComponent extends GameComponent {

	private Model model;
	private Material material;

	public RenderComponent(String modelName, Shader shader, String textureName) {
		this.model = new Model(modelName);
		this.material = new Material(shader, new Texture(textureName));
	}

	@Override
	public void init(Transform transform) {
		
	}
	
	@Override
	public void render(Transform transform) {
		material.bindMaterial();
		material.updateUniforms(transform);
		model.render();
	}

	@Override
	public void update(Transform transform) {
		
	}

	@Override
	public void input(Transform transform) {
		
	}
	
	@Override
	public void cleanUp() {
		material.cleanUp();
		model.cleanUp();
	}

	public void setTextureMultiplier(float multiplier) {
		material.setTextureMultiplier(multiplier);
	}
	
}
