/*
 *  OSSD Assignment 1 - The Chase
 *  Charles Yim - S3570764
 *  Jacob Paris - S3238163
 *  Chen Liu- S3481556
 *  Taison Eady - S3282633
 */

package models;


//An inner class for a cell
public class Pos {
	
	

	private int xPos;
	private int yPos;


	public Pos(int x, int y) {
		this.xPos = x;
		this.yPos = y;


	}
	public void setPos(int x, int y){
		this.xPos = x;
		this.yPos = y;
	}

	public int getXPos() {
		return xPos;
	}

	public int getYPos() {
		return yPos;
	}
	
}
