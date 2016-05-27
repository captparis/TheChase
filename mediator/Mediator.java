package mediator;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

public class Mediator {
	
	//board colleagues
	private JPanel boardCards;
	private JLabel background;
	private Image explorersWinImage;
	private Image guardiansWinImage;
	
	//menu colleagues
	
	//hud colleagues
	private JPanel hudCards;
	
	private JLabel playerName;
	private JLabel currentPlayer;
	private JLabel diceAmount;
	private JLabel instruction;
	private CardLayout cards;
	
	private JButton actionButton;
	private JButton undo;
	
	//hud variables
	private ImageIcon diceIcon;
	private ImageIcon endTurnIcon;
	private Boolean isExplorer;
	
	//unit hud colleagues
	private CardLayout unitHudLayout;
	private JPanel unitHudCards;
	
	private JLabel unitName;
	
	private JToggleButton agileStance;
	private JToggleButton specialStance;
	
	//unit hud variables
	private Boolean selectionViewShowing = false;
	
	public ImageIcon agileIcon;
	public ImageIcon defenseIcon;
	public ImageIcon attackIcon;
	
	// singleton
	private static Mediator instance;
	
	private Mediator(){  
		isExplorer = true;
	}
	
   public static Mediator getInstance()
   {
      if(instance==null)
         instance=new Mediator();
      
      return instance;
   }
   
   //Register colleagues
   
   public void registerOptionsColleagues(){
	   
   }
   
   public void registerHudColleagues(JPanel hudCards, JLabel playerName, JLabel currentPlayer, JLabel diceAmount, JLabel instruction, JButton actionButton, CardLayout cards, JButton undo){
	   this.hudCards = hudCards;
	   this.playerName = playerName;
	   this.currentPlayer = currentPlayer;
	   this.diceAmount = diceAmount;
	   this.instruction = instruction;
	   this.actionButton = actionButton;
	   this.cards = cards;
	   this.undo = undo;
   }
   
   public void registerHudVariables(ImageIcon diceIcon, ImageIcon endTurnIcon){
	   this.diceIcon = diceIcon;
	   this.endTurnIcon = endTurnIcon;
   }
   
   public void registerUnitHudColleagues(CardLayout unitHudLayout, JPanel unitHudCards, JToggleButton agileStance, JToggleButton specialStance, ImageIcon agileIcon, ImageIcon defenseIcon, ImageIcon attackIcon, JLabel unitName){
	   this.unitHudLayout = unitHudLayout;
	   this.unitHudCards = unitHudCards;
	   this.agileStance = agileStance;
	   this.specialStance = specialStance;
	   this.agileIcon = agileIcon;
	   this.defenseIcon = defenseIcon;
	   this.attackIcon = attackIcon;
	   this.unitName = unitName;
   }
   
   public void registerBoardColleagues(JPanel boardCards, JLabel background, Image explorersWinImage, Image guardiansWinImage){
	   this.boardCards = boardCards;
	   this.guardiansWinImage = guardiansWinImage;
	   this.explorersWinImage = explorersWinImage;
	   this.background = background;
   }
   
   
   //Board methods
   public void changeBoardScreen(Boolean showWin, Boolean isExplorers){
	   CardLayout boardLayout = (CardLayout) boardCards.getLayout();
	   if (showWin){
		   boardCards.setPreferredSize(new Dimension(940, 570));
		   boardLayout.show(boardCards, "win");
		   if (isExplorers){
			   System.out.println("EXPLORERS WIN!");
			   background.setIcon(new ImageIcon(explorersWinImage));
		   }
		   else {
			   System.out.println("GUARDIANS WIN!");
			   background.setIcon(new ImageIcon(guardiansWinImage));
		   }
	   }
	   else {
		   //boardCards.setPreferredSize(new Dimension(940, 570));
		   boardLayout.show(boardCards, "board");
	   }
   }
   
   //HUD methods
   public void setDiceRoll(int diceNum){
		diceAmount.setText(String.valueOf(diceNum));
	}
   
   public void setPlayerName(String newName){
		playerName.setText(newName);
	}
   
   public void setTeam(String team){
	   currentPlayer.setText(team);
   }
   
   public void swapPlayer(String newName){
		if (isExplorer){
			currentPlayer.setText("GUARDIANS");
			currentPlayer.setForeground(Color.red);
			isExplorer = false;
		}
		else {
			currentPlayer.setText("EXPLORERS");
			currentPlayer.setForeground(Color.blue);
			isExplorer = true;
		}
		playerName.setText(newName);
	}
   
   public void setInstruction(String newInstruction){
	   instruction.setText(newInstruction);
   }
   
   public void setActionButton(String newText){
	   actionButton.setText(newText);
   }
   
   public void setDiceState(){
		instruction.setText("Please roll dice");
		actionButton.setText("Roll dice");
		actionButton.setIcon(diceIcon);
	}
	
	public void setUnitState(){
		instruction.setText("Please select unit");
		actionButton.setText("End turn");
		actionButton.setIcon(endTurnIcon);	
	}
	
	public void setMoveState(){
		instruction.setText("Please choose movement position");
	}
	
	public void setAttackState(){
		instruction.setText("Would you like to attack?");
	}
	
	public void setWinState(String winner){
		instruction.setText(currentPlayer.getText() + " win!");
		if (winner.equals("Explorer")){
			
			changeBoardScreen(true, true);
		}
		else{
			changeBoardScreen(true, false);
		}
		actionButton.setText("Main Menu");
		actionButton.setIcon(null);
	}
	
	public void alertHit(Boolean didHit){
		System.out.println("Triggering alertHit");
		if (didHit){
			instruction.setText("Guardian strikes!");
			JOptionPane.showMessageDialog(null, "Guardian strikes!");
		} else {
			instruction.setText("Guardian misses!");
			JOptionPane.showMessageDialog(null, "Guardian misses!");
		} 
	}
	
	public void swapMenuView(Boolean isMenu){
		if (isMenu)
			cards.show(hudCards, "menu");
		else 
			cards.show(hudCards, "hud");
	}
	
	public void setUndoButton(Boolean isEnabled){
		if (isEnabled)
			undo.setEnabled(true);
		else 
			undo.setEnabled(false);
	}
	
	
	//Unit HUD methods
	
	//Swaps Unit HUD area to display different screens
	public void swapScreens(String toSwap){
		if (toSwap == "menu"){
			unitHudLayout.show(unitHudCards, "menu");
		}
		else if (toSwap == "selectionScreen"){
			if (selectionViewShowing)
				unitHudLayout.show(unitHudCards, "selected");
			else 
				unitHudLayout.show(unitHudCards, "notselected");
		}
		else if (toSwap == "selected"){
			unitHudLayout.show(unitHudCards, "selected");
			selectionViewShowing = true;
		}
		else if (toSwap == "notSelected"){
			unitHudLayout.show(unitHudCards, "notselected");
			selectionViewShowing = false;
		}
		else if (toSwap == "undo"){
			unitHudLayout.show(unitHudCards,  "undo");
		}
	}
	
	//Swaps what modes are displayed depending on if Guardian or Explorers are active
	public void swapTeam(String mod){
	    this.specialStance.setText(mod);
	    if(mod.equals("Attack"))
	    {
	        this.specialStance.setIcon(attackIcon);
	    }
	    else
	    {
	        this.specialStance.setIcon(defenseIcon);
	    }
	}
	
	//Used to update the mode buttons to show which mode is currently active upon selecting a unit
	public void setMode(String mode){
		if (mode.equals("Agile")){
			agileStance.setSelected(true);
			specialStance.setSelected(false);
		}
		else{
			agileStance.setSelected(false);
			specialStance.setSelected(true);
		}
		
	}
	
	//Functions to handle changes to the Unit HUD based on state changes
	public void changeUnitName(String selectedUnit){
		unitName.setText(selectedUnit);
	}

	
	public void setUnitName(String newUnitName){
		unitName.setText(newUnitName);
	}

}
