package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;

public class timeSetEventHandler implements ActionListener{
	public void actionPerformed(ActionEvent e) {
		try {
			Thread.sleep((new mainGUI()).timeUntil());
		}catch(InterruptedException e2) {
			e2.printStackTrace();
		}
		//TODO 매크로 실행시키는 거 구현!!!
		
	}
}
