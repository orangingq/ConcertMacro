package MainSrc;
import java.awt.Point;

public class point extends java.awt.Point{
	private double a;
	private double b;
	public point() {
	}
	public point(int a, int b) {
		this.a = a;
		this.b = b;
	}
	
	public point(Point p) {
		a = p.x;
		b = p.y;
	}
	
	public void setX(double x) {a = x;}
	public void setY(double y) {b = y;}
	public void setX(int x ) {a = x;}
	public void setY(int y) {b = y;}
	
	@Override
	public double getX() {return a;}
	@Override
	public double getY() {return b;}
	
	public int getIntX() { return (int) a;}
	public int getIntY() { return (int) b;}
	
	@Override
	public String toString() {
		String str = "("+a+", "+b+")";
		return str;
	}
}
