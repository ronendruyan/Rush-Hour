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
    // If user finishes in optimal moves, give maximum score
    if (userMoves == optimalMoves) {
        return 10;
    }

    // Calculate the ratio of user moves to optimal moves
    double ratio = (double) userMoves / optimalMoves;

    // Linear scaling: if the ratio is 1, score is 10, if ratio is 2, score might be 5, etc.
    // To fit the score in the range [1, 10], we use the following formula:
    // score = 10 - (ratio - 1) * 9
    // This ensures that the score linearly decreases as the ratio increases.
    
    int score = (int) Math.max(1, 10 - (ratio - 1) * 9);

    // Ensuring the score does not exceed 10 or fall below 1
    return Math.min(10, Math.max(1, score));
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
