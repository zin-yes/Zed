package zin.zedEngine.core;

public abstract class Game {

	private GameObject root;
	
	public abstract void init();

	public abstract void update();

	public abstract void input();
	
	public abstract void render();

	public abstract void cleanUp();
	
	public GameObject getRootObject() {
		if(root== null)
			root = new GameObject();
		
		return root;
	}

}
