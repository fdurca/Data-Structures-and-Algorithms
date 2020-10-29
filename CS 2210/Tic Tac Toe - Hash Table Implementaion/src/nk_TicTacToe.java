/*
 * This class contains all the methods necessary for the computer to run tic-tac-toe
 * 
 * @User Filip Durca - 251008743
 * @Date October 12, 2019
 */
public class nk_TicTacToe {

	// Instance Variables
	char[][] gameBoard;
	int boardSize;
	int inline;
	int maxLevel;

	/*
	 * This method is the constructor
	 * 
	 * @param int board_size is the dimension of the board
	 * 
	 * @param int inline is the amount of symbols needed in a row to win
	 * 
	 * @param int max_levels is the farthest the computer will search for moves
	 */
	public nk_TicTacToe(int board_size, int inline, int max_levels) {

		// Initialize the game board
		this.gameBoard = new char[board_size][board_size];
		// Turn every space empty
		for (int i = 0; i < board_size; i++) {
			for (int j = 0; j < board_size; j++) {
				gameBoard[i][j] = ' ';
			}
		}

		// Save the remaining instance variables
		this.boardSize = board_size;
		this.inline = inline;
		this.maxLevel = max_levels;
	}

	/*
	 * This function is used to create a new dictionary
	 * 
	 * @return Dictionary is a dictionary of the specified size
	 */
	public Dictionary createDictionary() {
		Dictionary d = new Dictionary(boardSize);
		return d;
	}

	/*
	 * This method is used to see if the configuration is already in the dictionary
	 * 
	 * @param Dictionary configurations is a dictionary of the possible moves
	 * 
	 * @return int returns the associated score to the configuration or -1
	 */
	public int repeatedConfig(Dictionary configurations) {
		return configurations.get(getConfig());
	}

	/*
	 * This method is used to insert the configuration into the configuration
	 * dictionary
	 * 
	 * @param Dictionary configurations is a dictionary of the possible moves
	 * 
	 * @param int score is the score of the current configuration
	 * 
	 * @return int returns the associated score to the configuration or -1
	 */
	public void insertConfig(Dictionary configurations, int score) {
		// Create a record
		Record pair = new Record(getConfig(), score);
		// Insert the record
		configurations.insert(pair);
		return;
	}

	/*
	 * This method is used to store the symbol on the game board
	 * 
	 * @param int row is the row of the move
	 * 
	 * @param int col is the column of the move
	 * 
	 * @param char symbol is the symbol being placed
	 */
	public void storePlay(int row, int col, char symbol) {
		// Store the symbol
		gameBoard[row][col] = symbol;
		return;
	}

	/*
	 * This method is used to check if a square is empty
	 * 
	 * @param int row is the row of the square being checked
	 * 
	 * @param int col is the column of the square being checked
	 * 
	 * @return boolean letting the user know if the square is empty
	 */
	public boolean squareIsEmpty(int row, int col) {
		return (gameBoard[row][col] == (' '));
	}

	/*
	 * This method is used to check if someone has won
	 * 
	 * @param char symbol is the symbol being checked
	 * 
	 * @return boolean letting the user know if someone has won
	 */
	public boolean wins(char symbol) {

		// Variable to check if a match has been found
		int check = 0;

		// Initialize the matching combo (the symbol inline amount of times)
		String winner = "" + symbol;
		for (int i = 0; i < inline - 1; i++) {
			winner += symbol;
		}

		// Strings to store rows, columns, and diagonals
		String rows = "";
		String cols = "";
		String diag = "";

		// Used to help with the diagonal algorithm
		String diagAssist = "";

		// Get the configuration
		String config = getConfig();

		// Fill the string with the string configurations for each row and column
		for (int i = 0; i < boardSize; i++) {

			// Use substrings to fill the row string
			rows += config.substring(i * boardSize, (i + 1) * boardSize) + "-";

			// Fill the column string using a different for loop
			for (int j = 0; j < config.length(); j += boardSize) {
				cols += config.charAt(j + i);
			}

			// Add a gap to columns
			cols += "-";

			// Diagonals
			// Fill the string using a different for loop
			// Check to the right
			if (i + inline <= boardSize) {
				for (int j = 0; j + i < config.length(); j += boardSize + 1) {
					diag += config.charAt(i + j);
					diagAssist += config.charAt(config.length() - (i + j) - 1);
				}
			}

			// Add the diagonal assist
			diag += "-" + diagAssist;
			// Reset the diagonal assist
			diagAssist = "";

			// Check to the left
			if (i - inline >= -1) {
				for (int j = 0; j + i < config.length() - boardSize + 1; j += boardSize - 1) {
					diag += config.charAt(j + i);
					diagAssist += config.charAt(config.length() - (j + i) - 1);
				}
			}
			// Add the diagonal assist
			diag += "-" + diagAssist;
			// Reset the diagonal assist
			diagAssist = "";

			// Add a gap to diagonals
			diag += "-";
			// Reset the diagonal assist
			diagAssist = "";

		}

		// Check the rows and columns to see if it contains a winner
		if (rows.contains(winner) || cols.contains(winner) || diag.contains(winner)) {
			return true;
		}

		// Return false if no match has been found
		return false;

	}

	/*
	 * This method is used to detect if a draw has been reached
	 * 
	 * @return boolean letting the user know if a draw has been reached
	 */
	public boolean isDraw() {
		return (!getConfig().contains(" ") && !wins('O') && !wins('X'));
	}

	/*
	 * This method evaluates the board to see if and how the game has finished
	 * 
	 * @return int a value corresponding to a win, loss, tie, or inconclusive
	 */
	public int evalBoard() {
		if (wins('O')) {
			return 3;
		} else if (wins('X')) {
			return 0;
		} else if (isDraw()) {
			return 2;
		} else {
			return 1;
		}
	}

	/*
	 * This method is used to convert the configuration of the board into a string
	 * 
	 * @return String a string representation of the board
	 */
	private String getConfig() {
		// String configuration of the board
		String config = "";
		// Loop through the entire board
		for (int i = 0; i < boardSize; i++) {
			for (int j = 0; j < boardSize; j++) {

				// Concatenate the board into a string by adding each value
				config += gameBoard[i][j];
			}
		}

		return config;
	}
}
