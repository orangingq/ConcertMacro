package gui;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;

import MainSrc.alertDialog;
import MainSrc.dataType;
import MainSrc.point;

public class timeSetEventHandler implements ActionListener{
	mainGUI main;
	dataList dataList;
	Robot robot;
	
	public timeSetEventHandler(mainGUI main) {
		this.main = main;
		dataList = main.getDataList();
		try {
			robot = new Robot();
		} catch (AWTException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		if (main.timeUntil() < 0) {
			alertDialog alert = new alertDialog("실행시간 설정 오류");
			alert.setVisible(true);
		}
		else {
			try {
				Thread.sleep(main.timeUntil());
			}catch(InterruptedException e2) {
				e2.printStackTrace();
			}

			robot.mouseMove(1167, 0);
			robot.mousePress(InputEvent.BUTTON1_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_MASK);
			robot.mousePress(InputEvent.BUTTON1_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_MASK);
			
			for(int i=0; i<dataList.getSize(); i++) {
				dataType data = dataList.getDataAt(i);
					if(data.getClrOrPnt()) {
						point[] P = new point[2];
						P = data.getRange();
						for(int x=P[0].getIntX(); x<P[1].getIntX(); x++)
							for(int y=P[0].getIntY(); y<P[1].getIntY(); y++) {
								if(robot.getPixelColor(x, y)!=data.getColor()) continue;
								robot.mouseMove(x, y);
								robot.mousePress(InputEvent.BUTTON1_MASK);
								robot.mouseRelease(InputEvent.BUTTON1_MASK);
							}
					}
					else {
						robot.mouseMove(data.getPoint().getIntX(), data.getPoint().getIntX());
						robot.mousePress(InputEvent.BUTTON1_MASK);
						robot.mouseRelease(InputEvent.BUTTON1_MASK);
					}
				
				
			}
		}
		
		
	}
	
}
