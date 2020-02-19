package gui;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import MainSrc.dataType;

public class mainGUI extends JFrame{
	//FIELDS
	private JButton delListBtn;
	private JButton colorRcgBtn;
	private JButton spcfPntBtn;
	private static JList<String> list;
	private static DefaultListModel<String> model = new DefaultListModel<>();
	private static JScrollPane scroll;
	private static dataList dataList = new dataList();
	private Container cPane;
	
	public mainGUI() {
		setSize(500, 500);
		setTitle("ConcertMacro");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		cPane = getContentPane();
		cPane.setLayout(new FlowLayout());
		delListBtn = new JButton("전체 리스트 삭제");
		delListBtn.addActionListener(new delListBtnEventHandler());
		colorRcgBtn = new JButton("범위 내 색인식 추가");
		colorRcgBtn.addActionListener(new colorRcgBtnEventHandler());
		spcfPntBtn = new JButton("지정 위치 추가");
		spcfPntBtn.addActionListener(new spcfPntBtnEventHandler());
		
		cPane.add(delListBtn);
		cPane.add(colorRcgBtn);
		cPane.add(spcfPntBtn);
		
		list = new JList(model);
		list.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		scroll = new JScrollPane(list);
		scroll.setPreferredSize(new Dimension(300, 300));
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setVisibleRowCount(10);
		cPane.add(scroll);
		list.addListSelectionListener((ListSelectionListener) new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if(e.getValueIsAdjusting() == false) {
					switch(list.getSelectedIndex()) {
					case 0: 
						///////////////////////////
						break;
					case 1:
						//////////////////////////
						break;
					default:
						//////////////////
						break;
					}
				}
				else {
					System.out.println("I think the value is adjusting");
				}
			}
		});
	}
	
//	public static void addAllDataList(dataList dL){
//		dataList.addAll(dL);
//		strDataList = dataList.strDataList();
//		list = new JList(strDataList);
//		System.out.println("add Data on strDataList : " + strDataList.toString());
//	}
	public static void addData(dataType data) {
		dataList.add(data);
		model.addElement(data.toString());
		System.out.println("added Data on model." + model.toString());
	}
}
