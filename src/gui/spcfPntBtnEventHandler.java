package gui;

import java.awt.AWTException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class spcfPntBtnEventHandler implements ActionListener{
	public void actionPerformed(ActionEvent e) {
		try {
			(new colorRcgFrame()).setVisible(true);
		} catch (AWTException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
