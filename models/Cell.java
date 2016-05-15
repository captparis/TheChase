/*
 *  OSSD Assignment 1 - The Chase
 *  Charles Yim - S3570764
 *  Jacob Paris - S3238163
 *  Chen Liu- S3481556
 *  Taison Eady - S3282633
 */

package models;


import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import models.items.Gate;

//An inner class for a cell
public class Cell extends JButton implements Cloneable {

	int xPos;
	int yPos;
	
	public static int CELL_SIZE = 50;

	BoardItem defaultItem;
	BoardItem item;
	Unit unit;

	public Cell(int x, int y, BoardItem defaultItem) {
		this.xPos = x;
		this.yPos = y;
		this.defaultItem = defaultItem;
	}

	/**
	 * Paint the cell
	 */
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.setBackground(null); // background color
		this.setSize(CELL_SIZE,CELL_SIZE);
		ImageIcon icon;

		if (unit != null) {
			icon = unit.getIcon();
		}else if (item != null) {
			icon = item.getIcon();
		}else{
			icon = defaultItem.getIcon();
		}

		g.drawImage(icon.getImage(), 0, 0, getSize().width, getSize().height, this);
		
	}

	public Unit getUnit() {
		return this.unit;
	}

	public void setUnit(Unit unit) {
		this.unit = unit;
	}

	public int getXPos() {
		return xPos;
	}

	public int getYPos() {
		return yPos;
	}

	public void setItem(BoardItem item) {
		this.item = item;
	}

	public BoardItem getItem() {
		return item;
	}

	public void setDefaultItem(BoardItem defaultItem) {
		this.defaultItem = defaultItem;
	}

	public BoardItem getDefaultItem() {
		return this.defaultItem;
	}
	
public Cell clone(){
        
		return new Cell(this.xPos, this.yPos, this.defaultItem.clone());
		
//		try {
//            return (Cell) super.clone();
//        } catch (CloneNotSupportedException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        return null;
    }
}
