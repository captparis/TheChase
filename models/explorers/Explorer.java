package models.explorers;

import models.AbstractUnit;

public abstract class Explorer extends AbstractUnit {

	public Explorer() {
		super();
	}

	public boolean attack(int[] pos) {
		return false;
	}

	@Override
	public boolean moveable(int x, int y) {
		return true;
	}
	
	@Override
	public boolean attackable(int x, int y){
		return false;
	}

}
