package obj;

public class Vector2 {
	private double x, y;
	
	public Vector2() {
		setVector(0, 0);
	}
	
	public Vector2(double x, double y) {
		setVector(x, y);
	}
	
	public void setVector(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public double getX() {
		return this.x;
	}
	
	public double getY() {
		return this.y;
	}
	
	public static double distance(double x1, double y1, double x2, double y2) {
		return Math.sqrt(Math.pow((x1 - x2), 2) + Math.pow((y1 - y2), 2));
	}
}
