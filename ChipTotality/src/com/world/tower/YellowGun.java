package com.world.tower;


public class YellowGun extends RocketTower{
	final static int width = 1;
	final static int height = 1;
	static int firepower=5;
	static int maxHitpoints=150;
	static int range=300;
	static float shootDelay=0.5f;

	
	public YellowGun(int x, int y) {
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
