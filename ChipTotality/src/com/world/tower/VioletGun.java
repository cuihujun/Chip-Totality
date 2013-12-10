package com.world.tower;


public class VioletGun extends RocketTower{
	final static int width = 2;
	final static int height = 2;
	static int maxHitpoints=150;
	static int range=300;
	static float shootDelay=1;
	
	public VioletGun(int x, int y) {
		super(x, y, width, height, maxHitpoints, range, shootDelay);
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

