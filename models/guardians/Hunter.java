/*
 *  OSSD Assignment 1 - The Chase
 *  Charles Yim - S3570764
 *  Jacob Paris - S3238163
 *  Chen Liu- S3481556
 *  Taison Eady - S3282633
 */

package models.guardians;

import models.Pos;

public class Hunter extends Guardian {

	public Hunter() {
		super();
		this.setAttakrange();
	}

	@Override
	public boolean attack() {
		System.out.println("Hunter attack!!!");
		return true;
	}

	@Override
	public boolean useAbility() {
		throw new UnsupportedOperationException("Not supported yet.");
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
	private void setAttakrange(){
		for(int i=-2;i<3;i++){
			for(int j=-2;j<3;j++){
				if(i==0||j==0){
					Pos pos = new Pos(i,j);
					super.setAttackRange(pos);
					}
				
			}
		}
		
	}

}
