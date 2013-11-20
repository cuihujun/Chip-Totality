package com.main;

public class Settings {
	public static final int WIDTH = 800;
	public static final int HEIGHT = 400;
	public static float ASPECT_RATIO = WIDTH/HEIGHT;// wyliczane na podstawie bierzacej rozdzielczosci
	public static final int VIEW_HEIGHT = 100;
	public static float VIEW_WIDTH = ASPECT_RATIO * VIEW_HEIGHT;// wyliczane na podstawie bierzacej rozdzielczosci
	
	public static final  boolean IS_DEBUG = true;
}