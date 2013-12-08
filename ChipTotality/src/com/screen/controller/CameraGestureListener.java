package com.screen.controller;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

class CameraGestureListener implements GestureListener {
	
	private OrthographicCamera camera;
	final Vector3 curr = new Vector3();
	final Vector3 last = new Vector3(-1, -1, -1);
	final Vector3 delta = new Vector3();		
	
	CameraGestureListener(OrthographicCamera cam){
		camera = cam;
	}	

	@Override
	public boolean touchDown(float x, float y, int pointer, int button) {
		return false;
	}

	@Override
	public boolean tap(float x, float y, int count, int button) {
		return false;
	}

	@Override
	public boolean longPress(float x, float y) {
		return false;
	}

	@Override
	public boolean fling(float velocityX, float velocityY, int button) {
		return false;
	}

	@Override
	public boolean pan(float x, float y, float deltaX, float deltaY) {				
		//simple the best;] but depends on screen resolution...		
		//camera.direction
		camera.translate(-deltaX*camera.zoom*2,deltaY*camera.zoom*2);				
		camera.update();
		return false;
	}

	@Override
	public boolean panStop(float x, float y, int pointer, int button) {
		return false;
	}

	@Override
	public boolean zoom(float initialDistance, float distance) {
		//simple the best;] but depends on screen resolution...
		camera.zoom += 0.0001 * (initialDistance - distance);
		if (camera.zoom < CameraController.MIN_ZOOM) camera.zoom = CameraController.MIN_ZOOM;
		if (camera.zoom > CameraController.MAX_ZOOM_OUT) camera.zoom = CameraController.MAX_ZOOM_OUT;
		return true;
	}

	@Override
	public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2,
			Vector2 pointer1, Vector2 pointer2) {
		return false;
	}

}