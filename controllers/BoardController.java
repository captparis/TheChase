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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

import controllers.GameController.State;
import models.*;
import models.explorers.Explorer;
import models.guardians.Guardian;
import models.items.*;
import views.BoardView;
import views.HudView;

public class BoardController {

	private GameController gameController;

	private Board board;
	private BoardView boardView;
	private HudView hudView;
	private BoardItem ground;
	private BoardItem movableGround;
	private BoardItem attackableGround;
	private BoardItem gate;


	public BoardController(GameController gameController) {

		this.gameController = gameController;
		ground = new Ground();
		movableGround = new MovableGround();
		attackableGround = new AttackGround();
	}

	// this method assumes that the board has been initialized prior to calling.
	public void showBoard(JFrame mainWindow) {
		// create a new content panel holding both the grid and the hud
		JPanel contentPanel = new JPanel(new BorderLayout());
		contentPanel.add(boardView, BorderLayout.CENTER);
		contentPanel.add(hudView, BorderLayout.SOUTH);
		mainWindow.getContentPane().add(contentPanel);
		mainWindow.pack();
		contentPanel.setVisible(true);
	}

	public void initBoard(int rows, int columns) {
		
		board = new Board(rows, columns, ground);

		boardView = new BoardView(new MouseActionListener(), rows, columns, board.getCells(),board.getBorder());
		hudView = new HudView(new HUDActionListener());

		for (Player player : gameController.getPlayers().values()) {
			for (Actor actor : player.actors.values()) {
				setCellUnit(actor.getInitX(), actor.getInitY(), actor);
			}
		}
		initItems();
	}

	private void initItems() {
		gate = new Gate();
		setItem();
	}
	private void setItem() {
		setCellItem(0, 0, gate);
		setCellItem(1, 0, gate);
		setCellItem(0, 1, gate);
	}

	// Sets the unit for the cell
	public void setCellUnit(int x, int y, Unit unit) {
		board.getCells()[x][y].setUnit(unit);
	}

	// Sets the unit for the cell
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

	void swapPlayer() {
		hudView.swapPlayer();
	}

	void setWinState() {
		hudView.setWinState();
	}

	// assumes origin contains a movable unit and can legally move to target.
	public int move(Cell origin, Cell target) {
		// move the unit from the origin to the target and replace the origin
		// with ground.
		target.setUnit(origin.getUnit());
		origin.setUnit(null);

		// if the explorer moving into a gate cell update game controller
		// winstate
		if (target.getUnit() instanceof Explorer && target.getItem() instanceof Gate) {
			gameController.setWinner(gameController.getPlayers().get("Explorer"));
		}

		// return the distance that the unit moved.
		return getDistance(origin, target);
	}
	public void kill(Cell target){
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
	public List<Cell> attackable(Cell origin) {
		// TODO need to fix 
		Guardian unit = (Guardian) origin.getUnit();
		List<Pos> attackRange = new ArrayList<Pos>(unit.getAttackRange());
		List<Cell> attackCells = new ArrayList<>();
		int xPos = origin.getXPos();
		int yPos = origin.getYPos();
		//temp
		for (Pos range :attackRange){
			if (xPos + range.getXPos() < 0 || xPos + range.getXPos() >= board.getColumns()) {
				continue;
			}
			else if (yPos + range.getYPos() < 0 || yPos + range.getYPos() >= board.getRows()) {
				continue;
			}
			else{
				int attackX = origin.getXPos() + range.getXPos();
				int attackY = origin.getYPos() + range.getYPos();

					Cell attackCell = board.getCells()[attackX][attackY];
					
					attackCells.add(attackCell);
			}
		}
	
		return attackCells;
	
	
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
	public void drawCells(List<Cell> Cells , GameController.State state) {
		
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

	public void resetMovable(List<Cell> movableCells) {
		for (Cell movableCell : movableCells) {
			movableCell.setItem(ground);
			movableCell.repaint();
		}
	}

	class MouseActionListener implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e)
		{
			// TODO Auto-generated method stub
			Cell cell = ((Cell) e.getSource());

			gameController.cellClicked(cell);
			
		}

		@Override
		public void mousePressed(MouseEvent e)
		{
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e)
		{
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e)
		{
			// TODO Auto-generated method stub
			//0 = item, 1 = ally, 2 = enemy
			int type = 0;

			
			if(((Cell) e.getSource()).getUnit() != null) {
				if (gameController.getCurrentPlayer().hasActor((Actor) ((Cell) e.getSource()).getUnit())) {
				    type = 1;
				}
				else {
					type = 2;
				}
			}
			boardView.changeBorder(((Cell) e.getSource()), board.getMouseOverBorder(type));	
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
			gameController.hudButtonClicked();
		}
	}

	public void repaintBoard() {
		setItem();
		boardView.repaintBoard();
	}
}
