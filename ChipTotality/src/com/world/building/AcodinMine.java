package com.world.building;

import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.gameInfo.GameStateHolder;

public class AcodinMine extends Building{

	private static int maxHitpoints=200;
	private static int cost = 20;
	private static int resourceDeliveryAmount = 5;
	private static int resourceDeliveryTime = 5;

	private Task addResources;
	
	
	public AcodinMine(float x, float y, int width, int height) {
		super(x, y, width, height);
		GameStateHolder.beings -= cost;
		hitpoints=maxHitpoints;
		
		addResources=new Task() {
			
			@Override
			public void run() {
				GameStateHolder.dirtyAcodin+=resourceDeliveryAmount;
			}
		};
		Timer.schedule(addResources, resourceDeliveryTime);
		
	}
	
	@Override
	public void destroy(){
		super.destroy();
		addResources.cancel();
	}
	
	
}
