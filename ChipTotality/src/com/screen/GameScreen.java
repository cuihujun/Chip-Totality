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
import com.screen.GUI.GameScreenGUI;
import com.screen.controller.CameraController;
import com.screen.controller.GameController;
import com.world.building.Building;

public class GameScreen implements Screen {

	final ChipTotality game;
	public OrthographicCamera camera;

	private final GameController gameController;
	private final CameraController cameraController;
	private final InputMultiplexer inputMultiplexer;

	GameScreenGUI gameScreenGUI;
	

	private final ShapeRenderer shapeRenderer;

	public void renderDebug(float delta) {
		shapeRenderer.setProjectionMatrix(camera.combined);
		shapeRenderer.begin(ShapeType.Line);

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
		
		game.batch.begin();
		for (Building building : game.asteroid.buildings) {
			game.batch.draw(building.getTexture(), building.coords.x*Settings.tileSize, building.coords.y*Settings.tileSize);
		}
		
		if(GameStateHolder.chosenBuilding!=GameStateHolder.ChosenBuilding.none){
			Vector3 pos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
			game.gameScreen.camera.unproject(pos);
			Vector2 tile = gameController.unprojectTile(pos.x, pos.y);
			if(tile!=null)
				game.batch.draw(GameStateHolder.chosenBuilding.getTexture(), tile.x*Settings.tileSize, tile.y*Settings.tileSize);
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