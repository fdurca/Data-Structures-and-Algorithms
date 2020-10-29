/*
 * This class is used to create pixels and keep track of them
 * @author Filip Durca
 */
public class Pixel {

	// Instance variables
	Location p;
	int color;

	/*
	 * This is the constructor method
	 * 
	 * @param location p is the location of the pixel
	 * 
	 * @param int color is the int value of the color of the pixel
	 */
	public Pixel(Location p, int color) {
		// Set the variables
		this.p = p;
		this.color = color;
	}

	// Getters

	/*
	 * This method gets the location of the pixel
	 * 
	 * @return location the location of the pixel
	 */
	public Location getLocation() {
		return p;
	}

	/*
	 * This method gets the color of the pixel
	 * 
	 * @return int the color of the pixel
	 */
	public int getColor() {
		return color;
	}

}
