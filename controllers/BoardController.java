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
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

import commands.ActionInvoker;
import controllers.GameController.State;
import decorators.*;
import flyweight.ItemFactory;
import mediator.Mediator;
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
	private List<Pos> gatePos;
	
	private HUDActionListener hudListener;
	
	private JPanel southPanel;
	private JPanel hudCards;
	
	private Mediator mediator;


	public BoardController(GameController gameController) {

		this.gameController = gameController;
		try {
			ground = ItemFactory.getItem("Ground");
			movableGround = ItemFactory.getItem("MovableGround");
			attackableGround = ItemFactory.getItem("AttackableGround");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		mediator = Mediator.getInstance();

		gatePos = new ArrayList<Pos>();
		gatePos.add(new Pos(0,0));
		gatePos.add(new Pos(0,1));
		gatePos.add(new Pos(1,0));

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
				setCellUnit(unit.getInitX(), unit.getInitY(), new AgileUnitDecorator(unit));
			}
		}
		initItems();
	}

	public void refreshBoard(int rows, int columns, Game game) {
		
		try {
			Board.setInstance(game.getBoard());
			board = Board.getInstance();
		} catch (Exception e) {
			 System.err.println(e);
		}
		hudListener = new HUDActionListener();
		
		System.out.println("refreshing boardView");

		boardView = new BoardView(new MouseActionListener(), rows, columns, board.getCells(),board.getBorder());
		mediator.setTeam(game.getCurrentPlayer().getTeam());
	}
	
	private void initItems() {
		try {
			
			gate = ItemFactory.getItem("Gate");
			
			for(Pos pos : gatePos){
			    setCellDefaultItem(pos.getXPos(), pos.getYPos(), gate);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	// Sets the default item for the cell
	private void setCellDefaultItem(int x, int y, BoardItem defaultItem) {
		board.getCells()[x][y].setDefaultItem(defaultItem);
	}

	// Sets the unit for the cell
	public void setCellUnit(int x, int y, Unit unit) {
		board.getCells()[x][y].setUnitCarrier(new UnitCarrier(unit));
	}
	public Cell getCell(int x, int y)
	{
	    return board.getCells()[x][y];
	}
	public List<Pos> getGate()
	{
	    return gatePos;
	}

	// Sets the item for the cell
	public void setCellItem(int x, int y, BoardItem item) {
		board.getCells()[x][y].setItem(item);
	}


	
	
	void switchSelectedHud(Boolean isSelected){
		if (isSelected)
			mediator.swapScreens("selected");
		else 
			mediator.swapScreens("notSelected");
		
		
	}
	
	void setUnitName (String unitName){
		unitHudView.setUnitName(unitName);
	}

	// assumes origin contains a movable unit and can legally move to target.
	public int move(Turn turn, Cell origin, Cell target) {		
		ActionInvoker.getInstance().move(turn, origin, target);
		// return the distance that the unit moved.
		return getDistance(origin, target);
	}

	// calculates the absolute distance between two given cells
	public int getDistance(Cell origin, Cell target) {
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
	
	// returns the array of possible coordinates for a unit to move or attack to.
	public List<Cell> getAbleList(Cell origin,State type)
	{
	    List<Cell> cells = new ArrayList<>();
	    int xPos = origin.getXPos();
        int yPos = origin.getYPos();
        int size ;
        if(type == State.ATTACK){       
            size = 2;
        }
        else{
            size = gameController.getCurrentPlayer().getRemainingMoves();
        }
        
        for (int x = -size; x <= size; x++) {
            if (xPos + x < 0 || xPos + x >= board.getColumns()) {
                continue;
            }
            for (int y = -size; y <= size; y++) {
                if (yPos + y < 0 || yPos + y >= board.getRows()) {
                    continue;
                }
                int tempX = origin.getXPos() + x;
                int tempY = origin.getYPos() + y;
                
                if (origin.getUnit().moveable(x, y) && type == State.MOVE) {
                    Cell cell = board.getCells()[tempX][tempY];
                    if ( cell.getUnit() != null) {
                        continue;
                    }
                    cells.add(cell);
                }
                else if(origin.getUnit().attackable(x, y) && type == State.ATTACK){
                    Cell cell = board.getCells()[tempX][tempY];
                    cells.add(cell);
                }
            }
        }       
	    return cells;
	}



	// Assumes the draw cells passed in are on the board
	public void drawActionCells(List<Cell> Cells , State state) {
		
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
		this.repaintBoard();
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
				mediator.swapMenuView(true);
				mediator.swapScreens("menu");
				break;
			case "backButton":
				mediator.swapMenuView(false);
				mediator.swapScreens("selectionScreen");
				break;
			case "mode":
			    gameController.swapMode(((JToggleButton) e.getSource()).getName());
				break;
			case "save":
				gameController.save();
				break;
			case "load":
				gameController.load();
				break;
			case "exit":
				gameController.showMainMenu();
				mediator.changeBoardScreen(false, false);
				break;
			case "undo":
				mediator.swapScreens("undo");
				break;
			case "undoturn":
				break;
			case "undomove":
				break;
			case "redoturn":
				break;
			case "redomove":
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
