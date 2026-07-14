package dev.kaldiroglu.bootcamp.fundamentals.leak.before;


import dev.kaldiroglu.bootcamp.fundamentals.leak.Item;

public class Y {
	private X x;

//	   …
	
	public void f() {
		x.getItems().add(new Item());
	}
	
//	   …
}
