package exFinal;

// Importing AWT library for graphics
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

// Class to draw the gameFrame
public class RushHourGameFrame extends Screen {
    private CarComp[] cars; // Array to hold car buttons
    private int gridSize = 80; // Size of each cell in the grid
    private JButton[][] cells; // 2D array to hold grid cells
    private testGameBoard gameBoard;

    public RushHourGameFrame(ScreenManager screenManager, testGameBoard gameBoard) {
        super();
        ImageIcon bg = new ImageIcon("textures/board_game.png");
        JButton boardPanel = new JButton(bg);
        boardPanel.setContentAreaFilled(false);
        boardPanel.setBorderPainted(false);
        boardPanel.setFocusPainted(false);
        boardPanel.setBounds(0, 0, boardPanel.getIcon().getIconWidth(), boardPanel.getIcon().getIconHeight());
        this.gameBoard = gameBoard;

        // TODO add level init level change path to car.path
        for (testCar car : gameBoard.getCars()) {
            System.out.println("1");
            _components.add(new CarComp(gridSize, gameBoard, car).getButton());
        }
        _components.add(boardPanel);
    }

    private int calculateScore(int userMoves, int optimalMoves) {
        int score;
        if (userMoves == optimalMoves) {
            score = 10;
        } else if (userMoves <= 2 * optimalMoves) {
            score = 7;
        } else if (userMoves <= 3 * optimalMoves) {
            score = 5;
        } else {
            score = 3;
        }
        return score;
    }

    private void displayWinningMessage(int userMoves, int optimalMoves) {
        int score = calculateScore(userMoves, optimalMoves);

        JFrame winningFrame = new JFrame("Game Over"); // Create a new JFrame for the winning message
        winningFrame.setSize(300, 200); // Set the size of the winning frame
        winningFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Set default close operation
        winningFrame.setLocationRelativeTo(null); // Center the winning frame on the screen

        JLabel messageLabel = new JLabel("Game Finished - You Won!", SwingConstants.CENTER);
        messageLabel.setFont(new Font("Arial", Font.BOLD, 16));
        messageLabel.setBounds(50, 30, 200, 50);

        JLabel scoreLabel = new JLabel("Your Score: " + score, SwingConstants.CENTER);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 14));
        scoreLabel.setBounds(50, 80, 200, 50);

        winningFrame.add(messageLabel);
        winningFrame.add(scoreLabel);
        winningFrame.setVisible(true);

        gameBoard.resetMoveCount(); // Reset move count at the end of the level
    }
}
