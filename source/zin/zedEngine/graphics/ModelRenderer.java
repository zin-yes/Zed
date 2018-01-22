package zin.zedEngine.graphics;

import zin.zedEngine.core.GameComponent;

public class ModelRenderer extends GameComponent {

	private Model model;
	private Material material;

	public ModelRenderer(Model model, Material material) {
		this.model = model;
		this.material = material;
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
