package com.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.gameInfo.GameStateHolder;
import com.main.ChipTotality;
import com.main.Settings;
import com.res.Loader.AssetsLoader;
import com.screen.GUI.GameScreenGUI;

public class GameScreen implements Screen {
	final ChipTotality game;
	public OrthographicCamera camera;
	public final GameScreenGUI gameScreenGUI;
	private final ShapeRenderer shapeRenderer;
	private final GameScreenRenderer renderer;

	public void renderDebug(float delta) {
		shapeRenderer.setProjectionMatrix(camera.combined);
		shapeRenderer.begin(ShapeType.Line);

		// grid
		shapeRenderer.setColor(new Color(0, 0, 1, 1));
		int size = Settings.tileSize;
		for (int row = 0; row < Settings.tilesHorizontal; row++) {
			for (int column = 0; column < Settings.tilesVertical; column++) {
				shapeRenderer.setColor(new Color(0, 0, 1, 1));
				shapeRenderer.line((row + 0) * size, (column + 0) * size,
						(row + 1) * size, (column + 0) * size);
				shapeRenderer.line((row + 0) * size, (column + 0) * size,
						(row + 0) * size, (column + 1) * size);
				shapeRenderer.line((row + 1) * size, (column + 0) * size,
						(row + 1) * size, (column + 1) * size);
				shapeRenderer.line((row + 1) * size, (column + 1) * size,
						(row + 0) * size, (column + 1) * size);
			}
		}

		// 2d axis
		shapeRenderer.setColor(new Color(0, 1, 0, 1));
		shapeRenderer.line(0, 0, 0, Settings.tilesHorizontal
				* Settings.tileSize);
		shapeRenderer.line(0, 0, Settings.tilesHorizontal * Settings.tileSize,
				0);
		shapeRenderer.line(0, 0, 0, -Settings.tilesHorizontal
				* Settings.tileSize);
		shapeRenderer.line(0, 0, -Settings.tilesHorizontal * Settings.tileSize,
				0);

		shapeRenderer.end();
	}

	public GameScreen(ChipTotality gam) {
		Gdx.app.log("screen", "GameScreen set");
		game = gam;

		Settings.ASPECT_RATIO = (float) Gdx.graphics.getWidth()
				/ (float) Gdx.graphics.getHeight();
		Settings.VIEW_WIDTH = Settings.VIEW_HEIGHT * Settings.ASPECT_RATIO;

		camera = new OrthographicCamera();
		camera.setToOrtho(false, Settings.VIEW_WIDTH, Settings.VIEW_HEIGHT);
		camera.update();

		gameScreenGUI = new GameScreenGUI(game);
		shapeRenderer = new ShapeRenderer();
		renderer = new GameScreenRenderer(game);

	}

	@Override
	public void render(float delta) {
		
		game.cameraController.update(delta);	
		//game.gameStage.resetTree();
		/*
		ArrayList<StageObject> returnObjects = new ArrayList<StageObject>();
		for (StageObject currentActor : game.gameStage.allActors) {
			returnObjects.clear();
			returnObjects=game.gameStage.quadTree.retrieve(currentActor);
			Rectangle2D.Float currentActorBounds = new Rectangle2D.Float(currentActor.getX(), currentActor.getY(), currentActor.getWidth(), currentActor.getHeight());
			for (Actor possiblyColliding : returnObjects) {
				Rectangle2D.Float possiblyCollidingBounds = new Rectangle2D.Float(possiblyColliding.getX(), possiblyColliding.getY(), possiblyColliding.getWidth(), possiblyColliding.getHeight());
				if(possiblyCollidingBounds.intersects(currentActorBounds))
					;//System.out.println("asd");
			}
		}*/
		
		//game.gameStage.resetTree();
		int k=0;
		for (int i =0; i<game.gameStage.allActors.size(); i++) {
			for (int j=0; j<game.gameStage.allActors.size(); j++) {
				if(game.gameStage.allActors.get(i).bounds.intersects(game.gameStage.allActors.get(j).bounds)){
					//System.out.println("Sds"+k);
					k++;
				}
			}
		}
		
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		camera.update();		
		game.batch.setProjectionMatrix(camera.combined);

		game.batch.begin();

		renderer.renderBackground();
		renderer.renderBuildings();
		if (GameStateHolder.chosenBuilding != GameStateHolder.ChosenBuilding.none)
			renderer.renderSelectedBuilding();
		game.batch.end();
		renderer.renderStage();

		gameScreenGUI.render(delta);
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
		gameScreenGUI.stage.setViewport(Settings.VIEW_WIDTH,
				Settings.VIEW_HEIGHT, true);

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