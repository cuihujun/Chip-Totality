package com.screen.controller;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector3;

public class CameraController  extends GestureDetector{
	public OrthographicCamera camera;
	final Vector3 curr = new Vector3();
	final Vector3 last = new Vector3(-1, -1, -1);
	final Vector3 delta = new Vector3();	
	
	final static public float MAX_ZOOM_OUT = 1.5f;
	final static public float MIN_ZOOM = 0.67f;
	
	public CameraController(OrthographicCamera cam) {
		super(new CameraGestureListener(cam));
		camera = cam;
	}
	
	//@Override
	//public boolean touchDragged (int x, int y, int pointer) {
		/*camera.unproject(curr.set(x, y, 0));
		if (!(last.x == -1 && last.y == -1 && last.z == -1)) {
			camera.unproject(delta.set(last.x, last.y, 0));
			delta.sub(curr);
			camera.position.add(delta.x, delta.y, 0);
		}
		last.set(x, y, 0);*/
	//	return false;
	//}

	//@Override
	//public boolean touchUp (int x, int y, int pointer, int button) {
		//last.set(-1, -1, -1);
	//	return false;
	//}		
	
	@Override
	public boolean scrolled(int amount){
		
		camera.zoom += 0.1 * amount;
		if (camera.zoom < MIN_ZOOM) camera.zoom = MIN_ZOOM;
		if (camera.zoom > MAX_ZOOM_OUT) camera.zoom = MAX_ZOOM_OUT;
		return true;
	}
	
}