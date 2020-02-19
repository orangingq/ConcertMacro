package gui;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

import MainSrc.dataType;

public class dataList implements Iterable {
	private int size;
	private LinkedList<dataType> list = new LinkedList<dataType>();;
	
	public dataList() {
		size = 0;
	}
	
	public void add(dataType data) {
		list.add(data);
		size ++;
	}
	
	public void addAll(dataList dL) {
		for(dataType dT:dL.getList()) list.add(dT);
		
	}
	
	public void remove(int index) {
		if(size > index && index >= 0) list.remove(index);
	}
	
	public int getSize() {return size;}
	
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

}
