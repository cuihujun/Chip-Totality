package com.world.spaceships;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.world.Asteroid;
public abstract class Spaceship {
	protected Vector2 mPos, mMove,mTarget;
	protected Texture mUnitTexture,mShootTexture;
	float mHP;
	
	public Spaceship(Vector2 pos,Texture unitTex, Texture shootTex)
	{
		mPos=pos;
		mShootTexture = shootTex;
		mUnitTexture = unitTex;
	}
	
	public Rectangle getBound()
	{
		return  new Rectangle(mPos.x-mUnitTexture.getWidth()/2 , mPos.y-mUnitTexture.getHeight()/2, mUnitTexture.getWidth(),mUnitTexture.getHeight());
	}
	public abstract void run(float deltaTime,Asteroid world);
	public void draw(SpriteBatch batch)
	{
		batch.draw(mUnitTexture, mPos.x, mPos.y);
		Gdx.app.log("spaceship", "Rysowanie");
	}
	public void giveTarget(Vector2 pos)	// TEST 
	{
		mTarget = pos;
	}
	public abstract void shoot();
}
