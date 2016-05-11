package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class UnitHudView extends JPanel{
	
	public ImageIcon agileIcon;
	public ImageIcon defenseIcon;
	
	private Image agileImage;
	private Image defenseImage;
	
	public JPanel modesPanel;
	public JPanel abilitiesPanel;

	public JLabel unitName;
	public JLabel modesTitle;
	public JLabel abilitiesTitle;
	
	public JToggleButton agileStance;
	public JToggleButton defenseStance;
	public JButton ability;
	
	private ButtonGroup modeButtonGroup;
	
	
	
	public UnitHudView(ActionListener hudListener){
		
		Color tan = new Color(255, 217, 102);
		this.setBackground(tan);
		this.setPreferredSize(new Dimension(700, 50));
		
		modesPanel = new JPanel();
		abilitiesPanel = new JPanel();
		
		unitName = new JLabel ("No unit selected");
		modesTitle = new JLabel ("Modes");
		abilitiesTitle = new JLabel ("Abilities");
		
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
		modesPanel.setLayout(new BoxLayout(modesPanel, BoxLayout.LINE_AXIS));
		modesPanel.setOpaque(true);
        modesPanel.setBackground(Color.WHITE);
		modesPanel.add(Box.createRigidArea(new Dimension(15,40)));
		modesPanel.add(modesTitle);
		modesPanel.add(Box.createRigidArea(new Dimension(30,0)));
		modesPanel.add(agileStance);
		modesPanel.add(Box.createRigidArea(new Dimension(30, 0)));
		modesPanel.add(defenseStance);
		modesPanel.add(Box.createRigidArea(new Dimension(30,0)));
		
		
		
		//Set up abilities panel
		abilitiesPanel.setLayout(new BoxLayout(abilitiesPanel, BoxLayout.LINE_AXIS));
		abilitiesPanel.setOpaque(false);
		abilitiesPanel.add(Box.createRigidArea(new Dimension(15,0)));
		abilitiesPanel.add(abilitiesTitle);
		abilitiesPanel.add(Box.createRigidArea(new Dimension(30,0)));
		abilitiesPanel.add(ability);	
		abilitiesPanel.add(Box.createRigidArea(new Dimension(15,0)));
		
		

		//Layout elements across Unit HUD bar
        this.add(Box.createVerticalStrut(40));
		this.add(unitName);
		this.add(Box.createRigidArea(new Dimension(50, 0)));
		this.add(modesPanel);
		//this.add(abilitiesPanel);
	}
	
	//Functions to handle changes to the Unit HUD based on state changes
	public void changeUnitName(String selectedUnit){
		unitName.setText(selectedUnit);
	}

}
