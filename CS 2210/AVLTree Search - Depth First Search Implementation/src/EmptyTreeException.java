/*
 * This class is used to throw an exception for when the tree is empty
 * @author Filip Durca
 */
public class EmptyTreeException extends RuntimeException {

	public EmptyTreeException() {
		super("Error! Tree is empty");
	}
}
