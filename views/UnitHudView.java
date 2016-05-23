package views;

import javax.swing.*;

import models.Unit;

import java.awt.*;
import java.awt.event.ActionListener;

public class UnitHudView extends JPanel{
	
	public ImageIcon agileIcon;
	public ImageIcon defenseIcon;
	public ImageIcon attackIcon;
	
	private Image agileImage;
	private Image defenseImage;
	private Image attackImage;
	
	private JPanel unitHudCards;
	private JPanel selectedHud;
	private JPanel notSelectedHud;
	private JPanel modesPanelExplorers;
	private JPanel modesPanelGuardians;
	private JPanel abilitiesPanel;
	private JPanel modesCards;
	private JPanel menuPanel;
	
	private CardLayout unitHudLayout;

	private JLabel unitName;
	private JLabel noSelection;
	private JLabel modesTitle;
	private JLabel abilitiesTitle;
	
	private JToggleButton agileStance;
	private JToggleButton specialStance;
	private JButton ability;
	
	private ButtonGroup modeButtonGroup;
	
	private Boolean selectionViewShowing = false;
	
	
	
	public UnitHudView(ActionListener hudListener){
		
		Color tan = new Color(255, 217, 102);
		this.setBackground(tan);
		this.setPreferredSize(new Dimension(700, 50));
		
		modesPanelExplorers = new JPanel();
		abilitiesPanel = new JPanel();
		unitHudCards = new JPanel(new CardLayout());
		selectedHud = new JPanel();
		notSelectedHud = new JPanel();
		menuPanel = new JPanel();
		
		unitName = new JLabel ("Unknown unit");
		modesTitle = new JLabel ("Modes");
		abilitiesTitle = new JLabel ("Abilities");
		noSelection = new JLabel ("No unit selected");
		
		agileStance = new JToggleButton ("Agile");
		agileStance.setName("modeAgile");
		specialStance = new JToggleButton ("Defense");
		specialStance.setName("modeDefense");
		ability = new JButton ("Ability");
		
		agileStance.setFocusPainted(false);
		specialStance.setFocusPainted(false);
		ability.setFocusPainted(false);
		
		agileStance.addActionListener(hudListener);
		specialStance.addActionListener(hudListener);
		
		modeButtonGroup = new ButtonGroup();
		modeButtonGroup.add(agileStance);
		modeButtonGroup.add(specialStance);
		
		attackIcon = new ImageIcon("bin/images/attack.png");
        attackImage = attackIcon.getImage();
        attackImage = attackImage.getScaledInstance( 15, 15,  java.awt.Image.SCALE_SMOOTH ) ;
        attackIcon.setImage(attackImage);
        
		agileIcon = new ImageIcon("bin/images/agile.png");
		agileImage = agileIcon.getImage();
		agileImage = agileImage.getScaledInstance( 20, 20,  java.awt.Image.SCALE_SMOOTH ) ;
		agileIcon.setImage(agileImage);		
		agileStance.setIcon(agileIcon);
		agileStance.setToolTipText("Agile mode allows for maximum movement but also increases the chance of the explorer being killed during an attack");
		
		defenseIcon = new ImageIcon("bin/images/defense.png");
		defenseImage = defenseIcon.getImage();
		defenseImage = defenseImage.getScaledInstance( 15, 15,  java.awt.Image.SCALE_SMOOTH ) ;
		defenseIcon.setImage(defenseImage);
		specialStance.setIcon(defenseIcon);
		specialStance.setToolTipText("Defense mode reduces movement but decreases the chance of the explorer being killed during an attack");
		
		//Set up modes panel
		modesPanelExplorers.setLayout(new BoxLayout(modesPanelExplorers, BoxLayout.LINE_AXIS));
		modesPanelExplorers.setOpaque(true);
		modesPanelExplorers.setBackground(Color.WHITE);
		modesPanelExplorers.add(Box.createRigidArea(new Dimension(15,40)));
		modesPanelExplorers.add(modesTitle);
		modesPanelExplorers.add(Box.createRigidArea(new Dimension(30,0)));
		modesPanelExplorers.add(agileStance);
		modesPanelExplorers.add(Box.createRigidArea(new Dimension(30, 0)));
		modesPanelExplorers.add(specialStance);
		modesPanelExplorers.add(Box.createRigidArea(new Dimension(30,0)));
		
		
		
		//Set up abilities panel
		abilitiesPanel.setLayout(new BoxLayout(abilitiesPanel, BoxLayout.LINE_AXIS));
		abilitiesPanel.setOpaque(false);
		abilitiesPanel.add(Box.createRigidArea(new Dimension(15,0)));
		abilitiesPanel.add(abilitiesTitle);
		abilitiesPanel.add(Box.createRigidArea(new Dimension(30,0)));
		abilitiesPanel.add(ability);	
		abilitiesPanel.add(Box.createRigidArea(new Dimension(15,0)));
		
		selectedHud.setOpaque(false);
		notSelectedHud.setOpaque(false);
		unitHudCards.setOpaque(false);
		menuPanel.setOpaque(false);
		

		//Layout elements across Unit HUD bar
        selectedHud.add(Box.createVerticalStrut(40));
        selectedHud.add(unitName);
        selectedHud.add(Box.createRigidArea(new Dimension(50, 0)));
        selectedHud.add(modesPanelExplorers);
		//this.add(abilitiesPanel);
        
        notSelectedHud.add(noSelection);
        
        
		
		unitHudCards.add(selectedHud, "selected");
		unitHudCards.add(notSelectedHud, "notselected");
		unitHudCards.add(menuPanel, "menu");
		this.add(unitHudCards);
		
		unitHudLayout = (CardLayout) unitHudCards.getLayout();
		unitHudLayout.show(unitHudCards, "notselected");
		
	}
	
	//Functions to handle changes to the Unit HUD based on state changes
	public void changeUnitName(String selectedUnit){
		unitName.setText(selectedUnit);
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
	
	//Swaps the HUD to display mode options, unit name and so on when unit has been selected
	public void switchSelectedHud(Boolean isSelected){
		System.out.println("Unit Setting selected to " + isSelected);
		if (isSelected){
			unitHudLayout.show(unitHudCards, "selected");
			selectionViewShowing = true;
		}
		else {
			unitHudLayout.show(unitHudCards, "notselected");
			selectionViewShowing = false;
		}
	}
	
	public void setUnitName(String newUnitName){
		unitName.setText(newUnitName);
	}
	
	public void swapMenuView(Boolean isMenu){
		if (isMenu)
			unitHudLayout.show(unitHudCards, "menu");
		else {
			if (selectionViewShowing)
				unitHudLayout.show(unitHudCards, "selected");
			else 
				unitHudLayout.show(unitHudCards, "notselected");
		}
	}
	
	

}
