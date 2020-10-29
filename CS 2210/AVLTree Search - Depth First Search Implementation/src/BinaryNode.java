/*
 * This class is for the binary node object
 * @author Filip Durca
 */
public class BinaryNode {

	// Instance variables
	Pixel value;
	BinaryNode left;
	BinaryNode right;
	BinaryNode parent;

	/*
	 * This method constructs the binary node
	 * 
	 * @param Pixel value is the pixel value of the node
	 * 
	 * @param BinaryNode left is the left child of the node
	 * 
	 * @param BinaryNode right is the right child
	 * 
	 * @param BinaryNode parent is the parent of the node
	 */
	public BinaryNode(Pixel value, BinaryNode left, BinaryNode right, BinaryNode parent) {

		// Set the variables
		this.value = value;
		this.left = left;
		this.right = right;
		this.parent = parent;
	}

	/*
	 * This method creates a binary node with no data
	 */
	public BinaryNode() {

		// Set the variables to null
		value = null;
		left = null;
		right = null;
		parent = null;
	}

	///////////
	// Getters//
	///////////

	/*
	 * This method returns the parent of the node
	 * 
	 * @return BinaryNode the parent of the node
	 */
	public BinaryNode getParent() {
		return parent;
	}

	/*
	 * This method returns the data of the node
	 * 
	 * @return BinaryNode the data of the node
	 */
	public Pixel getData() {
		return value;
	}

	/*
	 * This method returns the left node of the node
	 * 
	 * @return BinaryNode the left node of the node
	 */
	public BinaryNode getLeft() {
		return left;
	}

	/*
	 * This method returns the right node of the node
	 * 
	 * @return BinaryNode the right node of the node
	 */
	public BinaryNode getRight() {
		return right;
	}

	///////////
	// Setters//
	///////////

	/*
	 * This method sets the parent of the node
	 * 
	 * @param BinaryNode the parent of the node
	 */
	public void setParent(BinaryNode parent) {
		this.parent = parent;
		;
	}

	/*
	 * This method sets the data of the node
	 * 
	 * @param BinaryNode the data of the node
	 */
	public void setData(Pixel data) {
		this.value = data;
		;
	}

	/*
	 * This method sets the left node of the node
	 * 
	 * @param BinaryNode the left node of the node
	 */
	public void setLeft(BinaryNode left) {
		this.left = left;
		;
	}

	/*
	 * This method sets the right node of the node
	 * 
	 * @param BinaryNode the right node of the node
	 */
	public void setRight(BinaryNode right) {
		this.right = right;
		;
	}

	/////////////////
	// Other Methods//
	/////////////////

	/*
	 * This method checks if the node is a leaf
	 * 
	 * @return boolean the boolean value of if the node is a leaf
	 */
	public boolean isLeaf() {
		return (getLeft() == null);
	}
}
