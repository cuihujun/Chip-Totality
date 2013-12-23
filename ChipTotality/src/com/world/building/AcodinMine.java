package com.world.building;

import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.gameInfo.GameStateHolder;

public class AcodinMine extends Building{
	private static int resourceDeliveryAmount = 7;
	private static int resourceDeliveryTime = 5;

	private Task addResources;
	
	

	
	public AcodinMine(int x, int y) {
		super(x, y);	
	}
	
	@Override
	public void destroy(){
		addResources.cancel();
	}


	@Override
	public void doTask() {
		addResources=new Task() {
			
			@Override
			public void run() {
				GameStateHolder.dirtyAcodin+=resourceDeliveryAmount;
			}
		};
		Timer.schedule(addResources, resourceDeliveryTime, resourceDeliveryTime);	
	}
	

}
