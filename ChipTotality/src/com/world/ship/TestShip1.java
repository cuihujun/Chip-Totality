package com.world.ship;

import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;



public class TestShip1 extends Ship {
	static int maxHitpoints = 100;
	static float speed = 200;
	static int width = 10;
	static int height = 10;
	public MoveToAction action = new MoveToAction();
	
	public TestShip1(int x, int y)  {
		super(x, y, width, height, maxHitpoints);
	}
	

}
