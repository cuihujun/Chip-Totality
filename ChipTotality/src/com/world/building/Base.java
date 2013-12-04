package com.world.building;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.gameInfo.GameStateHolder;
import com.res.Loader.AssetsLoader;

public class Base extends Building implements Upgradeable{
	final static int width=1;
	final static int height=1;
	static int maxHitpoints=500;
	private static int cost = 121;
	static int resourceDeliveryAmount = 7;
	static int resourceDeliveryTime = 5;
	
	
	private Task addResources;

	
	public Base(){
		super(width, height);
	}
	
	public Base(int x, int y) {
<<<<<<< HEAD
		super(x, y, width, height);		
		hitpoints=maxHitpoints;
=======
<<<<<<< HEAD
		super(x, y, width, height);		
		hitpoints=maxHitpoints;
=======
		super(x, y, width, height, maxHitpoints);		
>>>>>>> 5d92dc957f51a7f999b4229a43f19e3eb417e2e1
>>>>>>> 555e9397ac1e207a3f6ce48ac2fe572784fe5ff5
	}
	

	@Override
	public void destroy(){
		super.destroy();
		addResources.cancel();
	}


	@Override
	public void pay() {
		GameStateHolder.beings -= cost;	
	}


	@Override
	public void doTask() {
		addResources = new Task() {
			@Override
			public void run() {
				GameStateHolder.beings += resourceDeliveryAmount;
			}
		};
		Timer.schedule(addResources, resourceDeliveryTime, resourceDeliveryTime);
		
	}

<<<<<<< HEAD

	@Override
	public Texture getTexture() {
		return AssetsLoader.getTexture("TestBuilding1");
=======


	@Override
	public void dispose() {
		addResources.cancel();
		
>>>>>>> 5d92dc957f51a7f999b4229a43f19e3eb417e2e1
	}
	

		
	
	
}
