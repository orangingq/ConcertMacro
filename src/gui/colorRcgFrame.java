package gui;

import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.MouseInfo;
import java.awt.PointerInfo;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import MainSrc.FullScreenCapture;
import MainSrc.dataType;
import MainSrc.point;

public class colorRcgFrame extends JFrame {
	Container cPane;
	JLabel lbl;
	static JPanel colorBox;
	JPanel capturedImageBox;
	JPanel layoutBox;
	JButton clrChgBtn;
	JButton clrsvBtn;
	JButton rangeChgBtn;
	JButton rangesvBtn;
	JLabel rangeLbl;
	FullScreenCapture screencapture;
	dataType data;
	Color pickedColor;
	
	public colorRcgFrame() throws AWTException{
		setSize(500, 500);
		setTitle("ConcertMacro_click by color");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		cPane = getContentPane();
		cPane.setLayout(new BorderLayout());
		lbl = new JLabel("범위 내 색인식 추가");
		cPane.add(lbl, "North");
		colorBox = new JPanel();
		layoutBox = new JPanel(new GridLayout(2,3));
		clrChgBtn = new JButton("색 지정"); //색 지정 버튼 설정
		clrChgBtn.addActionListener(new clrChgBtnListener());
		rangeChgBtn = new JButton("범위 지정"); //범위 지정 버튼 설정
		rangeChgBtn.addActionListener(new rangeChgBtnListener());
		clrsvBtn = new JButton("색 저장"); //색 저장 버튼 설정. 
		clrsvBtn.setEnabled(false); //지정버튼 누르기 전에는 비활성화
		clrsvBtn.addActionListener(new clrsvBtnListener());
		rangesvBtn = new JButton("범위 저장");
		rangesvBtn.setEnabled(false);
		layoutBox.add(clrChgBtn);
		layoutBox.add(clrsvBtn);
		layoutBox.add(colorBox);
		layoutBox.add(rangeChgBtn);
		layoutBox.add(rangesvBtn);
	//	layoutBox.add(rangeLbl);
		cPane.add(layoutBox, "Center");
		setVisible(true);
		data = new dataType();
		
		
	}
	
	public void openImageFile() throws FileNotFoundException {
		JFrame IFrame = new JFrame("캡처 화면-색을 선택하세요.");
		ImageIcon image = new ImageIcon(screencapture.getFilePath());
		JLabel lbl = new JLabel(image);
		IFrame.add(lbl);
		IFrame.pack();
		IFrame.setVisible(true);
		IFrame.setDefaultCloseOperation(IFrame.DISPOSE_ON_CLOSE);;
		System.out.println("file is opened.");
		lbl.addMouseListener(new colorPickListener());
	}
	
	public class clrChgBtnListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e){
			clrsvBtn.setEnabled(true);
			clrChgBtn.setEnabled(false);
			System.out.println("mouse is clicked.");
			try {
				screencapture = new FullScreenCapture();
				openImageFile();
				colorBox.setBackground(mousePointingColor());
				System.out.println("color is sent");
			} catch (AWTException | FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}
	
	class clrsvBtnListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			clrsvBtn.setEnabled(false);
			clrChgBtn.setEnabled(true);		
			//data.setColor(pickedColor);
		}
	}
	
	class rangeChgBtnListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			rangesvBtn.setEnabled(true);
			rangeChgBtn.setEnabled(false);
			System.out.println("mouse is clicked.");
			addMouseListener(new MouseAdapter() {
				point p1;
				point[] p2;
				@Override
				public void mousePressed(MouseEvent m) {
					p2[0] = new point(m.getX(), m.getY());					
				}
				@Override
				public void mouseReleased(MouseEvent m) {
					p2[1] = new point(m.getX(), m.getY());
					rangeLbl.setText(p2.toString());
				}
				@Override
				public void mouseClicked(MouseEvent m) {
					p1 = new point(m.getX(), m.getY());
					rangeLbl.setText(p1.toString());
				}
			});
		}
	}	

	class colorPickListener implements MouseListener {
		@Override
		public void mouseClicked(MouseEvent e) {
			try {
				colorBox.setBackground(mousePointingColor());
			} catch (AWTException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
	
	protected static Color mousePointingColor() throws AWTException{
		Robot robot = new Robot();
		PointerInfo a = MouseInfo.getPointerInfo();
		point b = new point(a.getLocation());
		colorBox.setBackground(robot.getPixelColor(b.getIntX(), b.getIntY()));
		Color rgb = robot.getPixelColor(b.getIntX(), b.getIntY());
		return rgb;
	}
	
	public static void main(String args[]) throws AWTException {
		colorRcgFrame c = new colorRcgFrame();
		c.setEnabled(true);
	}
}
