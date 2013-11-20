package com.world.spaceships;

import com.res.TexturesHolder;
import com.world.Asteroid;
import com.badlogic.gdx.math.Vector2;

public class WeakShip extends Spaceship{
	Asteroid mWorld;
	public WeakShip(Vector2 pos)
	{
		super(pos,TexturesHolder.weakSpace, TexturesHolder.shoot1);
		mMove=new Vector2(0,0);
	}
	public  void run(float deltaTime, Asteroid world)
	{
		mWorld = world;
		mPos.x+=mMove.x*deltaTime;
		mPos.y+=mMove.y*deltaTime;
		
		mMove.add(mTarget);
		mMove.sub(mPos);
		mMove.mul(0.2f);
	}
	public  void shoot()
	{
		mWorld.shoots.add(new Shoot(new Vector2(mPos), mShootTexture, new Vector2(mTarget), 10, 500));
	}
}
