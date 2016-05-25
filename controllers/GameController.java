/*
 *  OSSD Assignment 1 - The Chase
 *  Charles Yim - S3570764
 *  Jacob Paris - S3238163
 *  Chen Liu- S3481556
 *  Taison Eady - S3282633
 */

package controllers;

import java.awt.CardLayout;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;

import decorators.*;
import models.*;
import models.items.*;
import main.*;
import mediator.Mediator;
import memento.Caretaker;
import memento.GameMemento;
import views.*;


public class GameController {

	// Constants
	private static DiceUtility dice;
	
	private Mediator mediator = Mediator.getInstance();

	public static enum State {
		DICE_ROLL, MOVE, ATTACK, CHECK_WIN
	};

	// Game Setup Variables
	private Map<String, List<UnitType>> teamSetup;

	// Models
	public Game game;
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

	// Memento
	Caretaker ct = new Caretaker();

	// Constructor
	public GameController(JFrame mainWindow) {
	    
		this.mainWindow = mainWindow;
		mainWindow.setResizable(false);
		this.game = Game.getInstance();
		boardController = new BoardController(this);
		unitController = new UnitController(this);
		playerController = new PlayerController(this, unitController);
		game.setLastCells(new ArrayList<Cell>());
		dice = DiceUtility.getInstance();
		game.setGameState(State.DICE_ROLL);
		settings = Settings.getInstance();
	}

	private void setupTeams() {

		teamSetup = new HashMap<>();
		
		List<UnitType> explorerUnitTypes = new ArrayList<UnitType>();
		if (settings.activeUnits.get("hero") == true){
			explorerUnitTypes.add(new UnitType("Hero", "models.explorers", 
					settings.rows - 1, settings.columns - 2));
		}
		if (settings.activeUnits.get("trapmaster") == true){
			explorerUnitTypes.add(new UnitType("TrapMaster", "models.explorers", 
					settings.rows - 2, settings.columns - 2));
		}
		if (settings.activeUnits.get("tactician") == true){
			explorerUnitTypes.add(new UnitType("Tactician", "models.explorers", settings.rows - 1, settings.columns - 1));
		}
		if (settings.activeUnits.get("scout") == true){
			explorerUnitTypes.add(new UnitType("Scout", "models.explorers", settings.rows - 2, settings.columns - 1));
		}
		
		List<UnitType> guardianUnitTypes = new ArrayList<UnitType>();;
		if (settings.activeUnits.get("golem") == true){
			guardianUnitTypes.add(new UnitType("Golem", "models.guardians", 0, settings.columns - 1));
		}
		if (settings.activeUnits.get("hunter") == true){
			guardianUnitTypes.add(new UnitType("Hunter", "models.guardians", settings.rows - 1, 0));
		}
		if (settings.activeUnits.get("behemoth") == true){
			guardianUnitTypes.add(new UnitType("Behemoth", "models.guardians", 0, 0));
		}
		
		teamSetup.put("Explorer", explorerUnitTypes);
		teamSetup.put("Guardian", guardianUnitTypes);
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
		
		setCurrentPlayer(game.addPlayer("Explorer", playerController.newPlayer("Explorer")));
		game.addPlayer("Guardian", playerController.newPlayer("Guardian"));
		boardController.initBoard(settings.rows, settings.columns, game);
		mediator.setPlayerName(game.getCurrentPlayer().getName());
		
	}
	
	public void loadGame(Game game){
		Board.clearInstance();
		this.game = game;
		System.out.println("Load game!!!");
		System.out.println(game.getBoard());
		//boardController.initBoard(game.getBoard().getRows(), game.getBoard().getColumns(), game);
		
		boardController.refreshBoard(game.getBoard().getRows(), game.getBoard().getColumns(), game);
		boardView = boardController.buildBoard();

		cards.add(boardView, "boardView");

		CardLayout cardLayout = (CardLayout) cards.getLayout();
		cardLayout.show(cards, "boardView");

		// resize the main window to fit the size of the components.
		mainWindow.pack();

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
		return game.getGameState();
	}

	public void checkWin() {
	    if (!playerController.hasLiveActor("Explorer")) {
            this.setWinner(game.getCurrentPlayer());
        }
		// Reset the dice rolls to 0
		mediator.setDiceRoll(0);
		boardController.resetCells(game.getLastCells());
		boardController.repaintBoard();
		game.setSelectedCell(null);
		game.setGameState(GameController.State.CHECK_WIN);
		if (game.getWinner() == null) {
			mediator.setDiceState();
			// Swap to the next player, this could be changed later to
			// facilitate more than 2 players

			if (getCurrentPlayer().getTeam().equals("Explorer")) {
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
			mediator.swapPlayer(game.getCurrentPlayer().getName());
			game.setGameState(GameController.State.DICE_ROLL);
		} else {
			mediator.setWinState();
		}
		boardController.switchSelectedHud(false);
	}

	public Player getCurrentPlayer() {
		return game.getCurrentPlayer();
	}

	public void setCurrentPlayer(Player currentPlayer) {
		game.setCurrentPlayer(currentPlayer);
		System.out.println(currentPlayer);
	}

	public int rollDice() {
		return dice.roll();
	}
	public void swapMode(String mode){
	    System.out.println(game.getSelectedCell().getUnit().toString());
	    System.out.print("Unit mode before: "+game.getSelectedCell().getUnit().getClass()+" Click mode: "+mode);
	    try{
	    if(game.getSelectedCell().getUnit().getClass().getSimpleName().equals("AgileUnitDecorator") && !mode.equals("modeAgile"))
	    {	        
	            if(game.getCurrentPlayer().getTeam().equals("Explorer")){	                
	            	game.getSelectedCell().setUnit(new DefensiveUnitDecorator(((AbstractUnitDecorator)game.getSelectedCell().getUnit()).getInnerUnit()));
	            }
	            else{
	            	game.getSelectedCell().setUnit(new AttackUnitDecorator(((AbstractUnitDecorator)game.getSelectedCell().getUnit()).getInnerUnit()));
	            }
	    }
	    else if (!game.getSelectedCell().getUnit().getClass().getSimpleName().equals("AgileUnitDecorator") && mode.equals("modeAgile") )
	    {     
	        	   game.getSelectedCell().setUnit(new AgileUnitDecorator(((AbstractUnitDecorator)game.getSelectedCell().getUnit()).getInnerUnit()));
	               game.getCurrentPlayer().setUnit(game.getSelectedCell().getUnit().toString(), game.getSelectedCell().getUnit());    
	    }
        game.getCurrentPlayer().setUnit(game.getSelectedCell().getUnit().toString(), game.getSelectedCell().getUnit());    

	    }
	    catch(Exception e) {
            e.printStackTrace();
        }
	    System.out.println(" Unit mode after: "+game.getSelectedCell().getUnit().getClass());
	    if (game.getGameState() == State.MOVE) {

            this.move(game.getSelectedCell());
        }
        // Game state must be ATTACK to reach this point
        if (game.getGameState() == State.ATTACK){
            this.attack(game.getSelectedCell());
        }
	}

	// This method decides what happens when a cell is clicked.
	public void cellClicked(Cell cell) {

		

		// If the game is in either the DICE_ROLL or CHECK_WIN state there
		// should
		// be no action when a cell is clicked.
		if (game.getGameState() == State.DICE_ROLL || game.getGameState() == State.CHECK_WIN) {
			return;
		}
		//If the selected cell don't have unit
		if (cell.getUnit() == null) {
            boardController.switchSelectedHud(false);
        }
		else{
		    boardController.switchSelectedHud(true);
		    boardController.setUnitName(cell.getUnit().toString());
		}

		// If it is the guardians turn and the user clicks on the selected unit,
		// toggle between move and attack
		if (cell == game.getSelectedCell() && game.getCurrentPlayer().getTeam() == "Guardian") {
			this.swapAtkMov();
		}

		// Handles the movement phase
		if (game.getGameState() == State.MOVE) {

			this.move(cell);
		}
		// Game state must be ATTACK to reach this point
		if (game.getGameState() == State.ATTACK){
			this.attack(cell);
		}
		game.setSelectedCell(cell);
	}
	private void swapAtkMov()
	{
	    if (game.getGameState() == State.MOVE) {
            game.setGameState(State.ATTACK);
        } else if(this.getCurrentPlayer().getRemainingMoves() !=0){
            game.setGameState(State.MOVE);
        }
        System.out.println("gameState : " + game.getGameState());
	}
	private void move(Cell cell)
	{
	    if (game.getCurrentPlayer().hasUnit(cell.getUnit())) {
            boardController.resetCells(game.getLastCells());
            game.setLastCells(boardController.getAbleList(cell, this.getGameState()));
            boardController.drawActionCells(game.getLastCells(), game.getGameState());
        } else if (cell.getItem() instanceof MovableGround) {
            // move the unit in the selected cell to the clicked cell
            boardController.resetCells(game.getLastCells());
            int moveDistance = boardController.move(game.getSelectedCell(), cell);

            // subtract the current players remaining moves by the distance
            // moved
            // (remaining moves go to zero for guardians as they can only
            // move
            // once)
            try {
                if (game.getCurrentPlayer().getTeam() == "Guardian") {
                    game.getCurrentPlayer().subtractRemainingMoves(game.getCurrentPlayer().getRemainingMoves());
                    this.swapAtkMov();
                } else {
                    game.getCurrentPlayer().subtractRemainingMoves(moveDistance);
                    
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            // update the hudview with the number of remaining moves
            mediator.setDiceRoll(game.getCurrentPlayer().getRemainingMoves());
            // reset the movable squares to ground and repaint the board
            boardController.resetCells(game.getLastCells());
        }  
	}
	private void attack(Cell cell){
	 // If the selected cell belongs to the current player show its
        // attackable field on the board
        if (game.getCurrentPlayer().hasUnit(cell.getUnit())) {
            boardController.resetCells(game.getLastCells());
            if(!cell.getUnit().getClass().getSimpleName().equals("AgileUnitDecorator"))
            {
            game.setLastCells(boardController.getAbleList(cell, this.getGameState()));
            boardController.drawActionCells(game.getLastCells(), game.getGameState());
            }
        }
        // if the selected cell contains a unit from the other team, attack
        // it.
        else if (!game.getCurrentPlayer().hasUnit(cell.getUnit()) && cell.getUnit() != null
                && cell.getItem() instanceof AttackableGround) {

            System.out.println(game.getSelectedCell().getUnit().getClass().getSimpleName() + " is attacking "
                    + cell.getUnit().getClass().getSimpleName());
            boardController.kill(cell); 
            this.endTurn();
        
        }
	}
	private void endTurn(){
	    checkWin();
	    
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
		if (game.getGameState() == GameController.State.DICE_ROLL) {
			// player rolls dice
			playerController.newDiceRoll(game.getCurrentPlayer(), rollDice());

			// update the hud view with the new dice amount
			mediator.setDiceRoll(game.getCurrentPlayer().getRemainingMoves());
			boardController.swapTeam(game.getCurrentPlayer().getTeam());
			game.setGameState(GameController.State.MOVE);
			mediator.setUnitState();
		} // Move to check win state, restart if nobody won
		else if (game.getGameState() == GameController.State.MOVE || game.getGameState() == GameController.State.ATTACK) {

			this.endTurn();
		}
		// game state must be in CHECK_WIN
		else {
			if (game.getWinner() != null) {
				showMainMenu();
				mediator.changeBoardScreen(false, false);
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
	
	public Map<String, List<UnitType>> getTeamSetup() {
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
				optionsBoardSizeBtn(8);
				break;
			case "medium":
				optionsBoardSizeBtn(12);
				break;
			case "large":
				optionsBoardSizeBtn(15);
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
		game.setWinner(winner);
	}
	
	
	public void save (){
		System.out.println("save");
		GameMemento memento = new GameMemento(game);
		ct.setMemento(memento);
		System.out.println("Board is: "+memento.getGame().getBoard().getCells().length);
	}
	
	public void load (){
		Caretaker newct = new Caretaker();
		System.out.println("load");
		System.out.println(newct.getMemento().getGame().getBoard().getCells().length);
		loadGame(ct.getMemento().getGame());
	}

}

