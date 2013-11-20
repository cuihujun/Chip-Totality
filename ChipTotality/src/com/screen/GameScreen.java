package com.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.loaders.AssetLoader;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.IsometricTiledMapRenderer;
<<<<<<< HEAD
import com.badlogic.gdx.math.Vector3;
=======
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
>>>>>>> upstream/master
import com.gameInfo.ChosenBuilding;
import com.gameInfo.GameStateHolder;
import com.main.ChipTotality;
import com.main.Settings;
import com.res.Loader.AssetsLoader;
import com.res.Musics;
import com.res.Sounds;
import com.res.Textures;
import com.world.Asteroid;
import com.world.building.Building;

public class GameScreen implements Screen, InputProcessor {
	

	final ChipTotality game;
	public OrthographicCamera camera;
	CameraController cameraController;
	private InputMultiplexer inputMultiplexer;

	Asteroid asteroid;
<<<<<<< HEAD
	
	private IsometricTiledMapRenderer renderer;
=======
	private IsometricTiledMapRenderer mapRenderer;
>>>>>>> upstream/master
	private TiledMapTileLayer freeLayer;
	private TiledMapTileLayer selectionLayer;
	private TiledMapTileLayer buldingsLayer;
	
	//TODO aaa dirty code
	private TiledMapTile selectTile;
	private TiledMapTile buldingTile;
	private Vector2 lastSel = new Vector2(0,0);
	
		
	private TiledMap tiledMap;
	
	DiplomacyScreen diplomacyScreen;
	
<<<<<<< HEAD
	
	GameScreen(ChipTotality gam) {
=======
	//do ui
	private Stage stage;
		
	Base base;
	
	
	//TODO do wydzielenia do innje klasy np World, Asteroid, WorldRenderer?
	private final int tilesCountVertical = 20;
	private final float tileSize = 64f;
	private final float tileHeight = 32f;
	private final float unitScale = (Settings.VIEW_HEIGHT/10f)/tileSize;//wejdzie 10 tili(64f) 20 tili(32f) pionowo
	
	private ShapeRenderer shapeRenderer;
	
	public void renderDebug(float delta) {			
		shapeRenderer.setProjectionMatrix(camera.combined);		
		shapeRenderer.begin(ShapeType.Line);
		
		//osie z punktem 0,0 (worldSpace)
		shapeRenderer.setColor(new Color(0, 1, 0, 1));
		shapeRenderer.line(0, 0, 0, 200);
		shapeRenderer.line(0, 0, 200, 0);
		shapeRenderer.line(0, 0, 0, -200);
		shapeRenderer.line(0, 0, -200, 0);
		shapeRenderer.end();
	}	
	
	GameScreen(ChipTotality gam) {	
>>>>>>> upstream/master
		Gdx.app.log("screen", "GameScreen set");
		game = gam;		
		
		shapeRenderer = new ShapeRenderer();
		
		
		Settings.ASPECT_RATIO  = (float) ((float) Gdx.graphics.getWidth() / (float) Gdx.graphics.getHeight());         
		Settings.VIEW_WIDTH = Settings.VIEW_HEIGHT* Settings.ASPECT_RATIO;
		camera = new OrthographicCamera();
		camera.setToOrtho(false, Settings.VIEW_WIDTH ,  Settings.VIEW_HEIGHT);		
		//wysrodkowanie na mape(TODO da sie przesunac jakos mape? albo czemu ona w takim miejscu sie renderuje ustawienia w edytorze Tiled?)
		camera.position.set(unitScale*tileSize*tilesCountVertical/2, unitScale*tileHeight/2, 0);
		camera.update();
		
		
        //do ui 
        stage = new Stage(Settings.VIEW_WIDTH*4, Settings.VIEW_HEIGHT*4, true);
        Skin skin = AssetsLoader.getInstance().getSkin();        
        Button buttonBuldingTogle = new TextButton(" Bulding \n Mode ", skin, "toggle");  
        buttonBuldingTogle.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				if( GameStateHolder.mode != GameStateHolder.Mode.BUILDING){
					GameStateHolder.mode =  GameStateHolder.Mode.BUILDING;
				}
				else{
					 GameStateHolder.mode = GameStateHolder.Mode.NONE;
				}
			}
		});         
        
		Table t = new Table();
		t.align(Align.bottom);
		t.add(buttonBuldingTogle);
        t.pack();
        stage.addActor(t);		
		
		
		inputMultiplexer = new InputMultiplexer();
		cameraController = new CameraController(camera);
		inputMultiplexer.addProcessor(this);
		inputMultiplexer.addProcessor(cameraController);
		//TODO gameControler
		inputMultiplexer.addProcessor(stage);
		Gdx.input.setInputProcessor(inputMultiplexer);
		
<<<<<<< HEAD
		tiledMap = AssetsLoader.getTileMap();
		renderer = new IsometricTiledMapRenderer(tiledMap, 100f / 64f);//TODO wielkosc tili do sprawdzenia
		freeLayer = (TiledMapTileLayer)tiledMap.getLayers().get("FreeSpace");

=======
		tiledMap = AssetsLoader.getInstance().getTileMap();		
		mapRenderer = new IsometricTiledMapRenderer(tiledMap, unitScale);		
		freeLayer = (TiledMapTileLayer)tiledMap.getLayers().get("FreeSpace");
		selectionLayer = (TiledMapTileLayer)tiledMap.getLayers().get("Selection");
		buldingsLayer = (TiledMapTileLayer)tiledMap.getLayers().get("Buldings");
		
		//very dirty code just to work fast TODO poprawic wyrzucic itp itd;]
		selectTile = selectionLayer.getCell(0, 0).getTile();
		selectionLayer.getCell(0, 0).setTile(null);
		buldingTile = buldingsLayer.getCell(0, 0).getTile();
		buldingsLayer.getCell(0, 0).setTile(null);
		
		

		
>>>>>>> upstream/master
		
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

<<<<<<< HEAD
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
=======
		game.batch.end();		
		mapRenderer.setView(camera);
		mapRenderer.render();
		stage.draw();
				
		if (Settings.IS_DEBUG) renderDebug(delta);
>>>>>>> upstream/master
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
        float aspectRatio = (float) width / (float) height;
        camera.viewportHeight = Settings.VIEW_HEIGHT;
        camera.viewportWidth = Settings.VIEW_HEIGHT * aspectRatio;
        camera.update();
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
    	Vector3 pos = new Vector3(screenX, screenY, 0);
    	camera.unproject(pos);    	        	
    	checkTileClicked(pos.x, pos.y);
    	
		return false;
	}
		
	private void checkTileClicked(float x, float y){
    	int xx, yy;
    	float tileHeightUnits = tileHeight*unitScale;
    	float tileWidthUnits = tileSize*unitScale;
    	    	
    	//Gdx.app.log("World", "x,y : " + x + "," + y);
    	//ze wzgledu na dziwne przesuniecie mapy...   (tak by punkt 0,0 dla lewy dolny róg)//TODO czy da sie wysrodkowac jakos mape? Tile editor? albo sam renderer?
    	y = y + (tilesCountVertical/2-0.5f)*tileHeightUnits;
    	    	
    	//y = 20*tileHeightUnits-y; //(tak by punkt 0,0 dla lewy górny róg)
    	//Gdx.app.log("World przesuniety", "x,y : " + x + "," + y);
    	
    	
    	//y = (20*tileHeightUnits)/2-y; //(tak by punkt 0,0 na œrodku mapy)
    	//x = (20*tileWidthUnits)/2-x; //(tak by punkt 0,0 na œrodku mapy)
    	
    	
    	xx =  (int) Math.floor((y + x/2)/tileHeightUnits);
    	yy = (int)Math.floor((y - x/2)/tileHeightUnits);

    	
    	//do kordynatów w tilach z layera na mapie//TODO usunac te magick numbery jakos;]
		xx = xx -10;
		yy = Math.abs(yy -9);
    	
		
		//TODO zamieniony xx,yy? dirty code
		selectionLayer.setCell((int)lastSel.y ,(int)lastSel.x, null);
		lastSel.x = xx;
		lastSel.y = yy;		
		Cell cell = new Cell();		
		if(GameStateHolder.mode == GameStateHolder.Mode.BUILDING){
			cell.setTile(buldingTile);
			buldingsLayer.setCell(yy, xx, cell);			
		}
		else{
			cell.setTile(selectTile);		
			selectionLayer.setCell(yy, xx, cell);			
		}
		
    	Sounds.play("Click");
    	Gdx.app.log("Selected tile", "x,y : " + xx + "," + yy);
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
