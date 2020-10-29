/*
 * This method holds the edge object and its methods
 * @author Filip Durca
 */
public class Edge {

	// Variables
	Node u;
	Node v;
	int type;

	/*
	 * This method is the constructor
	 * 
	 * @param Node u is the first endpoint
	 * 
	 * @param Node v is the second endpoint
	 * 
	 * @param int type is the int value of the type of road
	 */
	Edge(Node u, Node v, int type) {
		// Set the variables
		this.u = u;
		this.v = v;
		this.type = type;
	}

	/*
	 * This method returns the first endpoint
	 * 
	 * @return node the node of the endpoint
	 */
	Node firstEndpoint() {
		return u;
	}

	/*
	 * This method returns the second endpoint
	 * 
	 * @return node the node of the endpoint
	 */
	Node secondEndpoint() {
		return v;
	}

	/*
	 * This method returns the type of road
	 * 
	 * @return int the int value of the road
	 */
	int getType() {
		return type;
	}
}
