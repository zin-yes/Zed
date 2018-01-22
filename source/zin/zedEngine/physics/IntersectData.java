package zin.zedEngine.physics;

public class IntersectData {
	
	private boolean intersecting;
	private float penetrationDistance;
	
	public IntersectData(boolean intersecting, float penetrationDistance) {
		this.intersecting = intersecting;
		this.penetrationDistance = penetrationDistance;
	}

	public boolean isIntersecting() {
		return intersecting;
	}

	public float getPenetrationDistance() {
		return penetrationDistance;
	}

}
