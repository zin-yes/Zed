package zin.zedEngine.core;

import java.util.ArrayList;
import java.util.List;

import zin.zedEngine.graphics.Transform;

public class GameObject {

	private List<GameObject> children;
	private List<GameComponent> components;
	
	private Transform transform;

	public GameObject() {
		children = new ArrayList<>();
		components = new ArrayList<>();
		transform = new Transform();
	}

	public GameObject addChild(GameObject child) {
		children.add(child);
		return this;
	}

	public GameObject addComponent(GameComponent component) {
		components.add(component);
		return this;
	}

	public void init() {
		for (GameComponent component : components)
			component.init(transform);
		
		for (GameObject child : children)
			child.init();
	}

	public void input() {
		for (GameComponent component : components)
			component.input(transform);
		
		for (GameObject child : children)
			child.input();
	}

	public void update() {
		for (GameComponent component : components)
			component.update(transform);
		
		for (GameObject child : children)
			child.update();
	}

	public void render() {
		for (GameComponent component : components)
			component.render(transform);
		
		for (GameObject child : children)
			child.render();
	}
	
	public void cleanUp() {
		for(GameComponent component : components)
			component.cleanUp();

		for (GameObject child : children)
			child.cleanUp();
	}
	
	public Transform getTransform() {
		return transform;
	}

}
