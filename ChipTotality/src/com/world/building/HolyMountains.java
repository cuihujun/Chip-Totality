package com.world.building;

import com.gameInfo.GameStateHolder;

public class HolyMountains extends Building implements Upgradeable{
	final static int width=3;
	final static int height=3;
	private static int maxHitpoints=200;
	private static int cost = 20;
	
	public HolyMountains(int x, int y, int width, int height) {
		super(x, y, width, height);
	}
	
	@Override
	public void destroy(){
		super.destroy();
	}

	@Override
	public void pay() {
		GameStateHolder.beings-=cost;
		
	}

	@Override
	public void doTask() {
		// TODO Auto-generated method stub
		
	}

	
}