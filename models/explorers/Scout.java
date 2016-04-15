package models.explorers;

public class Scout extends Explorer{

    public Scout()
	{
		super();
	}

	public boolean useAbility(){
        System.out.println("Scout use ability");
        return true;
    }
	
    @Override
    public boolean attack() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
