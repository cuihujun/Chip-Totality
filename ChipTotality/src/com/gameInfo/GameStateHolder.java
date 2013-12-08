package com.gameInfo;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import com.badlogic.gdx.graphics.Texture;
import com.res.Loader.AssetsLoader;
import com.world.building.AcodinMine;
import com.world.building.Base;
import com.world.building.Building;
import com.world.building.HolyMountains;
import com.world.building.Rafinery;
import com.world.building.Temple;

/**
 * @author arap This class contains variables regarding current game state.
 * @param buildingMode
 *            flag useful to check if player wants to build something
 * @param chosenBuilding
 *            building chosen by player. Includes none
 * 
 */

public final class GameStateHolder {
	// resources
	public static int dirtyAcodin = 0;
	public static int acodin = 0;
	public static int beings = 0;

	// current gamemode
	public enum Mode {
		NONE, BUILDING, DIPLOMACY
	};

	// Building mode
	public static Mode mode = Mode.NONE;
	public static ChosenBuilding chosenBuilding = ChosenBuilding.none;

	public enum ChosenBuilding {
		none(null), 
		AcodinMine(new AcodinMine(0,0)),
		Base(new Base(0,0)),
		HolyMountains(new HolyMountains(0,0)), 
		Rafinery(new Rafinery(0,0)),
		Temple(new Temple(0,0));

		Building buildingSample;

		ChosenBuilding(Building building) {
			this.buildingSample = building;
		}

		public int getBuildingWidth() {
			return (int) buildingSample.size.x;
		}

		public int getBuildingHeight() {
			return (int) buildingSample.size.y;
		}
		
		public Texture getTexture() {
			return AssetsLoader.getTexture(this.toString());
		}
		
		public Building getChosenBuilding(int x, int y){
			
			
			Class newBuildingClass = null;
			try {
				newBuildingClass = Class.forName("com.world.building."+GameStateHolder.chosenBuilding.toString());
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
					
			
			Constructor con = null;
			try {
				con = newBuildingClass.getConstructor(int.class, int.class);
			} catch (NoSuchMethodException e1) {
				e1.printStackTrace();
			} catch (SecurityException e1) {
				e1.printStackTrace();
			}
			
			
			
			Object newBuildingObject=null;
			try {
				newBuildingObject = con.newInstance(x, y);
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
			return (Building)newBuildingObject;
		}
		

	}
	
	
	
}
