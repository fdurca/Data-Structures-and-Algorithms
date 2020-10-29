/*
 * This program is used to adjust an images brightness based on its distance to the top left corner
 * @author Filip Durca, 251008743
 */

//Imports
import java.awt.Color;

public class AdjustmentOperation implements ImageOperation {
	public Color[][] doOperation(Color[][] imageArray) {

		// Load the image
		int numOfRows = imageArray.length;
		int numOfColumns = imageArray[0].length;

		// 2-dimensional array to store the processed image
		Color[][] result = new Color[numOfRows][numOfColumns];

		// Calculate the maximum distance a pixel can have
		double max = Math.sqrt(Math.pow((numOfRows - 1), 2) + Math.pow((numOfColumns - 1), 2));

		// Declare variables
		double distance = 0;
		int pRed;
		int pGreen;
		int pBlue;
		double adjust;

		// Loop Through every pixel
		for (int i = 0; i < numOfRows; i++) {
			for (int j = 0; j < numOfColumns; j++) {

				// Calculate the distance of the pixel to the top left
				distance = Math.sqrt(Math.pow(i, 2) + Math.pow(j, 2));

				// Calculate the adjustment value
				adjust = distance / max;

				// Adjust the colors
				pRed = (int) Math.round(imageArray[i][j].getRed() * adjust);
				pGreen = (int) Math.round(imageArray[i][j].getGreen() * adjust);
				pBlue = (int) Math.round(imageArray[i][j].getBlue() * adjust);

				// Set the new color
				result[i][j] = new Color(pRed, pGreen, pBlue);
			}
		}

		// Return the result
		return result;
	}
}
