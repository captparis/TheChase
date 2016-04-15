package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class HudView extends JPanel{
	
	public JLabel instruction;
	public JLabel diceAmount;
	public JButton actionButton;
	public ImageIcon diceIcon;
	public ImageIcon endTurnIcon;
	public JLabel currentPlayer;
	public Boolean isExplorer;

	public HudView(ActionListener hudListener){
		// TODO Auto-generated constructor stub
		
		Color bluegrey = new Color(179, 204, 204);
		this.setBackground(bluegrey);
		this.setPreferredSize(new Dimension(800, 50));
		
		isExplorer = true;
		
		instruction = new JLabel("Please roll dice");
		instruction.setFont (instruction.getFont ().deriveFont (18.0f));
		diceAmount = new JLabel("0");
		currentPlayer = new JLabel("EXPLORER");
		currentPlayer.setForeground(Color.blue);
		actionButton = new JButton("Roll dice");
		actionButton.setPreferredSize(new Dimension(140, 40));
        actionButton.addActionListener(hudListener);
		
		
		diceIcon = new ImageIcon("bin/images/dice.png");
		endTurnIcon = new ImageIcon("bin/images/endTurn.png");
		
	    actionButton.setIcon(diceIcon);
	    
	    actionButton.setFocusPainted(false);

		//Layout elements across HUD bar
        this.add(Box.createVerticalStrut(40));
		this.add(instruction);
		this.add(Box.createHorizontalStrut(60));
		this.add(actionButton);
		this.add(Box.createHorizontalStrut(20));
		this.add(diceAmount);
		this.add(Box.createHorizontalStrut(60));
		this.add(currentPlayer);
	}
	
	//Functions to handle changes to the HUD based on state changes
	
	public void swapPlayer(){
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
	}
	
	public void setDiceRoll(int diceNum){
		diceAmount.setText(String.valueOf(diceNum));
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
	}
}
