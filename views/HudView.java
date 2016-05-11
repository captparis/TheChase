package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class HudView extends JPanel{
	
	public JLabel instruction;
	public JLabel diceAmount;
	public JLabel playerName;
	
	public JButton actionButton;
	public JButton menuButton;
	
	public ImageIcon diceIcon;
	public ImageIcon endTurnIcon;
	public ImageIcon menuIcon;
	public JLabel currentPlayer;
	public Boolean isExplorer;
	
	private Image diceImage;
	private Image endTurnImage;
	

	public HudView(ActionListener hudListener){
		
		Color bluegrey = new Color(179, 204, 204);
		this.setBackground(bluegrey);
		this.setPreferredSize(new Dimension(700, 50));
		
		isExplorer = true;
		
		instruction = new JLabel("Please roll dice");
		instruction.setFont (instruction.getFont ().deriveFont (18.0f));
		diceAmount = new JLabel("0");
		currentPlayer = new JLabel("EXPLORER");
		currentPlayer.setForeground(Color.blue);
		playerName = new JLabel("PLAYERNAME");
		actionButton = new JButton("Roll dice");
		actionButton.setName("actionButton");
		actionButton.setPreferredSize(new Dimension(140, 40));
        actionButton.addActionListener(hudListener);
        
        
        
        menuIcon = new ImageIcon("bin/images/menu.png");
        Image menuImage = menuIcon.getImage() ;  
        Image newMenuImage = menuImage.getScaledInstance( 20, 20,  java.awt.Image.SCALE_SMOOTH ) ;
        menuIcon.setImage(newMenuImage);
        
        
        menuButton = new JButton (menuIcon);
        menuButton.setName("menuButton");
        menuButton.setBackground(Color.WHITE);
        menuButton.setPreferredSize(new Dimension(30, 30));
        menuButton.addActionListener(hudListener);
        menuButton.setFocusPainted(false);
		
		
		diceIcon = new ImageIcon("bin/images/diceIcon.png");
		diceImage = diceIcon.getImage();
		diceImage = diceImage.getScaledInstance( 20, 20,  java.awt.Image.SCALE_SMOOTH ) ;
		diceIcon.setImage(diceImage);
		
		endTurnIcon = new ImageIcon("bin/images/endTurnIcon.png");
		endTurnImage = endTurnIcon.getImage();
		endTurnImage = endTurnImage.getScaledInstance( 15, 15,  java.awt.Image.SCALE_SMOOTH ) ;
		endTurnIcon.setImage(endTurnImage);
		
		
	    actionButton.setIcon(diceIcon);
	    actionButton.setIconTextGap(15);
	    
	    actionButton.setFocusPainted(false);

		//Layout elements across HUD bar
        this.add(Box.createVerticalStrut(40));
        this.add(menuButton);
        this.add(Box.createHorizontalStrut(60));
		this.add(instruction);
		this.add(Box.createHorizontalStrut(60));
		this.add(actionButton);
		this.add(Box.createHorizontalStrut(20));
		this.add(diceAmount);
		this.add(Box.createHorizontalStrut(60));
		this.add(currentPlayer);
		this.add(Box.createHorizontalStrut(20));
		this.add(playerName);
	}
	
	//Functions to handle changes to the HUD based on state changes
	
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
		actionButton.setText("Main Menu");
		actionButton.setIcon(null);
	}
}
