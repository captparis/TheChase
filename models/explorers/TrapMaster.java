package models.explorers;

public class TrapMaster extends Explorer{


    public TrapMaster()
	{
		super();
	}

	public boolean useAbility(){
        System.out.println("TrapMaster use ability");
        return true;
    }

    @Override
    public boolean attack() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
