package com.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.main.ChipTotality;
import com.main.Settings;
import com.res.Loader.AssetsLoader;
import com.screen.GUI.GameScreenGUI;
import com.screen.controller.CameraController;
import com.screen.controller.GameController;
import com.world.Asteroid;

public class GameScreen implements Screen {
	final ChipTotality game;
	public OrthographicCamera camera;
	public final GameScreenGUI gameScreenGUI;
	private final GameScreenRenderer renderer;
	public  GameStage gameStage;	
	public  CameraController cameraController;
	public  GameController gameController;
	

	public GameScreen(ChipTotality gam) {
		Gdx.app.log("screen", "GameScreen set");
		game = gam;
		
		game.asteroid = new Asteroid();
		
		
		cameraController = new CameraController(game.camera);
		gameController=new GameController(game, this);
		gameStage = new GameStage(game);
		gameScreenGUI = new GameScreenGUI(game,gameStage);
		
		game.inputMultiplexer.clear();
		game.inputMultiplexer.addProcessor(gameScreenGUI.stage);
		game.inputMultiplexer.addProcessor(cameraController);
		game.inputMultiplexer.addProcessor(gameController);
		game.inputMultiplexer.addProcessor(gameStage);
		Gdx.input.setInputProcessor(game.inputMultiplexer);		
		

		Settings.ASPECT_RATIO = (float) Gdx.graphics.getWidth()
				/ (float) Gdx.graphics.getHeight();
		Settings.VIEW_WIDTH = Settings.VIEW_HEIGHT * Settings.ASPECT_RATIO;

		camera = game.camera;
		camera.setToOrtho(false, Settings.VIEW_WIDTH, Settings.VIEW_HEIGHT);
		game.backGroundCamera.setToOrtho(false, Settings.VIEW_WIDTH, Settings.VIEW_HEIGHT);
		game.backGroundCamera.update();
		camera.update();

		renderer = new GameScreenRenderer(game, this);		
	}

	@Override
	public void render(float delta) {
		gameStage.act(delta);
		cameraController.update(delta);	
		
		renderer.render(delta);		
		gameScreenGUI.render(delta);
	}

	@Override
	public void resize(int width, int height) {
		float aspectRatio = (float) width / (float) height;
		Settings.VIEW_WIDTH = Settings.VIEW_HEIGHT * aspectRatio;

		camera.viewportHeight = Settings.VIEW_HEIGHT;
		camera.viewportWidth = Settings.VIEW_WIDTH;
		camera.update();
		game.backGroundCamera.viewportHeight = Settings.VIEW_HEIGHT;
		game.backGroundCamera.viewportWidth = Settings.VIEW_WIDTH;
		game.backGroundCamera.position.x = 0;
		game.backGroundCamera.position.y = 0;
		game.backGroundCamera.position.z = 0;
		game.backGroundCamera.update();				
		
		gameScreenGUI.stage.setViewport(Settings.VIEW_WIDTH,
				Settings.VIEW_HEIGHT, true);

		AssetsLoader.recreateAfterResize(Settings.VIEW_WIDTH, Settings.VIEW_HEIGHT);
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