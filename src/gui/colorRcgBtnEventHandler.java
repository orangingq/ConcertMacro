package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class colorRcgBtnEventHandler implements ActionListener{
	public void actionPerformed(ActionEvent e) {
		(new colorRcgFrame()).setVisible(true);
		
	}
}
