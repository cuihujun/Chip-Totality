package com.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;
import com.gameInfo.Coords;
import com.gameInfo.GameStateHolder;
import com.main.ChipTotality;
import com.main.Settings;
import com.res.Loader.AssetsLoader;

public class GameScreenRenderer {
	final ChipTotality game;
	float delta;
	
	
	
	public GameScreenRenderer(final ChipTotality game){
		this.game=game;
	}
	
	public void renderBackground(){

		//game.batch.draw(AssetsLoader.getTexture("background"), 0, 0);
		game.batch.draw(AssetsLoader.getTexture("Meteorite"), 0, 0);
		delta = Gdx.graphics.getDeltaTime();//TODO z render metod z GameScreen przekazac jakos do renderera		
		
		//Texture stars = AssetsLoader.getTexture("blackDreams");
		Texture stars = AssetsLoader.getTexture("starSpaceTile");
		//Texture stars = AssetsLoader.getTexture("starsSeamless");		
				
		
		Vector3 postion = game.gameScreen.camera.position;
		float x,y,w,h;
		w = stars.getWidth();
		h = stars.getHeight();
		x = (postion.x - (postion.x % w)); 
		y = (postion.y - (postion.y % h));
		int repeatCount = 3;
		for(int row = -(repeatCount+1); row<repeatCount+1; row++){
			for(int column = -(repeatCount); column<repeatCount; column++){
				game.batch.draw(stars, x + row*stars.getWidth(), y + column*stars.getHeight());
			}
		}
		
		Texture meteorite = AssetsLoader.getTexture("Meteorite");		
		game.batch.draw(meteorite, 0, 0);		
		/*Particles.get("stars1").setPosition(meteorite.getWidth()/2,meteorite.getHeight()*0.35f);
		Particles.get("stars1").update(delta);
		Particles.get("stars1").draw(game.batch);*/			

	}
	
	/*public void renderBuildings(){
		for (Building building : game.asteroid.buildings) {
			game.batch.draw(building.getTexture(), building.coords.x*Settings.tileSize, building.coords.y*Settings.tileSize);
		}
	}*/
	public void renderStage(){
		game.gameStage.act();
		game.gameStage.draw();
	}


	public void renderSelectedBuilding(){
		Vector3 pos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
		game.gameScreen.camera.unproject(pos);
		Coords tile = game.gameController.unprojectTile(pos.x, pos.y);
		
		if(tile!=null){
			//building not possbile - tint red
			if(!game.gameController.buildingPossibleHere(tile.x, tile.y, GameStateHolder.chosenBuilding.getBuildingWidth(), GameStateHolder.chosenBuilding.getBuildingHeight()))
				game.batch.setColor(1f, 0.1f, 0.1f, 0.7f);							
			else	//else tint green
				game.batch.setColor(0.1f, 1f, 0.1f, 0.7f);		
			game.batch.draw(GameStateHolder.chosenBuilding.getTexture(), tile.x*Settings.tileSize, tile.y*Settings.tileSize);
			game.batch.setColor(Color.WHITE);
		}			
	}
	
}
		
	

