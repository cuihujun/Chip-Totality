package com.screen;

import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

import com.badlogic.gdx.scenes.scene2d.Actor;

public class StageObject extends Actor{
	public Rectangle2D bounds;
	
	public StageObject(int x, int y, int width, int height){
		bounds = new Rectangle(x, y, width, height);
	}
}
