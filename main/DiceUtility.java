/*
 *  OSSD Assignment 1 - The Chase
 *  Charles Yim - S3570764
 *  Jacob Paris - S3238163
 *  Chen Liu- S3481556
 *  Taison Eady - S3282633
 */
package main;

import java.util.Random;

// the dice can return a random number from 1-6
public class DiceUtility {
	
	public static int roll() {
		int number = 0;

		Random rand = new Random();

		number = rand.nextInt(6) + 1;

		return number;
	}

}
