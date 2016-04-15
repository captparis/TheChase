package models.Explorer;

import models.Actor;

public abstract class Explorer extends Actor
{

	public Explorer(int x, int y)
	{
		super(x, y);
		// TODO Auto-generated constructor stub
	}
	public boolean attack(int[] pos)
	{
		
		
		return false;
		
	}
	@Override
	public int getMoveableCount(int rollCount)
	{
		return (rollCount*2+1)*(rollCount*2+1);
		
	}

        @Override
	public boolean moveable(int x, int y)
	{
		return true;
	}


//	public boolean move(int x, int y)
//	{
//		int count;
//		int rollCount = 1;
//		// ^ need to pass a roll count ^
//		if(Math.abs(super.getX()-x)<Math.abs(super.getY()-y))
//		{
//			 count = Math.abs(super.getY()-y);
//		}
//		else
//		{
//			 count = Math.abs(super.getX()-x);
//		}
//		if(count >rollCount)
//		{
//			return false;
//		}
//		else
//		{
//			super.setPos(x, y);
//			// need to change roll count
//		return true;
//		}
//	}

}
