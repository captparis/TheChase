package main;

import java.awt.event.*;

import javax.swing.*;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import controllers.GameController;
import memento.*;
import models.Game;

public class TempUI {

	public TempUI() {
		JFrame tempWindow = new JFrame();
		tempWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		tempWindow.setSize(200, 100);
		tempWindow.setVisible(true);
		// tempWindow.pack();
		JPanel panel = new JPanel();
		tempWindow.add(panel);
		JButton button = new JButton("Save");
		panel.add(button);
		button.addActionListener(new Save());

		JButton button2 = new JButton("Load");
		panel.add(button2);
		button.addActionListener(new Load());
	}

}

class Save implements ActionListener {
	public void actionPerformed(ActionEvent e) {
		Game game = null;
		GameMemento memento = game.createMemento();
        Caretaker ct = new Caretaker();
        ct.setMemento(memento);
	}
}

class Load implements ActionListener {
	public void actionPerformed(ActionEvent e) {
		Game game = null;
		Caretaker ct = new Caretaker();
		game.restore(ct.getMemento());
	}
}
