package dev.kaldiroglu.bootcamp.fundamentals.leak.after;


import dev.kaldiroglu.bootcamp.fundamentals.leak.Item;

public class Y {
	private X x;

//	   …
	
	public void f() {
		x.addItem(new Item());
	}
	
//	   …
}
