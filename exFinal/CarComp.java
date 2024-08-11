package exFinal;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JButton;
//class to make drawing cars easier
//TODO add way to get grapics from car object
//TODO maybe implements movement
public class CarComp extends ButtonComp{
    private Point initialClick; // Point to store initial click location
    private int gridSize; // Size of each cell in the grid
    private Boolean flag = true;

	
	public CarComp(int gridSize,testGameBoard gameBoard,testCar car) {
		super(car.getPath());
		//_button.setBackground(color); // Set the background color of the car
        if(!car.isVertical()) {
        	_button.setBounds(car.getX()*gridSize+gridSize, car.getY()*gridSize+gridSize, car.getSize()*gridSize, gridSize); // Set the bounds of the car        	
        }
        else {
        	_button.setBounds(car.getX()*gridSize+gridSize, car.getY()*gridSize+gridSize, gridSize, car.getSize()*gridSize); // Set the bounds of the car        	
        }
        this.gridSize = gridSize;
        this.addDragFunctionality(gameBoard,car);
	}
    
	
	private void addDragFunctionality(testGameBoard gameBoard, testCar car) {
        _button.addMouseListener(new MouseAdapter() { // Add mouse listener to the car
            public void mousePressed(MouseEvent e) {
                initialClick = e.getPoint(); // Store the initial click location
                _button.getParent().setComponentZOrder(_button, 0); // Bring the car to the front
                _button.setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR)); // Change cursor to move cursor
            }

            public void mouseReleased(MouseEvent e) {
                _button.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR)); // Change cursor back to default
                
                //RushHourGameFrame.checkGameStatus(_button); // Check the game status //TODO make sure the car is red
            }
        });

        _button.addMouseMotionListener(new MouseMotionAdapter() { // Add mouse motion listener to the car
            public void mouseDragged(MouseEvent e) {
                int thisX = _button.getLocation().x; // Get current x location of the car
                int thisY = _button.getLocation().y; // Get current y location of the car

                // Determine how much the mouse moved since the initial click
                int xMoved = e.getX() - initialClick.x; // Calculate x movement
                int yMoved = e.getY() - initialClick.y; // Calculate y movement

                // Move the car to the new location
                int x = thisX + xMoved; // Calculate new x location
                int y = thisY + yMoved; // Calculate new y location

                // Check if the car can move in the specified direction
                if (!car.isVertical()) {
                    y = _button.getLocation().y;
                }
                else{
                    x = _button.getLocation().x;
                }

                // Snap to grid
                x = (x / gridSize) * gridSize; // Snap x location to grid
                y = (y / gridSize) * gridSize; // Snap y location to grid
                
                //Rectangle rectangle = new Rectangle(x,y,_button.getWidth(),_button.getHeight());
                // Ensure the car stays within bounds
                if (gameBoard.isValidMove(car,x/gridSize-1,y/gridSize-1)) {
                    _button.setLocation(x, y); // Set the new location of the car
                    gameBoard.moveCar(car, x/gridSize-1, y/gridSize-1);
                    flag = true;
                    
                }
                else if(flag){
                	SoundPlayer s = new SoundPlayer();
                	//s.playSound("textures/car_horn.wav");//in testing
                	System.out.println("1");
                	flag=false;
                }
            }
        });
    }
	
	public JButton getCar() {
		return _button;
	}
	public Rectangle getLocation() {
		return _button.getBounds();
	}
}
