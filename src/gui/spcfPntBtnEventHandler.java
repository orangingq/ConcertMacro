package gui;

import java.awt.AWTException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class spcfPntBtnEventHandler implements ActionListener{
	public void actionPerformed(ActionEvent e) {
		(new spcfPntFrame()).setVisible(true);
	}
}
