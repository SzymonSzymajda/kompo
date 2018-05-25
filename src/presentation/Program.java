package presentation;

import logic.LogicLayer;
import logic.LogicLayerException;
import logic.XMLImporter;
import logic.XMLSaver;
import presentation.UserInterface;

public class Program {

	public static void main2(String[] args) {
		LogicLayer ll = new LogicLayer();
		try {
			ll.loadDataService(XMLImporter.importData("test.xml"));
		} catch (LogicLayerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		UserInterface gui = new UserInterface();
		gui.run(ll);
	}
	public static void main(String[] args) {
		LogicLayer ll = new LogicLayer();
		UserInterface gui = new UserInterface();
		gui.run(ll);
		for(data.Event ev : ll.getAllEvents().values()) {
			System.out.println(ev.toString());
		}
		XMLSaver.save("test.xml", ll.getDataService());
	}

}
