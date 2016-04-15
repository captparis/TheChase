package models.explorers;

import models.Actor;

public abstract class Explorer extends Actor
{

	public Explorer()
	{
		super();
	}
	
	public boolean attack(int[] pos)
	{
		return false;
	}

    @Override
	public boolean moveable(int x, int y)
	{
		return true;
	}

}
