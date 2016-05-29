package views;

import javax.swing.*;

import mediator.Mediator;
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
	private JPanel undoScreen;
	
	private CardLayout unitHudLayout;

	private JLabel unitName;
	private JLabel noSelection;
	private JLabel modesTitle;
	private JLabel abilitiesTitle;
	private JLabel undoLabel;
	
	private JToggleButton agileStance;
	private JToggleButton specialStance;
	private JButton ability;
	private JButton save;
	private JButton load;
	private JButton exit;
	private JButton undoTurn;
	private JButton redoTurn;
	private JButton undoMove;
	private JButton redoMove;
	
	private ButtonGroup modeButtonGroup;
	
	private Boolean selectionViewShowing = false;
	
	
	
	public UnitHudView(ActionListener hudListener){
		
		Color grey = new Color(194, 194, 194);
		this.setBackground(grey);
		this.setPreferredSize(new Dimension(700, 50));
		
		modesPanelExplorers = new JPanel();
		abilitiesPanel = new JPanel();
		unitHudCards = new JPanel(new CardLayout());
		selectedHud = new JPanel();
		notSelectedHud = new JPanel();
		menuPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		menuPanel.setPreferredSize(new Dimension(700, 50));
		undoScreen = new JPanel();
		
	
		undoScreen.setOpaque(false);
		
		unitName = new JLabel ("Unknown unit");
		modesTitle = new JLabel ("Modes");
		abilitiesTitle = new JLabel ("Abilities");
		noSelection = new JLabel ("No unit selected");
		undoLabel = new JLabel ("Rewind Time");
		
		ImageIcon undoMoveIcon = new ImageIcon("bin/images/undo.png");
        Image undoMoveImage = undoMoveIcon.getImage();
        undoMoveImage = undoMoveImage.getScaledInstance( 20, 20,  java.awt.Image.SCALE_SMOOTH ) ;
		undoMoveIcon.setImage(undoMoveImage);
		
		ImageIcon redoMoveIcon = new ImageIcon("bin/images/redo.png");
        Image redoMoveImage = redoMoveIcon.getImage();
        redoMoveImage = redoMoveImage.getScaledInstance( 20, 20,  java.awt.Image.SCALE_SMOOTH ) ;
        redoMoveIcon.setImage(redoMoveImage);
        
    	ImageIcon undoTurnIcon = new ImageIcon("bin/images/undoTurn.png");
        Image undoTurnImage = undoTurnIcon.getImage();
        undoTurnImage = undoTurnImage.getScaledInstance( 20, 20,  java.awt.Image.SCALE_SMOOTH ) ;
		undoTurnIcon.setImage(undoTurnImage);
		
		ImageIcon redoTurnIcon = new ImageIcon("bin/images/redoTurn.png");
        Image redoTurnImage = redoTurnIcon.getImage();
        redoTurnImage = redoTurnImage.getScaledInstance( 20, 20,  java.awt.Image.SCALE_SMOOTH ) ;
		redoTurnIcon.setImage(redoTurnImage);
		
		agileStance = new JToggleButton ("Agile");
		agileStance.setName("modeAgile");
		specialStance = new JToggleButton ("Defense");
		specialStance.setName("modeDefense");
		ability = new JButton ("Ability");
		save = new JButton("Save");
		save.setName("save");
		load = new JButton("Load");
		load.setName("load");
		exit = new JButton("Exit");
		exit.setName("exit");
		undoMove = new JButton(undoMoveIcon);
		undoMove.setName("undomove");
		undoMove.addActionListener(hudListener);
		undoTurn = new JButton(undoTurnIcon);
		undoTurn.setName("undoturn");
		undoTurn.addActionListener(hudListener);

		
		
		agileStance.setFocusPainted(false);
		specialStance.setFocusPainted(false);
		ability.setFocusPainted(false);
		
		agileStance.addActionListener(hudListener);
		specialStance.addActionListener(hudListener);
		save.addActionListener(hudListener);
		load.addActionListener(hudListener);
		exit.addActionListener(hudListener);
		
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
		modesPanelExplorers.setOpaque(false);
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
		
		//Setup menu panel
		menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.LINE_AXIS));
		menuPanel.add(Box.createRigidArea(new Dimension(90,0)));
		menuPanel.add(save);
		menuPanel.add(Box.createRigidArea(new Dimension(30,0)));
	    menuPanel.add(load);
	    menuPanel.add(Box.createRigidArea(new Dimension(30,0)));
	    menuPanel.add(exit);
		

		//Layout elements across Unit HUD bar
        selectedHud.add(Box.createVerticalStrut(50));
        selectedHud.add(unitName);
        selectedHud.add(Box.createRigidArea(new Dimension(50, 0)));
        selectedHud.add(modesPanelExplorers);
		//this.add(abilitiesPanel);
        
        undoScreen.add(undoTurn);
        undoScreen.add(undoMove);
        undoScreen.add(undoLabel);
        
        notSelectedHud.add(noSelection);
        

		unitHudCards.add(selectedHud, "selected");
		unitHudCards.add(notSelectedHud, "notselected");
		unitHudCards.add(menuPanel, "menu");
		unitHudCards.add(undoScreen, "undo");
		this.add(unitHudCards);
		
		unitHudLayout = (CardLayout) unitHudCards.getLayout();
		unitHudLayout.show(unitHudCards, "notselected");
		
		Mediator.getInstance().registerUnitHudColleagues(unitHudLayout, unitHudCards);
		
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

}
