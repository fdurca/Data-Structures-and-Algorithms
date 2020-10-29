
/*
 * This class creates a dictionary for records

 * 
 * @User Filip Durca - 251008743]
 * @Date October 12, 2019
 */

//Imports
import java.util.*;

public class Dictionary implements DictionaryADT {

	// Variables
	private LinkedList<Record>[] dict;
	private int size = 7951;
	boolean thing = true;

	// Class initializer
	public Dictionary(int size) {

		this.size = size;
		// Create the dictionary table
		dict = (LinkedList<Record>[]) new LinkedList[size];

		// Initialize the linked lists within the array
		for (int i = 0; i < size; i++) {
			dict[i] = new LinkedList<Record>();
		}
	}

	/*
	 * This method is a hash function. It is used to hash data and then store it
	 * 
	 * @param String str is the string being hashed
	 * 
	 * @return int is the integer hashed from the string
	 */
	private int hash(String str) {

		// Use the characters of the hash to do a polynomial hash
		// Set a variable to the first character
		int hash = 0;

		// Loop through the string and perform polynomial hash
		for (int i = 0; i < str.length(); i++) {
			// Compress the hash functions
			hash = (33 * hash + str.charAt(i)) % size;
		}

		return hash;

	}

	/*
	 * This method is used to insert a pair into the linked list after being hashed
	 * 
	 * @param Record pair the pair being added
	 * 
	 * @return int 0 for no collision or 1 for side chaining
	 */
	public int insert(Record pair) throws DictionaryException {
		// If the pair doesnt exist in the linked list array, add it
		if (dict[hash(pair.getConfig())].size() == 0) {
			dict[hash(pair.getConfig())].add(pair);

			// Return 0 for collision free
			return 0;

		}
		// If the pair does exist, side chain
		else {
			// Loop through until the next available space in the array
			for (int i = 0; i < dict[hash(pair.getConfig())].size(); i++) {
				// Throw an exception if the pair's configuration is the same as the existing
				// element's configuration
				if (dict[hash(pair.getConfig())].get(i).getConfig() == pair.getConfig())
					throw new DictionaryException();
			}

			// If the configuration is different, add it
			dict[hash(pair.getConfig())].add(pair);

			// Return 1 to indicate sidechaining
			return 1;
		}

	}

	/*
	 * This method is used to remove a configuration from the dictionary
	 * 
	 * @param String config the configuration being removed
	 */
	public void remove(String config) throws DictionaryException {

		// Loop through the array located at the hash of the configuration
		for (int i = 0; i < dict[hash(config)].size(); i++) {

			// Check to make sure the configuration being removed is the correct one
			if (dict[hash(config)].get(i).getConfig().equals(config)) {

				// Remove it from the dictionary
				dict[hash(config)].remove(i);
				return;
			}
		}

		// Throw an error if the configuration is not found
		throw new DictionaryException();
	}

	/*
	 * Get the score of a configuration
	 * 
	 * @param String config is the configuration
	 * 
	 * @return int is the score of the configuration
	 */
	public int get(String config) {
		// Loop through the array located at the hash of the configuration
		for (int i = 0; i < dict[hash(config)].size(); i++) {

			// Check to make sure the configuration being returned is the correct one
			if (dict[hash(config)].get(i).getConfig().equals(config)) {
				// Return the score
				return dict[hash(config)].get(i).getScore();
			}
		}

		// Return -1 if it is not found
		return -1;
	}

	/*
	 * This method returns the number of elements in the list
	 * 
	 * @return int the number of elements
	 */
	public int numElements() {
		// Set the number of elements to 0
		int numElements = 0;

		// Go through the entire
		for (int i = 0; i <= dict.length - 1; i++) {

			// Add the size of each linked to the total number of elements
			numElements += dict[i].size();
		}

		// Return the number of elements
		return numElements;
	}

}
