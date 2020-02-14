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
	JButton saveBtn;
	FullScreenCapture screencapture;
	dataType data;
	Color pickedColor;
	point[] p;
	
	public colorRcgFrame() throws AWTException{
		setSize(500, 500);
		setTitle("ConcertMacro_click by color");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		cPane = getContentPane();
		cPane.setLayout(new BorderLayout());
		lbl = new JLabel("범위 내 색인식 추가");
		cPane.add(lbl, "North");
		colorBox = new JPanel();
		layoutBox = new JPanel(new GridLayout(3,3));
		clrChgBtn = new JButton("색 지정"); //색 지정 버튼 설정
		clrChgBtn.addActionListener(new clrChgBtnListener());
		rangeChgBtn = new JButton("범위 지정"); //범위 지정 버튼 설정
		rangeChgBtn.addActionListener(new rangeChgBtnListener());
		clrsvBtn = new JButton("색 저장"); //색 저장 버튼 설정. 
		clrsvBtn.setEnabled(false); //지정버튼 누르기 전에는 비활성화
		clrsvBtn.addActionListener(new clrsvBtnListener());
		rangesvBtn = new JButton("범위 저장");
		rangesvBtn.setEnabled(false);
		rangesvBtn.addActionListener(new rangesvBtnListener());
		rangeLbl = new JLabel();
		saveBtn = new JButton("저장하기");
		saveBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//TODO data가 다 형성되지 않은 채 버튼이 눌렸을 경우 알림창 띄우기 
				saveBtn.setEnabled(false);
				(new mainGUI()).addOnDataList(data);
				//TODO mainGUI에 data를 전달해서 리스트에 띄우기 & 리스트 보여주기
			}
		});
		if(!saveBtn.isEnabled()) {
			System.out.println("saveButton is clicked");
			System.exit(0);
		}
		/*layoutBox에 추가*/
		layoutBox.add(clrChgBtn);
		layoutBox.add(clrsvBtn);
		layoutBox.add(colorBox);
		layoutBox.add(rangeChgBtn);
		layoutBox.add(rangesvBtn);
		layoutBox.add(rangeLbl);
		layoutBox.add(saveBtn);
		cPane.add(layoutBox, "Center");
		setVisible(true);
		data = new dataType();
		screencapture = new FullScreenCapture();
		
	}
	
	public void openImageFile() throws FileNotFoundException {
		JFrame IFrame = new JFrame("캡처 화면-색을 선택하세요.");
		ImageIcon image = new ImageIcon(screencapture.getFilePath());
		JLabel lbl = new JLabel(image);
		IFrame.add(lbl);
		IFrame.pack();
		IFrame.setVisible(true);
		IFrame.setDefaultCloseOperation(IFrame.DISPOSE_ON_CLOSE);;
		System.out.println("file is opened.. path: "+ screencapture.getFilePath());
		lbl.addMouseListener(new pickListener());
	}
	
	private class clrChgBtnListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e){
			clrsvBtn.setEnabled(true);
			clrChgBtn.setEnabled(false);
			System.out.println("mouse is clicked..ColorChangeButton");
			try {
				openImageFile();
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
		}

	}
	
	private class clrsvBtnListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			clrsvBtn.setEnabled(false);
			clrChgBtn.setEnabled(true);		
			data.setColor(pickedColor);
			System.out.println("data..Color is sent. ");
		}
	}
	
	private class rangeChgBtnListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			rangesvBtn.setEnabled(true);
			rangeChgBtn.setEnabled(false);
			System.out.println("mouse is clicked..RangeChangeButton");
			try {
				openImageFile();
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
//			addMouseListener(new MouseAdapter() {
//				point p1;
//				point[] p2;
//				@Override
//				public void mousePressed(MouseEvent m) {
//					p2[0] = new point(m.getX(), m.getY());					
//				}
//				@Override
//				public void mouseReleased(MouseEvent m) {
//					p2[1] = new point(m.getX(), m.getY());
//					rangeLbl.setText(p2.toString());
//				}
//				@Override
//				public void mouseClicked(MouseEvent m) {
//					p1 = new point(m.getX(), m.getY());
//					rangeLbl.setText(p1.toString());
//				}
//			});
		}
	}	
	
	private class rangesvBtnListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			rangesvBtn.setEnabled(false);
			rangeChgBtn.setEnabled(true);
			data.setRange(p);
			System.out.println("data.. range is sent. p: " + p[0].toString());
		}
	}

	private class pickListener implements MouseListener {
		@Override
		public void mouseClicked(MouseEvent e) {
			if(clrsvBtn.isEnabled()) try {
				pickedColor = mousePointingColor();
				colorBox.setBackground(pickedColor);
			} catch (AWTException e1) {
				e1.printStackTrace();
			}
		}
		@Override
		public void mousePressed(MouseEvent e) {
			if(rangesvBtn.isEnabled()) {
				System.out.println("mousePressed");
				p = new point[2];
				p[0] = new point(e.getPoint());
			}
		}
		@Override
		public void mouseReleased(MouseEvent e) {
			if(rangesvBtn.isEnabled()) {
				System.out.println("mouseReleased");
				p[1] = new point(e.getPoint());
				rangeLbl.setText(p[0].toString()+" ~ " + p[1].toString()); 
			}
		}
		@Override
		public void mouseEntered(MouseEvent e) {}
		@Override
		public void mouseExited(MouseEvent e) {}
	}
	
	protected static Color mousePointingColor() throws AWTException{
		Robot robot = new Robot();
		PointerInfo a = MouseInfo.getPointerInfo();
		point b = new point(a.getLocation());
		return robot.getPixelColor(b.getIntX(), b.getIntY());
	}

//	public static void main(String args[]) throws AWTException {
//		colorRcgFrame c = new colorRcgFrame();
//		c.setEnabled(true);
//	}
}
