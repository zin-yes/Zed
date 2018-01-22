package zin.zedEngine.core;

import zin.zedEngine.graphics.Transform;

public abstract class GameComponent {

	public abstract void update(Transform transform);

	public abstract void input(Transform transform);

	public abstract void render(Transform transform);
	
	public abstract void cleanUp();

}
