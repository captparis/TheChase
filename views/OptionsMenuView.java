package views;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class OptionsMenuView extends javax.swing.JPanel {
	
	private Image backgroundImage;
	
	//Panels
	private JPanel boardSizeButtons;
	private JPanel boardSizeFields;
	private JPanel piecesFields;
	public JPanel backApply;
	
	//Buttons
    private javax.swing.JButton btnSmall;
    private javax.swing.JButton btnMedium;
    private javax.swing.JButton btnLarge;
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnDefaultPieces;
    private javax.swing.JButton btnApply;
    
    //TextFields
    private javax.swing.JTextField txtWidth;
    private javax.swing.JTextField txtHeight;
    private javax.swing.JTextField txtGuardians;
    private javax.swing.JTextField txtExplorers;
    
    
    //Labels
    private javax.swing.JLabel lblBoardSize;
    private javax.swing.JLabel lblNumberPieces;
    private javax.swing.JLabel lblWidth;
    private javax.swing.JLabel lblHeight;
    private javax.swing.JLabel lblGuardians;
    private javax.swing.JLabel lblExplorers;
	
	@Override
	  protected void paintComponent(Graphics g) {

	    super.paintComponent(g);
	        g.drawImage(backgroundImage, 0, 0, null);
	}
	
    public OptionsMenuView(ActionListener actionListener) {
    	this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    	initComponents();
        try {
			backgroundImage = ImageIO.read(new File("bin/images/optionsBG.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
        this.setPreferredSize(new Dimension(919,566));
        btnBack.addActionListener(actionListener);
        btnSmall.addActionListener(actionListener);
        btnMedium.addActionListener(actionListener);
        btnLarge.addActionListener(actionListener);
        btnDefaultPieces.addActionListener(actionListener);
    }
    
    public void setBoardFields(int size){
    	txtWidth.setText(Integer.toString(size));
    	txtHeight.setText(Integer.toString(size));
    }
    
    public void setToDefault(){
    	txtGuardians.setText("3");
    	txtExplorers.setText("4");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
    	
    	boardSizeFields = new JPanel();
        boardSizeButtons = new JPanel();
        piecesFields = new JPanel();
        backApply = new JPanel();
        
        boardSizeButtons.setLayout(new BoxLayout(boardSizeButtons, BoxLayout.LINE_AXIS));
        boardSizeFields.setLayout(new BoxLayout(boardSizeFields, BoxLayout.LINE_AXIS));
        piecesFields.setLayout(new BoxLayout(piecesFields, BoxLayout.LINE_AXIS));
        backApply.setLayout(new BoxLayout(backApply, BoxLayout.LINE_AXIS));
        //Gets rid of backgrounds
        boardSizeFields.setOpaque(false);
        piecesFields.setOpaque(false);
        backApply.setOpaque(false);
        
    	lblBoardSize = new javax.swing.JLabel();
    	lblNumberPieces = new javax.swing.JLabel();
    	lblWidth = new javax.swing.JLabel();
    	lblHeight = new javax.swing.JLabel(); 
    	lblGuardians = new javax.swing.JLabel(); 
    	lblExplorers = new javax.swing.JLabel(); 
    	
    	btnBack = new javax.swing.JButton();
    	btnApply = new javax.swing.JButton();
        btnSmall = new javax.swing.JButton();
        btnMedium = new javax.swing.JButton();
        btnDefaultPieces = new javax.swing.JButton();
        btnLarge = new javax.swing.JButton();
        
        
        txtWidth = new javax.swing.JTextField();
        txtHeight = new javax.swing.JTextField();;
        txtGuardians = new javax.swing.JTextField();
        txtExplorers = new javax.swing.JTextField();
        
        setBackground(new java.awt.Color(251, 242, 243));
        setBorder(new javax.swing.border.MatteBorder(null));
        
        this.add(Box.createVerticalStrut(120));
        
        //BOARD SIZE SECTION
        
        lblBoardSize.setFont(new java.awt.Font("Ubuntu", 1, 18));
        //lblBoardSize.setMaximumSize(new Dimension(300, 20));
        //lblBoardSize.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
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
        
        txtWidth.setText("15");
        txtWidth.setMinimumSize(new Dimension(70,30));
        txtWidth.setMaximumSize(new Dimension(70,30));
        boardSizeFields.add(txtWidth);
        
        boardSizeFields.add(Box.createRigidArea(new Dimension(20,1)));
        
        lblHeight.setText("Height:");
        lblHeight.setForeground(Color.WHITE);
        boardSizeFields.add(lblHeight);
        
        boardSizeFields.add(Box.createRigidArea(new Dimension(10,1)));
        
        txtHeight.setText("15");
        txtHeight.setMinimumSize(new Dimension(70,30));
        txtHeight.setMaximumSize(new Dimension(70,30));
        boardSizeFields.add(txtHeight);
        
        boardSizeFields.add(Box.createRigidArea(new Dimension(5,1)));
        
        this.add(boardSizeFields);
        
        this.add(Box.createVerticalStrut(40));
        
        //NUMBER OF PIECES SECTION
        
        lblNumberPieces.setFont(new java.awt.Font("Ubuntu", 1, 18));
        //lblBoardSize.setMaximumSize(new Dimension(300, 20));
        //lblBoardSize.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblNumberPieces.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblNumberPieces.setAlignmentY(Component.CENTER_ALIGNMENT);
        lblNumberPieces.setText("Number of Pieces");
        lblNumberPieces.setInheritsPopupMenu(false);
        lblNumberPieces.setForeground(Color.WHITE);
        this.add(lblNumberPieces);
        
        this.add(Box.createVerticalStrut(15));
        
        btnDefaultPieces.setText("Default");
        btnDefaultPieces.setName("defaultpieces");
        btnDefaultPieces.setMinimumSize(new Dimension(80,30));
        btnDefaultPieces.setMaximumSize(new Dimension(80,30));
        btnDefaultPieces.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnDefaultPieces.setAlignmentY(Component.CENTER_ALIGNMENT);
        this.add(btnDefaultPieces);
        
        this.add(Box.createVerticalStrut(20));
        
        piecesFields.add(Box.createRigidArea(new Dimension(5,1)));
        
        lblGuardians.setText("Guardians:");
        lblGuardians.setForeground(Color.WHITE);
        piecesFields.add(lblGuardians);
        
        piecesFields.add(Box.createRigidArea(new Dimension(5,1)));
        
        txtGuardians.setText("3");
        txtGuardians.setMinimumSize(new Dimension(70,30));
        txtGuardians.setMaximumSize(new Dimension(70,30));
        piecesFields.add(txtGuardians);
        
        piecesFields.add(Box.createRigidArea(new Dimension(15,1)));
        
        lblExplorers.setText("Explorers:");
        lblExplorers.setForeground(Color.WHITE);
        piecesFields.add(lblExplorers);
        
        piecesFields.add(Box.createRigidArea(new Dimension(5,1)));
        
        txtExplorers.setText("4");
        txtExplorers.setMinimumSize(new Dimension(70,30));
        txtExplorers.setMaximumSize(new Dimension(70,30));
        piecesFields.add(txtExplorers);
        
        piecesFields.add(Box.createRigidArea(new Dimension(5,1)));
        
        this.add(piecesFields);
        
        this.add(Box.createVerticalStrut(80));

        btnBack.setText("Back");
        btnBack.setName("back");
        btnBack.setMinimumSize(new Dimension(100,30));
        btnBack.setMaximumSize(new Dimension(100,30));
        btnBack.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnBack.setAlignmentY(Component.CENTER_ALIGNMENT);
        backApply.add(btnBack);
        
        backApply.add(Box.createRigidArea(new Dimension(50,1)));
        
        btnApply.setText("Apply");
        btnApply.setName("apply");
        btnApply.setMinimumSize(new Dimension(100,30));
        btnApply.setMaximumSize(new Dimension(100,30));
        btnApply.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnApply.setAlignmentY(Component.CENTER_ALIGNMENT);
        backApply.add(btnApply);
        
        this.add(backApply);
        
    }

    
}
