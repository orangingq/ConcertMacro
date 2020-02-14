package MainSrc;

import java.awt.Color;

public class dataType {
	private boolean clrOrPnt;
	/*color:There are two types of selecting data.
	 *  	One is selecting color and range of exploration,
	 *  	the other is selecting a point without color info.
	 */
	private Color color = null;
	/*range:can be a point(point[1]) or range of points(point[2]).
	 * 		In the case of range, first point is up-left side point of the rectangle range,
	 * 		and the second point is down-right side point of the range. 
	 */
	private point[] range = null;
	private point point = null;
	
	public dataType() {};
	public dataType(Color color, point[] range) {
		this.color = color;
		this.range = range;
		clrOrPnt = true;
	}
	public dataType(point point) {
		clrOrPnt = false;
		this.point = point;
	}
	
	public void setColor(Color color) {
		this.color = color;
		clrOrPnt = true;
	}
	
	public void setRange(point[] range) {
		this.range = range;
		clrOrPnt = true;
	}
	
	public void setPoint(point point) {
		this.point = point;
		clrOrPnt = false;
	}
	
	public boolean getClrOrPnt() {return clrOrPnt;}
	public Color getColor() {
		if(clrOrPnt == false) throw new IllegalStateException();
		return color;
	}
	
	public point[] getRange() {
		if(clrOrPnt == false) throw new IllegalStateException();
		return range;
	}
	
	public point getPoint() {
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
