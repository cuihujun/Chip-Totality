package com.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.main.Settings;



public class CameraController{
	public OrthographicCamera camera;

	
	public CameraController(Settings settings) {
		camera = new OrthographicCamera(settings.CAMERA_WIDTH, settings.CAMERA_HEIGHT);
		camera.setToOrtho(false, settings.CAMERA_WIDTH, settings.CAMERA_HEIGHT);
		camera.update();
	}
	
	public void handleInput(){
		if(Gdx.input.isKeyPressed(Input.Keys.D)) {
			camera.position.add(10, 0, 0);
		}
		if(Gdx.input.isKeyPressed(Input.Keys.A)) {		
			camera.position.sub(10, 0, 0);
		}
		if(Gdx.input.isKeyPressed(Input.Keys.W)  && (camera.position.y + 768/2)<1024) {
			camera.position.add(0, 10, 0);
		}
		if(Gdx.input.isKeyPressed(Input.Keys.S)  && (camera.position.y - 768/2)>0) {		
			camera.position.sub(0, 10, 0);
		}
		
		
		camera.update();
		
	}
	

	
}

