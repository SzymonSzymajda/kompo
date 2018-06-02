package logic;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

import data.DataService;

public class XMLSerializer implements Serializer {
	
	public void serialize(String filename, DataService data) throws LogicLayerException {
		XStream xstream = new XStream(new StaxDriver());
		try {
			xstream.toXML(data,new FileWriter(filename));
		} catch (IOException e) {
			throw new LogicLayerException("Unable to save");
		}
	}
	
	public DataService deserialize(String fileName) throws LogicLayerException {
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
