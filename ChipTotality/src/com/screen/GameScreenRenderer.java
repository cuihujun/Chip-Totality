package com.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector3;
import com.gameInfo.Coords;
import com.gameInfo.GameStateHolder;
import com.main.ChipTotality;
import com.main.Settings;
import com.res.Loader.AssetsLoader;

public class GameScreenRenderer {
	final ChipTotality game;
	final GameScreen gameScreen;
	float delta;
	
	
	
	public GameScreenRenderer(final ChipTotality game, final GameScreen screen){
		this.game=game;
		this.gameScreen = screen;
	}
	
	public void render(float delta){
		
		game.camera.update();		
		game.batch.setProjectionMatrix(game.camera.combined);
		
		game.batch.begin();
		renderBackground();
		game.batch.end();
		renderStage();
		game.batch.begin();
		if (GameStateHolder.chosenBuilding != GameStateHolder.ChosenBuilding.none)
			renderSelectedBuilding();		
		if (Settings.DEBUG) renderDebug(delta);
		game.batch.end();
	}
	
	public void renderBackground(){

		//game.batch.draw(AssetsLoader.getTexture("background"), 0, 0);
		game.batch.draw(AssetsLoader.getTexture("Meteorite"), 0, 0);
		delta = Gdx.graphics.getDeltaTime();//TODO z render metod z GameScreen przekazac jakos do renderera		
		
		//Texture stars = AssetsLoader.getTexture("blackDreams");
		Texture stars = AssetsLoader.getTexture("starSpaceTile");
		//Texture stars = AssetsLoader.getTexture("starsSeamless");		
				
		
		Vector3 postion = game.camera.position;
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
		gameScreen.gameStage.draw();
	}


	public void renderSelectedBuilding(){
		Vector3 pos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
		game.camera.unproject(pos);
		Coords tile = gameScreen.gameController.unprojectTile(pos.x, pos.y);
		
		if(tile!=null){
			//building not possbile - tint red
			if(!gameScreen.gameController.buildingPossibleHere(tile.x, tile.y, GameStateHolder.chosenBuilding.getBuildingWidth(), GameStateHolder.chosenBuilding.getBuildingHeight()))
				game.batch.setColor(1f, 0.1f, 0.1f, 0.7f);							
			else	//else tint green
				game.batch.setColor(0.1f, 1f, 0.1f, 0.7f);		
			game.batch.draw(GameStateHolder.chosenBuilding.getTextureRegion(), tile.x*Settings.tileSize, tile.y*Settings.tileSize);
			game.batch.setColor(Color.WHITE);
		}			
	}
	
	public void renderDebug(float delta) {
		game.shapeRenderer.setProjectionMatrix(game.camera.combined);
		game.shapeRenderer.begin(ShapeType.Line);

		// grid
		game.shapeRenderer.setColor(new Color(0, 0, 1, 1));
		int size = Settings.tileSize;
		for (int row = 0; row < Settings.tilesHorizontal; row++) {
			for (int column = 0; column < Settings.tilesVertical; column++) {
				game.shapeRenderer.setColor(new Color(0, 0, 1, 1));
				game.shapeRenderer.line((row + 0) * size, (column + 0) * size,
						(row + 1) * size, (column + 0) * size);
				game.shapeRenderer.line((row + 0) * size, (column + 0) * size,
						(row + 0) * size, (column + 1) * size);
				game.shapeRenderer.line((row + 1) * size, (column + 0) * size,
						(row + 1) * size, (column + 1) * size);
				game.shapeRenderer.line((row + 1) * size, (column + 1) * size,
						(row + 0) * size, (column + 1) * size);
			}
		}

		// 2d axis
		game.shapeRenderer.setColor(new Color(0, 1, 0, 1));
		game.shapeRenderer.line(0, 0, 0, Settings.tilesHorizontal
				* Settings.tileSize);
		game.shapeRenderer.line(0, 0, Settings.tilesHorizontal * Settings.tileSize,
				0);
		game.shapeRenderer.line(0, 0, 0, -Settings.tilesHorizontal
				* Settings.tileSize);
		game.shapeRenderer.line(0, 0, -Settings.tilesHorizontal * Settings.tileSize,
				0);

		game.shapeRenderer.end();
	}
	
	
}
		
	

