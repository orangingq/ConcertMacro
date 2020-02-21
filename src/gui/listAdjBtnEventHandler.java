package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class listAdjBtnEventHandler implements ActionListener{
	public void actionPerformed(ActionEvent e) {
		(new listAdjFrame()).setVisible(true);
	}
}
