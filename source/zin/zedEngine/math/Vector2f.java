package zin.zedEngine.math;

public class Vector2f {

	public float x, y;

	public Vector2f(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public Vector2f(Vector2f source) {
		x = source.x;
		y = source.y;
	}

	public Vector2f() {
		x = 0;
		y = 0;
	}

	public Vector2f add(Vector2f amount) {
		x += amount.x;
		y += amount.y;
		return this;
	}

	public Vector2f add(float x, float y) {
		this.x += x;
		this.y += y;
		return this;
	}

	public Vector2f add(float amount) {
		x += amount;
		y += amount;
		return this;
	}

	public Vector2f subtract(Vector2f amount) {
		x -= amount.x;
		y -= amount.y;
		return this;
	}

	public Vector2f subtract(float x, float y) {
		this.x -= x;
		this.y -= y;
		return this;
	}

	public Vector2f subtract(float amount) {
		x -= amount;
		y -= amount;
		return this;
	}

	public Vector2f multiply(Vector2f amount) {
		x *= amount.x;
		y *= amount.y;
		return this;
	}

	public Vector2f multiply(float x, float y, float z) {
		this.x *= x;
		this.y *= y;
		return this;
	}

	public Vector2f multiply(float amount) {
		x *= amount;
		y *= amount;
		return this;
	}

	public Vector2f divide(Vector2f amount) {
		x /= amount.x;
		y /= amount.y;
		return this;
	}

	public Vector2f divide(float x, float y, float z) {
		this.x /= x;
		this.y /= y;
		return this;
	}

	public Vector2f divide(float amount) {
		x /= amount;
		y /= amount;
		return this;
	}

	public Vector2f lerp(Vector2f other, float lerpFactor) {
		Vector2f result = other.subtract(this).multiply(lerpFactor).add(this);
		x = result.x;
		y = result.y;
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

}
