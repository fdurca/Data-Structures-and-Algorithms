/*
 * This class contains all the methods for the graphial figure object
 * @author Filip Durca
 */
public class GraphicalFigure implements GraphicalFigureADT {

	// Instance Variables
	int id;
	int width;
	int height;
	String type;
	Location pos;
	BinarySearchTree bst;

	/*
	 * This is the constructor method
	 * 
	 */
	public GraphicalFigure(int id, int width, int height, String type, Location pos) {
		// Set the variables
		this.id = id;
		this.width = width;
		this.height = height;
		this.type = type;
		this.pos = pos;
		bst = new BinarySearchTree();
	}

	///////////
	// Getters//
	///////////

	/*
	 * This method gets the width of the figure
	 * 
	 * @return int the width of the figure
	 * 
	 */
	public int getWidth() {
		return width;
	}

	/*
	 * This method gets the height of the figure
	 * 
	 * @return int the height of the figure
	 * 
	 */
	public int getHeight() {
		return height;
	}

	/*
	 * This method gets the type of the figure
	 * 
	 * @return String the type of the figure
	 * 
	 */
	public String getType() {
		return type;
	}

	/*
	 * This method gets the id of the figure
	 * 
	 * @return String the id of the figure
	 * 
	 */
	public int getId() {
		return id;
	}

	/*
	 * This method gets the offset of the figure
	 * 
	 * @return Location the offset of the figure
	 * 
	 */
	public Location getOffset() {
		return pos;
	}

	///////////
	// Setters//
	///////////

	/*
	 * This methods sets the offset of the figure
	 * 
	 * @param Location value the offset amount
	 * 
	 */
	public void setOffset(Location value) {
		this.pos = value;
	}

	/*
	 * This methods sets the type of the figure
	 * 
	 * @param String t the type of figure
	 * 
	 */
	public void setType(String t) {
		this.type = t;
	}

	/*
	 * This method adds a pixel to the binary search tree
	 * 
	 * @param Pixel pix is the pixel being added
	 * 
	 */
	public void addPixel(Pixel pix) throws DuplicatedKeyException {
		// Try to add the pixel
		try {
			bst.put(bst.getRoot(), pix);
		} catch (Exception e) {
			// Throw an exception
			throw new DuplicatedKeyException();
		}
	}

	////////////////
	// More Methods//
	////////////////

	/*
	 * This method checks if there is any intersection between this figure and
	 * figure fig
	 * 
	 * @param GraphicalFigure fig is the figure being compared to
	 * 
	 * @return Boolean true for interesction, false otherwise
	 */
	public boolean intersects(GraphicalFigure fig) {

		// Get the distance between the 2 figures on the x axis
		int xDist = pos.xCoord() - fig.getOffset().xCoord();

		// Get the distance on the y axis
		int yDist = pos.yCoord() - fig.getOffset().xCoord();

		// All if's allow for if the x's are the same
		// Check to see if the figure is on the right and overlaps
		if (xDist >= 0 && xDist < fig.getWidth()) {

			// For loop variables
			int initial = 0;
			int compare = 0;
			Boolean loopCheck = false;

			// All if's allow for if the y's are the same
			// Check if it's above the object and within range
			if (yDist >= 0 && xDist < fig.getHeight()) {

				// Set loopCheck to true
				loopCheck = true;

				// For loop variables
				// Set initial to the location of the y coordinate
				initial = pos.yCoord();

				// Set the compare value to the location of the y + the height of the figure -
				// the y distance
				compare = pos.yCoord() + fig.getHeight() - yDist;
			}

			// Check if it's below the object and within range
			else if (yDist <= 0 && xDist < height) {

				// Set loopCheck to true
				loopCheck = true;

				// For loop variables
				// Set initial to the location of the y coordinate
				initial = fig.getOffset().yCoord();

				// Set the compare value to the location of the y + the height of the figure -
				// the y distance
				compare = fig.getOffset().yCoord() + fig.getHeight() + yDist;
			}

			// If the loop check is true, do a for loop
			if (loopCheck) {

				// Create a location variable
				Location loc;
				// Loop through the intersecting rectangle
				// Loop Horizontally
				for (int i = pos.xCoord(); i < pos.xCoord() + fig.getWidth() - xDist; i++) {
					// Loop through vertically
					for (int j = initial; j < compare; j++) {
						// Create a location for the new pixel
						loc = new Location(i, j);

						// Check for overlap, return true
						if (findPixel(loc) && fig.findPixel(loc)) {
							return true;
						}
					}
				}
			}
		}

		// All if's allow for if the x's are the same
		// Check if the figure is on the left and overlaps
		else if (xDist >= 0 && xDist < width) {
			// For loop variables
			int initial = 0;
			int compare = 0;
			Boolean loopCheck = false;

			// All if's allow for if the y's are the same
			// Check if it's above the object and within range
			if (yDist >= 0 && xDist < fig.getHeight()) {

				// Set loopCheck to true
				loopCheck = true;

				// For loop variables
				// Set initial to the location of the y coordinate
				initial = pos.yCoord();

				// Set the compare value to the location of the y + the height of the figure -
				// the y distance
				compare = pos.yCoord() + fig.getHeight() - yDist;
			}

			// Check if it's below the object and within range
			else if (yDist <= 0 && xDist < height) {

				// Set loopCheck to true
				loopCheck = true;

				// For loop variables
				// Set initial to the location of the y coordinate
				initial = fig.getOffset().yCoord();

				// Set the compare value to the location of the y + the height of the figure -
				// the y distance
				compare = fig.getOffset().yCoord() + fig.getHeight() + yDist;
			}

			// If the loop check is true, do a for loop
			if (loopCheck) {

				// Create a location variable
				Location loc;
				// Loop through the intersecting rectangle
				// Loop Horizontally
				for (int i = fig.getOffset().xCoord(); i < fig.getOffset().xCoord() + width + xDist; i++) {
					// Loop through vertically
					for (int j = initial; j < compare; j++) {
						// Create a location for the new pixel
						loc = new Location(i, j);

						// Check for overlap, return true
						if (findPixel(loc) && fig.findPixel(loc)) {
							return true;
						}
					}
				}
			}
		}

		// If no intersection found, return false
		return false;
	}

	/*
	 * This method checks if there is a pixel at the location specified
	 * 
	 * @param Location p is the location being checked
	 * 
	 * @return boolean true if a pixel is there, false otherwise
	 */
	private boolean findPixel(Location p) {
		return !(bst.get(bst.getRoot(), p) == null);
	}
}
