package exFinal;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

//class to draw the rules screem
//TODO add rules for the game and improve graphics
public class RulesScreen extends Screen {

	public RulesScreen(ScreenManager screenManager) {
		super();
		System.out.println("rulesScreen");
		String text = new String("this is how you play");
		JLabel label = new JLabel(text, SwingConstants.CENTER);
		label.setFont(new Font("Serif", Font.PLAIN, 24));
		label.setBounds(100, 100, 300, 100);
		ButtonComp back = new ButtonComp("back");
		back.setBounds(300, 300, 100, 50);
		back.setAction(new Action("toMenu",0), screenManager);
		_components.add(label);
		_components.add(back.getButton());

	}

}
