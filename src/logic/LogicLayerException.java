package logic;

/**
 * Exception class for logic package
 *
 */
@SuppressWarnings("serial")
public class LogicLayerException extends Exception {

	/**
	 * Exception thrown in logic package
	 * @param msg the detail message
	 */
	public LogicLayerException(String msg) {
		super(msg);
	}
}
