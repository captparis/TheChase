package models.guardians;


public class Behemoth extends Guardian {
	
	public Behemoth(){
		super();
	}


	@Override
    public boolean attack(){
        System.out.println("Behemoth attack!!!");
        return true;
    }

    @Override
    public boolean useAbility() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

	@Override
	public boolean moveable(int x, int y)
	{
		//Behemoth can only move left right up down
		if(x ==0||y ==0){
			return true;
		} else {
			return false;
		}
	}
}
