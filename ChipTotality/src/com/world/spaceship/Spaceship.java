package com.world.spaceship;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.gameInfo.GameStateHolder;
import com.world.Asteroid;
public abstract class Spaceship {
	protected Vector2 mPos, mMove,mTarget;
	protected Sprite mUnitSprite;
	protected Texture mShootTexture;
	float mHP;
	private Task process;
	
	public Spaceship(Vector2 pos,Texture unitTex, Texture shootTex)
	{
		mPos=pos;
		mShootTexture = shootTex;
		mUnitSprite = new Sprite(unitTex);
		mUnitSprite.setPosition(mPos.x, mPos.y);
		process = new Task() {
			
			@Override
			public void run() {
				shoot();
			}
		};
		Timer.schedule(process, 1,1);
	}
	
	public Rectangle getBound()
	{
	
		return  new Rectangle(mPos.x-mUnitSprite.getWidth()/2,mPos.y-mUnitSprite.getHeight()/2,72*mUnitSprite.getScaleX(),77*mUnitSprite.getScaleY()); //new Rectangle(mPos.x-mUnitTexture.getWidth()/2 , mPos.y-mUnitTexture.getWidth()/2, mUnitTexture.getWidth(),mUnitTexture.getHeight());
	}
	public abstract void run(float deltaTime,Asteroid world);
	public void draw(SpriteBatch batch)
	{
		
		mUnitSprite.setPosition(mPos.x, mPos.y);
		mUnitSprite.setRotation((float)(-Math.atan2(mMove.x, mMove.y)/3.14*180-90));
		mUnitSprite.draw(batch);
	}
	public void giveTarget(Vector2 pos)	// TEST 
	{
		mTarget = pos;
	}
	public abstract void shoot();
}
