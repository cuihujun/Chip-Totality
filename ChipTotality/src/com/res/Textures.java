package com.res;

import com.badlogic.gdx.graphics.Texture;

public final class Textures {
	public static Texture  get(String name){
		return AssetsLoader.getInstance().getTexture(name);
	}	
}