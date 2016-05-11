package memento;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

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
		for (Entry<String, Player> entry : players.entrySet()){
			Player player = entry.getValue().clone();
			this.players.put(entry.getKey(), player);
		}
		this.board = board.clone();
	}
	
	public Map<String, Player> getPlayers(){
		return players;
	}
	
	public Board getBoard(){
		return board;
	}
}
