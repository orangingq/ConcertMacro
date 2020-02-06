package MainSrc;

import java.awt.Color;

public class dataType {
	private boolean clrOrPnt;
	private Color color;
	private Point[] range;
	private Point point;
	
	public dataType() {};
	public dataType(Color color, Point[] range) {
		this.color = color;
		this.range = range;
		clrOrPnt = true;
	}
	public dataType(Point point) {
		clrOrPnt = false;
		this.point = point;
	}
	
	public boolean getClrOrPnt() {return clrOrPnt;}
	public Color getColor() {
		if(clrOrPnt == false) throw new IllegalStateException();
		return color;
	}
	
	public Point[] getRange() {
		if(clrOrPnt == false) throw new IllegalStateException();
		return range;
	}
	
	public Point getPoint() {
		if(clrOrPnt == true) throw new IllegalStateException();
		return point;
	}
	
	public String toString() {
		String str;
		if(clrOrPnt == true) 
			str = color.toString() + "\t " + range[0].toString() + "~" + range[1].toString() + "\t x";
		else
			str = "x\t x\t " + point.toString(); 
		return str;
	}
	
}
