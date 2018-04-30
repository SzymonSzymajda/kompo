import java.io.*;
import java.beans.*;

public class siemka {

	public static void main(String[] args) throws DataFormatException, FileNotFoundException {
		try {
			Cos cos = new Cos(3);
		} catch (DataFormatException e) {
			System.out.println(e.getMessage());
		} finally {
			System.out.println("elo");
		}
		
		System.out.print("siemaaaa");
		
		//serializacja do XML
		Cos cos1 = new Cos(2);
		FileOutputStream fos = new FileOutputStream("Test.xml");
		BufferedOutputStream bos = new BufferedOutputStream(fos);
		XMLEncoder xmlEncoder = new XMLEncoder(bos);
		xmlEncoder.writeObject(cos1);
		xmlEncoder.close();
		
		
		//deserializacja z XML
		FileInputStream fis = new FileInputStream("Test.xml");
		BufferedInputStream bis = new BufferedInputStream(fis);
		XMLDecoder xmlDecoder = new XMLDecoder(bis);
		Cos cos2 = (Cos) xmlDecoder.readObject();
		System.out.println(cos2.getMsg() + " " + cos2.getNumber());
	}	
}