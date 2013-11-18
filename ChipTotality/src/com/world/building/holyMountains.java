package com.world.building;

public class holyMountains extends Building{

	private static int maxHitpoints=200;
	private static int cost = 20;
	
	public holyMountains(float x, float y, int width, int height) {
		super(x, y, width, height);
		isResearching=false;
	}
	
	@Override
	public void destroy(){
		super.destroy();
	}

	
}
