/*
 * This class represents the road map.
 * @author Filip Durca
 */

//Imports
import java.util.*;
import java.io.*;

public class RoadMap {

	// Instance Variables
	Graph graph;
	int start;
	int end;

	int width;
	int leng;

	int initialMoney;
	int toll;
	int gain;

	Stack<Node> stack;

	/*
	 * This is the constructor method
	 * 
	 * @param String inputFile has all the necessary information
	 */
	RoadMap(String inputFile) {

		// Try to open the file
		try {
			// Open the file
			File file = new File(inputFile);

			// Read the file
			BufferedReader br = new BufferedReader(new FileReader(file));

			// Skip the first line
			br.readLine();

			// Save variables
			start = Integer.parseInt(br.readLine());
			end = Integer.parseInt(br.readLine());

			width = Integer.parseInt(br.readLine());
			leng = Integer.parseInt(br.readLine());

			initialMoney = Integer.parseInt(br.readLine());
			toll = Integer.parseInt(br.readLine());
			gain = Integer.parseInt(br.readLine());

			// Set up the graph
			graph = new Graph(width * leng);

			// Set up variables
			String str;
			int value = 2;
			int row = 0;
			int node = 0;

			// Loop through every road and build the graph
			while ((str = br.readLine()) != null) {

				// Traverse the line
				for (int i = 1 + (-1 * (row % 2)); i < str.length(); i += 2) {
					// Check the value of the piece
					if (str.charAt(i) == 'F')
						value = 0;
					else if (str.charAt(i) == 'T')
						value = 1;
					else if (str.charAt(i) == 'C')
						value = -1;

					// Add it only if the value was changed
					if (value != 2) {
						// Horizontal
						if (row % 2 == 0) {
							graph.insertEdge(graph.getNode(node), graph.getNode(node + 1), value);
						}
						// Vertical
						else {
							graph.insertEdge(graph.getNode(node - width), graph.getNode(node), value);
						}

						// Reset value
						value = 2;
					}

					// Increase the node
					node++;

				}

				// Increase the row
				if (row % 2 != 0)
					node = node - width;
				else
					node++;
				row++;

			}

			// Close the input stream
			br.close();

			// Create the path stack
			stack = new Stack<Node>();

		}
		// Catch the exception
		catch (Exception e) {
			throw new MapException();
		}
	}

	/*
	 * This method returns the graph stored in the map
	 * 
	 * @return Graph the graph representation of the map
	 */
	Graph getGraph() {
		return graph;
	}

	/*
	 * This methods returns the starting node of the map
	 * 
	 * @return int the integer representation of the node
	 */
	int getStartingNode() {
		return start;
	}

	/*
	 * This methods returns the destination node of the map
	 * 
	 * @return int the integer representation of the node
	 */
	int getDestinationNode() {
		return end;
	}

	/*
	 * This methods returns the starting money the driver has
	 * 
	 * @return int the integer representation of the money available
	 */
	int getInitialMoney() {
		return initialMoney;
	}

	/*
	 * This method finds the path to the destination from the start
	 * 
	 * @param int start is the starting position
	 * 
	 * @param int destination is the final position
	 * 
	 * @param initialMoney is the money the user has
	 * 
	 * @return Iterator an iterator of the stack holding the path
	 */
	Iterator<Node> findPath(int start, int destination, int initialMoney) {
		// Create a stack to hold the path
		System.out.println("Start");
		if (path(start, destination, initialMoney))
			return stack.iterator();

		// If no path found, return null
		return null;
	}

	/*
	 * This method helps create the path by checking if the path is possible
	 * 
	 * @param int start is the starting piece
	 * 
	 * @param int destination is the destination node
	 * 
	 * @param int initialMoney is the money on the account
	 * 
	 * @return boolean true if a path is possible
	 */
	private boolean path(int start, int destination, int initialMoney) {
		// Make and mark the starting node
		Node current = graph.getNode(start);
		current.setMark(true);
		Node next;

		// Create an empty edge
		Edge e;

		// Variables
		int money;

		// Push it into the stack
		stack.push(current);

		// Check if destination has been reached
		if (start == destination)
			return true;

		// Find the next node
		else {

			Iterator<Edge> edges = graph.incidentEdges(current);

			// Loop through adjacent nodes
			while (edges.hasNext()) {

				// Check the edges
				e = edges.next();

				// Check which node to check
				if (e.firstEndpoint().getName() == start)
					next = e.secondEndpoint();
				else
					next = e.firstEndpoint();
				if (next.getMark() == false) {

					// Add or remove funds as needed
					if (e.getType() == -1)
						money = initialMoney + gain;
					else if (e.getType() == 1)
						money = initialMoney - toll;
					else
						money = initialMoney;

					// Check if viable path
					if (money >= 0 && path(next.getName(), destination, money))
						return true;
				}

			}
			// Remove the node from the stack
			current.setMark(false);
			stack.pop();
			// No path found, return false
			return false;
		}
	}

}
