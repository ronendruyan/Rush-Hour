package exFinal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class MenuScreen extends Screen{

	public MenuScreen(ScreenManager screenManager) {
		super();
		ImageIcon levelsTexture = new ImageIcon("textures/levelsButton.png");
		ImageIcon rulesTexture = new ImageIcon("textures/rulesButton.png");

		// Check if images are loaded
		if (levelsTexture.getIconWidth() == -1 || levelsTexture.getIconHeight() == -1) {
			System.out.println("Error: texture1.png not found.");
		}
		if (rulesTexture.getIconWidth() == -1 || rulesTexture.getIconHeight() == -1) {
			System.out.println("Error: texture2.png not found.");
		}

		// Create two buttons with textures
		JButton levelsButton = new JButton(levelsTexture);
		JButton RulesButton = new JButton(rulesTexture);

		// Set button properties to make the background transparent
		levelsButton.setContentAreaFilled(false);
		levelsButton.setBorderPainted(false);
		levelsButton.setFocusPainted(false);

		RulesButton.setContentAreaFilled(false);
		RulesButton.setBorderPainted(false);
		RulesButton.setFocusPainted(false);

		// Set initial positions and sizes of buttons
		levelsButton.setBounds(200, 50, levelsTexture.getIconWidth(), levelsTexture.getIconHeight());
		RulesButton.setBounds(200, 150, rulesTexture.getIconWidth(), rulesTexture.getIconHeight());

		// Add action listeners to the buttons
		levelsButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("button pressed");
				screenManager.toLevels();
				
			}
		});

		RulesButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("button pressed");
				screenManager.toRules();
			}
		});
		_components.add(RulesButton);
		_components.add(levelsButton);
	}
}
