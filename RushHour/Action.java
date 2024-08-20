package RushHour;

//class to make adding a action to a button easier by allowing to send functions as parameters
public class Action {
	private Pair<Integer, String> _action;

	// Constructor
	public Action(String action, int num) {
		_action = new Pair<>(num, action);
	}

	// perform unique action
	public void preformAction(Object param) {
		switch (_action.getValue()) {
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
			((ScreenManager) param).toGame(_action.getKey());
			break;
		}
		default:
			System.out.println("Unexpected value: ");
		}
	}
}
