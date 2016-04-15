package models;

public abstract class Actor extends Unit {
	
	private boolean alive;
	private int initX;
	private int initY;

	public Actor() {
		super();
		this.alive = true;
	}

    public int getInitX() {
		return initX;
	}

	public int getInitY() {
		return initY;
	}
	
	public void setInitX(int x){
		initX = x;
	}
	
	public void setInitY(int y){
		initY = y;
	}
	

	public abstract boolean moveable(int x, int y);
        
    public abstract boolean attack();
    
    public abstract boolean useAbility();
    
    @Override
    public String toString(){
       return this.getClass().getSimpleName();
    }
    
    public boolean isAlive() {
        return this.alive;
    }

    public void setStatus(boolean alive) {
        this.alive = alive;
    }
    


}
