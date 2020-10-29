import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;

/*
 * This class finds the location of the words the user requests
 * @author Filip Durca
 */
public class Searcher {
	private HashTable table;
	private String inputFile;

	/*
	 * This is the class constructor
	 * 
	 * @param HashTable table - the table of data
	 * 
	 * @param String theInputTestFile - the file to read the data from
	 */
	public Searcher(HashTable table, String theInputTestFile) {
		this.table = table;
		inputFile = theInputTestFile;
	}

	/*
	 * This method prints the lexicon location of every word in the file
	 */
	public void findAllWords() {
		// Try to read the file
		try {
			BufferedReader in = new BufferedReader(new FileReader(inputFile));
			String line = in.readLine();

			// Read every line
			while (line != null) {

				// Split the lines into words
				String[] words = line.split("\\s+");

				for (int i = 0; i < words.length; i++) {
					findWord(words[i]);
				}

				// Read the next line
				line = in.readLine();
			}

			// Close the stream
			in.close();

		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);
		}

	}

	/*
	 * This method finds the word's location in the lexicon
	 * 
	 * @param String searchWord - the word being looked for
	 */
	public void findWord(String searchWord) {

		// Find the index of the word in the table
		int indx = table.computeIndex(searchWord);
		BinSearchTree bst = table.getTable()[indx];

		// Check if the word is on some node
		if (bst.getWord(searchWord) == null) {
			// Say the word is not found
			CustomPrinter.wordNotFound(searchWord, inputFile);
		} else {
			// Mark the word as found
			CustomPrinter.wordFound(searchWord, inputFile);
			LinkedList list = bst.getWord(searchWord).getFiles();
			FileNode temp = list.getHead();

			// Print where the word is found
			while (temp != null) {
				CustomPrinter.printPositionsPerFileFound(temp.getFilename(), temp.getPositions(), inputFile);
				temp = temp.getNext();
			}
		}
	}
}
