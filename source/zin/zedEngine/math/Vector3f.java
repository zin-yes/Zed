package zin.zedEngine.math;

public class Vector3f {

	public float x, y, z;

	public Vector3f(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public Vector3f(Vector3f source) {
		x = source.x;
		y = source.y;
		z = source.z;
	}

	public Vector3f() {
		x = 0;
		y = 0;
		z = 0;
	}

	public Vector3f add(Vector3f amount) {
		x += amount.x;
		y += amount.y;
		z += amount.z;
		return this;
	}

	public Vector3f add(float x, float y, float z) {
		this.x += x;
		this.y += y;
		this.z += z;
		return this;
	}

	public Vector3f add(float amount) {
		x += amount;
		y += amount;
		z += amount;
		return this;
	}

	public Vector3f subtract(Vector3f amount) {
		x -= amount.x;
		y -= amount.y;
		z -= amount.z;
		return this;
	}

	public Vector3f subtract(float x, float y, float z) {
		this.x -= x;
		this.y -= y;
		this.z -= z;
		return this;
	}

	public Vector3f subtract(float amount) {
		x -= amount;
		y -= amount;
		z -= amount;
		return this;
	}

	public Vector3f multiply(Vector3f amount) {
		x *= amount.x;
		y *= amount.y;
		z *= amount.z;
		return this;
	}

	public Vector3f multiply(float x, float y, float z) {
		this.x *= x;
		this.y *= y;
		this.z *= z;
		return this;
	}

	public Vector3f multiply(float amount) {
		x *= amount;
		y *= amount;
		z *= amount;
		return this;
	}

	public Vector3f divide(Vector3f amount) {
		x /= amount.x;
		y /= amount.y;
		z /= amount.z;
		return this;
	}

	public Vector3f divide(float x, float y, float z) {
		this.x /= x;
		this.y /= y;
		this.z /= z;
		return this;
	}

	public Vector3f divide(float amount) {
		x /= amount;
		y /= amount;
		z /= amount;
		return this;
	}

	public Vector3f lerp(Vector3f other, float lerpFactor) {
		Vector3f result = other.subtract(this).multiply(lerpFactor).add(this);
		x = result.x;
		y = result.y;
		z = result.z;
		return this;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getZ() {
		return z;
	}

	public void setZ(float z) {
		this.z = z;
	}
	
	public Vector3f setVector(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
		return this;
	}
	
	public Vector3f setVector(Vector3f source) {
		x = source.x;
		y = source.y;
		z = source.z;
		return this;
	}
	
	public Vector3f setVector(float xyz) {
		x = xyz;
		y = xyz;
		z = xyz;
		return this;
	}
	
	public Vector2f getXY() {
		return new Vector2f(x, y);
	}
	
	public Vector2f getXZ() {
		return new Vector2f(x, z);
	}
	
	public Vector2f getYX() {
		return new Vector2f(y, x);
	}
	
	public Vector2f getYZ() {
		return new Vector2f(y, z);
	}
	
	public Vector2f getZX() {
		return new Vector2f(z, x);
	}
	
	public Vector2f getZY() {
		return new Vector2f(z, y);
	}

}
