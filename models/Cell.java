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

//An inner class for a cell
public class Cell extends JButton {

	int xPos;
	int yPos;
	
	public static int CELL_SIZE = 50;


	BoardItem item;
	Unit unit;

	public Cell(int x, int y, BoardItem item) {
		this.xPos = x;
		this.yPos = y;
		this.item = item;
		

	}

	/**
	 * Paint the cell
	 */
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.setBackground(null); // background color
//		this.setText(String.format("X: %d Y: %d", this.xPos, this.yPos));
		this.setSize(CELL_SIZE,CELL_SIZE);
		ImageIcon icon;

		if (unit == null) {
			icon = item.getIcon();
		} else {
			icon = unit.getIcon();
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
}
