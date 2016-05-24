package mediator;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Mediator {
	
	//board colleagues
	
	//menu colleagues
	
	//hud colleagues
	private JPanel hudCards;
	
	private JLabel playerName;
	private JLabel currentPlayer;
	private JLabel diceAmount;
	private JLabel instruction;
	private CardLayout cards;
	
	private JButton actionButton;
	
	//hud variables
	private ImageIcon diceIcon;
	private ImageIcon endTurnIcon;
	private Boolean isExplorer;
	
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
   
   public void registerHudColleagues(JPanel hudCards, JLabel playerName, JLabel currentPlayer, JLabel diceAmount, JLabel instruction, JButton actionButton, CardLayout cards){
	   this.hudCards = hudCards;
	   this.playerName = playerName;
	   this.currentPlayer = currentPlayer;
	   this.diceAmount = diceAmount;
	   this.instruction = instruction;
	   this.actionButton = actionButton;
	   this.cards = cards;
   }
   
   public void registerHudVariables(ImageIcon diceIcon, ImageIcon endTurnIcon){
	   this.diceIcon = diceIcon;
	   this.endTurnIcon = endTurnIcon;
   }
   
   public void registerBoardColleagues(){
	   
   }
   
   
   
   public void startGame(){

   }
   
   public void quitGame(){
	   
   }
   
   public void options(){
	   
   }
   
   public void hudMenu(){
	   
   }
   
   public void hudUndo(){
	   
   }
   
   //HUD methods
   public void setDiceRoll(int diceNum){
		diceAmount.setText(String.valueOf(diceNum));
	}
   
   public void setPlayerName(String newName){
		playerName.setText(newName);
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
	
	public void setWinState(){
		instruction.setText(currentPlayer.getText() + " win!");
		actionButton.setText("Main Menu");
		actionButton.setIcon(null);
	}
	
	public void swapMenuView(Boolean isMenu){
		if (isMenu)
			cards.show(hudCards, "menu");
		else 
			cards.show(hudCards, "hud");
	}

}
