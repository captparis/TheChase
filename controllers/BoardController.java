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

import commands.ActionInvoker;
import controllers.GameController.State;
import decorators.AgileUnitDecorator;
import flyweight.ItemFactory;
import mediator.Mediator;
import models.*;
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
	private Mediator mediator;


	public BoardController(GameController gameController) {

		this.gameController = gameController;
		try {
			ground = ItemFactory.getItem("Ground");
			movableGround = ItemFactory.getItem("MovableGround");
			attackableGround = ItemFactory.getItem("AttackableGround");
			gate = ItemFactory.getItem("Gate");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		mediator = Mediator.getInstance();


	}

	// this method assumes that the board has been initialized prior to calling.
	public JPanel buildBoard() {
		// create a new content panel holding both the grid and the hud
		JPanel contentPanel = new JPanel(new BorderLayout());
		JPanel boardSpace = new JPanel(new GridBagLayout());		
		southPanel = new JPanel (new BorderLayout());
		new JPanel (new CardLayout());
		
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
		this.initItems();

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
		mediator.setTeam(game.getCurrentPlayer().getTeam(), game.getCurrentPlayer().getName());
	}
	public void initUnit(){
	    for (Player player : gameController.getPlayers().values()) {
            for (Unit unit : player.getUnits().values()) {
                setCellUnit(unit.getInitX(), unit.getInitY(), new AgileUnitDecorator(unit));
            }
        }
	    
	}
	private void initItems() {
	    this.getCell(0, 0).setDefaultItem(gate);
	    this.getCell(1, 0).setDefaultItem(gate);
	    this.getCell(0, 1).setDefaultItem(gate);
	    this.storeGate();

	}
	
	// Sets the unit for the cell
	public void setCellUnit(int x, int y, Unit unit) {
		board.getCells()[x][y].setUnitCarrier(new UnitCarrier(unit));
	}
	public Cell getCell(int x, int y)
	{
	    return board.getCells()[x][y];
	}
	public List<Pos> getGate(){
	    return board.getGate();
	}

	// Sets the item for the cell
	public void setCellItem(int x, int y, BoardItem item) {
		board.getCells()[x][y].setItem(item);
	}
	public void setGate(Cell cell)
	{
	    if(cell.getDefaultItem() == gate)
	    {
	        cell.setDefaultItem(ground);
	    }
	    else
	    {
	        cell.setDefaultItem(gate);
	    }
	}
	public void storeGate()
	{
	    List<Pos> gatePos = new ArrayList<Pos>();
	    for (int x = 0; x < board.getColumns(); x++) {
            for (int y = 0; y < board.getRows(); y++) {
                if(this.getCell(x, y).getDefaultItem() == gate)
                {
                    gatePos.add(new Pos(x,y));
                }

            }
        }
	    board.setGate(gatePos);
	}

	
	
	void switchSelectedHud(Boolean isSelected){
		if (isSelected)
			mediator.swapScreens("selected");
		else 
			mediator.swapScreens("notSelected");
		
		
	}
	
	void setUnitName (String unitName){
		mediator.setUnitName(unitName);
	}

	// assumes origin contains a movable unit and can legally move to target.
	public int move(Turn turn, Cell origin, Cell target) {		
		ActionInvoker.getInstance().move(origin, target);
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
	
	public void setMode(String mode){
	    this.mediator.setMode(mode);
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
			    System.out.println(cell.getUnit().getMod());
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

			option = ((AbstractButton) e.getSource()).getName();

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
			case "modeAgile":
			case "modeSpecial":
			    gameController.swapMode(option);
				break;
			case "save":
				gameController.save();
				break;
			case "load":
				gameController.load();
				break;
			case "exit":
			    gameController.backMainMenu();
				mediator.changeBoardScreen(false, false);
				break;
			case "undo":
				mediator.swapScreens("undo");
				break;
			case "undoturn":
				ActionInvoker.getInstance().undoTurn();
				System.out.println("Undoing Turn!");
				break;
			case "undomove":
				ActionInvoker.getInstance().undoAction();
				System.out.println("Undoing Action");
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
