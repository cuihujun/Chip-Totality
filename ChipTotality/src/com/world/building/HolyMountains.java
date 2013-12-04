package com.world.building;

<<<<<<< HEAD
import com.badlogic.gdx.graphics.Texture;
import com.gameInfo.GameStateHolder;
import com.res.Loader.AssetsLoader;
=======
import com.gameInfo.GameStateHolder;
>>>>>>> 5d92dc957f51a7f999b4229a43f19e3eb417e2e1

public class HolyMountains extends Building implements Upgradeable{
	final static int width=3;
	final static int height=3;
	private static int maxHitpoints=200;
	private static int cost = 20;
	
<<<<<<< HEAD
	public HolyMountains(int x, int y) {
		super(x, y, width, height);
=======
	public HolyMountains(){
		super(width, height);
	}
	
	public HolyMountains(int x, int y) {
		super(x, y, width, height, maxHitpoints);
		
>>>>>>> 5d92dc957f51a7f999b4229a43f19e3eb417e2e1
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

<<<<<<< HEAD
	@Override
	public Texture getTexture() {
		return AssetsLoader.getTexture("HolyMountains");
=======

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
>>>>>>> 5d92dc957f51a7f999b4229a43f19e3eb417e2e1
	}

	
}