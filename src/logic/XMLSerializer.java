package logic;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

import data.DataService;

/**
 * Class used for serialization and deserialization to and from XML format, implements Serializer interface
 *
 */
public class XMLSerializer implements Serializer {
	
	/* (non-Javadoc)
	 * @see logic.Serializer#serialize(java.lang.String, data.DataService)
	 */
	@Override
	public void serialize(String filename, DataService data) throws LogicLayerException {
		XStream xstream = new XStream(new StaxDriver());
		try {
			xstream.toXML(data,new FileWriter(filename));
		} catch (IOException e) {
			throw new LogicLayerException("Unable to save");
		}
	}
	
	/* (non-Javadoc)
	 * @see logic.Serializer#deserialize(java.lang.String)
	 */
	@Override
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
