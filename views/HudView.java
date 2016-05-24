package views;

import javax.swing.*;

import mediator.Mediator;

import java.awt.*;
import java.awt.event.ActionListener;

public class HudView extends JPanel{
	
	private JPanel hudCards;
	private JPanel hud;
	private JPanel menu;
	
	private CardLayout cards;
	
	private JLabel instruction;
	private JLabel diceAmount;
	private JLabel playerName;
	
	private JButton actionButton;
	private JButton menuButton;
	private JButton menuBackButton;
	
	private ImageIcon diceIcon;
	private ImageIcon endTurnIcon;
	private ImageIcon menuIcon;
	private JLabel currentPlayer;
	private Boolean isExplorer;
	
	private Image diceImage;
	private Image endTurnImage;
	

	public HudView(ActionListener hudListener){
		
		Color bluegrey = new Color(179, 204, 204);
		this.setBackground(bluegrey);
		this.setPreferredSize(new Dimension(700, 50));
		
		isExplorer = true;
		
		hudCards = new JPanel(new CardLayout());
		hud = new JPanel();
		menu = new JPanel();
		
		hudCards.setOpaque(false);
		hud.setOpaque(false);
		menu.setOpaque(false);
		
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
        Image newMenuImage = menuImage.getScaledInstance( 10, 10,  java.awt.Image.SCALE_SMOOTH ) ;
        menuIcon.setImage(newMenuImage);
        
        
        menuButton = new JButton ("Menu", menuIcon);
        menuButton.setName("menuButton");
        menuButton.setBackground(Color.WHITE);
        menuButton.setPreferredSize(new Dimension(90, 30));
        menuButton.addActionListener(hudListener);
        menuButton.setFocusPainted(false);
        menuButton.setIconTextGap(15);
        
        menuBackButton = new JButton ("Back");
        menuBackButton.setName("backButton");
        menuBackButton.addActionListener(hudListener);
        menuBackButton.setFocusPainted(false);
		
		
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
	    hud.add(Box.createVerticalStrut(40));
	    hud.add(menuButton);
	    hud.add(Box.createHorizontalStrut(60));
	    hud.add(instruction);
	    hud.add(Box.createHorizontalStrut(60));
	    hud.add(actionButton);
	    hud.add(Box.createHorizontalStrut(20));
	    hud.add(diceAmount);
	    hud.add(Box.createHorizontalStrut(60));
	    hud.add(currentPlayer);
	    hud.add(Box.createHorizontalStrut(20));
	    hud.add(playerName);
	    
	    menu.add(menuBackButton);
	    
	    hudCards.add(hud, "hud");
	    hudCards.add(menu, "menu");
	    this.add(hudCards);
	    
	    cards = (CardLayout) hudCards.getLayout();
	    
	    Mediator.getInstance().registerHudColleagues(hudCards, playerName, currentPlayer, diceAmount, instruction, actionButton, cards);
	    Mediator.getInstance().registerHudVariables(diceIcon, endTurnIcon);
	}
}
