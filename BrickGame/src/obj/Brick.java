package obj;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class Brick extends Rectangle implements IHittable{
	// Constant
	public static final int WIDTH	= 40;
	public static final int HEIGHT	= 40;
	public static final int GRID_SIZE = 40;
	public static final Image brickImg = new Image("img/defaultBrick.png");
	public static final ImagePattern brickImgPtn = new ImagePattern(brickImg, 0, 0, 1, 1, true);
	public enum State {
		New,
		Young,
		Old,
		Dead
	}

	
	// Field
	private State state;
	private double leftEdge, rightEdge, topEdge, bottomEdge;
	
	// Constructors
	public Brick() {
		setPosition(0, 0);
		setFill(Brick.brickImgPtn);
		setState(State.New);
		setSize(Brick.WIDTH, Brick.HEIGHT);
	}
	
	public Brick(double gPosX, double gPosY) {
		setGridPosition(gPosX, gPosY);
		setFill(brickImgPtn);
		setState(State.New);
		setSize(Brick.WIDTH, Brick.HEIGHT);
	}
	
	// Getter and Setter
	public void setPosition(double posX, double posY) {
		setX(posX);
		setY(posY);
		leftEdge	= posX;
		rightEdge	= posX + Brick.WIDTH;
		topEdge		= posY;
		bottomEdge	= posY + Brick.HEIGHT;
	}
	
	public void setGridPosition(double gPosX, double gPosY) {
		setX(gPosX * Brick.GRID_SIZE);
		setY(gPosY * Brick.GRID_SIZE);
		leftEdge	= getX();
		rightEdge	= getX() + Brick.WIDTH;
		topEdge		= getY();
		bottomEdge	= getY() + Brick.HEIGHT;
	}
	
	public void setState(State state) {
		this.state = state;
	}
	
	public State getState() {
		return this.state;
	}
	
	public void nextState() {
		switch(this.state) {
			case New:
				setState(State.Dead);
				setVisible(false);
				break;
			case Dead:
				break;
			default:
				break;
		}
	}
	
	private void setSize(double width, double height) {
		setWidth(width);
		setHeight(height);
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
	
	
	// implement methods of interfaces
	public void hitBy(IHitter hitter) {
		nextState();
	}
}
