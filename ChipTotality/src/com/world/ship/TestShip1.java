package com.world.ship;

import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;



public class TestShip1 extends Ship {
	static int maxHitpoints = 100;
	static float speed = 200;
	
	public TestShip1(float x, float y)  {
		super(x, y, maxHitpoints);
		//TODO do usuniecia
		
		MoveToAction action = new MoveToAction();
		action.setDuration(3);
		action.setPosition(1500, 1400);
		
		addAction(action);

	}
	

}
