/*
 * This method throws an exception for when the key doesnt exist
 * @author Filip Durca
 */
public class InexistentKeyException extends RuntimeException {

	public InexistentKeyException() {
		super("Error! Key does not exist");
	}
}
