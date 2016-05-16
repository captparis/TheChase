/*
 *  OSSD Assignment 1 - The Chase
 *  Charles Yim - S3570764
 *  Jacob Paris - S3238163
 *  Chen Liu- S3481556
 *  Taison Eady - S3282633
 */
package controllers;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.*;

import models.*;
import models.guardians.Guardian;
import models.items.*;
import main.*;
import memento.Caretaker;
import memento.GameMemento;
import views.*;

public class GameController {

	// Constants
	private static DiceUtility dice;

	public static enum State {
		DICE_ROLL, MOVE, ATTACK, CHECK_WIN
	};

	// Game Setup Variables
	private static final int ROWS = 8;
	private static final int COLUMNS = 8;
	private Map<String, UnitType[]> teamSetup;

	// Models
	private Game game;
	private Settings settings;

	// views
	private MainMenuView mainMenuView;
	private OptionsMenuView optionsMenuView;
	private JPanel boardView;
	private JFrame mainWindow;
	private JPanel cards;

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

	// Constructor
	public GameController(JFrame mainWindow) {
		this.mainWindow = mainWindow;
		mainWindow.setResizable(false);
		this.game = Game.getInstance();
		boardController = new BoardController(this);
		unitController = new UnitController(this);
		playerController = new PlayerController(this, unitController);
		lastCells = new ArrayList<Cell>();
		dice = DiceUtility.getInstance();
		gameState = State.DICE_ROLL;
		settings = Settings.getInstance();
	}

	private void setupTeams() {

		teamSetup = new HashMap<>();

		teamSetup.put("Explorer",
				new UnitType[] { new UnitType("Hero", "models.explorers", settings.rows - 1, settings.columns - 2),
						new UnitType("Scout", "models.explorers", settings.rows - 2, settings.columns - 1),
						new UnitType("Tactician", "models.explorers", settings.rows - 1, settings.columns - 1),
						new UnitType("TrapMaster", "models.explorers", settings.rows - 2, settings.columns - 2), });

		teamSetup.put("Guardian",
				new UnitType[] { new UnitType("Behemoth", "models.guardians", 0, 0),
						new UnitType("Golem", "models.guardians", 0, settings.columns - 1),
						new UnitType("Hunter", "models.guardians", settings.rows - 1, 0) });
	}

	public void startGame() {
		System.out.println("Start Game");
		try {
			setupTeams();
			initGame();
			System.out.println("Explorer: " + game.getPlayer("Explorer").getName());
			System.out.println("Guardian: " + game.getPlayer("Guardian").getName());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		boardView = boardController.buildBoard();

		cards.add(boardView, "boardView");

		CardLayout cardLayout = (CardLayout) cards.getLayout();
		cardLayout.show(cards, "boardView");

		// resize the main window to fit the size of the components.
		mainWindow.pack();

	}

	public void initGame() throws Exception {

		// just for test save and load function
		JFrame tempWindow = new JFrame();
		tempWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		tempWindow.setSize(200, 100);
		tempWindow.setVisible(true);
		// tempWindow.pack();
		JPanel panel = new JPanel();
		tempWindow.add(panel);
		JButton button = new JButton("save");
		panel.add(button);
		button.addActionListener(new Save(game));

		JButton button2 = new JButton("Load");
		panel.add(button2);
//		button.addActionListener(new Load(game));
		// END for test save and load function

		setCurrentPlayer(game.addPlayer("Explorer", playerController.newPlayer("Explorer")));
		game.addPlayer("Guardian", playerController.newPlayer("Guardian"));
		boardController.initBoard(settings.rows, settings.columns);
		boardController.setPlayerName(currentPlayer);
	}

	// Set ups menu JPanel objects before displaying
	public void initialiseMenu() {
		cards = new JPanel();

		MenuActionListener listener = new MenuActionListener();
		mainMenuView = new MainMenuView(listener);

		OptionsActionListener optListener = new OptionsActionListener();
		optionsMenuView = new OptionsMenuView(optListener);

		cards.setLayout(new CardLayout());
		cards.add(mainMenuView, "mainMenu");
		cards.add(optionsMenuView, "optionsMenu");

		mainWindow.getContentPane().add(cards);

		// mainWindow.getContentPane().add(mainMenuView);
		showMainMenu();
	}

	public void showMainMenu() {
		CardLayout cardLayout = (CardLayout) cards.getLayout();
		cardLayout.show(cards, "mainMenu");
	}

	public void showOptions() {
		CardLayout cardLayout = (CardLayout) cards.getLayout();
		cardLayout.show(cards, "optionsMenu");
		// optionsMenuView.setVisible(true);
		// mainWindow.getContentPane().add(optionsMenuView);
		// mainMenuView.setVisible(false);
		// optionsMenuView.setVisible(true);
	}

	State getGameState() {
		return this.gameState;
	}

	public void checkWin() {
		// Reset the dice rolls to 0
		boardController.setDiceRoll(0);
		boardController.resetCells(lastCells);
		boardController.repaintBoard();
		selectedCell = null;
		gameState = GameController.State.CHECK_WIN;
		if (winner == null) {
			boardController.setDiceState();
			// Swap to the next player, this could be changed later to
			// facilitate more than 2 players

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
			boardController.swapPlayer(currentPlayer);
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

	// This method decides what happens when a cell is clicked.
	public void cellClicked(Cell cell) {

		if (cell.getUnit() == null) {
			boardController.switchSelectedHud(false);
		}

		// If the game is in either the DICE_ROLL or CHECK_WIN state there
		// should
		// be no action when a cell is clicked.
		if (gameState == State.DICE_ROLL || gameState == State.CHECK_WIN) {
			return;
		}

		// If it is the guardians turn and the user clicks on the selected unit,
		// toggle between move and attack
		if (cell == selectedCell && currentPlayer.getTeam() == "Guardian") {
			if (gameState == State.MOVE) {
				gameState = State.ATTACK;
			} else {
				gameState = State.MOVE;
			}
			System.out.println("gameState : " + gameState);
		}

		// Handles the movement phase
		if (gameState == State.MOVE) {

			if (currentPlayer.hasUnit(cell.getUnit())) {
				boardController.switchSelectedHud(true);
				boardController.setUnitName(cell.getUnit().toString());
				boardController.resetCells(lastCells);
				boardController.repaintBoard();
				selectedCell = cell;
				lastCells = boardController.movable(cell, currentPlayer.getRemainingMoves());
				boardController.drawActionCells(lastCells, gameState);
			} else if (cell.getItem() instanceof MovableGround) {
				// move the unit in the selected cell to the clicked cell
				boardController.resetCells(lastCells);
				boardController.repaintBoard();
				int moveDistance = boardController.move(selectedCell, cell);

				// subtract the current players remaining moves by the distance
				// moved
				// (remaining moves go to zero for guardians as they can only
				// move
				// once)
				try {
					if (currentPlayer.getTeam() == "Guardian") {
						currentPlayer.subtractRemainingMoves(currentPlayer.getRemainingMoves());
						gameState = State.ATTACK;
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
		// Game state must be ATTACK to reach this point
		else {
			// If the selected cell belongs to the current player show its
			// attackable field on the board
			if (currentPlayer.hasUnit(cell.getUnit())) {
				boardController.resetCells(lastCells);
				selectedCell = cell;
				lastCells = boardController.attackable(cell);
				boardController.drawActionCells(lastCells, gameState);
			}
			// if the selected cell contains a unit from the other team, attack
			// it.
			else if (!currentPlayer.hasUnit(cell.getUnit()) && cell.getUnit() != null
					&& cell.getItem() instanceof AttackableGround) {

				System.out.println(selectedCell.getUnit().getClass().getSimpleName() + " is attacking "
						+ cell.getUnit().getClass().getSimpleName());
				boardController.kill(cell);
				if (!playerController.hasLiveActor("Explorer")) {
					this.setWinner(currentPlayer);
				}

				checkWin();
				return;

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
		// game state must be in CHECK_WIN
		else {
			if (winner != null) {
				showMainMenu();
			}
		}
	}

	// Sets text fields on the option screens to preset sizes when buttons are
	// clicked
	public void optionsBoardSizeBtn(int size) {
		optionsMenuView.setBoardFields(size);
	}

	public void defaultPieces() {
		optionsMenuView.setToDefault();
	}
	
	public void applySettings(){
		int newColumns = optionsMenuView.getColumns();
		int newRows = optionsMenuView.getRows();
		settings.setBoardSize(newRows, newColumns);
		System.out.println("Applied new settings");
		
		ArrayList<String> inactiveUnits = optionsMenuView.getInactiveUnits();
		if (inactiveUnits != null){
			for (String t : inactiveUnits){
				settings.activeUnits.put(t, false);
			}
		}
	}
	
	public Map<String, UnitType[]> getTeamSetup() {
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
				showOptions();
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

	class OptionsActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			String option = ((JButton) e.getSource()).getName();

			switch (option) {
			case "startGame":
				startGame();
				break;
			case "options":
				showOptions();
				break;
			case "back":
				showMainMenu();
				break;
			case "small":
				optionsBoardSizeBtn(15);
				break;
			case "medium":
				optionsBoardSizeBtn(25);
				break;
			case "large":
				optionsBoardSizeBtn(40);
				break;
			case "defaultpieces":
				defaultPieces();
				break;
			case "apply":
				applySettings();
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

// just for test save and load function
class Save implements ActionListener {
	Game game;
	public Save(Game game){
		this.game = game;
	}
	public void actionPerformed(ActionEvent e) {
		System.out.println("save");
		GameMemento memento = game.createMemento();
		Caretaker ct = new Caretaker();
		ct.setMemento(memento);
	}
}

//class Load implements ActionListener {
//	Game game;
//	public Load(Game game){
//		this.game = game;
//	}
//	public void actionPerformed(ActionEvent e) {
//		Caretaker ct = new Caretaker();
//		game.restore(ct.getMemento());
//	}
//}
// END for test save and load function
