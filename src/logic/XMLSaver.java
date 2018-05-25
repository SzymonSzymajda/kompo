package logic;

import java.io.FileWriter;
import java.io.IOException;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

import data.DataService;

public class XMLSaver {
	public static void save(String filename, DataService data) {
		XStream xstream = new XStream(new StaxDriver());
		try {
			xstream.toXML(data,new FileWriter(filename));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
