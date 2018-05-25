package presentation;

import java.io.FileWriter;
import java.io.IOException;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

import data.Event;

public class Program {

	public static void main(String[] args) {
		Event ev = new Event(1, 1, 1, "ruchane");
		
		XStream xstream = new XStream(new StaxDriver());
		
		try {
			xstream.toXML(ev,new FileWriter("aaaaaa.xml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		

	}

}
