/*
 *  OSSD Assignment 1 - The Chase
 *  Charles Yim - S3570764
 *  Jacob Paris - S3238163
 *  Chen Liu- S3481556
 *  Taison Eady - S3282633
 */

package models.guardians;

public class Hunter extends Guardian
{

	public Hunter()
	{
		super();
	}

	@Override
	public boolean attack()
	{
		System.out.println("Hunter attack!!!");
		return true;
	}

	@Override
	public boolean useAbility()
	{
		throw new UnsupportedOperationException("Not supported yet."); 
	}

	@Override
	public boolean moveable(int x,int y)
	{
		//Hunter can only move on the diagonal
		if(Math.abs(x)==Math.abs(y)){
			return true;
		}else{
			return false;
		}
	}

}
