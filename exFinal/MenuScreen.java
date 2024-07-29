package exFinal;

//class to draw the main screen
//TODO improve graphics
public class MenuScreen extends Screen {

	public MenuScreen(ScreenManager screenManager) {
		super();
		ButtonComp levelsButton = new ButtonComp("levelsButton");
		ButtonComp rulesButton = new ButtonComp("rulesButton");

		// Set initial positions and sizes of buttons
		levelsButton.setBounds(200, 50);
		rulesButton.setBounds(200, 150);

		// Add action listeners to the buttons
		levelsButton.setAction(new Action("toLevels"), screenManager);
		rulesButton.setAction(new Action("toRules"), screenManager);

		_components.add(rulesButton.getButton());
		_components.add(levelsButton.getButton());
	}
}
