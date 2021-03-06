package com.main;

public class Settings {
	public static final int FRAMES_PER_SECOND = 60;
	public static final float UPDATE_TIME=1/30f;
	public static final int WIDTH = 1280;
	public static final int HEIGHT = 720;
	public static float ASPECT_RATIO = 0;//WIDTH/HEIGHT;
	public static final int VIEW_HEIGHT = 720;
	public static float VIEW_WIDTH = 0;//ASPECT_RATIO * VIEW_HEIGHT;
	
	public static boolean DEBUG = false;
	
	public final static int tileSize=40;
	public final static int tilesVertical=40, tilesHorizontal=40;
	
	public final static int cameraScrollSpeed = 800;
}