package RushHour;

//class to draw the menu screen
public class MenuScreen extends Screen {

	public MenuScreen(ScreenManager screenManager) {
		super();
		System.out.println("Menu Screen");
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
