package com.world.building;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.gameInfo.GameStateHolder;
import com.res.Loader.AssetsLoader;

public class Temple extends Building implements Upgradeable {
	final static int width = 2;
	final static int height = 2;
	private static int maxHitpoints = 200;
	private static int cost = 20;
	private static int resourceDeliveryAmount = 5;
	private static int resourceDeliveryTime = 5;

	Task addResources;

<<<<<<< HEAD
	public Temple(int x, int y) {
		super(x, y, width, height);
		hitpoints = maxHitpoints;

=======
	public Temple(){
		super(width, height);
	}
	
	public Temple(int x, int y) {
		super(x, y, width, height, maxHitpoints);
>>>>>>> 5d92dc957f51a7f999b4229a43f19e3eb417e2e1
	}

	@Override
	public void destroy() {
		super.destroy();
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
		Timer.schedule(addResources, resourceDeliveryTime);
	}
<<<<<<< HEAD

	@Override
	public Texture getTexture() {
		return AssetsLoader.getTexture("Temple");
	}

=======


	@Override
	public void dispose() {
		addResources.cancel();
		
	}

>>>>>>> 5d92dc957f51a7f999b4229a43f19e3eb417e2e1
}
