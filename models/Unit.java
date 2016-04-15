/*
 *  OSSD Assignment 1 - The Chase
 *  Charles Yim - S3570764
 *  Jacob Paris - S3238163
 *  Chen Liu- S3481556
 *  Taison Eady - S3282633
 */

package models;

import javax.swing.ImageIcon;

public abstract class Unit {
	private ImageIcon icon;
	
    public Unit() {
    	icon = new ImageIcon("bin/images/"+this.getClass().getSimpleName().toLowerCase()+".png");
    }

    public ImageIcon getIcon(){
    	return icon;
    }
    
    public abstract boolean moveable(int x, int y);

}
