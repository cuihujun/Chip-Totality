package com.res;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;


public class TexturesHolder {
	public Texture worldBackground;
	
	
	public TexturesHolder(){
		worldBackground = new Texture(Gdx.files.internal("data/sc_map.png"));
		Gdx.app.log("loading", "textures loaded");
	}
}
