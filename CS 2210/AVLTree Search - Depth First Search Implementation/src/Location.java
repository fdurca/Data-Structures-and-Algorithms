/*This class stores the location of the figures
 * @author Filip Durca
 * 
 */

public class Location {

	// Instance Variables
	int x;
	int y;

	/*
	 * This method is used to initialize the location
	 * 
	 * @param int x is the x coordinate
	 * 
	 * @param int y is the y coordinate
	 */
	public Location(int x, int y) {
		// Set the coordinates
		this.x = x;
		this.y = y;
	}

	// Getters

	/*
	 * This method gets the x coordinate of the location
	 * 
	 * @return int the x location
	 */
	public int xCoord() {
		return x;
	}

	/*
	 * This method gets the y coordinate of the location
	 * 
	 * @return int the y location
	 */
	public int yCoord() {
		return y;
	}

	/*
	 * This method compares the location to another location to see if they match
	 * 
	 * @param Location p the location being compared to
	 * 
	 * @return int 1 for this > p, 0 for a match, -1 for this < p
	 */
	public int compareTo(Location p) {

		// Compare for same location
		if (x == p.xCoord() && y == p.yCoord())
			return 0;

		// Compare the y values if the x values the same
		if ((x == p.xCoord() && y < p.yCoord()) || x < p.xCoord()) {
			return -1;
		}

		// If no other if statement is true, this > p
		return 1;
	}
}
