package com.gameInfo;

import com.badlogic.gdx.graphics.Texture;
import com.res.Loader.AssetsLoader;
import com.screen.GameStage;
import com.world.tower.Bullet;

public class Stats {
	public static enum Buildings{
		//cost, maxHP, width, height
		AcodinMine(100, 300, 2, 2),
		Base(100, 200, 1, 1),
		HolyMountains(200, 200, 3, 3),
		Rafinery(200, 500, 3,3),
		Temple(200, 200, 2, 2),
		VioletGun(100, 250, 1, 1),
		YellowGun(100, 205, 1, 1);
		
		
		Buildings(int cost, int maxHitpoints, int width, int height){
			this.maxHitpoints=maxHitpoints;
			this.cost=cost;
			this.width=width;
			this.height=height;
		}
		
		public int cost;
		public int maxHitpoints;
		public final int width;		//in tiles
		public final int height; 	//in tiles
		
	}
	public static enum Towers{
		VioletGun(300f, 1.5f),
		YellowGun(500f, 3.0f);
		
		Towers(float range, float shootDelay){
			this.range=range;
			this.shootDelay=shootDelay;
		}
		
		public float shootDelay;
		public float range;
	}
	
	public static enum Ships{
		//maxHP, speed, width, height, range, shootDelay
		TestShip1(14, 2, 20, 20, 300, 1.8f);
		
		Ships(int maxHitpoints, float speed, int width, int height, float range, float shootDelay){
			this.maxHitpoints=maxHitpoints;
			this.speed=speed;
			this.width=width;
			this.height=height;
			this.range=range;
			this.shootDelay=shootDelay;
		}
		
		
		public int maxHitpoints;
		public float speed;
		public final int width;
		public final int height;
		public float range;
		public float shootDelay;
	}
	
	public static enum Bullets{	
		//width, height, firepower, speed, action, texture
		simpleBullet(10, 10, 5, 3, AssetsLoader.getTexture("TestBullet1"))
				;
		
		public float speed;
		public int width, height;
		public int firePower;
		public Texture texture;
		
		Bullets(int width, int height, int firePower, float speed, Texture texture){
			this.width=width;
			this.height=height;
			this.firePower=firePower;
			this.speed=speed;
			this.texture=texture;
		}
		
		public Bullet spawnBullet(float x, float y){
	        Bullet newBullet = GameStage.bulletPool.obtain();
	        newBullet.init(x, y, width, height, texture, firePower);
			return newBullet;
		}
		
		
	}
}
