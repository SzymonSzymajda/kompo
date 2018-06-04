package logic;

import data.DataService;

public interface Serializer {
	
	/**
	 * @param filename name of target file
	 * @param data DataService object to be serialized
	 * @throws LogicLayerException if IOException occurs
	 */
	public void serialize(String filename, DataService data) throws LogicLayerException;
	/**
	 * @param fileName name of source file
	 * @return deserialized DataService object
	 * @throws LogicLayerException if file does not exist
	 */
	public DataService deserialize(String fileName) throws LogicLayerException;

}
