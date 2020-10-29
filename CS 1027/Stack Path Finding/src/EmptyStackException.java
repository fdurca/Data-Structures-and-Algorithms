/*
 * This class is used to throw an empty stack exception
 * @author Filip Durca
 */
public class EmptyStackException extends RuntimeException {
	public EmptyStackException(String message) {
		super(message + " is empty");
	}
}
