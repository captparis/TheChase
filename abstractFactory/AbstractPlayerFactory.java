package abstractFactory;

import javax.swing.JOptionPane;

import controllers.UnitController;
import models.Player;

public abstract class AbstractPlayerFactory {
	
	protected UnitController unitController;
	
	public AbstractPlayerFactory(UnitController unitController){
		this.unitController = unitController;
	}
	
	public abstract Player getPlayer();
	
	abstract void initUnits(Player player);
	
	String getPlayerName(String team){
		boolean accepted = false;
		String name = null;

		while (!accepted) {
			try {
				name = validatedName(getNameInput(team));	
				accepted = true;
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Your name must be between 1 "
						+ "and 50 characters long.");
			}
		}
		return name;
	}
	
	private String getNameInput(String team) {
		String playerName = (String) JOptionPane.showInputDialog("What is your" + ""
				+ " name " + team + "?", "");

		return playerName;
	}

	private String validatedName(String nameInput) throws Exception {
		if (nameInput.length() < 1 || nameInput.length() > 50) {
			throw new Exception("Name length is out of bounds");
		}
		return nameInput;
	}
	
}
