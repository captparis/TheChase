package models.guardians;

public class Golem extends Guardian {

    public Golem()
	{
		super();
	}

	@Override
    public boolean attack(){
        System.out.println("Golem attack!!!");
        return true;
    }

    @Override
    public boolean useAbility() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

	@Override
	public boolean moveable(int x,int y){
		
		if(Math.abs(x) >2 || Math.abs(y) > 2){
			return false;
		}else{
			return true;
		}
			
	}

}
