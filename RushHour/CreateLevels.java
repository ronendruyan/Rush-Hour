package RushHour;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

//class to make a GameBoard Object for a specific level based on a saved file
public class CreateLevels {

	private String _filePath;
	private GameBoard _gameBoard;

	// Constructor method
	public CreateLevels(String filePath, int numOfLevel) {
		this._filePath = filePath;
		this._gameBoard = new GameBoard();
		this._gameBoard.setNumOfLevel(numOfLevel);
		this.initLevel(numOfLevel);
	}

	// Method to initialize the GameBoard by parsing the pathed file
	// using the format
	// levelNum:carsNum$car1X,car1Y,car1Size,car1Direction,car1Texture$car2X...
	public void initLevel(int levelNumber) {
		// try to read file
		try (BufferedReader reader = new BufferedReader(new FileReader(this._filePath))) {
			String line;
			// While file not finished read the next line
			while ((line = reader.readLine()) != null) {
				String[] parts = line.split(":");// start parsing
				if (parts.length == 2) {
					int num = Integer.parseInt(parts[0].trim());
					// check if reached line for desired level
					if (num == levelNumber) {
						// separate line the the different cars
						String[] levelData = parts[1].trim().split("\\$");
						int carCount = Integer.parseInt(levelData[0].trim());
						// parse the text for each car
						for (int i = 1; i <= carCount; i++) {
							String[] carData = levelData[i].trim().split(",");
							int x = Integer.parseInt(carData[0].trim());
							int y = Integer.parseInt(carData[1].trim());
							int size = Integer.parseInt(carData[2].trim());
							boolean isVertical = Boolean.parseBoolean(carData[3].trim());
							String path = carData[4].trim();
							boolean isPlayerCar = false;
							if (path.compareTo("red_car") == 0)
								isPlayerCar = true;
							// create car object and add it to _gameBoard
							Car car = new Car(x, y, size, isVertical, path, _gameBoard, isPlayerCar);
							this._gameBoard.addItemToBoard(car);
						}
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	// getter for gameBoard
	public GameBoard getGameBoard() {

		return this._gameBoard;
	}

}