package gui;
import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import MainSrc.alertDialog;
import MainSrc.dataType;
import MainSrc.point;

public class mainGUI extends JFrame{
	//FIELDS
	private JButton listAdjBtn;
	private JButton colorRcgBtn;
	private JButton spcfPntBtn;
	private JButton userBtn;
	private JList<String> list;
	private DefaultListModel<String> model = new DefaultListModel<>();
	private JScrollPane scroll;
	private dataList dataList = new dataList();
	private Container cPane;
	//public static JTextField monthTF;
	//public static JTextField dayTF;
	private JTextField hourTF;
	private JTextField minTF;
	private JTextField secondTF;
	//private JLabel monthLbl;
	//private JLabel dayLbl;
	private JLabel hourLbl;
	private JLabel minLbl;
	private JLabel secondLbl;
	private JButton runBtn;
	private JPanel btnLayout;
	private JPanel timeSetLayout;
	
	public mainGUI() {
		setSize(500, 500);
		setTitle("ConcertMacro");
		// 성혜야 사랑해~ 2020.07.08 대전복합 공차에서, 정솔
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		cPane = getContentPane();
		cPane.setLayout(new BorderLayout());
		listAdjBtn = new JButton("리스트 삭제/수정");
		listAdjBtn.addActionListener(new listAdjBtnEventHandler(this));
		colorRcgBtn = new JButton("범위 내 색인식 추가");
		colorRcgBtn.addActionListener(new colorRcgBtnEventHandler(this));
		spcfPntBtn = new JButton("지정 위치 추가");
		spcfPntBtn.addActionListener(new spcfPntBtnEventHandler(this));		
		userBtn = new JButton("사용자 클릭 대기 추가");
		userBtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
            	dataType userdt = new dataType(dataType.USER);
            	addData(userdt);
            }
            
        });
		
		btnLayout = new JPanel(new GridLayout(4,1));
		btnLayout.add(listAdjBtn);
		btnLayout.add(colorRcgBtn);
		btnLayout.add(spcfPntBtn);
		btnLayout.add(userBtn);
		cPane.add(btnLayout, "East");
		
		list = new JList(model);
		list.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		scroll = new JScrollPane(list);
		scroll.setPreferredSize(new Dimension(300, 300));
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setVisibleRowCount(10);
		cPane.add(scroll, "Center");
		
		//monthTF = new JTextField(3);
		//monthLbl = new JLabel("월 ");
		//dayTF = new JTextField(3);
		//dayLbl = new JLabel("일 ");		
		hourTF = new JTextField(3);
		hourLbl = new JLabel("시 ");
		minTF = new JTextField(3);
		minLbl = new JLabel("분");
		secondTF = new JTextField(3);
		secondLbl = new JLabel("초 ");
		runBtn = new JButton("실행");
		runBtn.addActionListener(new timeSetEventHandler());
		timeSetLayout = new JPanel(new FlowLayout());
		//timeSetLayout.add(monthTF);
		//timeSetLayout.add(monthLbl);
		//timeSetLayout.add(dayTF);
		//timeSetLayout.add(dayLbl);
		timeSetLayout.add(hourTF);
		timeSetLayout.add(hourLbl);
		timeSetLayout.add(minTF);
		timeSetLayout.add(minLbl);
		timeSetLayout.add(secondTF);
		timeSetLayout.add(secondLbl);
		timeSetLayout.add(runBtn);
		cPane.add(timeSetLayout, "South");
	}
	
	public void addData(dataType data) {
		dataList.add(data);
		model.addElement(data.toString());
		System.out.println("added Data on model." + model.toString());
	}
	
	public dataList getDataList() {
		return dataList.clone();
	}
	
	public void setDataList(dataList dL) {
		dataList = dL.clone();
		model.clear();
		for(int i=0; i<dataList.getSize(); i++) {
			model.addElement(dataList.getDataAt(i).toString());
		}
	}
	
	private class timeSetEventHandler implements ActionListener{
		private Robot robot;
		private int hour;
		private int min;
		private int second;
		
		public void actionPerformed(ActionEvent e) {
			try {
				robot = new Robot();
			} catch (AWTException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			hour = Integer.parseInt(hourTF.getText());
			min = Integer.parseInt(minTF.getText());
			second = Integer.parseInt(secondTF.getText());
			
			timeUntil TU = new timeUntil(hour, min, second);
			if (TU.calculation() < 0) {
				alertDialog alert = new alertDialog("실행시간 설정 오류");
				alert.setVisible(true);
			}
			else {
				try {
					Thread.sleep(TU.calculation());
				}catch(InterruptedException e2) {
					e2.printStackTrace();
				}
				
				System.out.println("wake up!!");
				
				for(int i=0; i<dataList.getSize(); i++) {
					System.out.println("dataList: data_"+i);
					dataType data = dataList.getDataAt(i);
					System.out.println(i+": " + data.toString() + " "+ data.getClrOrPnt());
						if(data.isUser())
							try {
								Thread.sleep(1500);
							} catch (InterruptedException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						else if(data.getClrOrPnt()) {
							point[] P = new point[2];
							P = data.getRange();
							Color color = data.getColor();
							System.out.println("range: " + P[0].toString() + " color: " + color);
							for(int y=P[0].getIntY(); y<P[1].getIntY(); y+=5)
								for(int x=P[0].getIntX(); x<P[1].getIntX(); x+=5) {
									Color c = robot.getPixelColor(x, y);
									System.out.println("X,Y: " +x +","+ y + " color: "+c);
									if(color.getRed()-c.getRed()>5 || color.getRed()-c.getRed()<-5) continue;
									if(color.getGreen()-c.getGreen()>5 || color.getGreen()-c.getGreen()<-5) continue;
									if(color.getBlue()-c.getBlue()>5 || color.getBlue()-c.getBlue()<-5) continue;
									System.out.println("color_click mouse at :"+x+", " +y);
									robot.mouseMove(x, y);
									robot.mousePress(InputEvent.BUTTON1_MASK);
									robot.mouseRelease(InputEvent.BUTTON1_MASK);
									x=P[1].getIntX();
									y=P[1].getIntY(); //for 루프 끝내기. 한번 클릭하면 끝.
								}
						}
						else {
							System.out.println("point_click mouse at :"+data.getPoint().getIntX()+", " +data.getPoint().getIntY());
							robot.mouseMove(data.getPoint().getIntX(), data.getPoint().getIntY());
							robot.mousePress(InputEvent.BUTTON1_MASK);
							robot.mouseRelease(InputEvent.BUTTON1_MASK);
						}					
				}
			}			
		}
	}
}
