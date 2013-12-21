package com.world.tower;

import com.action.MoveTowardsAction;
import com.badlogic.gdx.math.Vector2;
import com.res.Loader.AssetsLoader;
import com.screen.GameStage;


public class VioletGun extends Tower{
	final static int width = 1;
	final static int height = 1;
	static int firepower=1;
	static int maxHitpoints=150;
	static int range=400;
	static float shootDelay=0.3f;
	
	public VioletGun(int x, int y) {
		super(x, y, width, height, maxHitpoints, range, shootDelay);
	}
	
	@Override
	public void shoot() {
		Bullet newBullet = new Bullet(getX(), getY(), 10, 10, AssetsLoader.getTexture("TestBullet1"), firepower);
		newBullet.addAction(new MoveTowardsAction(new Vector2(currentTarget.getX()-getX(), currentTarget.getY()-getY()), 0.02f, 3, true));
		GameStage.bulletsFromTowersGroup.addActor(newBullet);
	}	
			
	@Override
	public void pay() {	
		
	}

	@Override
	public void doTask() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}




}

