package MainSrc;

import java.awt.Color;

public class dataType {
	public static final String USER = "user"; //for boolean user

	private boolean clrOrPnt;
	/*color:There are two types of selecting data.
	 *  	One is selecting color and range of exploration,
	 *  	the other is selecting a point without color info.
	 */
	private Color color;
	/*range:can be a point(point[1]) or range of points(point[2]).
	 * 		In the case of range, first point is up-left side point of the rectangle range,
	 * 		and the second point is down-right side point of the range. 
	 */
	private point[] range;
	private point point;
	private boolean user; // user type: when the computer wait until user clicks somewhere once.
	
	public dataType() {
		clrOrPnt = true;
		color = null;
		range = null;
		point = null;
		user = false;
	};
	public dataType(String str) {
		if(str.contentEquals(USER)){
			clrOrPnt = true;
			color = null;
			range = null;
			point = null;
			user = true;
		}
	}
	public dataType(Color color, point[] range) {
		this.color = color;
		this.range = range;
		this.point = null;
		clrOrPnt = true;
		user = false;
	}
	public dataType(point point) {
		clrOrPnt = false;
		this.color = null;
		this.range = null;
		this.point = point;
		user = false;
	}
	
	public void setClrOrPnt(boolean isclr) {
		clrOrPnt = isclr;
	}
	
	public void setColor(Color color) {
		if(user == false && clrOrPnt == true) this.color = color;
	}
	
	public void setRange(point[] range) {
		if(user == false && clrOrPnt == true) this.range = range;
	}
	
	public void setPoint(point point) {
		if(user == false && clrOrPnt == false) this.point = point;
	}
	
	public boolean isUser() {return user;}
	public boolean getClrOrPnt() {return clrOrPnt;}
	public Color getColor() {
		if(user == true || clrOrPnt == false) throw new IllegalStateException();
		return color;
	}
	
	public point[] getRange() {
		if(user == true || clrOrPnt == false) throw new IllegalStateException();
		return range;
	}
	
	public point getPoint() {
		if(user == true || clrOrPnt == true) throw new IllegalStateException();
		return point;
	}
	
	public String toString() {
		String str;
		if(user) str = "사용자 클릭을 위한 1.5초 대기";
		else if(clrOrPnt == true) 
			str = "[r="+ color.getRed()+",g="+color.getGreen()+",b="+color.getBlue()+"]\t " + range[0].toString() + "~" + range[1].toString() + "\t x";
		else
			str = "x\t x\t " + point.toString(); 
		return str;
	}
	
}
