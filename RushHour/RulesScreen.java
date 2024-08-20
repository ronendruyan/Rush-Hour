package RushHour;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

// Class to draw the rules screen
public class RulesScreen extends Screen {

	public RulesScreen(ScreenManager screenManager) {
		super();
		System.out.println("Rules Screen");
		// Text for labels, using HTML tags for multiline support and bold titles
		String text1 = "<html><b>Welcome to Rush Hour!</b><br><br>" + "<b>Objective:</b><br>"
				+ "Your goal is to move the red car out of the parking lot by shifting other vehicles blocking its path.<br>"
				+ "You need to get the red car to the exit on the right side of the grid.<br><br></html>";

		String text2 = "<html><b>How to Play:</b><br>" + "1. Use the mouse to select and drag the vehicles.<br>"
				+ "2. Vehicles can only move forward or backward along the grid in the direction they are facing.<br>"
				+ "3. You cannot move vehicles diagonally or into occupied spaces.<br>"
				+ "4. The game tracks the number of moves you make. Try to solve the puzzle with the fewest moves possible to achieve a higher score.<br><br></html>";

		String text3 = "<html><b>Scoring:</b><br>"
				+ "1. Achieve the optimal number of moves for a perfect score of 10.<br>"
				+ "2. Your score decreases as the number of moves increases beyond the optimal solution.<br>"
				+ "3. The closer your moves are to the optimal number, the higher your score.<br><br>"
				+ "Good luck, and enjoy the challenge!</html>";

		JPanel bg = new JPanel();
		bg.setBackground(Color.white);
		bg.setBounds(110, 10, 600, 650);
		// Set the size of the color block
		// Create labels for each section using the desired format
		JLabel label1 = new JLabel(text1, SwingConstants.CENTER);
		JLabel label2 = new JLabel(text2, SwingConstants.CENTER);
		JLabel label3 = new JLabel(text3, SwingConstants.CENTER);

		// Set font and bounds for each label
		label1.setFont(new Font("Serif", Font.PLAIN, 18));
		label1.setBounds(120, 20, 500, 200);

		label2.setFont(new Font("Serif", Font.PLAIN, 18));
		label2.setBounds(120, 220, 500, 200);

		label3.setFont(new Font("Serif", Font.PLAIN, 18));
		label3.setBounds(120, 420, 500, 200);

		// Back button
		ButtonComp back = new ButtonComp("menu_button");
		back.setBounds(750, 20);
		back.setAction(new Action("toMenu", 0), screenManager);

		// Add labels and button to the screen
		_components.add(label1);
		_components.add(label2);
		_components.add(label3);
		_components.add(back.getButton());
		_components.add(bg);
	}
}
