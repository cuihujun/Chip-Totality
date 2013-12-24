package com.screen;


import java.util.Random;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Pool;
import com.main.ChipTotality;
import com.main.Settings;
import com.world.ShipManager;
import com.world.building.Building;
import com.world.ship.Ship;
import com.world.tower.Bullet;


public class GameStage extends Stage{
	final ChipTotality game;
	ShipManager shipManager;
	
	
	public static Group shipsGroup = new Group();
	public static Group buildingsGroup = new Group();
	public static Group bulletsFromTowersGroup = new Group();
	public static Group bulletsFromShipsGroup = new Group();
	
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
		addActor(shipsGroup);
		addActor(bulletsFromShipsGroup);
		addActor(bulletsFromTowersGroup);
		shipManager = new ShipManager();
		shipManager.generateWave();

		setCamera(game.camera);		
		
	}
	
	@Override
	public void act(float delta){
		super.act(delta);
		checkBulletCollisionsWithBuildings();
		checkBulletCollisionsWithShips();
		shipManager.update(delta);
	}
	
	
	private void checkBulletCollisionsWithBuildings(){
		Rectangle buildingRec = new Rectangle();//TODO rectangles in class instance so we dont have to create and update them here each iteration...
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
		
		Rectangle shipRec = new Rectangle();//TODO rectangles in class instance so we dont have to create and update them here each iteration...
		Rectangle bulletRec = new Rectangle();//TODO or dont use rectangles... and check manualy? 	
		for (Actor b : bulletsFromTowersGroup.getChildren()) {
			bulletRec.setPosition(b.getX(), b.getY());
			bulletRec.setSize(b.getWidth(), b.getHeight());
			for(Actor s : shipsGroup.getChildren()){
				shipRec.setPosition(s.getX(), s.getY());
				shipRec.setSize(s.getWidth(), s.getHeight());
				if (shipRec.overlaps(bulletRec)){
					((Bullet)b).explode((Ship)s, this);
					break;
				}			
			}
		}
		
		
		///OSTATNI DZIALAJACA WERSJA PONZEJ
		/*//TODO that is even faster than last one
		Rectangle shipRec = new Rectangle();//TODO rectangles in class instance so we dont have to create and update them here each iteration...
		Rectangle bulletRec = new Rectangle();//TODO or dont use rectangles... and check manualy? 			
		Iterator<Bullet> i = bulletsFromTowers.iterator();
				
		while (i.hasNext()) {
			Bullet b = i.next();
			bulletRec.setPosition(b.getX(), b.getY());
			bulletRec.setSize(b.getWidth(), b.getHeight());
			for (Ship s : ships) {
				shipRec.setPosition(s.getX(), s.getY());
				shipRec.setSize(s.getWidth(), s.getHeight());
				if (shipRec.overlaps(bulletRec)){
					b.remove();
					i.remove();
					break;
				}			
			}			
		}
		*/
		

		
		//TODO shuld be faster but to many methods caled;] get get get so slow;] 
		/*Iterator<Bullet> i = bulletsFromTowers.iterator();		
		while (i.hasNext()) {
			Bullet b = i.next();			
			for (Ship s : ships) {
				if(b.getX() < s.getX() + s.getWidth() && b.getX() + b.getWidth() > s.getX() && b.getY() < s.getY() + s.getHeight() && b.getY() + b.getHeight() > s.getY()){
					b.remove();
					i.remove();
					break;
				}
			}			
		}*/
		
	}


	public Building getRandBuilding() {
		Random gen = new Random();
		if(buildingsGroup.hasChildren() )
			return (Building)buildingsGroup.getChildren().get(gen.nextInt(buildingsGroup.getChildren().size));
		return null;			
	}

}
