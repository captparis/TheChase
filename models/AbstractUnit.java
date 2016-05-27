/*
 *  OSSD Assignment 1 - The Chase
 *  Charles Yim - S3570764
 *  Jacob Paris - S3238163
 *  Chen Liu- S3481556
 *  Taison Eady - S3282633
 */

package models;

import java.io.Serializable;

import javax.swing.ImageIcon;

public abstract class AbstractUnit implements Drawable, Unit, Serializable {
	private boolean alive;
	private int initX;
	private int initY;

	public AbstractUnit() {
		this.alive = true;
	}

	public ImageIcon getIcon() {
		return new ImageIcon("bin/images/" + this.toString() + ".png");
	}

    @Override
	public abstract boolean moveable(int x, int y);
	public abstract boolean attackable(int x, int y);

	public int getInitX() {
		return initX;
	}

	public int getInitY() {
		return initY;
	}

	public void setInitX(int x) {
		initX = x;
	}

	public void setInitY(int y) {
		initY = y;
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName();
	}

	public boolean isAlive() {
		return this.alive;
	}

	public void setStatus(boolean alive) {
		this.alive = alive;
	}
        
        @Override
        public boolean die(int diceRoll){
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
	
	public Unit clone(){
        try {
            return (AbstractUnit) super.clone();
        } catch (CloneNotSupportedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
	
	public Unit getInnerUnit(){
		return this;
	}
	
	public void setInnerUnit(Unit innerUnit){
		return;
	}

}
