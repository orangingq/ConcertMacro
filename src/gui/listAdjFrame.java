package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Iterator;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionListener;

import MainSrc.alertDialog;
import MainSrc.dataType;

public class listAdjFrame extends JFrame{
	private JButton delListBtn;
	private JLabel listLbl;
	private JButton delBtn;
	private JButton switchBtn;
	private JButton saveBtn;
	private Container cPane;
	private JList<String> list;
	private static DefaultListModel<String> model = new DefaultListModel<>();
	private static JScrollPane scroll;
	private static dataList dataList = new dataList();
	private static Iterator<dataType> itr;
	private JPanel southLayoutBox;
	private JPanel northLayoutBox;
	private boolean delBtnClicked = false;
	private boolean switchBtnClicked = false;
	private ListSelectionListener listSelecListener;
	
	public listAdjFrame() {
		setSize(500, 500);
		setTitle("ConcertMacro_list Adjustment");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		cPane = getContentPane();
		cPane.setLayout(new BorderLayout());
		northLayoutBox = new JPanel();
		listLbl = new JLabel("����Ʈ ���\n*���� Ŭ�� �� CtrlŰ ��������."
				+ "\n*������ �� ��ȯ �� CtrlŰ�� ������ �� ���� Ŭ���ϼ���.");
		northLayoutBox.add(listLbl);
		cPane.add(northLayoutBox, "North");
		delListBtn = new JButton("��ü ����Ʈ ����");
		northLayoutBox.add(delListBtn);
		list = new JList(model);
		list.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		scroll = new JScrollPane(list);
		scroll.setPreferredSize(new Dimension(300, 300));
		list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		list.setVisibleRowCount(10);
		//listSelecListener = new listSelectionListener();
		//list.addListSelectionListener(listSelecListener);
		cPane.add(scroll, "Center");
		delBtn = new JButton("����");
		delBtn.addActionListener(new delBtnlistener());
		switchBtn = new JButton("��ȯ");
		switchBtn.addActionListener(new switchBtnlistener());
		saveBtn = new JButton("����");
		saveBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				saveBtn.setEnabled(false);
				(new mainGUI()).setDataList(dataList);
				dispose();
			}
		});
		southLayoutBox = new JPanel(new FlowLayout());
		southLayoutBox.add(delBtn);
		southLayoutBox.add(switchBtn);
		southLayoutBox.add(saveBtn);
		cPane.add(southLayoutBox, "South");
		dataList = (new mainGUI()).getDataList();
		itr = dataList.iterator();
		model.clear();
		while(itr.hasNext()) model.addElement(itr.next().toString());
	}
	
	private class delBtnlistener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			int[] indices = list.getSelectedIndices();
			for(int i: indices) {
				dataList.remove(i);
				model.remove(i);
			}
		}
	}
	
	private class switchBtnlistener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			int[] indices = list.getSelectedIndices();
			if(indices.length != 2) new alertDialog("���� ������ �ٲ� �� ���� �����͸� �������ּ���.");
			else {
				dataList.swap(indices[0], indices[1]);
				model.setElementAt(dataList.getDataAt(indices[0]).toString(), indices[0]);
				model.setElementAt(dataList.getDataAt(indices[1]).toString(), indices[1]);
				System.out.println(model.toString());
			}
		}
	}
	
//	private class listSelectionListener implements ListSelectionListener {
//		public void valueChanged(ListSelectionEvent e) {
//			if(!e.getValueIsAdjusting()) {
//				e.
//			}
//		}
//	}
	
}