
/*
 * This method is used to find the shortest path to the customer, if any
 * @author Filip Durca
 */
import java.io.FileNotFoundException;
import java.io.IOException;

public class ShortestPath {
	Map cityMap;

	/*
	 * This method is the class constructor
	 */
	public ShortestPath(String filename) {
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

	// Main method
	public static void main(String[] args) {
		if (args.length < 1) {
			System.out.println("You must provide the name of the input file");
			System.exit(0);
		}
		String mapFileName = args[0];

		// Create the map
		ShortestPath shortestPath = new ShortestPath(mapFileName);

		// Find the shortest path
		DLList<MapCell> path = new DLList<MapCell>();
		// Checking if the customer cell is found
		boolean found = false;

		// variables
		int smallestDistance;
		int tempDistance;
		MapCell smallest;
		MapCell temp;
		int steps = 0;

		// Start cell
		MapCell start = shortestPath.startCell();
		path.insert(start, 0);
		start.markInList();

		// The big loop
		while (!path.isEmpty() && found == false) {

			// Remove the smallest cell
			smallest = path.getSmallest();
			// Mark out of list and find the next cell
			smallest.markOutList();

			// Check for customer
			if (smallest.isCustomer()) {
				found = true;
				steps = smallest.getDistanceToStart();
			} else {

				while (shortestPath.nextCell(smallest) != null) {

					temp = shortestPath.nextCell(smallest);

					// Determine the path
					smallestDistance = 1 + smallest.getDistanceToStart();

					// Check if the next cell is farther from the start
					if (temp.getDistanceToStart() > smallestDistance) {
						temp.setDistanceToStart(smallestDistance);
						temp.setPredecessor(smallest);

					}

					// Update tempDistance
					tempDistance = temp.getDistanceToStart();

					// Update the list
					if (temp.isMarkedInList() && tempDistance < path.getDataValue(temp)) {
						path.changeValue(temp, tempDistance);
					} else if (!temp.isMarkedInList()) {
						path.insert(temp, tempDistance);
						temp.markInList();
					}
				}
			}
		}

		// Print the results
		if (found == true) {
			System.out.println("Shortest path: " + (steps + 1));
		} else {
			System.out.println("No path was found");
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
	 * This method finds the next cell to go to from the current cell
	 * 
	 * @param MapCell cell is the current cell
	 * 
	 * @return MapCell is the next cell to go to
	 */
	private MapCell nextCell(MapCell cell) {
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

		// Find first available cell
		for (int i = start; i < 4; i += add) {
			neighbour = cell.getNeighbour(i);
			if (neighbour != null && !neighbour.isMarkedOutList() && !neighbour.isMarkedInList()) {
				if (neighbour.isOmniSwitch() || neighbour.isCustomer()) {
					return neighbour;
				}
				if (i % 2 == 0 && neighbour.isVerticalSwitch()) {
					return neighbour;
				}
				if (i % 2 != 0 && neighbour.isHorizontalSwitch()) {
					return neighbour;
				}
			}
		}
		return null;
	}
}
