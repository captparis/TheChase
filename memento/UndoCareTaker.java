package memento;

import java.util.Stack;

public class UndoCareTaker {
	private Stack<GameMemento> mementoList = new Stack<GameMemento>();

	   public void add(GameMemento memento){
	      mementoList.push(memento);
	   }

	   public GameMemento get(){
	      return mementoList.pop();
	   }
}
