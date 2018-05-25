package logic;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

import data.DataService;

public class XMLImporter {

	public static DataService importData(String fileName) throws LogicLayerException {
		XStream xstream = new XStream(new StaxDriver());
		FileInputStream fileIn = null;
		try {
			fileIn = new FileInputStream(fileName);
		} catch (FileNotFoundException e) {
			throw new LogicLayerException("File not found");
		}
		DataService data = (DataService)xstream.fromXML(fileIn);
		return data;
	}

}