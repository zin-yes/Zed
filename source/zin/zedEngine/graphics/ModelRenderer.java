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

}