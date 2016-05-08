package memento;

import java.util.Map;

import models.Board;
import models.Player;

public class GameMemento {
	
	// TODO change player from map to single object
	private Map<String, Player> players;
	private Board board;

	public GameMemento(Map<String, Player> players, Board board) {
		this.players = players;
		this.board = board.clone();
	}
	
	public Map<String, Player> getPlayers(){
		return players;
	}
	
	public Board getBoard(){
		return board;
	}
}
