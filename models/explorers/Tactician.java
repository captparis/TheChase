/*
 *  OSSD Asignment 1 - The Chase
 *  Charles Yim - S3570764
 *  Jacob Paris - S3238163
 *  Chen Liu- S3481556
 *  Taison Eady - S3282633
 */

package models.explorers;


public class Tactician extends Explorer{



    public Tactician()
	{
		super();
	}

	@Override
    public boolean useAbility(){
        System.out.println("Tactician use ability");
        return true;
    }

    @Override
    public boolean attack() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
