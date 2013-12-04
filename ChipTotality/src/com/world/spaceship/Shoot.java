package com.world.spaceship;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Timer.Task;
import com.world.Asteroid;

public class Shoot {
	protected Vector2 mPos, mMove;
	protected Texture mShootTexture;
	public float mDamage,mSpeed;
	public Sprite mSprite;
	
	public Shoot (Vector2 pos, Texture shootTex, Vector2 target, float damage,float speed)
	{
		mPos=pos;
		
		mDamage = damage;
		mShootTexture = shootTex;
		mSpeed = speed;
		
		mMove= new Vector2();
		mMove.add(target);
		mMove.sub(pos);
		mMove.nor();
		mMove.scl(mSpeed);
		
		mSprite = new Sprite(shootTex);

		double degree = -Math.atan2(mMove.x, mMove.y)/3.14*180;
		mSprite.rotate((float)(degree));
		mSprite.setScale(0.1f);
		
		
	}
	
	public Rectangle getBound()
	{
		return  new Rectangle(mPos.x-mShootTexture.getWidth()/2 , mPos.y-mShootTexture.getHeight()/2,mShootTexture.getWidth(),mShootTexture.getHeight());
	}
	public void run(float deltaTime,Asteroid world)
	{
		Vector2 move = new Vector2(mMove);
		move.scl(deltaTime);
		mPos.add(move);
	}
	public void draw(SpriteBatch batch)
	{
		mSprite.setPosition(mPos.x, mPos.y);
		mSprite.draw(batch);
	}
}
