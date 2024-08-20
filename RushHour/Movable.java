package RushHour;

// Interface for objects that can move on the board
// this class purpose is to support future updates which introduce new obstacles with different type
//of movement than car - drone for example.

public interface Movable {

	// Get the current X-coordinate of the object
	int getX();

	// Get the current Y-coordinate of the object
	int getY();

	// Set the X-coordinate of the object
	void setX(int x);

	// Set the Y-coordinate of the object
	void setY(int y);

	// Get the size of the object (e.g., length of the car)
	int getSize();

	// Check if moving the object to a new position (newX, newY) is valid
	boolean isValidMove(int newX, int newY);

	// Check if the object is oriented vertically on the board
	boolean isVertical();

	// Get the path of the image or texture associated with the object
	String getPath();
}
