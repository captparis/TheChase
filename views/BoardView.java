package views;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import models.Cell;


public class BoardView extends JPanel {

    //Holds reference to the array of cells in board.
    private Cell[][] cells;

    /**
     * Initialize UI
     */
    public BoardView(ActionListener listener, int rows, int columns, Cell[][] cells) {

        super(new GridLayout(rows, columns, 0, 0));
        this.setPreferredSize(new Dimension(700, 700));
        this.cells = cells;
        
        for(Cell[] cellRow : cells){
        	for(Cell cell : cellRow){
        		cell.addActionListener(listener);
        		this.add(cell);
        		System.out.println("Cell added");
        	}
        }
        // Set line borders on the cells panel and the status label
        this.setBorder(new LineBorder(Color.black, 1));
    }
    
    public void repaintBoard(){
        for(Cell[] cellRow : cells){
        	for(Cell cell : cellRow){
        		cell.repaint();
        	}
        }
    }	
}
