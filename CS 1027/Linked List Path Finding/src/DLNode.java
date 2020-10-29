/*
 * This node is used in the doubly linked list data structure
 * @author Filip Durca
 */
public class DLNode<T> {
	// Variables
	private T dataItem;
	private DLNode<T> next = null;
	private DLNode<T> previous = null;
	private int value;

	/*
	 * Initializes the node
	 * 
	 * @param T data - the data in the node
	 * 
	 * @param int value - the associated integer value to the data
	 */
	public DLNode(T data, int value) {
		dataItem = data;
		this.value = value;
	}

	////////////////////
	// GETTER METHODS //
	////////////////////

	/*
	 * This method returns the value of the node
	 * 
	 * @return int the nodes integer value
	 */
	public int getValue() {
		return value;
	}

	/*
	 * This method returns the data of the node
	 * 
	 * @return data the nodes dataitem
	 */
	public T getData() {
		return dataItem;
	}

	/*
	 * This method returns the node following the current node
	 * 
	 * @return DLNode<T> the next node
	 */
	public DLNode<T> getNext() {
		return next;
	}

	/*
	 * This method returns the node preceding the current node
	 * 
	 * @return DLNode<T> the previous node
	 */
	public DLNode<T> getPrevious() {
		return previous;
	}

	////////////////////
	// SETTER METHODS //
	////////////////////
	/*
	 * This method sets the value of the node
	 * 
	 * @param int newValue - the new integer value
	 */
	public void setValue(int newValue) {
		value = newValue;
	}

	/*
	 * This method sets the data of the node
	 * 
	 * @param T newData - the new data
	 */
	public void setData(T newData) {
		dataItem = newData;
	}

	/*
	 * This method sets the node following the current node
	 * 
	 * @param DLNode<T> newNext - the new next node
	 */
	public void setNext(DLNode<T> newNext) {
		next = newNext;
	}

	/*
	 * This method sets the node preceding the current node
	 * 
	 * @param DLNode<T> newPrevious
	 */
	public void setPrevious(DLNode<T> newPrevious) {
		previous = newPrevious;
	}

}
