package com.screen.controller;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector3;

public class CameraController  extends GestureDetector{
	public OrthographicCamera camera;
	final Vector3 curr = new Vector3();
	final Vector3 last = new Vector3(-1, -1, -1);
	final Vector3 delta = new Vector3();	
	
	final static public float MAX_ZOOM_OUT = 1.6f;
	final static public float MIN_ZOOM = 0.67f;
			
	static Map<Integer, Boolean> keys = new HashMap<Integer, Boolean>();
	static {
		keys.put(Keys.A, false);
		keys.put(Keys.Z, false);		
	};	
	
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
	
	public boolean keyDown (int keycode) {		
		if(keycode == Keys.A) keys.put(Keys.A, true);
	    if(keycode == Keys.Z) keys.put(Keys.Z, true);
		return false;	
	}	
	
	public boolean keyUp (int keycode) {
		if(keycode == Keys.A) keys.put(Keys.A, false);
	    if(keycode == Keys.Z) keys.put(Keys.Z, false);
		return false;	
	}	
	
	public void update(float delta){
		if((Boolean)keys.get(Keys.A)) camera.rotate(1f, 0, 0, 1);
		if((Boolean)keys.get(Keys.Z)) camera.rotate(-1f, 0, 0, 1);			
	}
	
}