/*
 * This program is used to threshold an image
 * @author Filip Durca, 251008743
 */

//Imports
import java.awt.Color;

public class ThresholdingOperation implements ImageOperation {
	public Color[][] doOperation(Color[][] imageArray) {

		// Load the image
		int numOfRows = imageArray.length;
		int numOfColumns = imageArray[0].length;

		// 2-dimensional array to store the processed image
		Color[][] result = new Color[numOfRows][numOfColumns];

		// Declare variables
		double brightness;

		// Loop Through every pixel
		for (int i = 0; i < numOfRows; i++) {
			for (int j = 0; j < numOfColumns; j++) {

				// Calculate the brightness
				brightness = checkBright(imageArray[i][j]);

				// Change the color to black or white depending on the brightness
				if (brightness > 100) {
					result[i][j] = new Color(255, 255, 255);
				} else {
					result[i][j] = new Color(0, 0, 0);
				}
			}
		}

		// Return the new picture
		return result;
	}

	/*
	 * This method is used to determine the brightness of a pixel
	 * 
	 * @param Color pixel is the pixel being tested
	 * 
	 * @return double b the brightness value
	 */
	public double checkBright(Color pixel) {

		// Calculate the brightness
		double b = 0.21 * (pixel.getRed()) + 0.71 * (pixel.getGreen()) + 0.07 * (pixel.getBlue());
		return b;
	}
}
