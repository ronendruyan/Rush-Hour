package exFinal;

//backend class for car
public class testCar {
	private int x, y; // top-left coordinate of the car
	private int size;
	private boolean isVertical;
	private String path;
	//TODO add path variable optional just colours

	public testCar(int x, int y, int size, boolean isVertical,String path) {
		this.x = x;
		this.y = y;
		this.size = size;
		this.isVertical = isVertical;
		this.path = path;
	}
	public String getPath() {
		return this.path;
	}
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getSize() {
		return size;
	}

	public boolean isVertical() {
		return isVertical;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}
}
