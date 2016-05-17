/*
 *  OSSD Assignment 1 - The Chase
 *  Charles Yim - S3570764
 *  Jacob Paris - S3238163
 *  Chen Liu- S3481556
 *  Taison Eady - S3282633
 */
package models;

import java.awt.Color;
import java.io.Serializable;

import javax.swing.border.LineBorder;

import main.DiceUtility;

public class Board implements Serializable{
	private int rows;
	private int columns;
	private Cell[][] cells;
	private LineBorder border;
	private LineBorder mouseOverItem;
	private LineBorder mouseOverEnemy;
	private LineBorder mouseOverAlly;

	/*
	 * add item on each cell
	 */
	
	// Singleton object
	private static Board instance;

	// Get instance of board or create an instance
	public static Board getInstance(int rows, int columns, BoardItem item) throws Exception {
		if (instance == null){
			instance = new Board(rows, columns, item);
		}
		if (instance.rows != rows || instance.columns != columns){
			throw new Exception("Cannot change board dimensions after board has been created");
		}
		return instance;
	}
	
	
	public Board(int rows, int columns, BoardItem item) {
		this.rows = rows;
		this.columns = columns;
		this.border = new LineBorder(Color.black, 1);
		this.mouseOverItem = new LineBorder(Color.blue, 3);
		this.mouseOverEnemy = new LineBorder(Color.red, 3);
		this.mouseOverAlly = new LineBorder(Color.green, 3);

		cells = new Cell[columns][rows];
		System.out.println(rows + " " + columns);
		for (int x = 0; x < columns; x++) {
			for (int y = 0; y < rows; y++) {
				cells[x][y] = new Cell(x, y, item);
				System.out.println("X: " + x + "y : " + y);
			}
		}

	}
	
	//Called from options menu to customise board size
	public void setBoardSize(int rows, int columns){
		this.rows = rows;
		this.columns = columns;
		
	}

	public Cell[][] getCells() {
		return cells;
	}

	public void setCells(Cell[][] cells) {
		this.cells = cells;
	}

	public LineBorder getBorder() {
		return border;
	}

	public LineBorder getMouseOverBorder(int type) {
		switch (type) {
		case 1:
			return mouseOverAlly;
		case 2:
			return mouseOverEnemy;
		default:
			return mouseOverItem;
		}

	}

	public int getRows() {
		return this.rows;
	}

	public int getColumns() {
		return this.columns;
	}
}
