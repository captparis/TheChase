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
import models.Cell;
import models.Unit;

public abstract class Guardian extends Unit {
	private List<Cell> attackRange;

	public Guardian() {
		super();
		attackRange = new ArrayList<Cell>();
	}
	
	public Guardian(List<Cell> attackPos){
		super();
		this.attackRange = attackPos;
	}
	
	protected void setAttackRange(Cell cell){
				this.attackRange.add(cell);		
	}
	
	public List<Cell> getAttackRange(){
		return attackRange;
	}
	
	@Override
	public abstract boolean attackable(int x, int y);
}
