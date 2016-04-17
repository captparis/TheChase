/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

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

public class MainMenuView extends javax.swing.JPanel {

    /**
     * Creates new form MainMenu
     * @param actionListener
     */
	
	private Image backgroundImage;
	
	@Override
	  protected void paintComponent(Graphics g) {

	    super.paintComponent(g);
	        g.drawImage(backgroundImage, 0, 0, null);
	}
	
    public MainMenuView(ActionListener actionListener) {
    	this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    	initComponents();
        try {
			backgroundImage = ImageIO.read(new File("bin/images/menuBG.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
        this.setPreferredSize(new Dimension(919,566));
        btnStart.addActionListener(actionListener);
        btnOptions.addActionListener(actionListener);
        btnQuit.addActionListener(actionListener);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblMainMenu = new javax.swing.JLabel();
        btnStart = new javax.swing.JButton();
        btnOptions = new javax.swing.JButton();
        btnQuit = new javax.swing.JButton();

        setBackground(new java.awt.Color(251, 242, 243));
        setBorder(new javax.swing.border.MatteBorder(null));

        lblMainMenu.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N
        lblMainMenu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMainMenu.setText("The Chase - Main Menu ");
        lblMainMenu.setInheritsPopupMenu(false);
        
        this.add(Box.createVerticalStrut(200));

        btnStart.setText("Start Game");
        btnStart.setName("startGame"); // NOI18N
        btnStart.setMinimumSize(new Dimension(300,50));
        btnStart.setMaximumSize(new Dimension(300,50));
        btnStart.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnStart.setAlignmentY(Component.CENTER_ALIGNMENT);
        this.add(btnStart);
        
        this.add(Box.createVerticalStrut(20));
        
        btnOptions.setText("Options");
        btnOptions.setName("options"); // NOI18N
        btnOptions.setMinimumSize(new Dimension(300,50));
        btnOptions.setMaximumSize(new Dimension(300,50));
        btnOptions.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnOptions.setAlignmentY(Component.CENTER_ALIGNMENT);
        this.add(btnOptions);
        
        this.add(Box.createVerticalStrut(20));

        btnQuit.setText("Quit Game");
        btnQuit.setName("quitGame"); // NOI18N
        btnQuit.setMinimumSize(new Dimension(300,50));
        btnQuit.setMaximumSize(new Dimension(300,50));
        btnQuit.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnQuit.setAlignmentY(Component.CENTER_ALIGNMENT);
        this.add(btnQuit);
        
        /*
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblMainMenu, javax.swing.GroupLayout.DEFAULT_SIZE, 344, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnStart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnOptions, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnQuit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lblMainMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnStart)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnOptions)
                .addGap(18, 18, 18)
                .addComponent(btnQuit)
                .addContainerGap(16, Short.MAX_VALUE))
        );*/
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnOptions;
    private javax.swing.JButton btnQuit;
    private javax.swing.JButton btnStart;
    private javax.swing.JLabel lblMainMenu;
    // End of variables declaration//GEN-END:variables
}

