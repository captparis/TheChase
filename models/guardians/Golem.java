package models.guardians;

import models.Pos;

public class Golem extends Guardian {

	public Golem() {
		super();
		this.setAttakrange();
	}

	@Override
	public boolean attack() {
		System.out.println("Golem attack!!!");
		return true;
	}

	@Override
	public boolean useAbility() {
		// To change body of generated methods, choose Tools | Templates.
		throw new UnsupportedOperationException("Not supported yet.");	
	}

	@Override
	public boolean moveable(int x, int y) {

		if (Math.abs(x) > 2 || Math.abs(y) > 2) {
			return false;
		} else {
			return true;
		}

	}
	private void setAttakrange(){
		for(int i=-2;i<3;i++){
			for(int j=-2;j<3;j++){
				if(!((Math.abs(i)==2)&&(Math.abs(j)==2)||(Math.abs(i)<2&Math.abs(j)<2))){
					Pos pos = new Pos(i,j);
					super.setAttackRange(pos);

			}
		}
		
	}
	}

}
