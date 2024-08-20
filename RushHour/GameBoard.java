package RushHour;

import java.util.ArrayList;

//The GameBoard class represents the backend logic for the game board.
//The methods parameters are of Movable type (interface) to support
//future updates which introduce new obstacles with different type
//of movement than car - drone for example.
public class GameBoard {
	// Constants
	private static final int SIZE = 6;
	private static final int WINNING_X = 4;
	private static final int WINNING_Y = 2;
	// Game state variables
	private int[][] _board;
	private ArrayList<Car> _cars;
	private int _moveCount;
	private GameScreen _frame;
	private int _numOfLevel;

	public GameBoard() {
		_board = new int[SIZE][SIZE];
		_cars = new ArrayList<Car>();
		_moveCount = 0;
	}

	// Methods to manage game board state

	// Adds movable item to the gameboard
	public boolean addItemToBoard(Movable item) {
		if (item instanceof Car) {
			Car car = (Car) item;
			_cars.add(car);
			if (isValidPosition(car)) {
				placeItemOnBoard(car);
				return true;
			}
		}
		return false;
	}

	// moves movable item to a new position on the gameboard
	public boolean moveBoardItem(Movable item, int newX, int newY) {
		if (item instanceof Car) {
			Car car = (Car) item;
			if (car.isValidMove(newX, newY) && !(car.getX() == newX && car.getY() == newY)) {
				removeItemFromBoard(car);
				car.setX(newX);
				car.setY(newY);
				placeItemOnBoard(car);
				// show level\game completed frame when red car in winning position
				if (car.getPlayerCar() && car.getX() == WINNING_X && car.getY() == WINNING_Y)
					_frame.displayWinningMessage(_moveCount);
				return true;
			}
		}
		return false;
	}

	// Helper methods

	// Checks if the given movable item (in this case, a car) is in a valid position
	// on the board.
	private boolean isValidPosition(Movable item) {
		if (item instanceof Car) { // Ensure the item is a Car
			Car car = (Car) item;
			int x = car.getX(); // Get the car's x-coordinate
			int y = car.getY(); // Get the car's y-coordinate
			int size = car.getSize(); // Get the car's size (length)
			boolean isVertical = car.isVertical(); // Check if the car is oriented vertically

			// Check if the car's position is valid based on its orientation
			if (isVertical) {
				// Loop through each part of the car's length and check if it fits on the board
				for (int i = 0; i < size; i++) {
					// Check if the car is out of bounds or collides with another car
					if (x < 0 || x >= SIZE || y + i < 0 || y + i >= SIZE || _board[x][y + i] != 0) {
						return false; // Invalid position
					}
				}
			} else {
				// Loop through each part of the car's length and check if it fits on the board
				for (int i = 0; i < size; i++) {
					// Check if the car is out of bounds or collides with another car
					if (x + i < 0 || x + i >= SIZE || y < 0 || y >= SIZE || _board[x + i][y] != 0) {
						return false; // Invalid position
					}
				}
			}
		}
		return true; // The position is valid
	}

	// Places the given movable item (in this case, a car) onto the board.
	private void placeItemOnBoard(Movable item) {
		if (item instanceof Car) { // Ensure the item is a Car
			Car car = (Car) item;
			int x = car.getX(); // Get the car's x-coordinate
			int y = car.getY(); // Get the car's y-coordinate
			int size = car.getSize(); // Get the car's size (length)
			boolean isVertical = car.isVertical(); // Check if the car is oriented vertically

			// Place the car on the board based on its orientation
			if (isVertical) {
				// Loop through each part of the car's length and place it on the board
				for (int i = 0; i < size; i++) {
					_board[x][y + i] = 1; // Mark the board cell as occupied
				}
			} else {
				// Loop through each part of the car's length and place it on the board
				for (int i = 0; i < size; i++) {
					_board[x + i][y] = 1; // Mark the board cell as occupied
				}
			}
		}
	}

	// Removes the given movable item (in this case, a car) from the board.
	private void removeItemFromBoard(Movable item) {
		if (item instanceof Car) { // Ensure the item is a Car
			Car car = (Car) item;
			int x = car.getX(); // Get the car's x-coordinate
			int y = car.getY(); // Get the car's y-coordinate
			int size = car.getSize(); // Get the car's size (length)
			boolean isVertical = car.isVertical(); // Check if the car is oriented vertically

			// Remove the car from the board based on its orientation
			if (isVertical) {
				// Loop through each part of the car's length and clear it from the board
				for (int i = 0; i < size; i++) {
					_board[x][y + i] = 0; // Mark the board cell as unoccupied
				}
			} else {
				// Loop through each part of the car's length and clear it from the board
				for (int i = 0; i < size; i++) {
					_board[x + i][y] = 0; // Mark the board cell as unoccupied
				}
			}
		}
	}

	// getter & setter methods
	public ArrayList<Car> getCars() {
		return this._cars;
	}

	public int getSize() {
		return SIZE;
	}

	public int[][] getBoard() {
		return _board;
	}

	public int getMoveCount() {
		return _moveCount;
	}

	public int getlevelNum() {
		return _numOfLevel;
	}

	public void setNumOfLevel(int level) {
		this._numOfLevel = level;
	}

	public void incMoveCount() {
		_moveCount++;
	}

	public GameScreen getFrame() {
		return this._frame;
	}

	// Sets the frame (game screen) for this game board
	public void setFrame(GameScreen frame) {
		this._frame = frame;
	}

}
