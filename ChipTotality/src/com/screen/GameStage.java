package com.screen;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.utils.Array;
import com.main.ChipTotality;
import com.main.Settings;
import com.res.Sounds;
import com.world.ShipManager;
import com.world.building.Building;
import com.world.ship.Ship;
import com.world.ship.TestShip1;
import com.world.tower.Bullet;
import com.world.tower.MoveToAtConstSpeed;
import com.world.tower.TestBullet1;
//class for ships, bullets, and buildings

public class GameStage extends Stage{
	final ChipTotality game;

	//statki moga zderzac sie z pociskami wyslanymi przez wiezyczke
	public static Vector<Ship> ships = new Vector<Ship>();
	//pociski wyslane rpzez staki moga zderzac sie z budynkami
	public static Vector<Building> buildings= new Vector<Building>();

	public QuadTree<Actor> quadTree; 
	public static QuadTree<Bullet> bulletsFromTowersTree, bulletsFromShipsTree;	
	public static Array<Bullet> bulletsFromTowers, bulletsFromShips;	// Bullets from towers, bullets from ships
	//ArrayList<Explosion> listOfExplossions;	
	
	
	ShipManager shipManager;
	public GameStage(ChipTotality game) {
		super(Settings.WIDTH, Settings.HEIGHT, true, game.batch);
		this.game=game;
		quadTree = new QuadTree<Actor>(0, new Rectangle(0,0, Settings.tilesHorizontal*Settings.tileSize, Settings.tilesVertical*Settings.tileSize));
		bulletsFromShipsTree = new QuadTree<Bullet>(0, new Rectangle(0,0, Settings.tilesHorizontal*Settings.tileSize, Settings.tilesVertical*Settings.tileSize));
		bulletsFromTowersTree = new QuadTree<Bullet>(0, new Rectangle(0,0, Settings.tilesHorizontal*Settings.tileSize, Settings.tilesVertical*Settings.tileSize));
		bulletsFromShips = new Array<Bullet>();
		bulletsFromTowers = new Array<Bullet>();
		shipManager = new ShipManager(this);
		
		
		//TODO usunac po testach
		/*TestShip1 ts1 = new TestShip1(300, 300);
		addActor(ts1);
		MoveToAction action = new MoveToAction();
		action.setDuration(10);
		action.setInterpolation(Interpolation.bounceOut);
		action.setPosition(1300, 1200);	
		ts1.addAction(action);	
		ships.add(ts1);
		
		TestShip1 ts2 = new TestShip1(590, 300);
		MoveToAtConstSpeed mta = new MoveToAtConstSpeed(new Vector2(800, 500), 2);
		ts2.addAction(mta);	
		addActor(ts2);
		ships.add(ts2);
		
		TestShip1 ts3 = new TestShip1(1500, 1400);
		addActor(ts3);
		ships.add(ts3);
		
		TestBullet1 tb1 = new TestBullet1(1200, 230, ts1);
		TestBullet1 tb2 = new TestBullet1(1000, 230, ts1);
		TestBullet1 tb3 = new TestBullet1(800, 130, ts1);
		TestBullet1 tb4 = new TestBullet1(-400, 530, ts1);
		TestBullet1 tb5 = new TestBullet1(0, 0, ts1);
		bulletsFromTowers.add(tb1);
		bulletsFromTowers.add(tb2);
		bulletsFromTowers.add(tb3);
		bulletsFromTowers.add(tb4);
		bulletsFromTowers.add(tb5);
		addActor(tb1);
		addActor(tb2);
		addActor(tb3);
		addActor(tb4);
		addActor(tb5);*/
		
		
		///////////////////
		setCamera(game.camera);		
	}
	
	
	public void checkCollisions(){
		quadTree.clear();
		quadTree.insert(getActors());
		ArrayList<Actor> returnObjects = new ArrayList<Actor>();
		
		Iterator<Actor> i = getActors().iterator();
		while (i.hasNext()) {
			Actor currentActor = i.next(); 
			returnObjects.clear();
			returnObjects=quadTree.retrieve(currentActor);
			Rectangle currBounds = new Rectangle(currentActor.getX(), currentActor.getY(), currentActor.getWidth(), currentActor.getHeight());
			for (Actor possiblyColliding : returnObjects) {				
				Rectangle possBounds = new Rectangle(possiblyColliding.getX(), possiblyColliding.getY(), possiblyColliding.getWidth(), possiblyColliding.getHeight());
				if(currBounds.overlaps(possBounds)){								
					Gdx.app.log("collision", "collision beetween"+currentActor.getClass().getSimpleName()+" and "+ possiblyColliding.getClass().getSimpleName());
					if (currentActor instanceof  Bullet ){								
						Gdx.app.log("collision", "Deleting bullet");
						Sounds.play("BoxCrash");
						currentActor.remove();
						//i.remove();
					}
				}
				
			}		   		   
		}
	}
	
	public void checkBulletCollisionsWithBuildings(){
		bulletsFromShipsTree.clear();
		bulletsFromShipsTree.insert(bulletsFromShips);
		ArrayList<Bullet> returnObjects = new ArrayList<Bullet>();
		
		for (Building building : buildings) {			
			returnObjects.clear();
			returnObjects=bulletsFromShipsTree.retrieve(building);
			Rectangle currBounds = new Rectangle(building.getX(), building.getY(), building.getWidth(), building.getHeight());		
			for (Bullet possiblyColliding : returnObjects) {
				Rectangle possBounds = new Rectangle(possiblyColliding.getX(), possiblyColliding.getY(), possiblyColliding.getWidth(), possiblyColliding.getHeight());
				if(currBounds.overlaps(possBounds)){
					Sounds.play("BoxCrash");
					possiblyColliding.remove();
					bulletsFromShips.removeValue(possiblyColliding, true);
				}
			}
		}
	}
	
	
	public void checkBulletCollisionsWithShips() {
		bulletsFromTowersTree.clear();
		bulletsFromTowersTree.insert(bulletsFromTowers);
		ArrayList<Bullet> returnObjects = new ArrayList<Bullet>();
		
		for (Ship ship : ships) {
			returnObjects.clear();
			returnObjects=bulletsFromTowersTree.retrieve(ship);
			Rectangle currBounds = new Rectangle(ship.getX(), ship.getY(), ship.getWidth(), ship.getHeight());		
			for (Bullet possiblyColliding : returnObjects) {
				Rectangle possBounds = new Rectangle(possiblyColliding.getX(), possiblyColliding.getY(), possiblyColliding.getWidth(), possiblyColliding.getHeight());
				if(currBounds.overlaps(possBounds)){
					Sounds.play("BoxCrash");
					possiblyColliding.remove();
					bulletsFromTowers.removeValue(possiblyColliding, true);
				}
			}
		}
	}
	
	

	public Building getRandBuilding() {
		for(int x = 0; x < Settings.tilesVertical; x++)
			for(int y = 0; y < Settings.tilesHorizontal; y++)
				if(game.asteroid.worldGrid[x][y].building != null)
				{
					return game.asteroid.worldGrid[x][y].building;
				}
		return null;			
	}
	public Vector<Ship> getShip()
	{
		return ships;
	}

}
