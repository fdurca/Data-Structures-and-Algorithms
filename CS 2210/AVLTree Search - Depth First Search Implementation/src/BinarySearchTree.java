/*
 * This class is used to hold all the methods for the object 
 */
public class BinarySearchTree implements BinarySearchTreeADT {

	// Instance variables
	BinaryNode root;

	/*
	 * This method creates an empty binary search tree
	 */
	public BinarySearchTree() {
		root = new BinaryNode();
	}

	/*
	 * This method gets the node at location key
	 * 
	 * @param BinaryNode r the root of the tree/subtree
	 * 
	 * @param Location key is the key being searched for
	 * 
	 * @return Pixel the pixel with that key
	 */
	public Pixel get(BinaryNode r, Location key) {
		// Return null if the key is null
		if (r.isLeaf()) {
			return r.getData();
		}

		// Check to see if the the key is on the current node
		if (r.getData().getLocation().compareTo(key) == 0) {
			return r.getData();
		}

		// Check to see if the the key is greater than the current node
		if (r.getData().getLocation().compareTo(key) == -1) {
			return get(r.getRight(), key);
		}

		// If not equal or greater, go left
		return get(r.getLeft(), key);

	}

	/*
	 * This method gets the root of the tree
	 * 
	 * @return BinaryNode the root of the tree
	 */
	public BinaryNode getRoot() {
		return root;
	}

	/*
	 * This method inserts the data into the bst
	 * 
	 * @param BinaryNode r is the root of the tree/subtree
	 * 
	 * @param Pixel data is the data being stored
	 */
	public void put(BinaryNode r, Pixel data) throws DuplicatedKeyException {

		// Find where the leaf should be
		BinaryNode p = getNode(r, data.getLocation());

		// If its not a leaf, throw an exception
		if (!p.isLeaf()) {
			throw new DuplicatedKeyException();

		}
		// Create the node
		else {
			p.setData(data);
			p.setLeft(new BinaryNode());
			p.getLeft().setParent(p);
			p.setRight(new BinaryNode());
			p.getRight().setParent(p);
		}
	}

	/*
	 * This method is used to remove a node with the key given
	 * 
	 * @param BinaryNode r is the root of the tree/subtree
	 * 
	 * @param Location key is the key being looked for
	 */
	public void remove(BinaryNode r, Location key) throws InexistentKeyException {

		// Get the node you wish to delete
		BinaryNode delete = getNode(r, key);

		// Check if the node is a leaf, throw exception
		if (delete.isLeaf())
			throw new InexistentKeyException();

		// Check if a node has a child that is a leaf
		if (delete.getLeft().isLeaf() || delete.getRight().isLeaf()) {
			BinaryNode parent = delete.getParent();

			// Create a child and set it to the other child
			BinaryNode child;
			if (delete.getLeft().isLeaf())
				child = delete.getRight();
			else
				child = delete.getLeft();

			// Replace the root if the root is being deleted
			if (root.equals(delete)) {
				root = child;
				root.setParent(null);
			}
			// Replace the deleted node with its child
			else if (parent.getLeft().equals(delete)) {
				parent.setLeft(child);
				child.setParent(parent);
			} else {
				parent.setRight(child);
				child.setParent(parent);
			}

		} else {
			// Get the smallest node
			BinaryNode small = getNode(root, smallest(delete.getRight()).getLocation());

			// Set the data from the small note to the node being deleted
			delete.setData(small.getData());

			// Recursive call
			remove(small, small.getData().getLocation());

		}
	}

	/*
	 * This method returns the smallest node larger than a given value
	 * 
	 * @param r is the root of the tree
	 * 
	 * @param location key is the max key
	 * 
	 * @return Pixel the smallest pixel larger than the given info
	 */
	public Pixel successor(BinaryNode r, Location key) {

		// Check if it is a leaf
		if (r.isLeaf())
			return null;

		// Create a temporary node
		BinaryNode p = getNode(r, key);

		// Check if p is an internal node
		if (!p.isLeaf() && !p.getRight().isLeaf()) {
			return smallest(p.getRight());
		}
		// If p is a leaf
		BinaryNode parent = p.getParent();

		// Loop through the left side until p = r
		while (!p.equals(root) && p.equals(parent.getRight())) {
			p = parent;
			parent = p.getParent();
		}

		// If parent is null, return null
		if (p.equals(r))
			return null;

		// Otherwise, return the parent
		return parent.getData();

	}

	/*
	 * This method returns the largest node smaller than a given value
	 * 
	 * @param r is the root of the tree
	 * 
	 * @param location key is the max key
	 * 
	 * @return Pixel the largest pixel smaller than the given info
	 */
	public Pixel predecessor(BinaryNode r, Location key) {

		// Check if it is a leaf
		if (r.isLeaf())
			return null;

		// Create a temporary node
		BinaryNode p = getNode(r, key);

		// Check if p is an internal node
		if (!p.isLeaf() && !p.getLeft().isLeaf()) {
			return largest(p.getLeft());
		}
		// If p is a leaf
		BinaryNode parent = p.getParent();

		// Loop through the left side until p = r
		while (p != r && p == parent.getLeft()) {
			p = parent;
			parent = parent.getParent();
		}

		// If parent is null, return null
		if (parent == null)
			return null;

		// Otherwise, return the parent
		return parent.getData();

	}

	/*
	 * This method returns the smallest pixel
	 * 
	 * @param BinaryNode r is root of the tree/subtree
	 * 
	 * @return Pixel is the smallest pixel
	 */
	public Pixel smallest(BinaryNode r) throws EmptyTreeException {

		// Throw exception if the tree is empty
		if (r.isLeaf()) {
			throw new EmptyTreeException();
		}

		// Temporary Node
		BinaryNode p = r;
		// Go as far possible to the left
		while (!p.isLeaf()) {
			p = p.getLeft();
		}

		return p.getParent().getData();

	}

	/*
	 * This method returns the largest pixel
	 * 
	 * @param BinaryNode r is root of the tree/subtree
	 * 
	 * @return Pixel is the largest pixel
	 */
	public Pixel largest(BinaryNode r) throws EmptyTreeException {

		// Throw exception if the tree is empty
		if (r.isLeaf()) {
			throw new EmptyTreeException();
		}

		// Temporary Node
		BinaryNode p = r;
		// Go as far possible to the left
		while (!p.isLeaf()) {
			p = p.getRight();
		}

		return p.getParent().getData();

	}

	/*
	 * This method gets the node at location key
	 * 
	 * @param BinaryNode r the root of the tree/subtree
	 * 
	 * @param Location key is the key being searched for
	 * 
	 * @return Pixel the pixel with that key
	 */
	private BinaryNode getNode(BinaryNode r, Location key) {

		// Return null if the key is null
		if (r.isLeaf()) {
			return r;
		}

		// Check to see if the the key is on the current node
		if (r.getData().getLocation().compareTo(key) == 0) {
			return r;
		}

		// Check to see if the the key is greater than the current node
		if (r.getData().getLocation().compareTo(key) == -1) {
			return getNode(r.getRight(), key);
		}

		// If not equal or greater, go left
		return getNode(r.getLeft(), key);

	}

}
