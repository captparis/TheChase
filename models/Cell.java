/*
 *  OSSD Assignment 1 - The Chase
 *  Charles Yim - S3570764
 *  Jacob Paris - S3238163
 *  Chen Liu- S3481556
 *  Taison Eady - S3282633
 */

package models;


import java.awt.Graphics;
import java.io.Serializable;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import decorators.AbstractUnitDecorator;
import models.items.Gate;

//An inner class for a cell
public class Cell extends JButton implements Serializable {

	int xPos;
	int yPos;
	
	public static int CELL_SIZE = 50;

	BoardItem defaultItem;
	BoardItem item;
	UnitCarrier unitCarrier;

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
		
		if (unitCarrier != null) {
			icon = unitCarrier.getIcon();
		}else if (item != null) {
			icon = item.getIcon();
		}else{
			icon = defaultItem.getIcon();
		}

		g.drawImage(icon.getImage(), 0, 0, getSize().width, getSize().height, this);
		
	}

	public Unit getUnit() {
		if (this.unitCarrier == null){
			return null;
		}
		
		return this.unitCarrier.getPassenger();
	}
	
	public UnitCarrier getUnitCarrier(){
		return this.unitCarrier;
	}

	public void setUnit(Unit unit) {
		this.unitCarrier.setPassenger(unit);
	}
	
	public void setUnitCarrier(UnitCarrier unitCarrier){
		this.unitCarrier = unitCarrier;
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

}
