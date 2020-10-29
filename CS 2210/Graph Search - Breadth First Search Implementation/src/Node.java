/*
 * This class holds a node object and its methods
 * @author Filip Durca
 * 
 */
public class Node {

	// Variables
	int name;
	boolean mark;

	/*
	 * This method is the constructor
	 * 
	 * @param int name is the integer value of the node
	 */
	Node(int name) {
		// Initialize the variables
		this.name = name;
		mark = false;
	}

	/*
	 * This method changes the mark value
	 * 
	 * @param boolean mark is the mark value
	 */
	void setMark(boolean mark) {
		this.mark = mark;
	}

	/*
	 * This method returns the mark value
	 * 
	 * @return boolean is the mark value
	 */
	boolean getMark() {
		return mark;
	}

	/*
	 * This method returns the int value of the node
	 * 
	 * @return int the value of the node
	 */
	int getName() {
		return name;
	}
}
