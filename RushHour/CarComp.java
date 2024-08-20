package RushHour;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JButton;

//CarComp class represents the visual component of a car on the game board
public class CarComp extends ButtonComp {
	// Constants for grid properties
	private final int _GRID_OFFSET_X, _GRID_OFFSET_Y;
	private final int _GRID_SIZE; // Size of each cell in the grid

	// Variables to track mouse interactions
	private Point _initialClick; // Point - initial click location
	private int _initialGridX, _initialGridY; // Initial grid position of the car

	// Constructor
	public CarComp(int gridSize, int gridXOffset, int gridYOffset, GameBoard gameBoard, Car car) {
		super(car.getPath());
		if (!car.isVertical()) {
			_button.setBounds(gridXOffset + car.getX() * gridSize + gridSize,
					gridYOffset + car.getY() * gridSize + gridSize, car.getSize() * gridSize, gridSize);
		} else {
			_button.setBounds(gridXOffset + car.getX() * gridSize + gridSize,
					gridYOffset + car.getY() * gridSize + gridSize, gridSize, car.getSize() * gridSize);
		}
		this._GRID_SIZE = gridSize;
		this._GRID_OFFSET_X = gridXOffset;
		this._GRID_OFFSET_Y = gridYOffset;
		this.addDragFunctionality(gameBoard, car);
	}

	// Add drag functionality to the car component
	private void addDragFunctionality(GameBoard gameBoard, Car car) {
		_button.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				_initialClick = e.getPoint();
				_button.getParent().setComponentZOrder(_button, 0);
				_button.setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));

				// Store the initial grid position
				_initialGridX = car.getX();
				_initialGridY = car.getY();
			}

			// Method to to track when the user finished a move
			public void mouseReleased(MouseEvent e) {
				_button.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				int x = _button.getX() - _GRID_OFFSET_X;
				int y = _button.getY() - _GRID_OFFSET_Y;

				// Snap to grid
				x = (x / _GRID_SIZE) * _GRID_SIZE;
				y = (y / _GRID_SIZE) * _GRID_SIZE;

				int newGridX = x / _GRID_SIZE - 1;
				int newGridY = y / _GRID_SIZE - 1;

				// Only count the move if the car has actually moved to a different grid cell
				if (car.isValidMove(newGridX, newGridY) && (newGridX != _initialGridX || newGridY != _initialGridY)) {
					gameBoard.incMoveCount();
					gameBoard.moveBoardItem(car, newGridX, newGridY);
				}
			}
		});

		// Method to give live feedback to moving the car on the screen
		_button.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e) {
				int thisX = _button.getLocation().x;
				int thisY = _button.getLocation().y;
				// calculate amount moved
				int xMoved = e.getX() - _initialClick.x;
				int yMoved = e.getY() - _initialClick.y;

				int x = thisX + xMoved - _GRID_OFFSET_X;
				int y = thisY + yMoved - _GRID_OFFSET_Y;

				// move make move on one axis
				if (!car.isVertical())
					y = _button.getLocation().y - _GRID_OFFSET_Y;
				else
					x = _button.getLocation().x - _GRID_OFFSET_X;
				// Snap to grid
				x = (x / _GRID_SIZE) * _GRID_SIZE;
				y = (y / _GRID_SIZE) * _GRID_SIZE;

				if (car.isValidMove(x / _GRID_SIZE - 1, y / _GRID_SIZE - 1))
					_button.setLocation(x + _GRID_OFFSET_X, y + _GRID_OFFSET_Y);

			}
		});
	}

	// Getter methods
	public JButton getCar() {
		return _button;
	}

	public Rectangle getLocation() {
		return _button.getBounds();
	}
}
