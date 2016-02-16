package CustomExceptions;

/** Handles errors associated with binary numerals */
public class BinaryFormatException extends NumberFormatException {
	public BinaryFormatException() { }
	
	public BinaryFormatException(String message) {
		super(message);
	}
}
