package models;

public abstract class BoardItem extends Unit{
	
	public BoardItem() {
		super();
	}
    public boolean moveable(int x,int y)
    {
        return false;
    }
}
