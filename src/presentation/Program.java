package presentation;

import logic.LogicLayer;
import presentation.UserInterface;

/**
 * Class containing main method
 */
public class Program {

	public static void main(String[] args) {
		LogicLayer ll = new LogicLayer();
		System.out.println(args.length);
		
		if(args.length >= 1) {
			System.out.println(args[0]);
			if(args[0].equals("d")) {
				System.out.println("siema");
				Settings.getInstance().database = true;
			}
		}
		UserInterface.getInstance(ll);
	}

}
