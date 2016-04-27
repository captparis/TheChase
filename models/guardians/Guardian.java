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
		setAttackRange();
		
	}
	public Guardian(List<Pos> attackPos){
		super();
		this.attackRange = attackPos;
	}
	private void setAttackRange(){
		
		for(int i=-1;i<2;i++){
			for(int j=-1;j<2;j++){
				Pos pos = new Pos(i,j);
				this.attackRange.add(pos);
			}
		}
		
	}
	public List<Pos> getAttackRange(){
		return attackRange;
	}


}
