package exFinal;

//class to draw level selction screen
//TODO add sending to levels with level number
//TODO add locked levels
public class LevelsScreen extends Screen {

	public LevelsScreen(ScreenManager screenManager) {
		super();
		System.out.println("rulesScreen");

		ButtonComp b1 = new ButtonComp("1_button");
		ButtonComp b2 = new ButtonComp("2_button");
		ButtonComp b3 = new ButtonComp("3_button");
		ButtonComp b4 = new ButtonComp("4_button");
		ButtonComp b5 = new ButtonComp("5_button");
		b1.setBounds(250, 450);
		b2.setBounds(400, 450);
		b3.setBounds(550, 450);
		b4.setBounds(250, 600);
		b5.setBounds(400, 600);
		// TODO add level num to set action
		b1.setAction(new Action("toGame", 1), screenManager);
		b2.setAction(new Action("toGame", 2), screenManager);
		b3.setAction(new Action("toGame", 3), screenManager);
		b4.setAction(new Action("toGame", 4), screenManager);
		b5.setAction(new Action("toGame", 5), screenManager);
		_components.add(b1.getButton());
		_components.add(b2.getButton());
		_components.add(b3.getButton());
		_components.add(b4.getButton());
		_components.add(b5.getButton());
	}
}
