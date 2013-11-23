package com.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.IsometricTiledMapRenderer;
import com.main.ChipTotality;
import com.main.Settings;
import com.res.Loader.AssetsLoader;
import com.res.Musics;
import com.res.Textures;
import com.screen.GUI.GameScreenGUI;
import com.screen.controller.CameraController;
import com.screen.controller.GameController;
import com.world.Asteroid;

public class GameScreen implements Screen {

	final ChipTotality game;
	public OrthographicCamera camera;

	private final GameController gameController;
	private final CameraController cameraController;
	private final InputMultiplexer inputMultiplexer;

	Asteroid asteroid;

	private final IsometricTiledMapRenderer mapRenderer;

	public TiledMapTileLayer freeLayer;
	private final TiledMapTileLayer selectionLayer;
	private final TiledMapTileLayer buldingsLayer;

	private final TiledMap tiledMap;
	GameScreenGUI gameScreenGUI;

	// TODO do wydzielenia do innje klasy np World, Asteroid, WorldRenderer?
	private final int tilesCountVertical = 20;
	private final float tileSize = 64f;
	private final float tileHeight = 32f;
	private final float unitScale = (Settings.VIEW_HEIGHT / 10f) / tileSize;// wejdzie
																			// 10
																			// tili(64f)
																			// 20
																			// tili(32f)
																			// pionowo

	private final ShapeRenderer shapeRenderer;

	public void renderDebug(float delta) {
		shapeRenderer.setProjectionMatrix(camera.combined);
		shapeRenderer.begin(ShapeType.Line);

		shapeRenderer.setColor(new Color(0, 1, 0, 1));
		shapeRenderer.line(0, 0, 0, 200);
		shapeRenderer.line(0, 0, 200, 0);
		shapeRenderer.line(0, 0, 0, -200);
		shapeRenderer.line(0, 0, -200, 0);
		shapeRenderer.end();
	}

	public GameScreen(ChipTotality gam) {
		Gdx.app.log("screen", "GameScreen set");
		game = gam;
		asteroid = new Asteroid();
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

		tiledMap = AssetsLoader.getTileMap();
		mapRenderer = new IsometricTiledMapRenderer(tiledMap, unitScale);
		freeLayer = (TiledMapTileLayer) tiledMap.getLayers().get("FreeSpace");
		selectionLayer = (TiledMapTileLayer) tiledMap.getLayers().get(
				"Selection");
		buldingsLayer = (TiledMapTileLayer) tiledMap.getLayers()
				.get("Buldings");

		// very dirty code just to work fast TODO poprawic wyrzucic itp itd;]
		// selectTile = selectionLayer.getCell(0, 0).getTile();
		selectionLayer.getCell(0, 0).setTile(null);
		// buldingTile = buldingsLayer.getCell(0, 0).getTile();
		buldingsLayer.getCell(0, 0).setTile(null);

		Musics.play("Music");
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		camera.update();
		game.batch.setProjectionMatrix(camera.combined);

		game.batch.begin();
		game.batch.draw(Textures.get("background"), 0, 0);
		game.batch.end();

		mapRenderer.setView(camera);
		mapRenderer.render();
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
		gameScreenGUI.stage.setViewport(Settings.VIEW_WIDTH * 4,
				Settings.VIEW_HEIGHT * 4, true);
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
		mapRenderer.dispose();
		tiledMap.dispose();
	}

}