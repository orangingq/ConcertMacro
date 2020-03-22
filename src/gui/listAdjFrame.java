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
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionListener;

import MainSrc.alertDialog;
import MainSrc.dataType;

public class listAdjFrame extends JFrame{
	private JButton delListBtn;
	private JTextPane listLbl;
	private JButton delBtn;
	private JButton switchBtn;
	private JButton saveBtn;
	private Container cPane;
	private JList<String> list;
	private DefaultListModel<String> model = new DefaultListModel<>();
	private JScrollPane scroll;
	private dataList dataList = new dataList();
	private Iterator<dataType> itr;
	private JPanel southLayoutBox;
	private JPanel northLayoutBox;
	private boolean delBtnClicked = false;
	private boolean switchBtnClicked = false;
	private ListSelectionListener listSelecListener;
	
	public listAdjFrame(mainGUI main) {
		setSize(500, 500);
		setTitle("ConcertMacro_list Adjustment");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		cPane = getContentPane();
		cPane.setLayout(new BorderLayout());
		northLayoutBox = new JPanel();
		listLbl = new JTextPane();
		listLbl.setText("<����Ʈ ���>\n*���� Ŭ�� �� CtrlŰ ��������."
				+ "\n*������ �� ��ȯ �� CtrlŰ�� ������ �� ���� Ŭ���ϼ���.");
		listLbl.setEditable(false);
		listLbl.setBackground(cPane.getBackground());
		northLayoutBox.add(listLbl);
		northLayoutBox.setAlignmentX(CENTER_ALIGNMENT);
		northLayoutBox.setAlignmentY(CENTER_ALIGNMENT);
		cPane.add(northLayoutBox, "North");
		list = new JList(model);
		list.setBorder(BorderFactory.createLineBorder(cPane.getBackground(), 10));
		scroll = new JScrollPane(list);
		scroll.setPreferredSize(new Dimension(300, 300));
		list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		list.setVisibleRowCount(10);
		cPane.add(scroll, "Center");
		delListBtn = new JButton("��ü ����Ʈ ����");
		delListBtn.addActionListener(new delListBtnlistener());
		delBtn = new JButton("����");
		delBtn.addActionListener(new delBtnlistener());
		switchBtn = new JButton("��ȯ");
		switchBtn.addActionListener(new switchBtnlistener());
		saveBtn = new JButton("����");
		saveBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				saveBtn.setEnabled(false);
				main.setDataList(dataList);
				dispose();
			}
		});
		southLayoutBox = new JPanel(new FlowLayout());
		southLayoutBox.add(delListBtn);
		southLayoutBox.add(delBtn);
		southLayoutBox.add(switchBtn);
		southLayoutBox.add(saveBtn);
		cPane.add(southLayoutBox, "South");
		dataList = main.getDataList();
		itr = dataList.iterator();
		model.clear();
		while(itr.hasNext()) model.addElement(itr.next().toString());
	}
	
	private class delListBtnlistener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			for(int i=dataList.getSize()-1; i>=0; i--) {
				dataList.remove(i);
				model.remove(i);
			}
		}
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
	
}
