package models.guardians;


public class Golem extends Guardian {

	public Golem() {
		super();
	}

	@Override
	public boolean moveable(int x, int y) {

		if (Math.abs(x) > 2 || Math.abs(y) > 2) {
			return false;
		} else {
			return true;
		}

	}
	
	@Override
	public boolean attackable(int x, int y){
		if((Math.abs(x) == 2 || Math.abs(y) == 2) && Math.abs(x) != Math.abs(y)){
			return true;
		}
		return false;
	}
	
}
