package models.guardians;



public class Behemoth extends Guardian {

	public Behemoth() {
		super();
	}

	@Override
	public boolean attack() {
		System.out.println("Behemoth attack!!!");
		return true;
	}

	@Override
	public boolean useAbility() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public boolean moveable(int x, int y) {
		// Behemoth can only move left right up down
		if (x == 0 || y == 0) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public boolean attackable(int x, int y){
		if(Math.abs(x)> 1 || Math.abs(y) > 1 ){
			return false;
		}
		return true;
	}
	
//	private void setAttakRange(){
//		for(int i=-1;i<2;i++){
//			for(int j=-1;j<2;j++){
//				Pos pos = new Pos(i,j);
//				super.setAttackRange(cell);
//				
//			}
//		}
//		
//	}
}
