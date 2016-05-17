package models;

import java.io.Serializable;
import java.util.*;

import memento.GameMemento;

public class Game implements Serializable {

	private Map<String, Player> players;
	private Board board;

	// create an object of SingleObject
	private static Game instance = new Game();

	private Game() {
		this.players = new HashMap<>();
	}

	// Get the only object available
	public static Game getInstance() {
		return instance;
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
}
