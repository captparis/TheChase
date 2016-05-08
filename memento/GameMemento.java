package memento;

import java.util.Map;

import models.Board;
import models.Player;

public class GameMemento {
	
	/**Example of save game
	 * GameMemento memento = game.createMemento();
        Caretaker ct = new Caretaker();
        ct.setMemento(memento);
        
        Example of load game
        game.restore(ct.getMemento());
	 */
	
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
