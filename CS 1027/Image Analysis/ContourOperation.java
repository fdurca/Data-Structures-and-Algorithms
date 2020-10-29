/*
 * This program is used to contour an image based on the color distance between particles
 * @author Filip Durca, 251008743
 */

//Imports
import java.awt.Color;
import java.lang.Math;

public class ContourOperation implements ImageOperation {
	public Color[][] doOperation(Color[][] imageArray) {

		// Load the image
		int numOfRows = imageArray.length;
		int numOfColumns = imageArray[0].length;

		// 2-dimensional array to store the processed image
		Color[][] result = new Color[numOfRows][numOfColumns];

		// Loop Through every pixel
		for (int i = 0; i < numOfRows; i++) {
			for (int j = 0; j < numOfColumns; j++) {

				// Check the pixel above, if it exists
				if (i - 1 >= 0) {
					// If color distance is too great, change the color to black and go to the next
					// iteration of the for loop
					if (colorDistance(imageArray[i][j], imageArray[i - 1][j]) > 65) {
						result[i][j] = new Color(0, 0, 0);
						continue;
					}
				}

				// Check the pixel to the left, if it exists
				if (j - 1 >= 0) {
					// If color distance is too great, change the color to black and go to the next
					// iteration of the for loop
					if (colorDistance(imageArray[i][j], imageArray[i][j - 1]) > 65) {
						result[i][j] = new Color(0, 0, 0);
						continue;
					}
				}

				// Check the pixel below, if it exists
				if (i + 1 < numOfRows) {
					// If color distance is too great, change the color to black and go to the next
					// iteration of the for loop
					if (colorDistance(imageArray[i][j], imageArray[i + 1][j]) > 65) {
						result[i][j] = new Color(0, 0, 0);
						continue;
					}
				}

				// Check the pixel to the right if it exists
				if (j + 1 < numOfColumns) {
					// If color distance is too great, change the color to black and go to the next
					// iteration of the for loop
					if (colorDistance(imageArray[i][j], imageArray[i][j + 1]) > 65) {
						result[i][j] = new Color(0, 0, 0);
						continue;
					}
				}

				// Check the pixel up left, if it exists
				if (i - 1 >= 0 && j - 1 >= 0) {
					// If color distance is too great, change the color to black and go to the next
					// iteration of the for loop
					if (colorDistance(imageArray[i][j], imageArray[i - 1][j - 1]) > 65) {
						result[i][j] = new Color(0, 0, 0);
						continue;
					}
				}

				// Check the pixel up right, if it exists
				if (i - 1 >= 0 && j + 1 < numOfColumns) {
					// If color distance is too great, change the color to black and go to the next
					// iteration of the for loop
					if (colorDistance(imageArray[i][j], imageArray[i - 1][j + 1]) > 65) {
						result[i][j] = new Color(0, 0, 0);
						continue;
					}
				}

				// Check the pixel down left, if it exists
				if (i + 1 < numOfRows && j - 1 >= 0) {
					// If color distance is too great, change the color to black and go to the next
					// iteration of the for loop
					if (colorDistance(imageArray[i][j], imageArray[i + 1][j - 1]) > 65) {
						result[i][j] = new Color(0, 0, 0);
						continue;
					}
				}

				// Check the pixel down right, if it exists
				if (i + 1 < numOfRows && j + 1 < numOfColumns) {
					// If color distance is too great, change the color to black and go to the next
					// iteration of the for loop
					if (colorDistance(imageArray[i][j], imageArray[i + 1][j + 1]) > 65) {
						result[i][j] = new Color(0, 0, 0);
						continue;
					}
				}

				// If none of the neighbors have caused the pixel to change to black, change it
				// to white
				result[i][j] = new Color(255, 255, 255);
			}
		}

		// Return the Color array
		return result;

	}

	/*
	 * This method is used to find the color distance between 2 pixels
	 * 
	 * @param Color firstPixel is the first pixel being compared
	 * 
	 * @param Color secondPixel is the second pixel being compared
	 * 
	 * @return double the color distance between the 2 pixels
	 */
	public double colorDistance(Color firstPixel, Color secondPixel) {
		// Get the difference for each individual color
		int redDiff = (int) Math.pow((firstPixel.getRed() - secondPixel.getRed()), 2);
		int greenDiff = (int) Math.pow((firstPixel.getGreen() - secondPixel.getGreen()), 2);
		int blueDiff = (int) Math.pow((firstPixel.getBlue() - secondPixel.getBlue()), 2);

		// Calculate the color distance using the differences
		double cDistance = Math.sqrt(redDiff + greenDiff + blueDiff);
		// Return the color distance
		return cDistance;
	}
}
