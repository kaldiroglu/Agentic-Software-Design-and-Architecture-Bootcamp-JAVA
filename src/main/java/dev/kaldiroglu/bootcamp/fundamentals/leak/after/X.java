package dev.kaldiroglu.bootcamp.fundamentals.leak.after;

import java.util.List;

import dev.kaldiroglu.bootcamp.fundamentals.leak.Item;

public class X {
	private List<Item> items;

//	   ...
	
	public boolean addItem(Item item) {
		//...
		return items.add(item);
	}

	public boolean removeItem(Item item) {
		//...
		return items.remove(item);
	}
	
//	   …
	
}


