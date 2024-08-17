package exFinal;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JButton;

public class CarComp extends ButtonComp {
	private Point initialClick; // Point to store initial click location
	private int gridSize; // Size of each cell in the grid
	private int gridXOffset, gridYOffset;
	private int initialGridX, initialGridY; // Initial grid position of the car

	public CarComp(int gridSize, int gridXOffset, int gridYOffset, testGameBoard gameBoard, testCar car) {
		super(car.getPath());
		if (!car.isVertical()) {
			_button.setBounds(gridXOffset + car.getX() * gridSize + gridSize,
					gridYOffset + car.getY() * gridSize + gridSize, car.getSize() * gridSize, gridSize);
		} else {
			_button.setBounds(gridXOffset + car.getX() * gridSize + gridSize,
					gridYOffset + car.getY() * gridSize + gridSize, gridSize, car.getSize() * gridSize);
		}
		this.gridSize = gridSize;
		this.gridXOffset = gridXOffset;
		this.gridYOffset = gridYOffset;
		this.addDragFunctionality(gameBoard, car);
	}

	private void addDragFunctionality(testGameBoard gameBoard, testCar car) {
		_button.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				initialClick = e.getPoint();
				_button.getParent().setComponentZOrder(_button, 0);
				_button.setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));

				// Store the initial grid position
				initialGridX = car.getX();
				initialGridY = car.getY();
			}

			public void mouseReleased(MouseEvent e) {
				_button.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				int x = _button.getX()-gridXOffset;
				int y = _button.getY()-gridYOffset;

				// Snap to grid
				x = (x / gridSize) * gridSize;
				y = (y / gridSize) * gridSize;

				int newGridX = x / gridSize - 1;
				int newGridY = y / gridSize - 1;

				// System.out.println(x);
				// System.out.println(y);
				// System.out.println(newGridX);
				// System.out.println(newGridY);
				// Only count the move if the car has actually moved to a different grid cell
				if (car.isValidMove(newGridX, newGridY) && (newGridX != initialGridX || newGridY != initialGridY)) {
					gameBoard.incMoveCount();
					gameBoard.moveBoardItem(car, newGridX, newGridY);
				}
			}
		});

		_button.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e) {
				int thisX = _button.getLocation().x;
				int thisY = _button.getLocation().y;

				int xMoved = e.getX() - initialClick.x;
				int yMoved = e.getY() - initialClick.y;

				int x = thisX + xMoved-gridXOffset;
				int y = thisY + yMoved-gridYOffset;

				if (!car.isVertical()) {
					y = _button.getLocation().y-gridYOffset;
				} else {
					x = _button.getLocation().x-gridXOffset;
				}

				x = (x / gridSize) * gridSize;
				y = (y / gridSize) * gridSize;
				if (car.isValidMove(x / gridSize - 1, y / gridSize - 1)) {
					_button.setLocation(x+gridXOffset, y+gridYOffset);
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
