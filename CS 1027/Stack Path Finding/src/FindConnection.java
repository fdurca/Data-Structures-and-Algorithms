/*
 * This program finds the connection to the customers house
 * @author Filip Durca 251008743
 */

//Imports
import java.io.FileNotFoundException;
import java.io.IOException;

public class FindConnection {
	private Map cityMap;

	/*
	 * This method is the class constructor
	 */
	public FindConnection(String filename) {
		try {
			cityMap = new Map(filename);
		} catch (InvalidMapException e) {
			System.out.println(e);
		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	/*
	 * Main method
	 */
	public static void main(String[] args) {
		if (args.length < 1) {
			System.out.println("You must provide the name of the input file");
			System.exit(0);
		}
		String mapFileName = args[0];
		FindConnection findConnection = new FindConnection(mapFileName);

		// Find the path
		ArrayStack<MapCell> path = new ArrayStack<MapCell>();

		// Start cell
		path.push(findConnection.startCell());
		// Mark in cell
		path.peek().markInStack();
		// The big loop
		outerloop: do {
			// Check if the top has anywhere to go
			while (bestCell(path.peek()) == null) {
				path.pop().markOutStack();

				// Check if it has returned to the beginning
				if (path.isEmpty()) {
					break outerloop;
				}
			}
			path.push(bestCell(path.peek()));
			path.peek().markInStack();
		} while (!path.peek().isCustomer());

		// Print the success
		if (path.isEmpty()) {
			System.out.println("Path not found");
		} else {
			System.out.println("Path found");
			System.out.println("Number of steps: " + path.size());
		}
	}

	/*
	 * This method is used to find the starting cell of the path
	 * 
	 * @return the start cell
	 */
	private MapCell startCell() {
		return cityMap.getStart();
	}

	/*
	 * This method finds the best cell to go to from the current cell
	 * 
	 * @param MapCell cell is the current cell
	 * 
	 * @return MapCell is the best next cell to go to
	 */
	private static MapCell bestCell(MapCell cell) {
		// Put special precautions on vertical and horizontal switches
		int start = 0;
		int add = 1;
		if (cell.isVerticalSwitch()) {
			add = 2;
		} else if (cell.isHorizontalSwitch()) {
			start = 1;
			add = 2;
		}
		// Loop through to find the best cell
		MapCell neighbour;
		// Customer cell
		for (int i = start; i < 4; i += add) {
			neighbour = cell.getNeighbour(i);
			if (neighbour != null && !neighbour.isMarked() && neighbour.isCustomer()) {
				return neighbour;
			}
		}

		// OmniSwitch cell
		for (int i = start; i < 4; i += add) {
			neighbour = cell.getNeighbour(i);
			if (neighbour != null && !neighbour.isMarked() && neighbour.isOmniSwitch()) {
				return neighbour;
			}
		}

		// Vertical and horizontal cell
		for (int i = start; i < 4; i += add) {
			neighbour = cell.getNeighbour(i);
			if (neighbour != null && !neighbour.isMarked() && neighbour.isVerticalSwitch() && i % 2 == 0) {
				return neighbour;
			} else if (neighbour != null && !neighbour.isMarked() && neighbour.isHorizontalSwitch() && i % 2 != 0) {
				return neighbour;
			}
		}
		return null;
	}
}
