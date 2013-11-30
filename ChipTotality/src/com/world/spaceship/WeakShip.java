package com.world.spaceship;


import com.res.Loader;
import com.world.Asteroid;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class WeakShip extends Spaceship{
	Asteroid mWorld;
	double angle = 0.0;
	public WeakShip(Vector2 pos)
	{
		super(pos, Loader.AssetsLoader.getTexture("spaceship"),Loader.AssetsLoader.getTexture("shoot1"));
		
		mMove=new Vector2(0,0);
		mTarget = new Vector2(-20.0f,-30.0f);
		mUnitSprite.setOrigin(35, 88);
		mUnitSprite.setScale(0.1f);
	}
	public  void run(float deltaTime, Asteroid world)
	{
		angle += 0.004;
		mTarget.x=(float) (-20.0+Math.sin(angle)*200.0);
		mTarget.y=(float) (-30.0+Math.cos(angle)*200.0);
		mWorld = world;
		mPos.x+=mMove.x*deltaTime;
		mPos.y+=mMove.y*deltaTime;
	//	mUnitSprite.rotate(3.0f);
		mMove.set(mTarget);
		
		mMove.sub(mPos);
		mMove.nor();
		mMove.scl(10.0f);
	}
	@Override
	public  Rectangle getBound()
	{
		return  new Rectangle(mPos.x-mUnitSprite.getWidth()/2,mPos.y-mUnitSprite.getHeight()/2,72*mUnitSprite.getScaleX(),77*mUnitSprite.getScaleY()); //new Rectangle(mPos.x-mUnitTexture.getWidth()/2 , mPos.y-mUnitTexture.getWidth()/2, mUnitTexture.getWidth(),mUnitTexture.getHeight());
	}
	public  void shoot()
	{
	
		mWorld.shoots.add(new Shoot(new Vector2(mPos.x+mUnitSprite.getOriginX()-18, mPos.y+mUnitSprite.getOriginY()-18), mShootTexture, new Vector2(mTarget), 10, 50));
	}
}
