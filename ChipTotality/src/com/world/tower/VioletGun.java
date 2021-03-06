package com.world.tower;

import com.action.MoveTowardsAction;
import com.badlogic.gdx.math.Vector2;
import com.gameInfo.Stats;
import com.screen.GameStage;


public class VioletGun extends Tower{
	
	public VioletGun(int x, int y) {
		super(x, y, Stats.Towers.VioletGun);		
	}
	
	@Override
	public void shoot() {
		Bullet newBullet = bulletType.spawnBullet(getX(), getY());
		newBullet.addAction(new MoveTowardsAction(new Vector2(currentTarget.getX()-getX(), currentTarget.getY()-getY()), 0.001f, 30, true));	
		GameStage.bulletsFromTowersGroup.addActor(newBullet);
	}	
			
	@Override
	public void pay() {	
		
	}

	@Override
	public void doTask() {
		// TODO Auto-generated method stub
		
	}

}

