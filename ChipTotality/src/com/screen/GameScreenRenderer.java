package com.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
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
	float stateTime;
	Texture meteorite;
	Texture stars;
	
		
	public GameScreenRenderer(final ChipTotality game, final GameScreen screen){
		this.game=game;
		this.gameScreen = screen;
		this.meteorite = AssetsLoader.getTexture("Meteorite");
		this.stars = AssetsLoader.getTexture("starsSeamless");
	}
	
	public void render(float delta){
		Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f );
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);		
		
		game.camera.update();
		game.batch.setProjectionMatrix(game.camera.combined);
		
		game.batch.begin();
		renderBackground(delta);
		game.batch.end();
		renderStage();
			
		//stateTime+=delta;
		//game.batch.draw(AssetsLoader.getObjectAnimation("explosionTest").getKeyFrame(stateTime), 500, 500);//TODO test animacji do zmiany jakis pool z nimi i w miejscu wybuchow:)		
		if (GameStateHolder.chosenBuilding != GameStateHolder.ChosenBuilding.none){
			game.batch.begin();
			renderSelectedBuilding();
			game.batch.end();
		}

		if (Settings.DEBUG) renderDebug(delta);		
	}
	
	public void renderBackground(float delta){


		game.batch.draw(meteorite, 0, 0);
								
		Vector3 postion = game.camera.position;
		float x,y,w,h;
		w = stars.getWidth();
		h = stars.getHeight();
		x = (postion.x - (postion.x % w)); 
		y = (postion.y - (postion.y % h));
		int repeatCount = 3;
		for(int row = -(repeatCount); row<repeatCount; row++){
			for(int column = -(repeatCount); column<repeatCount; column++){
				game.batch.draw(stars, x + row*stars.getWidth(), y + column*stars.getHeight());
			}
		}
		
		Texture meteorite = AssetsLoader.getTexture("Meteorite");		
		game.batch.draw(meteorite, 0, 0);					
	}
	
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