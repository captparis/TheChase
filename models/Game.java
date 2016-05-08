package models;

import java.util.*;

public class Game implements Cloneable{

	private Map<String, Player> players;
	private Board board;

	public Game() {
		this.players = new HashMap<>();
	}

	public Player addPlayer(String playerTeam, Player newPlayer) throws Exception {

		Player existingPlayer = players.put(playerTeam, newPlayer);

		if (existingPlayer == null) {
			return newPlayer;
		} else {
			throw new Exception("Player already exists.");
		}

	}

	public Player getPlayer(String playerTeam) throws Exception {
		Player player = players.get(playerTeam);

		if (player == null) {
			throw new Exception("Specified player does not exist.");
		} else {
			return player;
		}
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public Map<String, Player> getPlayers() {
		return this.players;
	}
	
	public void loadGame(String fileName){
		//TODO: read from file and create game objects
	}
	
	public void saveGame(String fileName){
		//TODO: save the game objects to the the given file.
	}
}
