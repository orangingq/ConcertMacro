package gui;

import java.awt.AWTException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class colorRcgBtnEventHandler implements ActionListener{
	private mainGUI main;
	public colorRcgBtnEventHandler(mainGUI main) { this.main = main;}
	
	public void actionPerformed(ActionEvent e) {
		try {
			(new colorRcgFrame(main)).setVisible(true);
		} catch (AWTException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
}
