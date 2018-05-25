package presentation;

import logic.LogicLayer;
import presentation.UserInterface;

public class Program {

	public static void main(String[] args) {
		LogicLayer ll = new LogicLayer();
		new UserInterface(ll);
	}

}
