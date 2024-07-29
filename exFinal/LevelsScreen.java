package exFinal;

//class to draw level selction screen
//TODO add sending to levels with level number
//TODO add locked levels
public class LevelsScreen extends Screen {

	public LevelsScreen(ScreenManager screenManager) {
		super();
		System.out.println("rulesScreen");
		ButtonComp b1 = new ButtonComp("1");
		ButtonComp b2 = new ButtonComp("2");
		ButtonComp b3 = new ButtonComp("3");
		ButtonComp b4 = new ButtonComp("4");
		ButtonComp b5 = new ButtonComp("5");
		b1.setBounds(100, 100, 100, 50);
		b2.setBounds(300, 100, 100, 50);
		b3.setBounds(100, 200, 100, 50);
		b4.setBounds(300, 200, 100, 50);
		b5.setBounds(200, 300, 100, 50);
		//TODO add level num to set action
		b1.setAction(new Action("toMenu"), screenManager);
		b2.setAction(new Action("toMenu"), screenManager);
		b3.setAction(new Action("toMenu"), screenManager);
		b4.setAction(new Action("toMenu"), screenManager);
		b5.setAction(new Action("toGame"), screenManager);
		_components.add(b1.getButton());
		_components.add(b2.getButton());
		_components.add(b3.getButton());
		_components.add(b4.getButton());
		_components.add(b5.getButton());
	}
}
