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

	public boolean addItemToBoard(Movable item) {
		if (item instanceof testCar) {
			testCar car = (testCar) item;
			cars.add(car);
			if (isValidPosition(car)) {
				placeItemOnBoard(car);
				return true;
			}
		}
		return false;
	}
	
	public int getSize() {
		return SIZE;
	}
	
	public int[][] getBoard() {
		return board;
	}


	private boolean isValidPosition(Movable item) {
		if(item instanceof testCar) {
			testCar car = (testCar) item;

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
		}
		return true;
	}

	private void placeItemOnBoard(Movable item) {
		if(item instanceof testCar) {
			testCar car = (testCar) item;
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
	}

	public boolean moveBoardItem(Movable item, int newX, int newY) {
		if (item instanceof testCar)
		{
			testCar car = (testCar)item;
		if (car.isValidMove(newX, newY)) {
			removeItemFromBoard(car);
			car.setX(newX);
			car.setY(newY);
			placeItemOnBoard(car);
			return true;
		}
		}
		return false;
	}

	private void removeItemFromBoard(Movable item) {
		if(item instanceof testCar) {
			testCar car = (testCar) item;
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
