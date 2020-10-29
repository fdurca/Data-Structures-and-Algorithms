/*
 * This class is used to throw exceptions for duplicated keys
 * @author Filip Durca
 */
public class DuplicatedKeyException extends RuntimeException {

	public DuplicatedKeyException() {
		super("Error! Key already exists");
	}
}
