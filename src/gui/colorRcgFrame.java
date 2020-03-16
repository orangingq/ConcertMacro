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

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import MainSrc.FullScreenCapture;
import MainSrc.alertDialog;
import MainSrc.dataType;
import MainSrc.openImageFile;
import MainSrc.point;

public class colorRcgFrame extends JFrame {
	private Container cPane;
	private JLabel lbl;
	private JPanel colorBox;
	private JPanel capturedImageBox;
	private JPanel layoutBox;
	private JButton clrChgBtn;
	private JButton clrsvBtn;
	private JButton rangeChgBtn;
	private JButton rangesvBtn;
	private JLabel rangeLbl;
	private JButton saveBtn;
	private FullScreenCapture screencapture;
	private dataType data;
	private Color pickedColor;
	private point[] p;
	private openImageFile OIF;
	
	public colorRcgFrame(mainGUI main) throws AWTException{
		setSize(500, 500);
		setTitle("ConcertMacro_click by color");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		cPane = getContentPane();
		cPane.setLayout(new BorderLayout());
		lbl = new JLabel("���� �� ���ν� �߰�");
		cPane.add(lbl, "North");
		colorBox = new JPanel();
		layoutBox = new JPanel(new GridLayout(3,3));
		clrChgBtn = new JButton("�� ����"); //�� ���� ��ư ����
		clrChgBtn.addActionListener(new clrChgBtnListener());
		rangeChgBtn = new JButton("���� ����"); //���� ���� ��ư ����
		rangeChgBtn.addActionListener(new rangeChgBtnListener());
		clrsvBtn = new JButton("�� ����"); //�� ���� ��ư ����. 
		clrsvBtn.setEnabled(false); //������ư ������ ������ ��Ȱ��ȭ
		clrsvBtn.addActionListener(new clrsvBtnListener());
		rangesvBtn = new JButton("���� ����");
		rangesvBtn.setEnabled(false);
		rangesvBtn.addActionListener(new rangesvBtnListener());
		rangeLbl = new JLabel();
		saveBtn = new JButton("�����ϱ�");
		saveBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(data.getColor()==null)
					(new alertDialog("���� �����ϼ���.")).setVisible(true);
				else if(data.getRange()==null)
					(new alertDialog("������ �����ϼ���.")).setVisible(true);
				else {
					saveBtn.setEnabled(false);
					main.addData(data);
					screencapture.getFile().delete();
					dispose();
				}
			}
		});
		/*layoutBox�� �߰�*/
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
		data.setClrOrPnt(true);
		screencapture = new FullScreenCapture();
		
	}
	
	private class clrChgBtnListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e){
			clrsvBtn.setEnabled(true);
			clrChgBtn.setEnabled(false);
			System.out.println("mouse is clicked..ColorChangeButton");
			try {
				OIF= new openImageFile(screencapture, (MouseListener)(new pickListener()));
				OIF.setFrameTxt("���� Ŭ���ϼ���.");
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
				OIF= new openImageFile(screencapture, (MouseListener)(new pickListener()));
				OIF.setFrameTxt("������ �巡���ϼ���.");
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
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
				if(p[0].getX()>p[1].getX()) {
					double tmp = p[0].getX();
					p[0].setX(p[1].getX());
					p[1].setX(tmp);
				}
				if(p[0].getY()>p[1].getY()) {
					double tmp = p[0].getY();
					p[0].setY(p[1].getY());
					p[1].setY(tmp);
				}
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
}
