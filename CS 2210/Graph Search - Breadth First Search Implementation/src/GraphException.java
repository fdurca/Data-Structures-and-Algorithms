/*
 * This class holds the graph exception
 * @author Filip Durca
 */
public class GraphException extends RuntimeException {

	public GraphException() {
		super("Error! Node not found or Node already Exists");
	}
}