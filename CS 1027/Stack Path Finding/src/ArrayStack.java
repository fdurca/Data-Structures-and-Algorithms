/*
 * This code is used to create an ArrayStack
 * @Author Filip Durca 251008743
 */
public class ArrayStack<T> implements ArrayStackADT<T> {
	private T[] stack;
	private int top = -1;
	private int capacity = 20;

	/*
	 * This method is used to the initialize the stack
	 */
	public ArrayStack() {
		stack = (T[]) (new Object[capacity]);
	}

	/*
	 * This method is used to initialize a stack with a certain capacity
	 * 
	 * @param int initialCapacity is the capacity of the user's choosing
	 */
	public ArrayStack(int initialCapacity) {
		capacity = initialCapacity;
		stack = (T[]) (new Object[capacity]);
	}

	/*
	 * This method adds an item to the stack
	 * 
	 * @param T dataItem is the item of type T being added to the stack
	 */
	public void push(T dataItem) {
		top++;
		stack[top] = dataItem;

		// Check if it has hit capacity
		if (size() == capacity) {
			if (capacity < 100) {
				capacity *= 2;
			} else {
				capacity += 50;
			}

			// Create a new ArrayStack
			T[] tempStack = (T[]) (new Object[capacity]);
			for (int i = 0; i < size(); i++) {
				tempStack[i] = stack[i];
			}

			// Replace stack
			stack = tempStack;
		}
	}

	/*
	 * This method removes and returns the top of the stack
	 * 
	 * @return T is the top of the stack
	 */
	public T pop() throws EmptyStackException {
		if (isEmpty()) {
			throw new EmptyStackException("stack");
		}
		T result = stack[top];
		stack[top] = null;
		top--;

		// Check if the size is less than a third of capacity
		if (size() < (capacity / 3) && capacity > 20) {
			capacity /= 2;

			// Create a new ArrayStack
			T[] tempStack = (T[]) (new Object[capacity]);
			for (int i = 0; i < size(); i++) {
				tempStack[i] = stack[i];
			}

			// Replace stack
			stack = tempStack;
		}

		return result;
	}

	/*
	 * This method is used to return the top of the stack
	 * 
	 * @return T is the top of the stack
	 */
	public T peek() throws EmptyStackException {
		if (isEmpty()) {
			throw new EmptyStackException("stack");
		} else {
			return stack[top];
		}
	}

	/*
	 * This method checks if the array is empty
	 * 
	 * @return Boolean is a true or false value of if the stack holds no values
	 */
	public boolean isEmpty() {
		return (top == -1);
	}

	/*
	 * This method returns the size of the arraystack
	 * 
	 * @return int is the size of the arraystack
	 */
	public int size() {
		return (top + 1);
	}

	/*
	 * This returns returns the capacity of the arraystack
	 * 
	 * @return int is the max capacity of the arraystack
	 */
	public int length() {
		return capacity;
	}

	/*
	 * This method returns the string interpretation
	 * 
	 * @return String is a string value representing this arrayStack
	 */
	public String toString() {
		String s = "Stack: " + stack[0];
		for (int i = 1; i < size(); i++) {
			s += ", " + stack[i];
		}
		return s;
	}
}
