package exFinal;

//class to draw the main screen
//TODO improve graphics
public class MenuScreen extends Screen {

	public MenuScreen(ScreenManager screenManager) {
		super();
		ButtonComp levelsButton = new ButtonComp("play_button");
		ButtonComp rulesButton = new ButtonComp("rules_button");

		// Set initial positions and sizes of buttons
		levelsButton.setBounds(400, 400);
		rulesButton.setBounds(400, 550);

		// Add action listeners to the buttons
		levelsButton.setAction(new Action("toLevels", 0), screenManager);
		rulesButton.setAction(new Action("toRules", 0), screenManager);

		_components.add(rulesButton.getButton());
		_components.add(levelsButton.getButton());
	}
}
