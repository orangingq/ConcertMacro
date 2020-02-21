package gui;

import java.util.Iterator;
import java.util.LinkedList;

import MainSrc.alertDialog;
import MainSrc.dataType;

public class dataList implements Iterable {
	private int size;
	private LinkedList<dataType> list = new LinkedList<dataType>();;
	
	public dataList() {
		size = 0;
	}
	
	public dataList(LinkedList<dataType> list) {
		this.list = (LinkedList<dataType>) list.clone();
		size = list.size();
	}
	
	public void add(dataType data) {
		list.add(data);
		size ++;
	}
	
	public void addAll(dataList dL) {
		for(dataType dT:dL.getList()) list.add(dT);
		
	}
	
	public void swap(int a, int b) {
		if(a==b) System.out.println(a+"와 "+b+"가 같습니다. dataList 내 swap 불가");
		else if(size<a || size<b || a<0 || b<0) System.out.println("dataList size 안 맞음.. swap 불가");
		else {
			LinkedList<dataType> tmp = new LinkedList<dataType>();
			for(int i=0; i<size; i++) {
				if(i==a) tmp.add(list.get(b));
				else if(i==b) tmp.add(list.get(a));
				else tmp.add(list.get(i));
			}
			list = (LinkedList<dataType>) tmp.clone();
			System.out.println(this.toString());
		}
	}
	
	public void remove(int index) {
		if(size > index && index >= 0) list.remove(index);
	}
	
	public int getSize() {return size;}
	
	public dataType getDataAt(int i) {return list.get(i);}
	
	public LinkedList<dataType> getList() { return list; }

	@Override
	public Iterator iterator() {
		return list.iterator();
	}
	
	@Override
	public String toString() {
		String str = "";
		for(int i=0; i<size; i++)
			str = str + list.get(i).toString() + "\n";
		return str;
	}
	
	public String[] strDataList() {
		String[] strDataList = new String[size+2];
		for(int i=0; i<size; i++)
			strDataList[i] = list.get(i).toString();
		return strDataList;
	}
	
	public dataList clone() {
		dataList dL = new dataList(list);
		return dL;
	}

}
