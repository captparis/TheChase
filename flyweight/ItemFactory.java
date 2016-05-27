package flyweight;

import java.util.HashMap;
import java.util.Map;

import models.BoardItem;
import models.items.*;

public class ItemFactory {

	public static final Map<String, BoardItem> itemMap = new HashMap<>();
	
	public static BoardItem getItem(String itemType) throws Exception{
		
		BoardItem item = itemMap.get(itemType);
		
		if(item == null){
			item = createItem(itemType);
			itemMap.put(itemType, item);
		}
		
		return item;
		
	}

	private static BoardItem createItem(String itemType) throws Exception {
		
		switch(itemType){
		case "AttackableGround":
			return new AttackableGround();
		case "Gate":
			return new Gate();
		case "Ground":
			return new Ground();
		case "MovableGround":
			return new MovableGround();
		}
		
		throw new Exception("Tried to get an invalid item!");

	}
}
