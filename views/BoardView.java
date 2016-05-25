package views;


import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;

import mediator.Mediator;
import models.Cell;

public class BoardView extends JPanel {
	
	private JPanel boardCards;
	private JPanel board;
	private JPanel winScreen;
	
	private JLabel background;
	
	public Image explorersWinImage;
	public Image guardiansWinImage;

	// Holds reference to the array of cells in board.
	private Cell[][] cells;

	/**
	 * Initialize UI with number of rows, columns.
	 * Initialize cells and add listener on each cell
	 */
	public BoardView(MouseListener mouseListener, int rows, int columns, Cell[][] cells,Border border) {
		
		boardCards = new JPanel(new CardLayout());
		board = new JPanel(new GridLayout(rows,columns,0,0));
		winScreen = new JPanel();
		
		try {
			guardiansWinImage = ImageIO.read(new File("bin/images/GuardiansWin.jpg"));
			Image scaledBG = guardiansWinImage.getScaledInstance( 940, 570,  java.awt.Image.SCALE_SMOOTH ) ;
			guardiansWinImage = scaledBG;
			
			explorersWinImage = ImageIO.read(new File("bin/images/ExplorersWin.jpg"));
			scaledBG = explorersWinImage.getScaledInstance( 940, 570,  java.awt.Image.SCALE_SMOOTH ) ;
			explorersWinImage = scaledBG;
			
			background = new JLabel(new ImageIcon(guardiansWinImage));
		} catch (IOException e) {
			System.out.println("Background image not found for win screen");
		}
		
		

		//super(new GridLayout(rows, columns, 0,0));
		winScreen.setPreferredSize(new Dimension(940, 570));
		boardCards.setPreferredSize(new Dimension(Cell.CELL_SIZE*columns, Cell.CELL_SIZE*rows));
		board.setPreferredSize(new Dimension(Cell.CELL_SIZE*columns, Cell.CELL_SIZE*rows));
		this.cells = cells;
		
		// add listener on cell, and add cell on board
		
		for (int i = 0; i< rows; i++){
		    for (int j=0;j<columns; j++){
		        cells[j][i].addMouseListener(mouseListener);
                cells[j][i].setBorder(border);
                board.add(cells[j][i]);
                System.out.println("Cell added");
		    }
		}

		// Set line borders on the cells panel and the status label
		board.setBorder(border);
		board.setSize(getSize().width, getSize().height);
		
		winScreen.add(background);
		
		//Setup card layout to allow for screen changes
		boardCards.add(board, "board");
		boardCards.add(winScreen, "win");
		
		this.add(boardCards);
		
		Mediator.getInstance().registerBoardColleagues(boardCards, background, explorersWinImage, guardiansWinImage);
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
