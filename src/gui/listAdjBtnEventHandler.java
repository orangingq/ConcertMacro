package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class listAdjBtnEventHandler implements ActionListener{
	private mainGUI main;
	public listAdjBtnEventHandler(mainGUI main) {
		this.main = main;
	}
	
	public void actionPerformed(ActionEvent e) {
		(new listAdjFrame(main)).setVisible(true);
	}
}
