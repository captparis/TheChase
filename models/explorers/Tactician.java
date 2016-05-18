/*
 *  OSSD Asignment 1 - The Chase
 *  Charles Yim - S3570764
 *  Jacob Paris - S3238163
 *  Chen Liu- S3481556
 *  Taison Eady - S3282633
 */

package models.explorers;

import models.Unit;

public class Tactician extends Explorer {

	// create an object of SingleObject
	private static Tactician instance = new Tactician();

	public Tactician() {
		super();
	}

	// Get the only object available
	public static Tactician getInstance() {
		return instance;
	}


}
