package views;

import javax.swing.*;

import models.Unit;

import java.awt.*;
import java.awt.event.ActionListener;

public class UnitHudView extends JPanel{
	
	public ImageIcon agileIcon;
	public ImageIcon defenseIcon;
	
	private Image agileImage;
	private Image defenseImage;
	
	private JPanel unitHudCards;
	private JPanel selectedHud;
	private JPanel notSelectedHud;
	private JPanel modesPanelExplorers;
	private JPanel modesPanelGuardians;
	private JPanel abilitiesPanel;
	private JPanel modesCards;
	
	private CardLayout unitHudLayout;

	private JLabel unitName;
	private JLabel noSelection;
	private JLabel modesTitle;
	private JLabel abilitiesTitle;
	
	private JToggleButton agileStance;
	private JToggleButton defenseStance;
	private JButton ability;
	
	private ButtonGroup modeButtonGroup;
	
	
	
	public UnitHudView(ActionListener hudListener){
		
		Color tan = new Color(255, 217, 102);
		this.setBackground(tan);
		this.setPreferredSize(new Dimension(700, 50));
		
		modesPanelExplorers = new JPanel();
		abilitiesPanel = new JPanel();
		unitHudCards = new JPanel(new CardLayout());
		selectedHud = new JPanel();
		notSelectedHud = new JPanel();
		
		unitName = new JLabel ("Unknown unit");
		modesTitle = new JLabel ("Modes");
		abilitiesTitle = new JLabel ("Abilities");
		noSelection = new JLabel ("No unit selected");
		
		agileStance = new JToggleButton ("Agile");
		defenseStance = new JToggleButton ("Defense");
		ability = new JButton ("Ability");
		
		agileStance.setFocusPainted(false);
		defenseStance.setFocusPainted(false);
		ability.setFocusPainted(false);
		
		modeButtonGroup = new ButtonGroup();
		modeButtonGroup.add(agileStance);
		modeButtonGroup.add(defenseStance);
		
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
		defenseStance.setIcon(defenseIcon);
		defenseStance.setToolTipText("Defense mode reduces movement but decreases the chance of the explorer being killed during an attack");
		
		//Set up modes panel
		modesPanelExplorers.setLayout(new BoxLayout(modesPanelExplorers, BoxLayout.LINE_AXIS));
		modesPanelExplorers.setOpaque(true);
		modesPanelExplorers.setBackground(Color.WHITE);
		modesPanelExplorers.add(Box.createRigidArea(new Dimension(15,40)));
		modesPanelExplorers.add(modesTitle);
		modesPanelExplorers.add(Box.createRigidArea(new Dimension(30,0)));
		modesPanelExplorers.add(agileStance);
		modesPanelExplorers.add(Box.createRigidArea(new Dimension(30, 0)));
		modesPanelExplorers.add(defenseStance);
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
		

		//Layout elements across Unit HUD bar
        selectedHud.add(Box.createVerticalStrut(40));
        selectedHud.add(unitName);
        selectedHud.add(Box.createRigidArea(new Dimension(50, 0)));
        selectedHud.add(modesPanelExplorers);
		//this.add(abilitiesPanel);
        
        notSelectedHud.add(noSelection);
        
        
		
		unitHudCards.add(selectedHud, "selected");
		unitHudCards.add(notSelectedHud, "notselected");
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
		if (mode == "agile"){
			agileStance.setSelected(true);
			defenseStance.setSelected(false);
		}
		else if (mode == "defense"){
			agileStance.setSelected(false);
			defenseStance.setSelected(true);
		}
		
	}
		
	//Swaps what modes are displayed depending on if Guardian or Explorers are active
	public void swapModes(){
	}
	
	//Swaps the HUD to display mode options, unit name and so on when unit has been selected
	public void switchSelectedHud(Boolean isSelected){
		System.out.println("Setting selected to " + isSelected);
		if (isSelected)
			unitHudLayout.show(unitHudCards, "selected");
		else {
			unitHudLayout.show(unitHudCards, "notselected");
		}
	}
	
	public void setUnitName(String newUnitName){
		unitName.setText(newUnitName);
	}
	

}
