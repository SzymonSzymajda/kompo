package logic;

import java.util.Arrays;

import org.odftoolkit.odfdom.doc.OdfTextDocument;

import data.DataService;

@SuppressWarnings("deprecation")
public class OpenOfficeSaver {

	private String peopleToString(DataService data) {
		String ret = "";
		ret += "\nPeople \n";
		ret += Arrays.toString(data.getAllPeople().toArray());
		return ret;
	}
	
	private String eventsToString(DataService data) {
		String ret = "";
		ret += "\nEvents \n";
		ret += Arrays.toString(data.getAllEvents().entrySet().toArray());
		return ret;
	}

	public void save(String filename, DataService data) throws LogicLayerException {

		OdfTextDocument odt;
		try {
			odt = OdfTextDocument.newTextDocument();
			odt.addText(peopleToString(data));
			odt.newParagraph();
			odt.addText(eventsToString(data));
			odt.save(filename);
		} catch (Exception e) {
			throw new LogicLayerException(e.getMessage());

		}

	}

}