package com.world.building;

import com.badlogic.gdx.graphics.Texture;
import com.gameInfo.GameStateHolder;
import com.res.Loader.AssetsLoader;

public class HolyMountains extends Building implements Upgradeable{
	final static int width=3;
	final static int height=3;
	private static int maxHitpoints=200;
	private static int cost = 20;
	
	public HolyMountains(){
		super(width, height);
	}
	
	public HolyMountains(int x, int y) {
		super(x, y, width, height);
		hitpoints=maxHitpoints;
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

	@Override
	public Texture getTexture() {
		return AssetsLoader.getTexture("HolyMountains");
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	
}