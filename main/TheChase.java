/*
 *  OSSD Asignment 1 - The Chase
 *  Charles Yim - S3570764
 *  Jacob Paris - S3238163
 *  Chen Liu- S3481556
 *  Taison Eady - S3282633
 */

package main;

import controllers.GameController;
import sound.SoundManager;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.WindowConstants;

public class TheChase {

	/**
	 * @param args
	 *            the command line arguments
	 */
	
	public static void main(String[] args) {
		
		/*
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (Exception e) {
		    // If Nimbus is not available, you can set the GUI to another look and feel.
		}
		*/
		
		JFrame mainWindow = new JFrame();
		SoundManager sm = new SoundManager("bin/sound/Thunderbird.mp3");
		sm.loop();
		mainWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		//mainWindow.setSize(700, 700);
		mainWindow.setVisible(true);
		GameController gameController = new GameController(mainWindow);
		gameController.initialiseMenu();
		mainWindow.pack();
//		TempUI temp = new TempUI();
	}
}
