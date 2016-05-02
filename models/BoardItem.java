package models;

import javax.swing.ImageIcon;

public abstract class BoardItem implements Drawable {
	
	public ImageIcon getIcon(){
		return new ImageIcon("bin/images/" + this.toString() + ".png");
	}
	
	@Override
	public String toString() {
		return this.getClass().getSimpleName();
	}
}
