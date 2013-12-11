package com.world.tower;

import com.screen.GameStage;


//class for towers, which spawn bullets, instead of immediate shooting
public class RocketTower extends Tower {
	
	
	public RocketTower(int x, int y, int width, int height, int maxHitpoints, int range, float shootDelay) {
		super(x, y, width, height, maxHitpoints, range, shootDelay);
	}

	@Override
	public void shoot() {
		Rocket newBullet =  new TestRocket1(getX()+this.getWidth()/3, getY()+this.getHeight()/3, currentTarget); //TODO typ pocisku zalezny od wiezy
		getParent().addActor(newBullet);
		GameStage.bulletsFromTowers.add(newBullet);
	}

	@Override
	public void pay() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doTask() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
