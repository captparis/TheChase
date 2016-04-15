/*
 *  OSSD Assignment 1 - The Chase
 *  Charles Yim - S3570764
 *  Jacob Paris - S3238163
 *  Chen Liu- S3481556
 *  Taison Eady - S3282633
 */
package models;

public class Board {
    private int rows;
    private int columns;
    private Cell[][] cells;
    
    public Board(int rows, int columns, BoardItem item){
    	this.rows = rows;
    	this.columns = columns;
    	
    	cells = new Cell[rows][columns];
    	
    	for(int x = 0 ; x < rows; x++){
    		for(int y = 0 ; y < columns; y++){
    			cells[x][y]=new Cell(x,y,item);
    		}
    	}
    }
        
    public Cell[][] getCells() {
        return cells;
    }
    
    public int getRows(){
    	return this.rows;
    }
    
    public int getColumns(){
    	return this.columns;
    }
    
}
