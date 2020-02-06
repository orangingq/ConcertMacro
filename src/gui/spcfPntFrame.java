package gui;

import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class spcfPntFrame extends JFrame{
	public spcfPntFrame() {
		setSize(500, 500);
		setTitle("ConcertMacro_click by point");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		Container cPane = getContentPane();
		cPane.setLayout(new FlowLayout());
		JLabel lbl = new JLabel("지정 위치 추가");
		
		cPane.add(lbl);
		
		
	}
}
