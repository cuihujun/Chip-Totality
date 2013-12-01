package com.gameInfo;

import com.badlogic.gdx.graphics.Texture;
import com.res.Loader.AssetsLoader;
import com.world.building.AcodinMine;
import com.world.building.Base;
import com.world.building.Building;
import com.world.building.HolyMountains;
import com.world.building.Rafinery;
import com.world.building.Temple;


/**
 * @author arap
 * This class contains variables regarding current game state.
 * @param buildingMode flag useful to check if player wants to build something
 * @param chosenBuilding building chosen by player. Includes none
 *
 */

public final class GameStateHolder 
{	
	//resources
	public static int dirtyAcodin=0;
	public static int acodin=0;
	public static int beings=0;
	
	
	//current gamemode
	public enum Mode {NONE, BUILDING, DIPLOMACY};
	
	
	//Building mode
	public static Mode mode = Mode.NONE;
	public static ChosenBuilding chosenBuilding = ChosenBuilding.Base;

	
	
	public enum ChosenBuilding {
		none,
		AcodinMine,
		Base,
		HolyMountains,
		Rafinery,
		Temple;
		
		public Building getBuilding(int x, int y){
			switch(this){
			case AcodinMine:
				return new AcodinMine(x, y);
			case Base:
				return new Base(x,y);
			case HolyMountains:
				return new HolyMountains(x, y);
			case Rafinery:
				return new Rafinery(x, y);
			case Temple:
				return new Temple(x, y);
			default:
				return null;
			}		
		}
		public Texture getTexture(){
			switch(this){
			case AcodinMine:
				return null;
			case Base:
				return AssetsLoader.getTexture("TestBuilding1");
			case HolyMountains:
				return null;
			case Rafinery:
				return null;
			case Temple:
				return null;
			default:
				return null;
			}
		}
	};
	
	



}
