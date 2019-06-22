package obj;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Plate extends Rectangle implements IHittable, IMovable{
	
	// Constant
	public static enum WIDTH{
		SHORT(60.0),
		NORMAL(120.0),
		LONG(240.0),
		EXTREME(360.0);
		
		private double width;
		
		private WIDTH(double width) {
			this.width = width;
		}
		
		public double getWidth() {
			return this.width;
		}
	}
	public static final double HEIGHT = 20;
	
	// Field
	private WIDTH widthState;
	private double leftEdge, rightEdge, topEdge, bottomEdge;
		
	// Constructor
	public Plate() {
		setFill(Color.AQUA);
		setSize(WIDTH.NORMAL, HEIGHT);
	}
	
	public Plate(double originX, double originY) {
		setFill(Color.AQUA);
		setSize(WIDTH.NORMAL, HEIGHT);
		setPosition(originX, originY);
	}
	
	// Getters and Setters
	public void setPosition(double originX, double originY) {
		setX(originX);
		setY(originY);
		leftEdge	= getX();
		rightEdge	= getX() + getWidth();
		topEdge		= getY();
		bottomEdge	= getY() + Plate.HEIGHT;
	}
	
	public void setSize(WIDTH width, double height) {
		setWidthState(width);
		setHeight(height);
		leftEdge	= getX();
		rightEdge	= getX() + width.getWidth();
		topEdge		= getY();
		bottomEdge	= getY() + height;
	}
	
	public double getLeftEdge() {
		return leftEdge;
	}
	
	public double getRightEdge() {
		return rightEdge;
	}
	
	public double getTopEdge() {
		return topEdge;
	}
	
	public double getBottomEdge() {
		return bottomEdge;
	}
	
	public void setWidthState(WIDTH widthState) {
		this.widthState = widthState;
		setWidth(widthState.getWidth());
	}
	
	public WIDTH getWidthState() {
		return this.widthState;
	}

	@Override
	public void hitBy(IHitter hitter) {

	}

	@Override
	public void move() {
		
	}
	
}
