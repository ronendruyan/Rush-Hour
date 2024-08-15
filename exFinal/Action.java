package exFinal;

//class to make adding a action to a button easier by allowing to send functions as parameters.....maybe needs to change
public class Action {
	//String _action;
	//int _num;
	private Pair<Integer, String> _action;
	private RushHourGameFrame frame = null;
	public Action(String action,int num) {
		//_action = action;
		_action = new Pair<>(num, action);
	}

	// perform unique action
	public RushHourGameFrame  preformAction(Object param) {
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
			//((ScreenManager) param.First arg).toGame(param.second arg);
			break;
		}

		default:
			System.out.println("Unexpected value: ");
		}
		return frame;
	}
}
