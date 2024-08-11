package exFinal;

public class carToLevel {
    private int x, y; // top-left coordinate of the car
    private int size;
    private boolean isVertical;
    private String path;
    private String color;
    private int gridSize;
    private testGameBoard gameBoard;
    private testCar car;
    private int numberOfCars;


    public carToLevel(int x, int y, int size, boolean isVertical, String path, String color, int gridSize, testGameBoard gameBoard, testCar car, int number) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.isVertical = isVertical;
        this.path = path;
        this.color = color;
        this.gridSize = gridSize;
        this.gameBoard = gameBoard;
        this.car = car;
        this.numberOfCars = number;
    }

    // Getters
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

    public String getPath() {
        return path;
    }

    public String getColor() {
    	
        return color;
    }

    public int getGridSize() {
        return gridSize;
    }

    public testGameBoard getGameBoard() {
        return gameBoard;
    }

    public testCar getCar() {
        return car;
    }
    public int getNumOfCars() {
        return numberOfCars;
    }
}
