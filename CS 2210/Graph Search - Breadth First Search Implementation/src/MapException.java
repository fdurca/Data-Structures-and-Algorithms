/*
 * This class holds the map exception
 * @author Filip Durca
 */
public class MapException extends RuntimeException {

	public MapException() {
		super("Error! File not found");
	}
}