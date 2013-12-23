package com.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.main.ChipTotality;
import com.main.Settings;
import com.res.Loader.AssetsLoader;

public class LoadingScreen implements Screen {
		
	private Texture splashTex, loadingBarTex;
	private Sprite splashSprite, loadingBarSprite;	
	private SpriteBatch batch;
	private OrthographicCamera camera;
	private ChipTotality game;
	
	private int loadBarMaxWidth;
	private int loadBarCurrentWidth;
	private int loadBarMaxHeight;
		
	public Screen afterLoadScreen = null;
	
	
	public LoadingScreen(ChipTotality game){
		this.game = game;
		this.camera = game.camera;
		this.batch = game.batch;
		
		Settings.ASPECT_RATIO = (float) Gdx.graphics.getWidth()
				/ (float) Gdx.graphics.getHeight();
		Settings.VIEW_WIDTH = Settings.VIEW_HEIGHT * Settings.ASPECT_RATIO;		
		camera.setToOrtho(false, Settings.VIEW_WIDTH, Settings.VIEW_HEIGHT);
		game.backGroundCamera.setToOrtho(false, Settings.VIEW_WIDTH, Settings.VIEW_HEIGHT);
		game.backGroundCamera.update();
		camera.update();
		
		splashTex = new Texture("Textures/loadingScreen.png");
		splashSprite = new Sprite(splashTex);
		splashSprite.setSize(camera.viewportWidth, camera.viewportHeight);
		splashSprite.setPosition(0f, 0f);
		
		loadingBarTex = new Texture("Textures/loadingBar.png");
		loadingBarSprite = new Sprite(loadingBarTex);
				
		float widthV = camera.viewportWidth;
		float widthH =  camera.viewportHeight;
		loadBarMaxWidth = (int)(widthV*0.50);
		loadBarMaxHeight = (int)(widthH*0.1);
		loadingBarSprite.setSize(1, loadBarMaxHeight);
		loadingBarSprite.setPosition((int)(widthV*0.47), (int)(widthH/2)-(loadingBarSprite.getHeight()/2));		
				
		AssetsLoader.loadAssets();		
	}
	
	public void setAfterLoadScreen(Screen screen){
		afterLoadScreen = screen;
	}


	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);		
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		camera.update();		
		batch.setProjectionMatrix(camera.combined);
		
		loadBarCurrentWidth = (int)(AssetsLoader.getProgress() * loadBarMaxWidth);
		loadingBarSprite.setSize(loadBarCurrentWidth, loadBarMaxHeight);
		
		batch.begin();		
		splashSprite.draw(batch);
		loadingBarSprite.draw(batch);
		batch.end();
		
		if (AssetsLoader.update()){
			AssetsLoader.createResourcesAfterLoad();
			if (afterLoadScreen == null){
				game.mainMenuScreen = new MainMenuScreen(game);
				game.setScreen(game.mainMenuScreen);
			}
			else{
				game.setScreen( afterLoadScreen );
			}
			
		}
	}
	
	@Override
	public void show() {
		loadBarCurrentWidth = 1;
	}
	
	@Override
	public void hide() {
	}
	
	@Override
	public void pause() {
	}
	
	@Override
	public void resume() {		
	}
	
    @Override
    public void dispose() {
    }

	@Override
	public void resize(int width, int height) {
		float aspectRatio = (float) width / (float) height;
		Settings.VIEW_WIDTH = Settings.VIEW_HEIGHT * aspectRatio;
		camera.viewportHeight = Settings.VIEW_HEIGHT;
		camera.viewportWidth = Settings.VIEW_WIDTH;
		camera.update();
	}
}