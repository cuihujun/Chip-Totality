package com.world.tower;

import com.action.FollowShipAction;
import com.gameInfo.Stats;
import com.screen.GameStage;


public class YellowGun extends Tower{
	
	public YellowGun(int x, int y) {
		super(x, y, Stats.Towers.YellowGun);
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
		Bullet newBullet = bulletType.spawnBullet(getX(), getY());
		newBullet.addAction(new FollowShipAction(currentTarget, 2f, 3f, true));
		GameStage.bulletsFromTowersGroup.addActor(newBullet);
		
	}

}
