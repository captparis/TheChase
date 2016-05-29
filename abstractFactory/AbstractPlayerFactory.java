package abstractFactory;

import javax.swing.JOptionPane;

import controllers.UnitController;
import models.Player;

public abstract class AbstractPlayerFactory {
	
	protected UnitController unitController;
	
	public AbstractPlayerFactory(UnitController unitController){
		this.unitController = unitController;
	}
	
	public abstract Player getPlayer() throws Exception;
	
	abstract void initUnits(Player player);
	
	String getPlayerName(String team) throws Exception{
		boolean accepted = false;
		String name = null;

		while (!accepted) {
			try {
				name = validatedName(getNameInput(team));	
				accepted = true;
			} catch (Exception e) {
				if(e.getMessage().equals("Cancel")){
					throw e;
				}else{
					JOptionPane.showMessageDialog(null, "Your name must be between 1 "
							+ "and 50 characters long.");
				}
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
		if(nameInput == null){
			throw new Exception("Cancel");
		}else if (nameInput.length() < 1 || nameInput.length() > 50) {
			throw new Exception("Out Of Bounds");
		}
		return nameInput;
	}
	
}
