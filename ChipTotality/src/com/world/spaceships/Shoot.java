package com.world.spaceships;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.world.Asteroid;

public class Shoot {
	protected Vector2 mPos, mMove;
	protected Texture mShootTexture;
	public float mDamage,mSpeed;
	
	public Shoot (Vector2 pos, Texture shootTex, Vector2 target, float damage,float speed)
	{
		mPos=pos;
		mDamage = damage;
		mShootTexture = shootTex;
		mSpeed = speed;
		mMove= new Vector2();
		mMove.add(target);
		mMove.sub(mPos);
		mMove.nor();
		mMove.mul(mSpeed);
	}
	
	public Rectangle getBound()
	{
		return  new Rectangle(mPos.x-mShootTexture.getWidth()/2 , mPos.y-mShootTexture.getHeight()/2,mShootTexture.getWidth(),mShootTexture.getHeight());
	}
	public void run(float deltaTime,Asteroid world)
	{
		Vector2 move = new Vector2(mMove);
		move.mul(deltaTime);
		mPos.add(move);
	}
	public void draw(SpriteBatch batch)
	{
		batch.draw(mShootTexture, mPos.x, mPos.y);
	}
}
