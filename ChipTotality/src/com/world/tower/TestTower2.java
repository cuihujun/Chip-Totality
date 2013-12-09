package com.world.tower;

import com.world.ship.Ship;

public class TestTower2 extends Tower {
	final static int width = 2;
	final static int height = 2;
	static int maxHitpoints=150;
	static int range=1150;
	static float shootDelay=1;
	
	
	TestTower2(int x, int y){
		super(x, y, width, height, maxHitpoints, range);
	}
	
	@Override
	void shoot(Ship target) {
		

	}

	@Override
	public void pay() {

	}

	@Override
	public void doTask() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
