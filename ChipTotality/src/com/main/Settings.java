package com.main;

public class Settings {
	public static final int WIDTH = 1280;
	public static final int HEIGHT = 720;
	public static float ASPECT_RATIO = WIDTH/HEIGHT;				// wyliczane na podstawie biezacej rozdzielczosci
	public static final int VIEW_HEIGHT = 720;
	public static float VIEW_WIDTH = ASPECT_RATIO * VIEW_HEIGHT;	// wyliczane na podstawie biezacej rozdzielczosci
	
	public static final  boolean DEBUG = true;
	
	public final static int tileSize=40;
	public final static int tilesVertical=40, tilesHorizontal=40;
}