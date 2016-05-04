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

	public Unit newUnit(UnitType unitType) throws Exception {

		Class<?> unitClass;
		Unit unit;

		unitClass = Class.forName(unitType.getQualifiedName());
		unit =  (Unit)unitClass.newInstance();

		unit.setInitX(unitType.getInitX());
		unit.setInitY(unitType.getInitY());

		return unit;
	}

	public Unit newUnit(String qualifiedUnitType) throws Exception {
		Class<?> unitClass;
		Unit unit;

		unitClass = Class.forName(qualifiedUnitType);
		unit = (Unit) unitClass.newInstance();

		return unit;
	}


}
