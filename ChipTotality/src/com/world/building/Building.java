package com.world.building;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.gameInfo.Coords;
import com.gameInfo.GameStateHolder;
import com.gameInfo.Stats;
import com.main.Settings;
import com.particles.ParticleEffectActor;
import com.particles.EffecteManagerHolder.EffectTypes;
import com.particles.EffecteManagerHolder.EffectsManager;
import com.res.Loader.AssetsLoader;
import com.screen.controller.GameController;

public abstract class Building extends Actor{
	public final Coords coords;	
	public int hitpoints;

	
	public abstract void doTask();
	
	public  void destroy(){
		this.getStage().addActor(EffectsManager.getActor(EffectTypes.explosionMed, getX(), getY()));//TODO zaleznie od typu inny wybuch?;p wysordkowanie wynuchu?
		GameController.removeBuilding(this);
	}
	
	public TextureRegion getTextureRegion() {
		return AssetsLoader.getBuilding(this.getClass().getSimpleName());
	}	
	
	public Building(int x, int y) {
		//rectangle bounds
		setBounds(x*Settings.tileSize, y*Settings.tileSize, getStats().width*Settings.tileSize, getStats().height*Settings.tileSize);
		//tile coords
		coords = new Coords(x, y);
		//size = new Vector2(width, height);
		hitpoints=getStats().maxHitpoints;
	}

	public Stats.Buildings getStats(){
		return Stats.Buildings.valueOf(this.getClass().getSimpleName());
	}
	
	public void pay(){
		GameStateHolder.beings-=getStats().cost;
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.draw(getTextureRegion(), getX(), getY());
	}
	
	

}
