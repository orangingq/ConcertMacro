package gui;

import java.awt.AWTException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class spcfPntBtnEventHandler implements ActionListener{
	private mainGUI main;
	public spcfPntBtnEventHandler(mainGUI main) {
		this.main = main;
	}
	
	public void actionPerformed(ActionEvent e) {
		(new spcfPntFrame(main)).setVisible(true);
	}
}
