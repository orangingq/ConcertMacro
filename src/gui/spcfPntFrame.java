package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
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

public class spcfPntFrame extends JFrame{
	private Container cPane;
	private JLabel lbl;
	private JButton pointChgBtn;
	private JButton pointsvBtn;
	private JLabel pointLbl;
	private JPanel capturedImageBox;
	private JPanel layoutBox;
	private JButton saveBtn;
	private FullScreenCapture screencapture;
	private dataType data;
	private Color pickedColor;
	private point p;
	private openImageFile OIF;
	private GridBagLayout gbl = new GridBagLayout();
	private GridBagConstraints[] gbc = new GridBagConstraints[4];
	
	public spcfPntFrame(mainGUI main) {
		setSize(500, 500);
		setTitle("ConcertMacro_click by point");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		cPane = getContentPane();
		cPane.setLayout(new BorderLayout());
		lbl = new JLabel("���� ��ġ �߰�");
		cPane.add(lbl, "North");
		layoutBox = new JPanel(new GridLayout(3,2));
		pointChgBtn = new JButton("��ġ ����"); //��ġ ���� ��ư ����
		pointChgBtn.addActionListener(new pointChgBtnListener());
		pointsvBtn = new JButton("��ġ ����");
		pointsvBtn.setEnabled(false);
		pointsvBtn.addActionListener(new pointsvBtnListener());
		pointLbl = new JLabel();
		saveBtn = new JButton("�����ϱ�");
		saveBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(data.getPoint()==null)
					(new alertDialog("��ġ�� �����ϼ���.")).setVisible(true);
				else {
					saveBtn.setEnabled(false);
					main.addData(data);
					screencapture.getFile().delete();
					dispose();
				}
			}
		});
		/*layoutBox�� �߰�*/
		layoutBox.add(pointChgBtn);
		layoutBox.add(pointsvBtn);
		layoutBox.add(pointLbl);
		layoutBox.add(saveBtn);
		cPane.add(layoutBox, "Center");
		setVisible(true);
		data = new dataType();
		data.setClrOrPnt(false);
		screencapture = new FullScreenCapture();
	}
	
	private class pointChgBtnListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			pointsvBtn.setEnabled(true);
			pointChgBtn.setEnabled(false);
			System.out.println("mouse is clicked..PointChangeButton");
			try {
				OIF= new openImageFile(screencapture, (MouseListener)(new pickListener()));
				OIF.setFrameTxt("��ġ Ŭ���ϼ���.");
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
		}
	}	
	
	private class pointsvBtnListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			pointsvBtn.setEnabled(false);
			pointChgBtn.setEnabled(true);
			data.setPoint(p);
			System.out.println("data.. point is sent. p: " + p.toString());
		}
	}
	
	private class pickListener implements MouseListener {
		@Override
		public void mouseClicked(MouseEvent e) {
			System.out.println("mouseClicked");
			p = new point(e.getPoint());
			pointLbl.setText(p.toString());
		}
		@Override
		public void mousePressed(MouseEvent e) {}
		@Override
		public void mouseReleased(MouseEvent e) {}
		@Override
		public void mouseEntered(MouseEvent e) {}
		@Override
		public void mouseExited(MouseEvent e) {}
	}
}
