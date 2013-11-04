package com.gameInfo;


/**
 * @author arap
 * This class contains variables regarding current game state.
 * @param buildingMode flag useful to check if player wants to build something
 * @param chosenBuilding building chosen by a player, including none
 * @param technologiesDiscovered array of booleans telling whether the technology was discovered, or not
 * @param researchTimeElapsed - countdown timer for techs
 */
public final class GameStateHolder {
	public static boolean buildingMode = false; 
	public static ChosenBuilding chosenBuilding = ChosenBuilding.none;
	
	public static boolean[] technologiesDiscovered = new boolean[Technology.values().length];
	public static boolean researching=false;
	public static float researchTimeElapsed=0;
	public static Technology toResearch = Technology.techName0;
	
}
