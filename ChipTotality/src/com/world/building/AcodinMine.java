package com.world.building;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.gameInfo.GameStateHolder;
import com.res.Loader.AssetsLoader;

public class AcodinMine extends Building{
	final static int width=2;
	final static int height=2;
	private static int maxHitpoints=200;
	private static int cost = 20;
	private static int resourceDeliveryAmount = 7;
	private static int resourceDeliveryTime = 5;

	private Task addResources;
	
	
<<<<<<< HEAD
	public AcodinMine(int x, int y) {
		super(x, y, width, height);
		hitpoints=maxHitpoints;
		doTask();
=======
	public AcodinMine(){
		super(width, height);
	}
	
	public AcodinMine(int x, int y) {
		super(x, y, width, height, maxHitpoints);	
>>>>>>> 5d92dc957f51a7f999b4229a43f19e3eb417e2e1
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
		addResources=new Task() {
			
			@Override
			public void run() {
				GameStateHolder.dirtyAcodin+=resourceDeliveryAmount;
			}
		};
<<<<<<< HEAD
		Timer.schedule(addResources, resourceDeliveryTime);	
	}

	@Override
	public Texture getTexture() {
		return AssetsLoader.getTexture("AcodinMine");
=======
		Timer.schedule(addResources, resourceDeliveryTime, resourceDeliveryTime);	
	}


	@Override
	public void dispose() {
		addResources.cancel();
		
>>>>>>> 5d92dc957f51a7f999b4229a43f19e3eb417e2e1
	}
}
