package memento;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import models.Board;
import models.Game;
import models.Player;

public class GameMemento implements Serializable{
	
	// TODO change player from map to single object
	private Game game;
//	private Board board;

	public GameMemento(Game game) {
		this.game = game;
//		this.board = board;
	}
	
	public Game getGame(){
		return game;
	}
	
//	public Board getBoard(){
//		return board;
//	}
	
}
