package models;

import java.io.Serializable;

import javax.swing.ImageIcon;

import flyweight.ImageIconFactory;

public abstract class BoardItem implements Drawable, Serializable {
	
	public ImageIcon getIcon(){
		return ImageIconFactory.getIcon(this.toString());
	}
	
	@Override
	public String toString() {
		return this.getClass().getSimpleName();
	}
}
