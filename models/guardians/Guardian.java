/*
 *  OSSD Assignment 1 - The Chase
 *  Charles Yim - S3570764
 *  Jacob Paris - S3238163
 *  Chen Liu- S3481556
 *  Taison Eady - S3282633
 */

package models.guardians;

import java.util.ArrayList;
import java.util.List;

import models.Actor;
import models.Pos;

public abstract class Guardian extends Actor {
	private List<Pos> attackRange;

	public Guardian() {
		super();
		attackRange = new ArrayList<Pos>();

		
	}
	public Guardian(List<Pos> attackPos){
		super();
		this.attackRange = attackPos;
	}
	protected void setAttackRange(Pos pos){
		

				this.attackRange.add(pos);
					
	}
	public List<Pos> getAttackRange(){
		return attackRange;
	}


}
