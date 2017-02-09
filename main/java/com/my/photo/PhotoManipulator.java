package com.my.photo;

import java.io.IOException;

public interface PhotoManipulator {
	
	/**
	 * Manipulates a photo.
	 * @param photo the source photo
	 * @return the manipulated photo
	 * @throws IOException
	 */
	Photo manipulate(Photo photo) throws IOException;

}
