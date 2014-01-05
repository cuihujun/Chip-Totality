package com.world.ship;

import com.action.MoveTowardsAction;
import com.badlogic.gdx.math.Vector2;
import com.gameInfo.Stats;
import com.screen.GameStage;
import com.world.tower.Bullet;



public class TestShip1 extends Ship {
		
	public TestShip1(int x, int y)  {		
		super(x, y, Stats.Ships.TestShip1);		
	}

	@Override
	public void shoot() {
		Bullet newBullet = bulletType.spawnBullet(getX(), getY());
		newBullet.addAction(new MoveTowardsAction(new Vector2(currentTarget.getX()-getX(), currentTarget.getY()-getY()), 0.02f, 3, true));	
		GameStage.bulletsFromShipsGroup.addActor(newBullet);
	}
	
}