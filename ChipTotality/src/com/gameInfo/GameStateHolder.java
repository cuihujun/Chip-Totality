package com.gameInfo;


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
	public static int beings=100;
	
	
	//current gamemode
	public enum Mode {NONE, BUILDING, DIPLOMACY};
	
	
	//Building mode
	public static Mode mode = Mode.NONE;
	public static ChosenBuilding chosenBuilding = ChosenBuilding.none;
	
	
	
	



}
