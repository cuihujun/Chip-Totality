package com.world.building;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.gameInfo.Coords;
import com.gameInfo.GameStateHolder;
import com.gameInfo.Stats;
import com.main.Settings;
import com.res.Loader.AssetsLoader;

public abstract class Building extends Actor{
	public final Coords coords;	
	protected int hitpoints;

	
	public abstract void doTask();
	public abstract void destroy(); // things to do apart from removing it from the logical world (e.g. animations)
		
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
