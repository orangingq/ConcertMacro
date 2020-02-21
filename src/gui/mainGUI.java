package gui;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.Calendar;
import java.util.Date;

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

import MainSrc.dataType;

public class mainGUI extends JFrame{
	//FIELDS
	private JButton listAdjBtn;
	private JButton colorRcgBtn;
	private JButton spcfPntBtn;
	private static JList<String> list;
	private static DefaultListModel<String> model = new DefaultListModel<>();
	private static JScrollPane scroll;
	private static dataList dataList = new dataList();
	private Container cPane;
	public static JTextField hourTF;
	public static JTextField minTF;
	public static JTextField secondTF;
	private JLabel hourLbl;
	private JLabel minLbl;
	private JLabel secondLbl;
	private JButton runBtn;
	private JPanel timeSetLayout;
	
	public mainGUI() {
		setSize(500, 500);
		setTitle("ConcertMacro");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		cPane = getContentPane();
		cPane.setLayout(new FlowLayout());
		listAdjBtn = new JButton("리스트 삭제/수정");
		listAdjBtn.addActionListener(new listAdjBtnEventHandler());
		colorRcgBtn = new JButton("범위 내 색인식 추가");
		colorRcgBtn.addActionListener(new colorRcgBtnEventHandler());
		spcfPntBtn = new JButton("지정 위치 추가");
		spcfPntBtn.addActionListener(new spcfPntBtnEventHandler());		
		
		cPane.add(listAdjBtn);
		cPane.add(colorRcgBtn);
		cPane.add(spcfPntBtn);
		
		list = new JList(model);
		list.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		scroll = new JScrollPane(list);
		scroll.setPreferredSize(new Dimension(300, 300));
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setVisibleRowCount(10);
		cPane.add(scroll);
		
		hourTF = new JTextField(3);
		hourLbl = new JLabel("시 ");
		minTF = new JTextField(3);
		minLbl = new JLabel("분");
		secondTF = new JTextField(3);
		secondLbl = new JLabel("초 ");
		runBtn = new JButton("실행");
		timeSetLayout = new JPanel(new FlowLayout());
		timeSetLayout.add(hourTF);
		timeSetLayout.add(hourLbl);
		timeSetLayout.add(minTF);
		timeSetLayout.add(minLbl);
		timeSetLayout.add(secondTF);
		timeSetLayout.add(secondLbl);
		timeSetLayout.add(runBtn);
		cPane.add(timeSetLayout);
		runBtn.addActionListener(new timeSetEventHandler());
	}
	
	public static void addData(dataType data) {
		dataList.add(data);
		model.addElement(data.toString());
		System.out.println("added Data on model." + model.toString());
	}
	
	public static dataList getDataList() {
		return dataList.clone();
	}
	
	public static void setDataList(dataList dL) {
		dataList = dL.clone();
		model.clear();
		for(int i=0; i<dataList.getSize(); i++) {
			model.addElement(dataList.getDataAt(i).toString());
		}
	}
	
	public static long timeUntil() {
		Date now = new Date();
		Calendar setTime = Calendar.getInstance();
		setTime.set(Calendar.HOUR, Integer.parseInt(hourTF.getText()));
		setTime.set(Calendar.MINUTE, Integer.parseInt(minTF.getText()));
		setTime.set(Calendar.SECOND, Integer.parseInt(secondTF.getText()));
		setTime.set(Calendar.MILLISECOND, 0);
		Date until = setTime.getTime();
		long sleep = until.getTime() - now.getTime();
		return sleep;
	}
	

}
