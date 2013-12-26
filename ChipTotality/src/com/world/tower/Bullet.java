package com.world.tower;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Pool.Poolable;
import com.particles.ParticleEffectActor;
import com.res.Loader.AssetsLoader;
import com.screen.GameStage;
import com.world.building.Building;
import com.world.ship.Ship;

public class Bullet extends Actor implements Poolable{
	int firePower;
	Texture texture;
	
	
	public void init(float x, float y, int width, int height, Texture texture, int firePower){
		setBounds(x, y, width, height);
		this.firePower=firePower;
		this.texture=texture;
	}
	
	public void explode(Ship target, GameStage stage){		
		stage.addActor(new ParticleEffectActor(AssetsLoader.particlePoolExplosion.obtain(), getX(), getY()));
		target.hitpoints-=firePower;
		if(target.hitpoints<=0)
			target.destroy();
		dispose();
		
	}
	public void explode(Building target, GameStage stage){
		stage.addActor(new ParticleEffectActor(AssetsLoader.particlePoolExplosion.obtain(), getX(), getY()));
		target.hitpoints-=firePower;
		if(target.hitpoints<=0)
			target.destroy();
		dispose();
	}
	
	private void dispose(){
		clearActions();
		remove();
		GameStage.bulletPool.free(this);
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.draw(texture, getX(), getY());	
	}

	@Override
	public void reset() {
		
	}
	

}
