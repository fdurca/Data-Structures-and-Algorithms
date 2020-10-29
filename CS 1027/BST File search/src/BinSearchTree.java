/*
 * This class creates a binary search tree object
 * @author Filip Durca
 */
public class BinSearchTree {
	// Instance Variables
	private BinSearchTreeNode root;

	/*
	 * This method returns the node containing the search word
	 * 
	 * @param String searchWord - the word being searched for
	 * 
	 * @return BinSearchTreeNode - the node containing that word
	 */
	public BinSearchTreeNode getWord(String searchWord) {
		return search(root, searchWord);
	}

	/*
	 * This method returns the node containing the serach word
	 * 
	 * @param BinSearchTreeNoder - the root being searched
	 * 
	 * @param String sWord - the word being searched for
	 * 
	 * @return BinSearchTreeNode - the node containing the search word
	 */
	private BinSearchTreeNode search(BinSearchTreeNode r, String sWord) {
		// Look for the word
		// Check if the tree is empty
		if (r == null) {
			return null;
			// Check if the word is found
		} else if (r.getWord().equals(sWord)) {
			return r;
			// Check if the word is in the left
		} else if (r.getWord().compareTo(sWord) > 0) {
			return search(r.getLeft(), sWord);
			// All else fails, return the right
		} else {
			return search(r.getRight(), sWord);
		}
	}

	/*
	 * This method inserts a word into the binary search tree
	 * 
	 * @param String theWord - the word being added
	 * 
	 * @param String theFileName - the name of the file
	 * 
	 * @param int thePosition - the position of the new word
	 */
	public void insertWord(String theWord, String theFileName, int thePosition) {
		// See if the word is already contained in a node
		BinSearchTreeNode r = this.getWord(theWord);

		// Check if the root is null
		if (r == null) {
			insert(root, theWord, theFileName, thePosition);

			// Check if the root contains the word
		} else if (r.getWord().equals(theWord)) {
			LinkedList newFiles = r.getFiles();
			newFiles.insertWord(theFileName, thePosition);
		}
	}

	/*
	 * This method inserts the new node into the binary tree
	 * 
	 * @param BinSearchTreeNode r - the node being added
	 * 
	 * @param String theWord - the word being added
	 * 
	 * @param String theFileName - the name of the file
	 * 
	 * @param int thePosition - the position of the new word
	 * 
	 */
	private void insert(BinSearchTreeNode r, String theWord, String theFileName, int thePosition) {

		// Use the algorithm
		if (r == null) {
			root = new BinSearchTreeNode(theWord, theFileName, thePosition);

		} else if (theWord.compareTo(r.getWord()) < 0) {
			if (r.getLeft() == null) {
				r.setLeft(new BinSearchTreeNode(theWord, theFileName, thePosition));
			} else {
				insert(r.getLeft(), theWord, theFileName, thePosition);
			}

		} else if (r.getRight() == null) {
			r.setRight(new BinSearchTreeNode(theWord, theFileName, thePosition));
		} else {
			insert(r.getRight(), theWord, theFileName, thePosition);
		}
	}
}
