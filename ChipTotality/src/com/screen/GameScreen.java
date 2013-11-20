package com.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.IsometricTiledMapRenderer;
import com.badlogic.gdx.math.Vector3;
import com.gameInfo.ChosenBuilding;
import com.gameInfo.GameStateHolder;
import com.main.ChipTotality;
import com.main.Settings;
import com.res.Loader.AssetsLoader;
import com.res.Musics;
import com.res.Textures;
import com.world.Asteroid;
import com.world.building.Building;

public class GameScreen implements Screen, InputProcessor {
	

	final ChipTotality game;
	public OrthographicCamera camera;
	CameraController cameraController;
	private InputMultiplexer inputMultiplexer;

	Asteroid asteroid;
	
	private IsometricTiledMapRenderer renderer;
	private TiledMapTileLayer freeLayer;
	private TiledMap tiledMap;
	
	DiplomacyScreen diplomacyScreen;
	
	
	GameScreen(ChipTotality gam) {
		Gdx.app.log("screen", "GameScreen set");
		game = gam;		
		camera = new OrthographicCamera(Settings.CAMERA_WIDTH, Settings.CAMERA_HEIGHT);
		camera.setToOrtho(false, Settings.CAMERA_WIDTH, Settings.CAMERA_HEIGHT);		
		camera.position.set(1000, 0, 0);
		camera.update();
		
		inputMultiplexer = new InputMultiplexer();
		cameraController = new CameraController(camera);
		inputMultiplexer.addProcessor(this);
		inputMultiplexer.addProcessor(cameraController);
		//TODO gameControler
		Gdx.input.setInputProcessor(inputMultiplexer);
		
		tiledMap = AssetsLoader.getTileMap();
		renderer = new IsometricTiledMapRenderer(tiledMap, 100f / 64f);//TODO wielkosc tili do sprawdzenia
		freeLayer = (TiledMapTileLayer)tiledMap.getLayers().get("FreeSpace");

		
		asteroid = new Asteroid();		
		diplomacyScreen = new DiplomacyScreen(game, this);		
		Musics.play("Music");
	}
	
	public void isFree(float cellCoordX, float cellCoordY) {

		boolean free = true;
		
		//Cell cellToCheck = freeLayer.getCell(cellCoordX, cellCoordY);
		//if(cellToCheck==null)
		//	System.out.println("pusto");
		//free=cellToCheck.getTile().getProperties().containsKey("free");
		
	}

	@Override
	public void render(float delta) {				
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		camera.update();
		game.batch.setProjectionMatrix(camera.combined);

		game.batch.begin();
		game.batch.draw(Textures.get("background"), 0, 0);
		for (Building buildings : asteroid.buildings)
			game.batch.draw(buildings.buildingTexture, buildings.coords.x, buildings.coords.y);

		game.batch.end();
		
		renderer.setView(camera);
		renderer.render();		
		
		 if(Gdx.input.isTouched()) {
			    Vector3 touchPos = new Vector3();
			    touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			    camera.unproject(touchPos);
			    System.out.println("x:"+touchPos.x+"y"+touchPos.y);
			    System.out.println("x1:"+32*1+16/2*1+"y1"+32*1+16*1);
			    
			    //isFree(touchPos.x, touchPos.y);
		}
	}

	
	@Override
	public boolean keyDown(int keycode) {		
		switch (GameStateHolder.mode) {
		case BUILDING:
			switch (keycode) {
										
			case Keys.F1:
				GameStateHolder.chosenBuilding = ChosenBuilding.testBuilding1;
				break;
				
			case Keys.ESCAPE:
				GameStateHolder.chosenBuilding = ChosenBuilding.none;
				GameStateHolder.mode = GameStateHolder.Mode.NONE;
				Gdx.app.log("buildingMode", "building mode:"+ GameStateHolder.mode.toString());
				break;
				
			default:
				break;
			}
		
		case DIPLOMACY:
			break;

		case NONE:
			switch (keycode) {

			case Keys.B:
				GameStateHolder.mode = GameStateHolder.Mode.BUILDING;
				break;
			case Keys.D:
				GameStateHolder.mode = GameStateHolder.Mode.DIPLOMACY;
				break;
			}
			break;			
		default:
			Gdx.app.log("Default", "action");
		}
		
		return false;
	}

	
	
	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
