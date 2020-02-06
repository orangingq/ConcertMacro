package MainSrc;

public class Point {
	int a;
	int b;
	public Point() {
	}
	public Point(int a, int b) {
		this.a = a;
		this.b = b;
	}
	public int getX() {
		return a;
	}
	public int getY() {
		return b;
	}
	
	public String toString() {
		String str = "("+a+", "+b+")";
		return str;
	}
}
