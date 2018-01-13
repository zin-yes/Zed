package zin.zedEngine.math;

public class Matrix4f {

	public float x, y;

	public Matrix4f(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public Matrix4f(Matrix4f source) {
		x = source.x;
		y = source.y;
	}

	public Matrix4f(float amount) {
		x = amount;
		y = amount;
	}

	public Matrix4f() {
		x = 0;
		y = 0;
	}

	public Matrix4f add(Matrix4f amount) {
		x += amount.x;
		y += amount.y;
		return this;
	}

	public Matrix4f add(float x, float y) {
		this.x += x;
		this.y += y;
		return this;
	}

	public Matrix4f add(float amount) {
		x += amount;
		y += amount;
		return this;
	}

	public Matrix4f subtract(Matrix4f amount) {
		x -= amount.x;
		y -= amount.y;
		return this;
	}

	public Matrix4f subtract(float x, float y) {
		this.x -= x;
		this.y -= y;
		return this;
	}

	public Matrix4f subtract(float amount) {
		x -= amount;
		y -= amount;
		return this;
	}

	public Matrix4f multiply(Matrix4f amount) {
		x *= amount.x;
		y *= amount.y;
		return this;
	}

	public Matrix4f multiply(float x, float y, float z) {
		this.x *= x;
		this.y *= y;
		return this;
	}

	public Matrix4f multiply(float amount) {
		x *= amount;
		y *= amount;
		return this;
	}

	public Matrix4f divide(Matrix4f amount) {
		x /= amount.x;
		y /= amount.y;
		return this;
	}

	public Matrix4f divide(float x, float y, float z) {
		this.x /= x;
		this.y /= y;
		return this;
	}

	public Matrix4f divide(float amount) {
		x /= amount;
		y /= amount;
		return this;
	}

	public Matrix4f lerp(Matrix4f other, float lerpFactor) {
		Matrix4f result = other.subtract(this).multiply(lerpFactor).add(this);
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

	public String toString() {
		return "Vector3f(" + x + ", " + y + ")";
	}

}
