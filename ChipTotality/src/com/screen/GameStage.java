package com.screen;


import java.util.Random;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Pool;
import com.main.ChipTotality;
import com.main.Settings;
import com.screen.controller.GameController;
import com.space.partitioning.SpatialIndex;
import com.world.ShipManager;
import com.world.building.Building;
import com.world.ship.Ship;
import com.world.tower.Bullet;
import com.world.tower.VioletGun;


public class GameStage extends Stage{
	final ChipTotality game;
	ShipManager shipManager;
	
	
	public static Group shipsGroup = new Group();
	public static Group buildingsGroup = new Group();
	public static Group bulletsFromTowersGroup = new Group();
	public static Group bulletsFromShipsGroup = new Group();
	public static Group guiObjectsGroup = new Group();
	
	
	SpatialIndex<Actor> towersBulletsIndex = new SpatialIndex<Actor>(160);
	//SpatialIndex<Actor> shipsBulletsIndex = new SpatialIndex<Actor>(160);
	//SpatialIndex<Actor> shipsIndex = new SpatialIndex<Actor>(160);
	//SpatialIndex<Actor> buildingsIndex = new SpatialIndex<Actor>(160);


	
    public static final Pool<Bullet> bulletPool = new Pool<Bullet>() {
		@Override
		protected Bullet newObject() {
			return new Bullet();
		}
    };
	


	
	public GameStage(ChipTotality game) {
		super(Settings.WIDTH, Settings.HEIGHT, true, game.batch);
		this.game=game;	
		addActor(buildingsGroup);
		addActor(guiObjectsGroup);
		addActor(shipsGroup);
		addActor(bulletsFromShipsGroup);
		addActor(bulletsFromTowersGroup);
		
		shipManager = new ShipManager();
		shipManager.generateWave();

		setCamera(game.camera);		
		
		//TODO for testing speed
		for(int row = 9;row<17;row++){
			for(int col = 6; col<31;col++){
				GameController.addBuilding(new VioletGun(col,row));
			}
		}
					
	}
	
	@Override
	public void act(float delta){
		super.act(delta);
		
		
		towersBulletsIndex.clear();
		for (Actor bullet : bulletsFromTowersGroup.getChildren()) {
			towersBulletsIndex.put(bullet, bullet.getX(), bullet.getY(), bullet.getWidth(), bullet.getHeight());
		}		
		
		checkBulletCollisionsWithBuildings();
		checkBulletCollisionsWithShips();
		shipManager.update(delta);
	}
	
	
	private void checkBulletCollisionsWithBuildings(){
		Rectangle buildingRec = new Rectangle();//TODO rectangles in class instance so we dont have to create and update them here each iteration...// Is it wasting much time ? 
		Rectangle bulletRec = new Rectangle();//TODO or dont use rectangles... and check manualy? 	
		for (Actor bullet : bulletsFromShipsGroup.getChildren()) {
			bulletRec.setPosition(bullet.getX(), bullet.getY());
			bulletRec.setSize(bullet.getWidth(), bullet.getHeight());
			for(Actor building : buildingsGroup.getChildren()){
				buildingRec.setPosition(building.getX(), building.getY());
				buildingRec.setSize(building.getWidth(), building.getHeight());
				if (buildingRec.overlaps(bulletRec)){
					((Bullet)bullet).explode((Building)building, this);
					break;
				}			
			}
		}
	}
	
	
	private void checkBulletCollisionsWithShips() {
		
		//SpatialIndex test (it is really fast:)
		Rectangle shipRec = new Rectangle();
		Rectangle bulletRec = new Rectangle();
		
		for(Actor s : shipsGroup.getChildren()){
			shipRec.setPosition(s.getX(), s.getY());
			shipRec.setSize(s.getWidth(), s.getHeight());
			
			for(Actor b : towersBulletsIndex.get(shipRec.x, shipRec.y, shipRec.width, shipRec.height))
			{		
				bulletRec.setPosition(b.getX(), b.getY());
				bulletRec.setSize(b.getWidth(), b.getHeight());
				if (shipRec.overlaps(bulletRec)){
					if ( ((Bullet)b).explode((Ship)s, this) ) break;				
				}
			}				
		}

		
	}


	public Building getRandBuilding() {
		Random gen = new Random();
		if(buildingsGroup.hasChildren() )
			return (Building)buildingsGroup.getChildren().get(gen.nextInt(buildingsGroup.getChildren().size));
		return null;			
	}

}
