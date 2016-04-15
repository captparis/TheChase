/*
 *  OSSD Assignment 1 - The Chase
 *  Charles Yim - S3570764
 *  Jacob Paris - S3238163
 *  Chen Liu- S3481556
 *  Taison Eady - S3282633
 */
package controllers;

import models.*;


public class UnitController {
    
    private GameController gameController;

    public UnitController(GameController gameController) {
        this.gameController = gameController;
    }
    
    public Actor newActor(ActorType actorType) throws Exception {
    	
    	Class<?> unitClass;
    	Actor actor;

		unitClass = Class.forName(actorType.getQualifiedName());
		actor = (Actor)unitClass.newInstance();
		
		actor.setInitX(actorType.getInitX());
		actor.setInitY(actorType.getInitY());
		
    	return actor;	
    }
    
    public Unit newUnit(String qualifiedUnitType) throws Exception {
    	Class<?> unitClass;
    	Unit unit;

		unitClass = Class.forName(qualifiedUnitType);
		unit = (Unit)unitClass.newInstance();

    	return unit;	
    }
    
}
