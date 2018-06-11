package presentation;

import logic.LogicLayer;
import presentation.UserInterface;

/**
 * Class containing main method
 */
public class Program {

	public static void main(String[] args) {		
		if(args.length >= 1) {
			if(args[0].equals("d")) {
				Settings.getInstance().database = true;
			}
		}
		LogicLayer ll = new LogicLayer();
		UserInterface.getInstance(ll);
	}

}
