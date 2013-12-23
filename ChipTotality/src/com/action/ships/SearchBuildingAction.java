package com.action.ships;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.screen.GameStage;
import com.world.building.Building;
import com.world.ship.Ship;

public class SearchBuildingAction extends Action {
	private final Ship actionOwner;
	float lastUpdateTime;
	
	public SearchBuildingAction(Ship actionOwner){
		this.actionOwner=actionOwner;
	}
	
	
	@Override
	public boolean act(float delta) {
		float shortestDistance=Float.POSITIVE_INFINITY;
		Vector2 shipsCoords = new Vector2(actionOwner.getX(), actionOwner.getY());
		
		for (Actor building : GameStage.buildingsGroup.getChildren()) {
			if(shipsCoords.dst(building.getX(), building.getY())<shortestDistance)
				actionOwner.setCurrentTarget((Building)building);
		}
		actionOwner.setCurrentAction(Ship.CurrentAction.approach);
		
		return true;
	}
	
	
}
