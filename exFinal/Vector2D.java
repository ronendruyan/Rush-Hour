package exFinal;

public class Vector2D {
	private double _x;
	private double _y;

	public Vector2D(double x, double y) {
		this._x = x;
		this._y = y;
	}
	public void setX(double x) {
		this._x = x;
	}
	public void setY(double y) {
		this._y = y;
	}
	public double getX() {
		return this._x;
	}
	public double getY() {
		return this._y;
	}
}
