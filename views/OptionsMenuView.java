package views;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import mediator.Mediator;

public class OptionsMenuView extends javax.swing.JPanel {
	
	private Image backgroundImage;
	
	private boolean setupOn = false;
	
	//Panels
	private JPanel boardSizeButtons;
	private JPanel boardSizeFields;
	private JPanel piecesFields;
	private JPanel guardianPieces;
	private JPanel guardianGrid;
	private JPanel guardianLabel;
	private JPanel explorerPieces;
	private JPanel explorerLabel;
	private JPanel explorerGrid;
	private JPanel backApply;
	private JPanel piecesGap;
	private JPanel piecesPush;
	private JPanel setupRow;
	
	//Buttons
    private JButton btnSmall;
    private JButton btnMedium;
    private JButton btnLarge;
    private JButton btnBack;
    private JButton btnApply;
    
    
    private JToggleButton btnHero;
    private JToggleButton btnTactician;
    private JToggleButton btnTrapmaster;
    private JToggleButton btnScout;
    private JToggleButton btnBehemoth;
    private JToggleButton btnHunter;
    private JToggleButton btnGolem;
    private JToggleButton setupMode;
    
    
    //TextFields
    private javax.swing.JTextField txtWidth;
    private javax.swing.JTextField txtHeight;
    //private javax.swing.JTextField txtGuardians;
    //private javax.swing.JTextField txtExplorers;
    
    
    //Labels
    private JLabel lblBoardSize;
    private JLabel lblNumberPieces;
    private JLabel lblWidth;
    private JLabel lblHeight;
    private JLabel lblGuardians;
    private JLabel lblExplorers;
    private JLabel lblSetup;
	
	@Override
	  protected void paintComponent(Graphics g) {

	    super.paintComponent(g);
	        g.drawImage(backgroundImage, 0, 0, null);
	}
	
    public OptionsMenuView(ActionListener actionListener) {
    	this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    	initComponents();
        try {
			backgroundImage = ImageIO.read(new File("bin/images/optionsBG-high.jpg"));
			Image scaledBG = backgroundImage.getScaledInstance( 940, 570,  java.awt.Image.SCALE_SMOOTH ) ;
			backgroundImage = scaledBG;
		} catch (IOException e) {
			e.printStackTrace();
		}
        this.setPreferredSize(new Dimension(919,566));
        btnBack.addActionListener(actionListener);
        btnSmall.addActionListener(actionListener);
        btnMedium.addActionListener(actionListener);
        btnLarge.addActionListener(actionListener);
        btnApply.addActionListener(actionListener);
    }
    
    public void setBoardFields(int size){
    	txtWidth.setText(Integer.toString(size));
    	txtHeight.setText(Integer.toString(size));
    }
    
  
    public void setToDefault(){
    	//txtGuardians.setText("3");
    	//txtExplorers.setText("4");
    }

    private void initComponents() {
    	
    	boardSizeFields = new JPanel();
        boardSizeButtons = new JPanel();
        piecesFields = new JPanel();
        guardianPieces = new JPanel();
        guardianGrid = new JPanel();
        explorerPieces = new JPanel();
        explorerGrid = new JPanel();
        backApply = new JPanel();
        explorerLabel = new JPanel();
        guardianLabel = new JPanel();
        piecesGap = new JPanel();
        piecesPush = new JPanel();
        setupRow = new JPanel();
        
        boardSizeButtons.setLayout(new BoxLayout(boardSizeButtons, BoxLayout.LINE_AXIS));
        boardSizeFields.setLayout(new BoxLayout(boardSizeFields, BoxLayout.LINE_AXIS));
        setupRow.setLayout(new BoxLayout(setupRow, BoxLayout.LINE_AXIS));
        piecesFields.setLayout(new BoxLayout(piecesFields, BoxLayout.LINE_AXIS));
        backApply.setLayout(new BoxLayout(backApply, BoxLayout.LINE_AXIS));
        explorerLabel.setLayout(new FlowLayout(FlowLayout.LEFT));
        guardianPieces.setLayout(new BoxLayout(guardianPieces, BoxLayout.Y_AXIS));
        explorerPieces.setLayout(new BoxLayout(explorerPieces, BoxLayout.Y_AXIS));
        guardianLabel.setLayout(new FlowLayout(FlowLayout.LEFT));
        guardianGrid.setLayout(new GridLayout(0, 2, 5, 5));
        explorerGrid.setLayout(new GridLayout(0, 2, 5, 5));
        
        //Gets rid of backgrounds
        boardSizeFields.setOpaque(false);
        piecesFields.setOpaque(false);
        backApply.setOpaque(false);
        guardianPieces.setOpaque(false);
        explorerPieces.setOpaque(false);
        guardianGrid.setOpaque(false);
        explorerGrid.setOpaque(false);
        guardianLabel.setOpaque(false);
        explorerLabel.setOpaque(false);
        piecesGap.setOpaque(false);
        piecesPush.setOpaque(false);
        setupRow.setOpaque(false);
        
        guardianGrid.setMinimumSize(new Dimension(100,100));
        guardianGrid.setMaximumSize(new Dimension(100,100));        
        guardianLabel.setMinimumSize(new Dimension(100,20));
        guardianLabel.setMaximumSize(new Dimension(100,20));        
        explorerGrid.setMinimumSize(new Dimension(100,100));
        explorerGrid.setMaximumSize(new Dimension(100,100));        
        explorerLabel.setMinimumSize(new Dimension(100,20));
        explorerLabel.setMaximumSize(new Dimension(100,20));
        piecesGap.setMinimumSize(new Dimension(40,100));
        piecesGap.setMaximumSize(new Dimension(40,100)); 
        piecesPush.setMinimumSize(new Dimension(20,100));
        piecesPush.setMaximumSize(new Dimension(20,100)); 
        
    	lblBoardSize = new JLabel();
    	lblNumberPieces = new JLabel();
    	lblWidth = new JLabel();
    	lblHeight = new JLabel(); 
    	lblGuardians = new JLabel(); 
    	lblExplorers = new JLabel(); 
    	lblSetup = new JLabel();
    	
    	btnBack = new JButton();
    	btnApply = new JButton();
        btnSmall = new JButton();
        btnMedium = new JButton();
        btnLarge = new JButton();
        
        btnBehemoth = new JToggleButton();
        btnGolem = new JToggleButton();
        btnHunter = new JToggleButton();
        btnScout = new JToggleButton();
        btnTactician = new JToggleButton();
        btnTrapmaster = new JToggleButton();
        btnHero = new JToggleButton();
        setupMode = new JToggleButton();
        
        btnBehemoth.setFocusPainted(false);
        btnGolem.setFocusPainted(false);
        btnHunter.setFocusPainted(false);
        btnScout.setFocusPainted(false);
        btnTactician.setFocusPainted(false);
        btnTrapmaster.setFocusPainted(false);
        btnHero.setFocusPainted(false);
        setupMode.setFocusPainted(false);
        
        
        txtWidth = new javax.swing.JTextField();
        txtHeight = new javax.swing.JTextField();;
        //txtGuardians = new javax.swing.JTextField();
        //txtExplorers = new javax.swing.JTextField();
        
        setBackground(new java.awt.Color(251, 242, 243));
        setBorder(new javax.swing.border.MatteBorder(null));
        
        this.add(Box.createVerticalStrut(80));
        
        //BOARD SIZE SECTION
        
        lblBoardSize.setFont(new java.awt.Font("Ubuntu", 1, 18));
        lblBoardSize.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblBoardSize.setAlignmentY(Component.CENTER_ALIGNMENT);
        lblBoardSize.setText("Board Size");
        lblBoardSize.setInheritsPopupMenu(false);
        lblBoardSize.setForeground(Color.WHITE);
        this.add(lblBoardSize);
        
        this.add(Box.createVerticalStrut(20));
        
        btnSmall.setText("Small");
        btnSmall.setName("small");
        btnSmall.setMinimumSize(new Dimension(70,30));
        btnSmall.setMaximumSize(new Dimension(70,30));
        btnSmall.setAlignmentX(Component.LEFT_ALIGNMENT);
        btnSmall.setAlignmentY(Component.LEFT_ALIGNMENT);
        boardSizeButtons.add(btnSmall);
        
        btnMedium.setText("Medium");
        btnMedium.setName("medium");
        btnMedium.setMinimumSize(new Dimension(80,30));
        btnMedium.setMaximumSize(new Dimension(80,30));
        btnMedium.setAlignmentX(Component.LEFT_ALIGNMENT);
        btnMedium.setAlignmentY(Component.LEFT_ALIGNMENT);
        boardSizeButtons.add(btnMedium);
        
        btnLarge.setText("Large");
        btnLarge.setName("large");
        btnLarge.setMinimumSize(new Dimension(70,30));
        btnLarge.setMaximumSize(new Dimension(70,30));
        btnLarge.setAlignmentX(Component.LEFT_ALIGNMENT);
        btnLarge.setAlignmentY(Component.LEFT_ALIGNMENT);
        boardSizeButtons.add(btnLarge);
        
        
        this.add(boardSizeButtons);
        
        this.add(Box.createVerticalStrut(15));
        
        boardSizeFields.add(Box.createRigidArea(new Dimension(5,50)));
        
        lblWidth.setText("Width:");
        lblWidth.setForeground(Color.WHITE);
        boardSizeFields.add(lblWidth);
        
        boardSizeFields.add(Box.createRigidArea(new Dimension(10,1)));
        
        txtWidth.setText("12");
        txtWidth.setMinimumSize(new Dimension(70,30));
        txtWidth.setMaximumSize(new Dimension(70,30));
        boardSizeFields.add(txtWidth);
        
        boardSizeFields.add(Box.createRigidArea(new Dimension(20,1)));
        
        lblHeight.setText("Height:");
        lblHeight.setForeground(Color.WHITE);
        boardSizeFields.add(lblHeight);
        
        boardSizeFields.add(Box.createRigidArea(new Dimension(10,1)));
        
        txtHeight.setText("12");
        txtHeight.setMinimumSize(new Dimension(70,30));
        txtHeight.setMaximumSize(new Dimension(70,30));
        boardSizeFields.add(txtHeight);
        
        boardSizeFields.add(Box.createRigidArea(new Dimension(5,1)));
        
        this.add(boardSizeFields);
        
        this.add(Box.createVerticalStrut(40));
        
        //SETUP BOARD SECTION
        
        lblSetup.setText("Customise board");
        lblSetup.setForeground(Color.WHITE);
        setupRow.add(lblSetup);
        
        setupRow.add(Box.createRigidArea(new Dimension(10,1)));
        
        setupMode.setText("OFF");
        setupMode.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent event) {
                if (setupMode.isSelected()){
                	setupMode.setText("ON");
                	setupOn = true;
                } else {
                	setupMode.setText("OFF");
                	setupOn = false;
                }
            }
        });
        
        setupRow.add(setupMode);
        
        this.add(setupRow);
        
        this.add(Box.createVerticalStrut(40));
        
        //NUMBER OF PIECES SECTION
        
        lblNumberPieces.setFont(new java.awt.Font("Ubuntu", 1, 18));
        lblNumberPieces.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblNumberPieces.setAlignmentY(Component.CENTER_ALIGNMENT);
        lblNumberPieces.setText("Number of Pieces");
        lblNumberPieces.setInheritsPopupMenu(false);
        lblNumberPieces.setForeground(Color.WHITE);
        this.add(lblNumberPieces);
        
        this.add(Box.createVerticalStrut(20));
        
        piecesFields.add(Box.createRigidArea(new Dimension(5,1)));
        
        lblGuardians.setText("Guardians:");
        lblGuardians.setForeground(Color.WHITE);  
        lblGuardians.setAlignmentY(Component.LEFT_ALIGNMENT);
        guardianLabel.add(lblGuardians);
        guardianPieces.add(guardianLabel);
        guardianPieces.add(Box.createRigidArea(new Dimension(0,10)));
        
        ImageIcon tempIcon = new ImageIcon("bin/images/golem.png");
        Image symbolImage = tempIcon.getImage() ;  
        Image newSymbolImage = symbolImage.getScaledInstance( 35, 35,  java.awt.Image.SCALE_SMOOTH ) ;
        tempIcon.setImage(newSymbolImage);
        btnGolem.setIcon(tempIcon);
        tempIcon = new ImageIcon("bin/images/golem_off.png");
        symbolImage = tempIcon.getImage() ;  
        newSymbolImage = symbolImage.getScaledInstance( 35, 35,  java.awt.Image.SCALE_SMOOTH ) ;
        tempIcon.setImage(newSymbolImage);
        btnGolem.setSelectedIcon(tempIcon);
        btnGolem.setName("golem");
        btnGolem.setMinimumSize(new Dimension(35, 35));
        btnGolem.setMaximumSize(new Dimension(35, 35));
        btnGolem.setAlignmentX(Component.LEFT_ALIGNMENT);
        btnGolem.setAlignmentY(Component.LEFT_ALIGNMENT);
        btnGolem.setOpaque(false);
        guardianGrid.add(btnGolem);
        
        
        
        tempIcon = new ImageIcon("bin/images/behemoth.png");
        symbolImage = tempIcon.getImage() ;  
        newSymbolImage = symbolImage.getScaledInstance( 35, 35,  java.awt.Image.SCALE_SMOOTH ) ;
        tempIcon.setImage(newSymbolImage);
        btnBehemoth.setIcon(tempIcon);
        tempIcon = new ImageIcon("bin/images/behemoth_off.png");
        symbolImage = tempIcon.getImage() ;  
        newSymbolImage = symbolImage.getScaledInstance( 35, 35,  java.awt.Image.SCALE_SMOOTH ) ;
        tempIcon.setImage(newSymbolImage);
        btnBehemoth.setSelectedIcon(tempIcon);
        btnBehemoth.setName("behemoth");
        btnBehemoth.setMinimumSize(new Dimension(35, 35));
        btnBehemoth.setMaximumSize(new Dimension(35, 35));
        btnBehemoth.setAlignmentX(Component.LEFT_ALIGNMENT);
        btnBehemoth.setAlignmentY(Component.LEFT_ALIGNMENT);
        btnBehemoth.setOpaque(false);
        guardianGrid.add(btnBehemoth);
        
        
        tempIcon = new ImageIcon("bin/images/hunter.png");
        symbolImage = tempIcon.getImage() ;  
        newSymbolImage = symbolImage.getScaledInstance( 35, 35,  java.awt.Image.SCALE_SMOOTH ) ;
        tempIcon.setImage(newSymbolImage);
        btnHunter.setIcon(tempIcon);
        tempIcon = new ImageIcon("bin/images/hunter_off.png");
        symbolImage = tempIcon.getImage() ;  
        newSymbolImage = symbolImage.getScaledInstance( 35, 35,  java.awt.Image.SCALE_SMOOTH ) ;
        tempIcon.setImage(newSymbolImage);
        btnHunter.setSelectedIcon(tempIcon);
        btnHunter.setName("hunter");
        btnHunter.setMinimumSize(new Dimension(35, 35));
        btnHunter.setMaximumSize(new Dimension(35, 35));
        btnHunter.setAlignmentX(Component.LEFT_ALIGNMENT);
        btnHunter.setAlignmentY(Component.LEFT_ALIGNMENT);
        btnHunter.setOpaque(false);
        guardianGrid.add(btnHunter);
        
        guardianPieces.add(guardianGrid);
        
        //piecesFields.add(Box.createRigidArea(new Dimension(500,100)));
        
        //txtGuardians.setText("3");
        //txtGuardians.setMinimumSize(new Dimension(70,30));
        //txtGuardians.setMaximumSize(new Dimension(70,30));
        //piecesFields.add(txtGuardians);
        
        //piecesFields.add(Box.createRigidArea(new Dimension(15,1)));
        
        lblExplorers.setText("Explorers:");
        lblExplorers.setForeground(Color.WHITE);
        lblExplorers.setAlignmentY(Component.LEFT_ALIGNMENT);
        lblExplorers.setAlignmentX(Component.LEFT_ALIGNMENT);
        explorerLabel.add(lblExplorers);
        explorerPieces.add(explorerLabel);
        explorerPieces.add(Box.createRigidArea(new Dimension(0,10)));
        
        tempIcon = new ImageIcon("bin/images/hero.png");
        symbolImage = tempIcon.getImage() ;  
        newSymbolImage = symbolImage.getScaledInstance( 35, 35,  java.awt.Image.SCALE_SMOOTH ) ;
        tempIcon.setImage(newSymbolImage);
        btnHero.setIcon(tempIcon);
        tempIcon = new ImageIcon("bin/images/hero_off.png");
        symbolImage = tempIcon.getImage() ;  
        newSymbolImage = symbolImage.getScaledInstance( 35, 35,  java.awt.Image.SCALE_SMOOTH ) ;
        tempIcon.setImage(newSymbolImage);
        btnHero.setSelectedIcon(tempIcon);
        btnHero.setName("hero");
        btnHero.setMinimumSize(new Dimension(35, 35));
        btnHero.setMaximumSize(new Dimension(35, 35));
        btnHero.setAlignmentX(Component.LEFT_ALIGNMENT);
        btnHero.setAlignmentY(Component.LEFT_ALIGNMENT);
        btnHero.setOpaque(false);
        explorerGrid.add(btnHero);
        
        tempIcon = new ImageIcon("bin/images/scout.png");
        symbolImage = tempIcon.getImage() ;  
        newSymbolImage = symbolImage.getScaledInstance( 35, 35,  java.awt.Image.SCALE_SMOOTH ) ;
        tempIcon.setImage(newSymbolImage);
        btnScout.setIcon(tempIcon);
        tempIcon = new ImageIcon("bin/images/scout_off.png");
        symbolImage = tempIcon.getImage() ;  
        newSymbolImage = symbolImage.getScaledInstance( 35, 35,  java.awt.Image.SCALE_SMOOTH ) ;
        tempIcon.setImage(newSymbolImage);
        btnScout.setSelectedIcon(tempIcon);
        btnScout.setName("scout");
        btnScout.setMinimumSize(new Dimension(35, 35));
        btnScout.setMaximumSize(new Dimension(35, 35));
        btnScout.setAlignmentX(Component.LEFT_ALIGNMENT);
        btnScout.setAlignmentY(Component.LEFT_ALIGNMENT);
        btnScout.setOpaque(false);
        explorerGrid.add(btnScout);
        
        
        tempIcon = new ImageIcon("bin/images/tactician.png");
        symbolImage = tempIcon.getImage() ;  
        newSymbolImage = symbolImage.getScaledInstance( 35, 35,  java.awt.Image.SCALE_SMOOTH ) ;
        tempIcon.setImage(newSymbolImage);
        btnTactician.setIcon(tempIcon);
        tempIcon = new ImageIcon("bin/images/tactician_off.png");
        symbolImage = tempIcon.getImage() ;  
        newSymbolImage = symbolImage.getScaledInstance( 35, 35,  java.awt.Image.SCALE_SMOOTH ) ;
        tempIcon.setImage(newSymbolImage);
        btnTactician.setSelectedIcon(tempIcon);
        btnTactician.setName("tactician");
        btnTactician.setMinimumSize(new Dimension(35, 35));
        btnTactician.setMaximumSize(new Dimension(35, 35));
        btnTactician.setAlignmentX(Component.LEFT_ALIGNMENT);
        btnTactician.setAlignmentY(Component.LEFT_ALIGNMENT);
        btnTactician.setOpaque(false);
        explorerGrid.add(btnTactician);
        
        tempIcon = new ImageIcon("bin/images/trapMaster.png");
        symbolImage = tempIcon.getImage() ;  
        newSymbolImage = symbolImage.getScaledInstance( 35, 35,  java.awt.Image.SCALE_SMOOTH ) ;
        tempIcon.setImage(newSymbolImage);
        btnTrapmaster.setIcon(tempIcon);
        tempIcon = new ImageIcon("bin/images/trapMaster_off.png");
        symbolImage = tempIcon.getImage() ;  
        newSymbolImage = symbolImage.getScaledInstance( 35, 35,  java.awt.Image.SCALE_SMOOTH ) ;
        tempIcon.setImage(newSymbolImage);
        btnTrapmaster.setSelectedIcon(tempIcon);
        btnTrapmaster.setName("trapmaster");
        btnTrapmaster.setMinimumSize(new Dimension(40,40));
        btnTrapmaster.setMaximumSize(new Dimension(35, 35));
        btnTrapmaster.setAlignmentX(Component.LEFT_ALIGNMENT);
        btnTrapmaster.setAlignmentY(Component.LEFT_ALIGNMENT);
        btnTrapmaster.setOpaque(false);
        explorerGrid.add(btnTrapmaster);
        
        explorerPieces.add(explorerGrid);
        
        piecesFields.add(piecesPush);
        piecesFields.add(guardianPieces);
        piecesFields.add(piecesGap);
        piecesFields.add(explorerPieces);
        
        //piecesFields.add(Box.createRigidArea(new Dimension(5,1)));
        
        //txtExplorers.setText("4");
        //txtExplorers.setMinimumSize(new Dimension(70,30));
        //txtExplorers.setMaximumSize(new Dimension(70,30));
        //piecesFields.add(txtExplorers);
        
        piecesFields.add(Box.createRigidArea(new Dimension(5,1)));
        
        this.add(piecesFields);
        
        this.add(Box.createVerticalStrut(30));

        btnBack.setText("Back");
        btnBack.setName("back");
        btnBack.setMinimumSize(new Dimension(100,30));
        btnBack.setMaximumSize(new Dimension(100,30));
        btnBack.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnBack.setAlignmentY(Component.CENTER_ALIGNMENT);
        backApply.add(btnBack);
        
        backApply.add(Box.createRigidArea(new Dimension(10,1)));
        
        btnApply.setText("Apply");
        btnApply.setName("apply");
        btnApply.setMinimumSize(new Dimension(100,30));
        btnApply.setMaximumSize(new Dimension(100,30));
        btnApply.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnApply.setAlignmentY(Component.CENTER_ALIGNMENT);
        backApply.add(btnApply);
        
        this.add(backApply);
        
        Mediator.getInstance().registerOptionsColleagues();
        
    }
    
    //Function
    
    public int getColumns (){
    	String tempString = txtWidth.getText();
    	int newColumns = Integer.parseInt(tempString);
    	return newColumns;
    }
    
    public int getRows(){
    	String tempString = txtHeight.getText();
    	int newRows = Integer.parseInt(tempString);
    	return newRows;
    }
    
    public boolean getSetup(){
    	return setupOn;
    }
    
    @SuppressWarnings("null")
	public ArrayList<String> getInactiveUnits(){
    	ArrayList<String> inactiveUnits = new ArrayList<String>();
    	if (btnGolem.isSelected()){
    		inactiveUnits.add("golem");
    	}
    	if (btnBehemoth.isSelected()){
    		inactiveUnits.add("behemoth");
    	}
    	if (btnHunter.isSelected()){
    		inactiveUnits.add("hunter");
    	}
    	if (btnTactician.isSelected()){
    		inactiveUnits.add("tactician");
    	}
    	if (btnHero.isSelected()){
    		inactiveUnits.add("hero");
    	}
    	if (btnScout.isSelected()){
    		inactiveUnits.add("scout");
    	}
    	if (btnTrapmaster.isSelected()){
    		inactiveUnits.add("trapmaster");
    	}
    	return inactiveUnits;
    }

    
}

