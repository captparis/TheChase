/*
 *  OSSD Assignment 1 - The Chase
 *  Charles Yim - S3570764
 *  Jacob Paris - S3238163
 *  Chen Liu- S3481556
 *  Taison Eady - S3282633
 */
package controllers;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.*;
import models.*;
import models.explorers.Explorer;
import models.items.*;
import views.BoardView;
import views.HudView;



public class BoardController {
    
    private GameController gameController;
    
    private Board board;
    private BoardView boardView;
    private HudView hudView;
    private Ground ground;
    private MovableGround movableGround;
        
    public BoardController(GameController gameController) {
        
        this.gameController = gameController;        
        ground = new Ground();
        movableGround = new MovableGround();
        
    }
    
    //this method assumes that the board has been initialized prior to calling.
    public void showBoard(JFrame mainWindow){        
        //create a new content panel holding both the grid and the hud
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.add(boardView, BorderLayout.CENTER);
        contentPanel.add(hudView, BorderLayout.SOUTH);
        mainWindow.getContentPane().add(contentPanel);
        mainWindow.pack();
        contentPanel.setVisible(true);
    }
  
    public void initBoard(int rows, int columns) {
        board = new Board(rows, columns, ground);
        
        boardView = new BoardView(new BoardActionListener(), rows, columns, board.getCells());
        hudView = new HudView(new HUDActionListener());

        for(Player player : gameController.getPlayers().values()){
        	for(Actor actor : player.actors.values()){
        		setCellUnit(actor.getInitX(), actor.getInitY(), actor);
        	}
        }
        initItems();
    }
    
    private void initItems(){
    	BoardItem gate = new Gate();
    	setCellItem(0,0, gate);
    	setCellItem(1,0, gate);
    	setCellItem(0,1, gate);
    }
    
    //Sets the unit for the cell
    public void setCellUnit(int x, int y, Unit unit){
    	board.getCells()[x][y].setUnit(unit);
    }
    
    //Sets the unit for the cell
    public void setCellItem(int x, int y, BoardItem item){
    	board.getCells()[x][y].setItem(item);
    }
    
    void setDiceRoll(int diceAmount) {
        hudView.setDiceRoll(diceAmount);
    }

    void setUnitState() {
        hudView.setUnitState();
    }

    void setDiceState() {
        hudView.setDiceState();
    }

    void swapPlayer() {
        hudView.swapPlayer();
    }

    void setWinState() {
        hudView.setWinState();
    }
    
    //assumes origin contains a movable unit and can legally move to target.
    public int move(Cell origin, Cell target)
    {
    	//move the unit from the origin to the target and replace the origin with ground.
    	target.setUnit(origin.getUnit());
    	origin.setUnit(null);
    	
    	//if the explorer moving into a gate cell update game controller winstate
    	if(target.getUnit() instanceof Explorer && target.getItem() instanceof Gate){
    		gameController.setWinner(gameController.getPlayers().get("Explorer"));
    	}
    	
    	//return the distance that the unit moved.
    	return getDistance(origin, target);
    }
    
    //calculates the absolute distance between two given cells
    private int getDistance(Cell origin, Cell target){
    	int x1 = origin.getXPos();
    	int y1 = origin.getYPos();
    	int x2 = target.getXPos();
    	int y2 = target.getYPos();
    	
    	return Math.max(Math.abs(x2-x1), Math.abs(y2-y1));
    }
    
    //returns the array of possible coordinates for a unit to move to.
    //assumes origin contains movable unit and rollCount is a valid dice roll.
    public List<Cell> movable(Cell origin, int rollCount){
        
    	List<Cell> movableCells = new ArrayList<>(rollCount*2+1);
    	int xPos = origin.getXPos();
    	int yPos = origin.getYPos();
    	
    	for(int x = -rollCount; x <= rollCount; x++){
    		if(xPos+x < 0||xPos+x >= board.getColumns()){
    			continue;
    		}
    		for(int y = -rollCount; y <= rollCount; y++){
        		if(yPos+y < 0||yPos+y >= board.getRows()){
        			continue;
        		}
        		
        		int movableX = origin.getXPos()+x;
        		int movableY = origin.getYPos()+y;
        		
        		if(origin.getUnit().moveable(x,y)){
        			Cell movableCell = board.getCells()[movableX][movableY];
        			if(movableCell.getItem() instanceof Gate || movableCell.getUnit() != null){
        				continue;
        			}
        			movableCells.add(movableCell);
        		}
    		}
    	}
    	
    	return movableCells;
    }
    
    //Assumes the movable cells passed in are on the board
	public void drawMovable(List<Cell> movableCells) {
		for(Cell movableCell : movableCells){
			movableCell.setItem(movableGround);
			movableCell.repaint();
		}
	}
	
	public void resetMovable(List<Cell> movableCells){
		for(Cell movableCell : movableCells){
			movableCell.setItem(ground);
			movableCell.repaint();
		}
	}
    
    class BoardActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            Cell cell = ((Cell)e.getSource());

            gameController.cellClicked(cell);

        }

    }
    

    class HUDActionListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
           gameController.hudButtonClicked();
        }
    }


	public void repaintBoard() {
		boardView.repaintBoard();
	}
}
   
