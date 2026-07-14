package dev.kaldiroglu.bootcamp.fundamentals.leak.after;

import dev.kaldiroglu.bootcamp.fundamentals.leak.Item;

public class Z {
	private X x;

//	   …
	
	public void g(Item item) {
		x.removeItem(item);
	}
	
//	   …
}
