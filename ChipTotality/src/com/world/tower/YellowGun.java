package com.world.tower;

import com.action.FollowShipAction;
import com.res.Loader.AssetsLoader;
import com.screen.GameStage;


public class YellowGun extends Tower{
	static int firepower=5;

	
	public YellowGun(int x, int y) {
		super(x, y);
	}
	

	@Override
	public void pay() {	
	}



	@Override
	public void doTask() {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void shoot() {
		Bullet newBullet = new Bullet(getX(), getY(), 10, 10, AssetsLoader.getTexture("TestBullet1"), firepower);
		newBullet.addAction(new FollowShipAction(currentTarget, 2f, 3f, true));
		GameStage.bulletsFromTowersGroup.addActor(newBullet);
		
	}


	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}





	

}
