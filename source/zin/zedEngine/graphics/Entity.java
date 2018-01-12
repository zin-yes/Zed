package zin.zedEngine.graphics;

import zin.zedEngine.math.Vector3f;

public class Entity {
	
	private Model model;
	private Vector3f position, rotation, scale;
	
	public Entity(Model model, Vector3f position, Vector3f rotation, Vector3f scale) {
		this.model = model;
		this.position = position;
		this.rotation = rotation;
		this.scale = scale;
	}
	
}
