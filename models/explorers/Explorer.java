package models.explorers;

import models.AbstractUnit;

public abstract class Explorer extends AbstractUnit {

	public Explorer() {
		super();
	}
	
	@Override
	public boolean attackable(int x, int y){
		return false;
	}
	@Override
    public boolean moveable(int x, int y) {
		return true;
    }
	
	@Override
	public String getMod(){
        if(!super.mod())
        {
            return "Agile";
            
        }
        else
        {
            return "Defensive";
        }
	    
	}
	

}
