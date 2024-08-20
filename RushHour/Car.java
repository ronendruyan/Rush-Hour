package RushHour;

//Car class represents the logical entity of a car in the game (backend)
public class Car implements Movable {
	// Properties of the car
	private int _x, _y; // top-left coordinate of the car
	private int _size;
	private boolean _isVertical;
	private String _path;
	private GameBoard _gameboard;
	private boolean _PlayerCar;

	// Constructor
	public Car(int x, int y, int size, boolean isVertical, String path, GameBoard board, boolean PlayerCar) {
		this._x = x;
		this._y = y;
		this._size = size;
		this._isVertical = isVertical;
		this._path = path;
		this._gameboard = board;
		this._PlayerCar = PlayerCar;

	}

	// Check if a move to a new position is valid
	public boolean isValidMove(int newX, int newY) {
		int oldX = this.getX();
		int oldY = this.getY();
		int boardSize = _gameboard.getSize();
		int size = this.getSize();
		boolean isVertical = this.isVertical();
		int[][] board = _gameboard.getBoard();
		// Check if the new position is out of bounds
		if (isVertical) {
			if (newX < 0 || newX >= boardSize || newY < 0 || newY + size > boardSize) {
				return false;
			}
		} else {
			if (newX < 0 || newX + size > boardSize || newY < 0 || newY >= boardSize) {
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

	// getter & setter methods
	public String getPath() {
		return this._path;
	}

	public boolean getPlayerCar() {
		return this._PlayerCar;
	}

	public int getX() {
		return _x;
	}

	public int getY() {
		return _y;
	}

	public int getSize() {
		return _size;
	}

	public boolean isVertical() {
		return _isVertical;
	}

	public void setX(int x) {
		this._x = x;
	}

	public void setY(int y) {
		this._y = y;
	}
}
