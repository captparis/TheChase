/*
 *  OSSD Assignment 1 - The Chase
 *  Charles Yim - S3570764
 *  Jacob Paris - S3238163
 *  Chen Liu- S3481556
 *  Taison Eady - S3282633
 */

package models.guardians;

import models.AbstractUnit;

public abstract class Guardian extends AbstractUnit {

	public Guardian() {
		super();
	}
        
                @Override
        public boolean die(int diceRoll) {
            return false;
        }

	@Override
	public abstract boolean attackable(int x, int y);
	@Override
	public boolean moveable(int x, int y)
	{
	    
        return this.initMoveable(x, y);
	    
	}
}
