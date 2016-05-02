package views;


import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;
import models.Cell;

public class BoardView extends JPanel {

	// Holds reference to the array of cells in board.
	private Cell[][] cells;

	/**
	 * Initialize UI with number of rows, columns.
	 * Initialize cells and add listener on each cell
	 */
	public BoardView(MouseListener mouseListener, int rows, int columns, Cell[][] cells,Border border) {

		super(new GridLayout(rows, columns, 0,0));
		this.setPreferredSize(new Dimension(Cell.CELL_SIZE*columns, Cell.CELL_SIZE*rows));
		this.cells = cells;
		
		// add listener on cell, and add cell on board
		
		for (int i = 0; i< rows; i++){
		    for (int j=0;j<columns; j++){
		        cells[j][i].addMouseListener(mouseListener);
                cells[j][i].setBorder(border);
                this.add(cells[j][i]);
                System.out.println("Cell added");
		    }
		}

		// Set line borders on the cells panel and the status label
		this.setBorder(border);
		this.setSize(getSize().width, getSize().height);
	}

	// draw image on each cell
	public void repaintBoard() {
		for (Cell[] cellRow : cells) {
			for (Cell cell : cellRow) {
				cell.repaint();
			}
		}
	}
	// set border
	public void changeBorder(Cell cell,Border border) {
		cell.setBorder(border);
	}
}
