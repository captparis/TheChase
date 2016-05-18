/*
 *  OSSD Assignment 1 - The Chase
 *  Charles Yim - S3570764
 *  Jacob Paris - S3238163
 *  Chen Liu- S3481556
 *  Taison Eady - S3282633
 */
package controllers;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

import controllers.GameController.State;
import decorators.AbstractUnitDecorator;
import models.*;
import models.explorers.Explorer;
import models.items.*;
import views.BoardView;
import views.HudView;
import views.UnitHudView;

public class BoardController {

	private GameController gameController;

	private Board board;
	private BoardView boardView;
	private HudView hudView;
	private UnitHudView unitHudView;
	private BoardItem ground;
	private BoardItem movableGround;
	private BoardItem attackableGround;
	private BoardItem gate;
	
	private HUDActionListener hudListener;
	
	private JPanel southPanel;
	private JPanel hudCards;


	public BoardController(GameController gameController) {

		this.gameController = gameController;
		ground = new Ground();
		movableGround = new MovableGround();
		attackableGround = new AttackableGround();
	}

	// this method assumes that the board has been initialized prior to calling.
	public JPanel buildBoard() {
		// create a new content panel holding both the grid and the hud
		JPanel contentPanel = new JPanel(new BorderLayout());
		JPanel boardSpace = new JPanel(new GridBagLayout());		
		southPanel = new JPanel (new BorderLayout());
		hudCards = new JPanel (new CardLayout());
		
		boardSpace.add(boardView);
		contentPanel.add(boardSpace, BorderLayout.CENTER);
		southPanel.add(hudView, BorderLayout.NORTH);
		southPanel.add(unitHudView, BorderLayout.SOUTH);
		contentPanel.add(southPanel, BorderLayout.SOUTH);
		//mainWindow.getContentPane().add(contentPanel);
		//mainWindow.pack();
		contentPanel.setVisible(true);
		boardSpace.setBackground(new Color (83, 83, 83));
		return contentPanel;
	}

	public void initBoard(int rows, int columns, Game game) {
		
		try {
			board = Board.getInstance(rows, columns, ground);
			game.setBoard(board);
		} catch (Exception e) {
			 System.err.println(e);
		}
		hudListener = new HUDActionListener();
		
		System.out.println("Creating boardView");

		boardView = new BoardView(new MouseActionListener(), rows, columns, board.getCells(),board.getBorder());
		
		
		
		hudView = new HudView(hudListener);
		unitHudView = new UnitHudView(hudListener);

		for (Player player : gameController.getPlayers().values()) {
			for (Unit unit : player.getUnits().values()) {
				setCellUnit(unit.getInitX(), unit.getInitY(), unit);
			}
		}
		initItems();
	}

	private void initItems() {
		gate = new Gate();
		setCellDefaultItem(0, 0, gate);
		setCellDefaultItem(1, 0, gate);
		setCellDefaultItem(0, 1, gate);
	}
	
	// Sets the default item for the cell
	private void setCellDefaultItem(int x, int y, BoardItem defaultItem) {
		board.getCells()[x][y].setDefaultItem(defaultItem);
	}

	// Sets the unit for the cell
	public void setCellUnit(int x, int y, Unit unit) {
		board.getCells()[x][y].setUnit(unit);
	}

	// Sets the item for the cell
	public void setCellItem(int x, int y, BoardItem item) {
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

	void swapPlayer(Player newPlayer) {
		String playerName = newPlayer.getName();
		hudView.swapPlayer(playerName);
	}
	
	void setPlayerName(Player newPlayer){
		String playerName = newPlayer.getName();
		hudView.setPlayerName(playerName);
	}

	void setWinState() {
		hudView.setWinState();
	}
	
	
	void switchSelectedHud(Boolean isSelected){
		unitHudView.switchSelectedHud(isSelected);
	}
	
	void setUnitName (String unitName){
		unitHudView.setUnitName(unitName);
	}

	// assumes origin contains a movable unit and can legally move to target.
	public int move(Cell origin, Cell target) {
	    // move the unit from the origin to the target and replace the origin
        // with ground.
        target.setUnit(origin.getUnit());
        origin.setUnit(null);


        
		// return the distance that the unit moved.
		return getDistance(origin, target);
	}
	
	public void kill(Cell target){
	    (target.getUnit()).setStatus(false);
		target.setUnit(null);
	
	}

	// calculates the absolute distance between two given cells
	private int getDistance(Cell origin, Cell target) {
		int x1 = origin.getXPos();
		int y1 = origin.getYPos();
		int x2 = target.getXPos();
		int y2 = target.getYPos();

		return Math.max(Math.abs(x2 - x1), Math.abs(y2 - y1));
	}
	public void swapTeam(String team){
	    if(team.equals("Guardian")){
	        unitHudView.swapTeam("Attack");
	    }
	    else{
	        unitHudView.swapTeam("Defense");
	    }
	    
	   
	}
	
	public List<Cell> attackable(Cell origin){
		List<Cell> attackableCells = new ArrayList<>(36); //TODO:change the magic number
		
		int xPos = origin.getXPos();
		int yPos = origin.getYPos();

		for (int x = -2; x <= 2; x++) {
			if (xPos + x < 0 || xPos + x >= board.getColumns()) {
				continue;
			}
			for (int y = -2; y <= 2; y++) {
				if (yPos + y < 0 || yPos + y >= board.getRows()) {
					continue;
				}

				int attackableX = origin.getXPos() + x;
				int attackableY = origin.getYPos() + y;

				if (origin.getUnit().attackable(x, y)) {
					Cell attackableCell = board.getCells()[attackableX][attackableY];
					attackableCells.add(attackableCell);
				}
			}
		}
		return attackableCells;
	}
	
	// returns the array of possible coordinates for a unit to move to.
	// assumes origin contains movable unit and rollCount is a valid dice roll.
	public List<Cell> movable(Cell origin, int rollCount) {

		List<Cell> movableCells = new ArrayList<>(rollCount * 2 + 1);
		int xPos = origin.getXPos();
		int yPos = origin.getYPos();

		for (int x = -rollCount; x <= rollCount; x++) {
			if (xPos + x < 0 || xPos + x >= board.getColumns()) {
				continue;
			}
			for (int y = -rollCount; y <= rollCount; y++) {
				if (yPos + y < 0 || yPos + y >= board.getRows()) {
					continue;
				}

				int movableX = origin.getXPos() + x;
				int movableY = origin.getYPos() + y;

				if (origin.getUnit().moveable(x, y)) {
					Cell movableCell = board.getCells()[movableX][movableY];
					if ( movableCell.getUnit() != null) {
						continue;
					}
					movableCells.add(movableCell);
				}
			}
		}

		return movableCells;
	}


	// Assumes the draw cells passed in are on the board
	public void drawActionCells(List<Cell> Cells , GameController.State state) {
		
		for (Cell cell : Cells) {
			if(state == State.MOVE){
				cell.setItem(movableGround);
			}
			else{
				cell.setItem(attackableGround);
			}
			cell.repaint();
		}
	}
	
	public void resetCells(List<Cell> Cells) {
		for (Cell Cell : Cells) {
			Cell.setItem(null);
			Cell.repaint();
		}
	}

	class MouseActionListener implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e)
		{
			// TODO Auto-generated method stub
		}

		@Override
		public void mousePressed(MouseEvent e)
		{
			// TODO Auto-generated method stub
			Cell cell = ((Cell) e.getSource());
			System.out.println(cell.getXPos()+"  "  +cell.getYPos());
			System.out.println("Unit: " + cell.getUnit() + " Item: " + cell.getItem() + " DefaultItem: " + cell.getDefaultItem());	
			gameController.cellClicked(cell);
			if(cell.getUnit()!=null){
			    unitHudView.setMode(cell.getUnit().getClass().getSimpleName().replace("UnitDecorator", "")); 
//			if(cell.getUnit().getClass().getSimpleName().equals("AgileUnitDecorator"))
//			{
//			    unitHudView.setMode("agile"); 
//			}
//			else 
//			{
//			    unitHudView.setMode("defense");
//			}
			}
			
		}

		@Override
		public void mouseReleased(MouseEvent e)
		{
			// TODO Auto-generated method stub
		}

		@Override
		public void mouseEntered(MouseEvent e)
		{
			Cell cell = ((Cell) e.getSource());
			
			// TODO Auto-generated method stub
			//0 = item, 1 = ally, 2 = enemy
			int type = 0;

			if(cell.getUnit() != null) {
			    
				if (gameController.getCurrentPlayer().hasUnit(cell.getUnit())) {
				    type = 1;
				}
				else {
					type = 2;
				}
			}
			boardView.changeBorder(cell, board.getMouseOverBorder(type));	
		}

		@Override
		public void mouseExited(MouseEvent e)
		{
			// TODO Auto-generated method stub
			boardView.changeBorder(((Cell) e.getSource()), board.getBorder());	
		}
	}

	class HUDActionListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
		    String option ;
		    if(e.getSource().getClass().getSimpleName().equals("JButton"))
		    {
			 option = ((JButton) e.getSource()).getName();
		    }
		    else
		    {
		         option = "mode";
		    }
			

			switch (option) {
			case "actionButton":
				gameController.hudButtonClicked();
				break;
			case "menuButton":
				hudView.swapMenuView(true);
				unitHudView.swapMenuView(true);
				break;
			case "backButton":
				hudView.swapMenuView(false);
				unitHudView.swapMenuView(false);
				break;
			case "mode":
			    gameController.swapMode(((JToggleButton) e.getSource()).getName());
				break;
			default:
				break;
			}	
		}
	}

	public void repaintBoard() {
		boardView.repaintBoard();
	}
}
