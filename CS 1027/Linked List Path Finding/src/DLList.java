/*
 * This class constructs the doubly linked list data structure
 * @author Filip Durca
 */

public class DLList<T> implements DLListADT<T> {

	// Variables
	private DLNode<T> front;
	private DLNode<T> rear;
	private int count = 0;

	/*
	 * This method creates an empty doubly linked list
	 */
	public DLList() {
		front = null;
		rear = null;
	}

	/*
	 * This method is used to add a dataitem to the back of the list
	 * 
	 * @param T dataItem - the dataItem on the node
	 * 
	 * @param int value - the integer value of the node
	 */
	public void insert(T dataItem, int value) {
		// Create a new node
		DLNode<T> temp = new DLNode<T>(dataItem, value);

		// Add it to the list and check if the list is empty
		if (size() == 0) {
			front = temp;
			rear = temp;
		} else {
			rear.setNext(temp);
			temp.setPrevious(rear);
			rear = temp;
		}
		count++;
	}

	/*
	 * This method is used to return the value of the dataItem in the list
	 * 
	 * @param T dataItem - the item being checked
	 * 
	 * @return int - the value of the node
	 */
	public int getDataValue(T dataItem) throws InvalidDataItemException {
		DLNode<T> temp = front;
		// Loop through the list to find the node with the data
		for (int i = 0; i < size(); i++) {
			if (temp.getData().equals(dataItem)) {
				return temp.getValue();
			}
			temp = temp.getNext();
		}
		// Throw an exception if the item is not found
		throw new InvalidDataItemException(dataItem.toString());
	}

	/*
	 * This method is used to change the value of a node
	 * 
	 * @param T dataItem - the item being checked
	 * 
	 * @param int newValue - the new value of the node
	 */
	public void changeValue(T dataItem, int newValue) throws InvalidDataItemException {
		if (size() != 0) {
			DLNode<T> temp = front;
			// Loop through the list to find the node with the data
			for (int i = 0; i < size(); i++) {
				if (temp.getData().equals(dataItem)) {
					temp.setValue(newValue);
					return;
				}
				temp = temp.getNext();
			}
		}
		// Throw an exception if the item is not found
		throw new InvalidDataItemException(dataItem.toString());
	}

	/*
	 * This method removes and returns the node with the smallest value
	 * 
	 * @return DLNode<T> - the node with the smallest value
	 */
	public T getSmallest() throws EmptyListException {
		// Throw exception if the list is empty
		if (isEmpty()) {
			throw new EmptyListException("List");
		} else {
			DLNode<T> temp = front;
			DLNode<T> smallest = temp;
			// Loop through the list to find the node with the smallest value
			for (int i = 0; i < size(); i++) {
				if (temp.getValue() < smallest.getValue()) {
					smallest = temp;
				}
				temp = temp.getNext();
			}

			// Remove the smallest node from the linked list
			// Check if its in the middle
			if (smallest.getPrevious() != null && smallest.getNext() != null) {
				smallest.getNext().setPrevious(smallest.getPrevious());
				smallest.getPrevious().setNext(smallest.getNext());
			}
			// Check if its in the rear
			if (smallest.getPrevious() != null && smallest.getNext() == null) {
				smallest.getPrevious().setNext(null);
				rear = smallest.getPrevious();
			}
			// Check if it is the front
			if (smallest.getPrevious() == null && smallest.getNext() != null) {
				smallest.getNext().setPrevious(null);
				front = smallest.getNext();
			}
			// Check if it is the front and the rear
			if (smallest.getPrevious() == null && smallest.getNext() == null) {
				rear = null;
				front = null;
			}
			count--;

			// Return the node
			return smallest.getData();
		}
	}

	/*
	 * This method checks if the list is empty
	 * 
	 * @return boolean - true for empty, false for not
	 */
	public boolean isEmpty() {
		return (size() == 0);
	}

	/*
	 * This method returns the size of the list
	 * 
	 * @return int - the amount of objects in the list
	 */
	public int size() {
		return count;
	}

	/*
	 * This method returns a string representation of the list
	 */
	public String toString() {
		DLNode<T> temp = front;
		String s = "List: " + temp.getData().toString() + " " + temp.getValue();
		for (int i = 1; i < size(); i++) {
			temp = temp.getNext();
			s += ", " + temp.getData().toString() + " " + temp.getValue();
			;
		}
		return s;
	}
}
