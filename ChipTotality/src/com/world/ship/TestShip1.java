package com.world.ship;



public class TestShip1 extends Ship {
	//ten ship wysyla bullety
	static int maxHitpoints = 100;
	static float speed = 200;
	static int width = 20;
	static int height = 20;
	static int range = 300;

	
	public TestShip1(int x, int y)  {
		super(x, y, width, height, maxHitpoints);
	}
	

}
