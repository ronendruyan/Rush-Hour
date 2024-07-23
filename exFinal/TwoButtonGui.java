package exFinal;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TwoButtonGui {
    public static void main(String[] args) {
    
    	ScreenManager screenManager = new ScreenManager(new Dimension(700,700));
    	//gameBoard g = new gameBoard();
    	//screenManager.MainScreen();
    }
    
    private static void displayText(JFrame frame, String text) {
        // Clear the frame's content pane
        frame.getContentPane().removeAll();
        
        // Create a new label with the text
        JLabel label = new JLabel(text, SwingConstants.CENTER);
        
        // Set the label's font size
        label.setFont(new Font("Serif", Font.PLAIN, 24));
        
        // Add the label to the frame's content pane
        frame.getContentPane().add(label);
        
        // Refresh the frame
        frame.revalidate();
        frame.repaint();
    }
}
