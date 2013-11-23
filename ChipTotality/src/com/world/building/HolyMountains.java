package com.world.building;

public class HolyMountains extends Building implements Upgradeable{

	private static int maxHitpoints=200;
	private static int cost = 20;
	
	public HolyMountains(float x, float y, int width, int height) {
		super(x, y, width, height);
	}
	
	@Override
	public void destroy(){
		super.destroy();
	}

	
}