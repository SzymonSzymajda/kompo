package logic;

import data.DataService;

public interface Serializer {
	
	public void serialize(String filename, DataService data) throws LogicLayerException;
	public DataService deserialize(String fileName) throws LogicLayerException;

}
