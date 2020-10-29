/*
 * This program is used to double the size of an image
 * @author Filip Durca, 251008743
 */

//Imports

import java.awt.Color;

public class MagnifyOperation implements ImageOperation {
	public Color[][] doOperation(Color[][] imageArray) {

		// Load the image
		int numOfRows = imageArray.length;
		int numOfColumns = imageArray[0].length;

		// 2-dimensional array to store the processed image
		Color[][] result = new Color[2 * numOfRows][2 * numOfColumns];

		// Declare variables
		int baseX = 0;
		int baseY = 0;

		// Loop through every pixel
		for (int i = 0; i < numOfRows; i++) {
			for (int j = 0; j < numOfColumns; j++) {

				// Set the pixels around the base pixel
				result[baseX][baseY] = imageArray[i][j];
				result[baseX + 1][baseY] = imageArray[i][j];
				result[baseX][baseY + 1] = imageArray[i][j];
				result[baseX + 1][baseY + 1] = imageArray[i][j];

				// increase the base y by 2
				baseY += 2;
			}

			// Increase the base x by 2
			baseX += 2;

			// Reset the base y
			baseY = 0;
		}

		// Return the result
		return result;
	}

}
