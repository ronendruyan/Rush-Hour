package exFinal;

//class to make adding a action to a button easier by allowing to send functions as parameters.....maybe needs to change
public class Action {
	String _action;

	public Action(String action) {
		_action = action;
	}

	// perform unique action
	public void preformAction(Object param) {
		switch (_action) {
		case "toMenu": {
			((ScreenManager) param).toMenu();
			break;
		}
		case "toLevels": {
			((ScreenManager) param).toLevels();
			break;
		}
		case "toRules": {
			((ScreenManager) param).toRules();
			break;
		}
		case "toGame": {
			((ScreenManager) param).toGame();
			//((ScreenManager) param.First arg).toGame(param.second arg);
			break;
		}

		default:
			System.out.println("Unexpected value: ");
		}
	}
}
