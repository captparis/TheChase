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
import java.util.List;
import java.util.Map;

import javax.swing.*;

import models.*;
import models.items.*;
import main.*;
import views.*;

public class GameController {

	// constants
	private static DiceUtility dice;

	public static enum State {
		DICE_ROLL, MOVE, ATTACK, CHECK_WIN
	};

	private static final int ROWS = 6;
	private static final int COLUMNS = 6;

	private Map<String, ActorType[]> teamSetup;

	// models
	private Game game;
	// private Board board;

	// views
	private MainMenuView mainMenuView;
	private JFrame mainWindow;

	// controllers
	private final PlayerController playerController;
	private final UnitController unitController;
	private BoardController boardController;

	// State variables
	private Player currentPlayer;
	private Cell selectedCell;
	private List<Cell> lastCells;
	private State gameState;
	private Player winner;

	public GameController(JFrame mainWindow) {
		this.mainWindow = mainWindow;
		mainWindow.setResizable(true);
		this.game = new Game();
		boardController = new BoardController(this);
		unitController = new UnitController(this);
		playerController = new PlayerController(this, unitController);
		lastCells = new ArrayList<Cell>();
		dice = new DiceUtility();
		gameState = State.DICE_ROLL;
		setupTeams();
	}

	private void setupTeams() {

		teamSetup = new HashMap<>();

		teamSetup.put("Explorer",
				new ActorType[] { new ActorType("Hero", "models.explorers", COLUMNS - 1, ROWS - 2),
						new ActorType("Scout", "models.explorers", COLUMNS - 2, ROWS - 1),
						new ActorType("Tactician", "models.explorers", COLUMNS - 1, ROWS - 1),
						new ActorType("TrapMaster", "models.explorers", COLUMNS - 2, ROWS - 2), });

		teamSetup.put("Guardian",
				new ActorType[] { new ActorType("Behemoth", "models.guardians", 0, 0),
						new ActorType("Golem", "models.guardians", COLUMNS - 1, 0),
						new ActorType("Hunter", "models.guardians", 0, ROWS - 1) 
				        });
	}

	public void startGame() {
		System.out.println("Start Game");
		try {
			initGame();
			System.out.println("Explorer: " + game.getPlayer("Explorer").getName());
			System.out.println("Guardian: " + game.getPlayer("Guardian").getName());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		boardController.showBoard(mainWindow);
		// resize the main window to fit the size of the components.
		mainWindow.pack();

	}

	public void initGame() throws Exception {

		setCurrentPlayer(game.addPlayer("Explorer", playerController.newPlayer("Explorer")));
		game.addPlayer("Guardian", playerController.newPlayer("Guardian"));
		boardController.initBoard(ROWS, COLUMNS);
	}

	public void showMainMenu() {
		MenuActionListener listener = new MenuActionListener();
		mainMenuView = new MainMenuView(listener);
		mainWindow.getContentPane().add(mainMenuView);
		mainMenuView.setVisible(true);
	}

	State getGameState() {
		return this.gameState;
	}
	public void checkWin(){
        // Reset the dice rolls to 0
        boardController.setDiceRoll(0);
        boardController.resetCells(lastCells);
        boardController.repaintBoard();
        selectedCell= null;
	    gameState = GameController.State.CHECK_WIN;
        if (winner == null) {
            boardController.setDiceState();
            // Swap to the next player, this could be changed later to
            // facilitate more than 2 players
            boardController.swapPlayer();
            if (getCurrentPlayer().getTeam() == "Explorer") {
                try {
                    setCurrentPlayer(game.getPlayer("Guardian"));
                } catch (Exception noPlayer) {
                    System.out.println("Guardian player not found");
                    noPlayer.printStackTrace();
                }
            } else {
                try {
                    setCurrentPlayer(game.getPlayer("Explorer"));
                } catch (Exception noPlayer) {
                    System.out.println("Explorer player not found");
                    noPlayer.printStackTrace();
                }
            }
            gameState = GameController.State.DICE_ROLL;
        } else {
            boardController.setWinState();
        }
	}

	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	public void setCurrentPlayer(Player currentPlayer) {
		this.currentPlayer = currentPlayer;
		System.out.println(currentPlayer);
	}

	public int rollDice() {
		return dice.roll();
	}

	public void cellClicked(Cell cell) {

		if (gameState == State.DICE_ROLL || gameState == State.CHECK_WIN) {
			return;
		}
		if(cell == selectedCell && currentPlayer.getTeam() == "Guardian"){
			if( gameState ==State.MOVE){
				gameState = State.ATTACK;
			}
			else {
				gameState = State.MOVE;
			}
			System.out.println("gameState : "+gameState);
				
		}
		if(gameState == State.MOVE) {

		if (currentPlayer.hasActor((Actor) cell.getUnit())) {
			boardController.resetCells(lastCells);
			boardController.repaintBoard();
			selectedCell = cell;
			lastCells = boardController.movable(cell, currentPlayer.getRemainingMoves());
			boardController.drawCells(lastCells, gameState);
		} else if (cell.getItem() instanceof MovableGround) {
			// move the unit in the selected cell to the clicked cell
			boardController.resetCells(lastCells);
			boardController.repaintBoard();
		    int moveDistance = boardController.move(selectedCell, cell);

			// subtract the current players remaining moves by the distance
			// moved
			// (remaining moves go to zero for guardians as they can only move
			// once)
			try {
				if (currentPlayer.getTeam() == "Guardian") {
					currentPlayer.subtractRemainingMoves(currentPlayer.getRemainingMoves());
				} else {
					currentPlayer.subtractRemainingMoves(moveDistance);
					
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			// update the hudview with the number of remaining moves
			boardController.setDiceRoll(currentPlayer.getRemainingMoves());
			// reset the movable squares to ground and repaint the board
		
			boardController.resetCells(lastCells);
			boardController.repaintBoard();
		}
		}
		else {
			if (currentPlayer.hasActor((Actor) cell.getUnit())) {
				boardController.resetCells(lastCells);
				selectedCell = cell;
				lastCells = boardController.attackable(cell);
				boardController.drawCells(lastCells, gameState);
			}
			else if(!currentPlayer.hasActor((Actor) cell.getUnit())&& cell.getUnit()!=null){
				for (Cell temp:lastCells)
				{
					if(cell.getXPos()==temp.getXPos()&&cell.getYPos()==temp.getYPos())
					{
						
						System.out.println(selectedCell.getUnit().getClass().getSimpleName()+" is attacking " + cell.getUnit().getClass().getSimpleName());
						boardController.kill(cell);
						if(!playerController.hasLiveActor("Explorer")){
						    this.setWinner(currentPlayer);
						    
						}
						checkWin();
						return;
					}
				}
				
			}
			
			
	}
	}

	private void quitGame() {
		// System.exit(0);
		return;
	}

	// getter for testing only
	public PlayerController getPlayerController() {
		return playerController;
	}

	public void hudButtonClicked() {
		// Determines what actions should be completed when HUD button is
		// pressed and instigates them
		if (gameState == GameController.State.DICE_ROLL) {
			// player rolls dice
			playerController.newDiceRoll(currentPlayer, rollDice());

			// update the hud view with the new dice amount
			boardController.setDiceRoll(currentPlayer.getRemainingMoves()); 
			gameState = GameController.State.MOVE;
			boardController.setUnitState();
		} // Move to check win state, restart if nobody won
		else if (gameState == GameController.State.MOVE || gameState == GameController.State.ATTACK) {

			// Check if the player has won
			checkWin();
		}
	}

	public Map<String, ActorType[]> getTeamSetup() {
		return teamSetup;
	}

	class MenuActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			String option = ((JButton) e.getSource()).getName();

			switch (option) {
			case "startGame":
				startGame();
				break;
			case "options":
				System.out.println("show the options menu here.");
				break;
			case "quit":
				quitGame();
				break;
			default:
				break;
			}

			mainMenuView.setVisible(false); // remove the menu component
		}
	}

	public Map<String, Player> getPlayers() {
		return game.getPlayers();
	}
	

	public void setWinner(Player winner) {
		this.winner = winner;
	}

}
