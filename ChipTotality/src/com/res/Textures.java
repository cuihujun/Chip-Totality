package com.res;

import com.badlogic.gdx.graphics.Texture;
import com.res.Loader.AssetsLoader;

public final class Textures {
	public static Texture get(String name){
		return AssetsLoader.getTexture(name);
	}	
}