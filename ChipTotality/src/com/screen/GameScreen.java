package com.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.gameInfo.GameStateHolder;
import com.main.ChipTotality;
import com.main.Settings;
import com.res.Loader.AssetsLoader;
import com.screen.GUI.GameScreenGUI;
import com.screen.controller.CameraController;
import com.screen.controller.GameController;
import com.world.Tile.TileType;
import com.world.building.Building;

public class GameScreen implements Screen {
	final ChipTotality game;
	public OrthographicCamera camera;

	private final GameController gameController;
	private final CameraController cameraController;
	private final InputMultiplexer inputMultiplexer;

	private final GameScreenGUI gameScreenGUI;
	

	private final ShapeRenderer shapeRenderer;

	public void renderDebug(float delta) {
		shapeRenderer.setProjectionMatrix(camera.combined);
		shapeRenderer.begin(ShapeType.Line);

		//grid
		shapeRenderer.setColor(new Color(0, 0, 1, 1));
		int size = Settings.tileSize;		
		for(int row=0;row<Settings.tilesHorizontal;row++){
			for(int column=0;column<Settings.tilesVertical;column++){
				shapeRenderer.setColor(new Color(0, 0, 1, 1));
				shapeRenderer.line((row+0)*size, (column+0)*size, (row+1)*size, (column+0)*size);
				shapeRenderer.line((row+0)*size, (column+0)*size, (row+0)*size, (column+1)*size);
				shapeRenderer.line((row+1)*size, (column+0)*size, (row+1)*size, (column+1)*size);
				shapeRenderer.line((row+1)*size, (column+1)*size, (row+0)*size, (column+1)*size);					
			}
		}		
		
		//2d axis
		shapeRenderer.setColor(new Color(0, 1, 0, 1));
		shapeRenderer.line(0, 0, 0, Settings.tilesHorizontal*Settings.tileSize);
		shapeRenderer.line(0, 0, Settings.tilesHorizontal*Settings.tileSize, 0);
		shapeRenderer.line(0, 0, 0, -Settings.tilesHorizontal*Settings.tileSize);
		shapeRenderer.line(0, 0, -Settings.tilesHorizontal*Settings.tileSize, 0);
		
		
		
		shapeRenderer.end();
	}

	public GameScreen(ChipTotality gam) {
		Gdx.app.log("screen", "GameScreen set");
		game = gam;	
		
		
		
		shapeRenderer = new ShapeRenderer();
			
		Settings.ASPECT_RATIO = (float) Gdx.graphics.getWidth() / (float) Gdx.graphics
				.getHeight();
		Settings.VIEW_WIDTH = Settings.VIEW_HEIGHT * Settings.ASPECT_RATIO;

		camera = new OrthographicCamera();
		camera.setToOrtho(false, Settings.VIEW_WIDTH, Settings.VIEW_HEIGHT);
		camera.update();

		gameScreenGUI = new GameScreenGUI(game);
		inputMultiplexer = new InputMultiplexer();
		cameraController = new CameraController(camera);
		gameController = new GameController(game);
		inputMultiplexer.addProcessor(gameScreenGUI.stage);
		inputMultiplexer.addProcessor(cameraController);
		inputMultiplexer.addProcessor(gameController);
		Gdx.input.setInputProcessor(inputMultiplexer);
		
		
		
		//Musics.play("Music");
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		camera.update();
		game.batch.setProjectionMatrix(camera.combined);
		
		//TODO moze jakas klasa Renderer? albo chociaz metoda?
		game.batch.begin();		
		game.batch.draw(AssetsLoader.getTexture("background"), 0, 0);
		for (Building building : game.asteroid.buildings) {
			game.batch.draw(building.getTexture(), building.coords.x*Settings.tileSize, building.coords.y*Settings.tileSize);
		}
		
		if(GameStateHolder.chosenBuilding!=GameStateHolder.ChosenBuilding.none){
			Vector3 pos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
			game.gameScreen.camera.unproject(pos);
			Vector2 tile = gameController.unprojectTile(pos.x, pos.y);
			
			if(tile!=null){
				if(game.asteroid.worldGrid[(int)tile.x][(int)tile.y].tileType==TileType.blocked || game.asteroid.worldGrid[(int)tile.x][(int)tile.y].building!=null)
					game.batch.setColor(1f, 0.1f, 0.1f, 0.7f);							
				else
					game.batch.setColor(0.1f, 1f, 0.1f, 0.7f);
				
				game.batch.draw(GameStateHolder.chosenBuilding.getTexture(), tile.x*Settings.tileSize, tile.y*Settings.tileSize);
				game.batch.setColor(Color.WHITE);
			}
			
			
		}
		
		game.batch.end();

		gameScreenGUI.stage.draw();

		if (Settings.DEBUG)
			renderDebug(delta);
	}

	@Override
	public void resize(int width, int height) {
		float aspectRatio = (float) width / (float) height;
		Settings.VIEW_WIDTH = Settings.VIEW_HEIGHT * aspectRatio;

		camera.viewportHeight = Settings.VIEW_HEIGHT;
		camera.viewportWidth = Settings.VIEW_WIDTH;
		camera.update();
		gameScreenGUI.stage.setViewport(Settings.VIEW_WIDTH ,
				Settings.VIEW_HEIGHT , true);
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

	}

}