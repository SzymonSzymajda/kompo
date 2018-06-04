package logic;

import data.DataService;

public interface Serializer {
	
	/**
	 * @param filename
	 * @param data
	 * @throws LogicLayerException
	 */
	public void serialize(String filename, DataService data) throws LogicLayerException;
	/**
	 * @param fileName
	 * @return
	 * @throws LogicLayerException
	 */
	public DataService deserialize(String fileName) throws LogicLayerException;

}
