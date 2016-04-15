package models.explorers;

public class Hero extends Explorer{

    public Hero()
	{
    	super();
	}

	public boolean useAbility(){
        System.out.println("Hero use ability");
        return true;
    }

    @Override
    public boolean attack() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
