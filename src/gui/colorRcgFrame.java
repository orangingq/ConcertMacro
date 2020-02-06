package gui;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class colorRcgFrame extends JFrame{
	public colorRcgFrame() {
		setSize(500, 500);
		setTitle("ConcertMacro_click by color");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		Container cPane = getContentPane();
		cPane.setLayout(new FlowLayout());
		JLabel lbl = new JLabel("범위 내 색인식 추가");
		cPane.add(lbl);
		
		
	}
	
	private Color ScreenColor(int x, int y) {
		Size sz = new Size(1,1);
		Bitmap bmp = new Bitmap(1,1);
		Graphics g = Graphics.FromImage(bmp);
		g
	}
}
