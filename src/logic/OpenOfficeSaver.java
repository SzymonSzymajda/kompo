package logic;

import java.util.Arrays;

import org.odftoolkit.odfdom.doc.OdfTextDocument;

import data.DataService;

/**
 * Class used to export data to ODT format
 *
 */
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

	/**
	 * Exports give DataService to ODT format
	 * @param filename name of output file
	 * @param data DataService to be exported to ODT format
	 * @throws LogicLayerException if exception occurs 
	 */
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