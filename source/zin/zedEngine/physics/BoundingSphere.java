package zin.zedEngine.physics;

import zin.zedEngine.math.Vector3f;

public class BoundingSphere {

	private Vector3f center;
	private float radius;

	public BoundingSphere(Vector3f center, float radius) {
		this.center = center;
		this.radius = radius;
	}

	public IntersectData getIntersection(BoundingSphere other) {
		float radiusDistance = radius + other.getRadius();
		float centerDistance = other.getCenter().subtract(center).length();

		return new IntersectData(centerDistance < radiusDistance, centerDistance - radiusDistance);
	}

	public Vector3f getCenter() {
		return center;
	}

	public float getRadius() {
		return radius;
	}

}
