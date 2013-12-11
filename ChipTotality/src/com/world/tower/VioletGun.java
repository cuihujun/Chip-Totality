package com.world.tower;

import com.screen.GameStage;


public class VioletGun extends RocketTower{
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
		Rocket newBullet =  new TestRocket1(getX()+this.getWidth()/3, getY()+this.getHeight()/3, currentTarget); //TODO typ pocisku zalezny od wiezy
		getParent().addActor(newBullet);
		GameStage.bulletsFromTowers.add(newBullet);
	}	
			
	@Override
	public void pay() {	
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

