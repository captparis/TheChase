package models;

import javax.swing.ImageIcon;

public abstract class BoardItem implements Drawable, Cloneable {
	
	public ImageIcon getIcon(){
		return new ImageIcon("bin/images/" + this.toString() + ".png");
	}
	
	@Override
	public String toString() {
		return this.getClass().getSimpleName();
	}
	
	public BoardItem clone(){
        try {
            return (BoardItem) super.clone();
        } catch (CloneNotSupportedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
}
