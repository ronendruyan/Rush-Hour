package exFinal;

import java.awt.Color;
import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JPanel;

public class CreateLevelsClass {
    private String _filePath;
    private int _numOfLevel; 
    private testGameBoard _gameBoard;
    private CarComp[] cars; // Array to hold car buttons

    public CreateLevelsClass(String filePath,int numOfLevel) {
        this._filePath = filePath;
        this._numOfLevel = numOfLevel;
        this._gameBoard = new testGameBoard();
        this.getCar(numOfLevel);
    }

    public void getCar(int levelNumber) {
        try (BufferedReader reader = new BufferedReader(new FileReader(this._filePath))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                //System.out.println(parts.length);
                if (parts.length == 2) {
                    int num = Integer.parseInt(parts[0].trim());
                    if (num == levelNumber) {
                        String[] levelData = parts[1].trim().split("\\$");
                        int carCount = Integer.parseInt(levelData[0].trim());
                        for (int i = 1; i <= carCount; i++) {
                            String[] carData = levelData[i].trim().split(",");
                            int x = Integer.parseInt(carData[0].trim());
                            //System.out.println(x);
                            int y = Integer.parseInt(carData[1].trim());
                            int size = Integer.parseInt(carData[2].trim());
                            boolean isVertical = Boolean.parseBoolean(carData[3].trim());
                            String path = carData[4].trim();
                            testCar car = new testCar(x, y, size, isVertical, path);
                            this._gameBoard.addCar(car);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
    }

    }
    
    public testGameBoard getGameBoard() {
    	
    	return this._gameBoard;
    } 
    
}