package com.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
<<<<<<< HEAD
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.gameInfo.GameStateHolder;

=======
import com.gameInfo.GameStateHolder;
>>>>>>> 5d92dc957f51a7f999b4229a43f19e3eb417e2e1
import com.main.ChipTotality;
import com.main.Settings;
import com.res.Loader.AssetsLoader;
import com.screen.GUI.GameScreenGUI;
import com.screen.controller.CameraController;
import com.screen.controller.GameController;
<<<<<<< HEAD
import com.world.Tile.TileType;
import com.world.building.Building;
=======
import com.world.ship.TestShip1;
import com.world.tower.TestTower1;
>>>>>>> 5d92dc957f51a7f999b4229a43f19e3eb417e2e1

public class GameScreen implements Screen {
	final ChipTotality game;
	public OrthographicCamera camera;

	final GameController gameController;
	final CameraController cameraController;
	private final InputMultiplexer inputMultiplexer;
<<<<<<< HEAD

	private final GameScreenGUI gameScreenGUI;
	

=======
	final GameScreenGUI gameScreenGUI;
>>>>>>> 5d92dc957f51a7f999b4229a43f19e3eb417e2e1
	private final ShapeRenderer shapeRenderer;
	private final GameScreenRenderer renderer;
	
	
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
<<<<<<< HEAD
		
		
		
		shapeRenderer = new ShapeRenderer();
			
=======
				
>>>>>>> 5d92dc957f51a7f999b4229a43f19e3eb417e2e1
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

		
<<<<<<< HEAD
		
		
		//Musics.play("Music");
=======
		shapeRenderer = new ShapeRenderer();
		renderer = new GameScreenRenderer(game);
		//Musics.play("Music");
			
		//TODO usunac to
		game.asteroid.ships.add(new TestShip1(250, 250));
		gameController.addBuilding(new TestTower1(10, 10));
>>>>>>> 5d92dc957f51a7f999b4229a43f19e3eb417e2e1
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		camera.update();
		game.batch.setProjectionMatrix(camera.combined);
		
<<<<<<< HEAD
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
		gameScreenGUI.render(delta);

mapRenderer.setView(camera);
		mapRenderer.render();
		game.batch.begin();	// Renderowanie rzeczy znajdujacych sie na asteroidzie
		asteroid.draw(game.batch);
		game.batch.end();
		gameScreenGUI.stage.draw();		if (Settings.DEBUG)
=======
		game.batch.begin();	
		
		renderer.renderBackground();
		renderer.renderBuildings();
		renderer.renderShips();
		if(GameStateHolder.chosenBuilding!=GameStateHolder.ChosenBuilding.none)
			renderer.renderSelectedBuilding();
		game.batch.end();
		
		//camera scrolling
		if(Gdx.input.getX()<=0.05*Settings.WIDTH )
			camera.position.add(-Settings.cameraScrollSpeed*Gdx.graphics.getDeltaTime(), 0, 0);
		if(Gdx.input.getX()>=0.95*Settings.WIDTH)
			camera.position.add(Settings.cameraScrollSpeed*Gdx.graphics.getDeltaTime(), 0, 0);
		if(Gdx.input.getY()>=0.95*Settings.HEIGHT)
			camera.position.add(0, -Settings.cameraScrollSpeed*Gdx.graphics.getDeltaTime(), 0);
		if(Gdx.input.getY()<=0.*Settings.HEIGHT)
			camera.position.add(0, Settings.cameraScrollSpeed*Gdx.graphics.getDeltaTime(), 0);
		
		
		gameScreenGUI.render(delta);
		if (Settings.DEBUG)
>>>>>>> 5d92dc957f51a7f999b4229a43f19e3eb417e2e1
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
		
		AssetsLoader.recreateAfterResize();
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