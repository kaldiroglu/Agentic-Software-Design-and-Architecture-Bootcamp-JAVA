package dev.kaldiroglu.bootcamp.fundamentals.leak.before;


import dev.kaldiroglu.bootcamp.fundamentals.leak.Item;

public class Z {
	private X x;

//	   …

	public void g(Item item) {
		x.getItems().remove(item);
	}

//	   …
}
