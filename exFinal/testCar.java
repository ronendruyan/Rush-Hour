package exFinal;

//backend class for car
public class testCar implements Movable {
	private int x, y; // top-left coordinate of the car
	private int size;
	private boolean isVertical;
	private String path;
	// i changed testGameBoard's SIZE to public
	// TODO add getters for SIZE and board
	testGameBoard gameboard;
	final int SIZE;
	boolean PlayerCar;
	// TODO add path variable optional just colours

	public testCar(int x, int y, int size, boolean isVertical, String path, testGameBoard board, boolean PlayerCar) {
		this.x = x;
		this.y = y;
		this.size = size;
		this.isVertical = isVertical;
		this.path = path;
		this.gameboard = board;
		this.SIZE = gameboard.getSize();
		this.PlayerCar = PlayerCar;

	}

	public boolean isValidMove(int newX, int newY) {
		int oldX = this.getX();
		int oldY = this.getY();

		int size = this.getSize();
		boolean isVertical = this.isVertical();
		int[][] board = gameboard.getBoard();
		// Check if the new position is out of bounds
		if (isVertical) {
			if (newX < 0 || newX >= SIZE || newY < 0 || newY + size > SIZE) {
				return false;
			}
		} else {
			if (newX < 0 || newX + size > SIZE || newY < 0 || newY >= SIZE) {
				return false;
			}
		}

		// Check if the path is clear
		if (isVertical) {
			if (newY < oldY) { // Moving up
				for (int i = newY; i < oldY; i++) {
					if (board[newX][i] != 0) {
						return false;
					}
				}
			} else { // Moving down
				for (int i = oldY + size; i <= newY + size - 1; i++) {
					if (board[newX][i] != 0) {
						return false;
					}
				}
			}
		} else {
			if (newX < oldX) { // Moving left
				for (int i = newX; i < oldX; i++) {
					if (board[i][newY] != 0) {
						return false;
					}
				}
			} else { // Moving right
				for (int i = oldX + size; i <= newX + size - 1; i++) {
					if (board[i][newY] != 0) {
						return false;
					}
				}
			}
		}
		return true;
	}

	public String getPath() {
		return this.path;
	}

	public boolean getPlayerCar() {
		return this.PlayerCar;
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
