package exFinal;

public class Action {
	String _action;
	public Action(String action) {
		_action = action;
	}
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
			break;
		}
		default:
			System.out.println("Unexpected value: ");
		}
	}
}
