package dev.kaldiroglu.bootcamp.fundamentals.leak.before;

import java.util.List;

import dev.kaldiroglu.bootcamp.fundamentals.leak.Item;

public class X {
	private List<Item> items;

//	   ...
	
	public List<Item> getItems() {
		return items;
	}
	
//	   …
	
}


