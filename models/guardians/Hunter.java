/*
 *  OSSD Assignment 1 - The Chase
 *  Charles Yim - S3570764
 *  Jacob Paris - S3238163
 *  Chen Liu- S3481556
 *  Taison Eady - S3282633
 */

package models.guardians;

// import models.Pos;

public class Hunter extends Guardian {

	public Hunter() {
		super();
//		this.setAttakrange();
	}

	@Override
	public boolean moveable(int x, int y) {
		// Hunter can only move on the diagonal
		if (Math.abs(x) == Math.abs(y)) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean attackable(int x, int y) {
		if(Math.abs(x)> 2 || Math.abs(y) > 2 || x == 0 || y == 0 ){
			return true;
		}
		return false;
	}

}
