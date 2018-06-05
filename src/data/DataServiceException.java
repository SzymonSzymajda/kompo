package data;

/**
 * Exception class for data package
 *
 */
@SuppressWarnings("serial")
public class DataServiceException extends Exception {

	/**
	 * Exception thrown in data package
	 * @param msg the detail message
	 */
	public DataServiceException(String msg) {
		super(msg);
	}
	

}
