package exFinal;

import java.util.ArrayList;

//backend class for gameBoard (mostly gpt, need to check if makes sense (it works)
public class testGameBoard {
	private static final int SIZE = 6;
	private int[][] board;
	ArrayList<testCar> cars;

	public testGameBoard() {
		board = new int[SIZE][SIZE];
		cars = new ArrayList<testCar>();
	}

	public boolean addCar(testCar car) {
		cars.add(car);
		if (isValidPosition(car)) {
			placeCarOnBoard(car);
			return true;
		}
		return false;
	}

	private boolean isValidPosition(testCar car) {
		int x = car.getX();
		int y = car.getY();
		int size = car.getSize();
		boolean isVertical = car.isVertical();

		if (isVertical) {
			for (int i = 0; i < size; i++) {
				if (x < 0 || x >= SIZE || y + i < 0 || y + i >= SIZE || board[x][y + i] != 0) {
					return false;
				}
			}
		} else {
			for (int i = 0; i < size; i++) {
				if (x + i < 0 || x + i >= SIZE || y < 0 || y >= SIZE || board[x + i][y] != 0) {
					return false;
				}
			}
		}
		return true;
	}

	private void placeCarOnBoard(testCar car) {
		int x = car.getX();
		int y = car.getY();
		int size = car.getSize();
		boolean isVertical = car.isVertical();

		if (isVertical) {
			for (int i = 0; i < size; i++) {
				board[x][y + i] = 1;
			}
		} else {
			for (int i = 0; i < size; i++) {
				board[x + i][y] = 1;
			}
		}
	}

	public boolean moveCar(testCar car, int newX, int newY) {
		if (isValidMove(car, newX, newY)) {
			removeCarFromBoard(car);
			car.setX(newX);
			car.setY(newY);
			placeCarOnBoard(car);
			return true;
		}
		return false;
	}

	public boolean isValidMove(testCar car, int newX, int newY) {
		int oldX = car.getX();
		int oldY = car.getY();
		int size = car.getSize();
		boolean isVertical = car.isVertical();

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

	private void removeCarFromBoard(testCar car) {
		int x = car.getX();
		int y = car.getY();
		int size = car.getSize();
		boolean isVertical = car.isVertical();

		if (isVertical) {
			for (int i = 0; i < size; i++) {
				board[x][y + i] = 0;
			}
		} else {
			for (int i = 0; i < size; i++) {
				board[x + i][y] = 0;
			}
		}
	}

	public ArrayList<testCar> getCars() {
		return this.cars;
	}

	public void printBoard() {
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				if (board[i][j] == 0) {
					System.out.print(". ");
				} else {
					System.out.print("C ");
				}
			}
			System.out.println();
		}
	}
}
