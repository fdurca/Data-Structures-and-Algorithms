/*
 * This class is used to create a record which is a configuration and its corresponding score
 */
public class Record {

	// Variables
	String config;
	int score;

	/*
	 * This method is used to initialize the array
	 * 
	 * @param String config is the configuration
	 * 
	 * @param int score is the score of the configuration
	 */
	public Record(String config, int score) {
		this.config = config;
		this.score = score;
	}

	// Getters

	/*
	 * This method returns the configuration of the record
	 * 
	 * @return string a string representation of the configuration
	 */
	public String getConfig() {
		return config;
	}

	/*
	 * This method returns the score of the record
	 * 
	 * @return int is the score of the record
	 */
	public int getScore() {
		return score;
	}
}
