package obj;

import com.BrickGame;
import com.MyMath;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

public class Ball extends Circle implements IHitter, IMovable{
	// Constant
	public static final double DEFAULT_RADIUS	= 10;
	public static final double INIT_VELOCITY	= 200;
	public static final double DEFAULT_ACCEL	= 2;
	public static final double MAX_VELOCITY		= 350;
	public static final Image ballImg = new Image("img/NormalBall.png");
	public static final ImagePattern ballImgPtn = new ImagePattern(ballImg, 0, 0, 1, 1, true);
	public static final double REFLECTION_VECTOR_LEFT_BOUND  = Math.PI * ( 1 + ( 1 / 6. ));
	public static final double REFLECTION_VECTOR_RIGHT_BOUND = Math.PI * ( 1 + ( 5 / 6. ));
	public enum State{
		Moving,
		Slave,
		Dead
	}
	
	// Field
	private double velocity;
	private double acceleration;
	private double d_velocity;
	private double d_vx, d_vy;
	private double vector;
	private double movingEdgeTop, movingEdgeRight, movingEdgeBottom, movingEdgeLeft;
	private State state;
	
	// Constructor
	public Ball() {
		// view
		setCenterX(0);
		setCenterY(0);
		setFill(ballImgPtn);
		setRadius(DEFAULT_RADIUS);
		// properties
		setVelocity(INIT_VELOCITY);
		setVector(Math.PI * 0.5);
		setAcceleration(DEFAULT_ACCEL);
	}
	
	public Ball(double posX, double posY) {
		// view
		setCenterX(posX);
		setCenterY(posY);
		setFill(ballImgPtn);
		setRadius(DEFAULT_RADIUS);
		// properties
		setVelocity(INIT_VELOCITY);
		setVector(-Math.PI / 6.0);
		setAcceleration(DEFAULT_ACCEL);
	}
	
	// Getters and Setters
	public void setVelocity(double velocity) {
		this.velocity = velocity;
		d_velocity = velocity / BrickGame.FRAME_PER_SECOND;
	}
	
	public double getVelocity() {
		return this.velocity;
	}
	
	public void setVector(double vector) {
		while(vector < 0) {
			vector += Math.PI * 2;
		}
		this.vector = vector;
		d_vx = d_velocity * Math.cos(this.vector);
		d_vy = d_velocity * Math.sin(this.vector);
	}
	
	public double getVector() {
		return this.vector;
	}
	
	public void setAcceleration(double acceleration) {
		this.acceleration = acceleration;
	}
	
	public double getAcceleration() {
		return acceleration;
	}
	
	public void setMovingEdge(double movingEdgeTop, double movingEdgeRight, double movingEdgeBottom, double movingEdgeLeft) {
		this.movingEdgeTop = movingEdgeTop;
		this.movingEdgeRight = movingEdgeRight;
		this.movingEdgeBottom = movingEdgeBottom;
		this.movingEdgeLeft = movingEdgeLeft;
	}
	
	public void setState(State state) {
		this.state = state;
		if(state == State.Dead) {
			setVelocity(0);
			setAcceleration(0);
		}
	}
	
	public State getState() {
		return this.state;
	}
	
	// Methods
	public void updateCenter(double dx, double dy) {
		setCenterX(getCenterX() + dx);
		setCenterY(getCenterY() + dy);
	}
	
	// Interactive with Brick
	public double distanceToBrick(Brick brick) {
		double result = Double.MAX_VALUE;
		switch(getDirectionFromBrick(brick)) {
			case North:
				result = brick.getTopEdge() - getCenterY();
				break;
			case NorthEast:
				result = Vector2.distance(getCenterX(), getCenterY(), brick.getRightEdge(), brick.getTopEdge());
				break;
			case East:
				result = getCenterX() - brick.getRightEdge();
				break;
			case SouthEast:
				result = Vector2.distance(getCenterX(), getCenterY(), brick.getRightEdge(), brick.getBottomEdge());
				break;
			case South:
				result = getCenterY() - brick.getBottomEdge();
				break;
			case SouthWest:
				result = Vector2.distance(getCenterX(), getCenterY(), brick.getLeftEdge(), brick.getBottomEdge());
				break;
			case West:
				result = brick.getLeftEdge() - getCenterX();
				break;
			case NorthWest:
				result = Vector2.distance(getCenterX(), getCenterY(), brick.getLeftEdge(), brick.getTopEdge());
				break;
			case Center:
				result = 0;
				break;
		}
		
		return result;
	}
	
	public Direction getDirectionFromBrick(Brick brick) {
		int dirX, dirY;
		
		if( getCenterX() < brick.getLeftEdge() ) {
			dirX = 0;
		}else if( getCenterX() > brick.getRightEdge() ) {
			dirX = 2;
		}else {
			dirX = 1;
		}
		
		if( getCenterY() < brick.getTopEdge() ) {
			dirY = 0;
		}else if( getCenterY() > brick.getBottomEdge() ) {
			dirY = 2;
		}else {
			dirY = 1;
		}
		
		return Direction.table[dirY][dirX];
	}
	
	// Interactive with Plate
	public double distanceToPlate(Plate plate) {
		double result = Double.MAX_VALUE;
		switch(getDirectionFromPlate(plate)) {
		case North:
			result = plate.getTopEdge() - getCenterY();
			break;
		case NorthEast:
			result = Vector2.distance(getCenterX(), getCenterY(), plate.getRightEdge(), plate.getTopEdge());
			break;
		case East:
			result = getCenterX() - plate.getRightEdge();
			break;
		case SouthEast:
			result = Vector2.distance(getCenterX(), getCenterY(), plate.getRightEdge(), plate.getBottomEdge());
			break;
		case South:
			result = getCenterY() - plate.getBottomEdge();
			break;
		case SouthWest:
			result = Vector2.distance(getCenterX(), getCenterY(), plate.getLeftEdge(), plate.getBottomEdge());
			break;
		case West:
			result = plate.getLeftEdge() - getCenterX();
			break;
		case NorthWest:
			result = Vector2.distance(getCenterX(), getCenterY(), plate.getLeftEdge(), plate.getTopEdge());
			break;
		case Center:
			result = 0;
			break;
	}
		return result;
	}
	
	public Direction getDirectionFromPlate(Plate plate) {
		int dirX, dirY;
		
		if( getCenterX() < plate.getLeftEdge() ) {
			dirX = 0;
		}else if( getCenterX() > plate.getRightEdge() ) {
			dirX = 2;
		}else {
			dirX = 1;
		}
		
		if( getCenterY() < plate.getTopEdge() ) {
			dirY = 0;
		}else if( getCenterY() > plate.getBottomEdge() ) {
			dirY = 2;
		}else {
			dirY = 1;
		}
		
		return Direction.table[dirY][dirX];
	}
	
	public void reflect(double normalVector) {
		setVector(vector + ( Math.PI - 2.0 * (vector - normalVector)));
	}
	
	@Override
	public void move() {
		updateCenter(d_vx, d_vy);
		
		// Check edge
		if( getCenterX() < movingEdgeLeft + getRadius() ) {
			setCenterX(getRadius());
			reflect(Math.PI * 0);
		}else if( getCenterX() > movingEdgeRight - getRadius() ) {
			setCenterX(movingEdgeRight - getRadius());
			reflect(Math.PI * 1);
		}
		if( getCenterY() < movingEdgeTop + getRadius() ) {
			setCenterY(getRadius());
			reflect(Math.PI * 0.5);
		}else if( getCenterY() > movingEdgeBottom + getRadius() ) {
			setState(State.Dead);
		}
		
	}

	@Override
	public void hit(IHittable target) {
		if(target instanceof Brick) {
			Brick brick = (Brick)target;
			
			switch(getDirectionFromBrick(brick)) {
				case North:
					setCenterY(brick.getTopEdge() - getRadius());
					reflect(Math.PI * 1.5);
					break;
				case NorthEast:
					reflect(Math.PI * 1.75);
					break;
				case East:
					setCenterX(brick.getRightEdge() + getRadius());
					reflect(Math.PI * 0);
					break;
				case SouthEast:
					reflect(Math.PI * 0.25);
					break;
				case South:
					setCenterY(brick.getBottomEdge() + getRadius());
					reflect(Math.PI * 0.5);
					break;
				case SouthWest:
					reflect(Math.PI * 0.75);
					break;
				case West:
					setCenterX(brick.getLeftEdge() - getRadius());
					reflect(Math.PI * 1);
					break;
				case NorthWest:
					reflect(Math.PI * 1.25);
					break;
				case Center:
					break;
			}
		}else if(target instanceof Plate) {
			Plate plate = (Plate)target;
			setVelocity( ((getVelocity() <= MAX_VELOCITY ) ? (getVelocity() + getAcceleration()) : MAX_VELOCITY) );
			
			switch(getDirectionFromPlate(plate)) {
			case Center:
			case North:
				setCenterY(plate.getTopEdge() - getRadius());
				setVector(MyMath.interpolation(
							REFLECTION_VECTOR_LEFT_BOUND,
							REFLECTION_VECTOR_RIGHT_BOUND,
							(getCenterX()-plate.getX()) / plate.getWidth()
						) );
				break;
			case NorthEast:
			case East:
				setVector(REFLECTION_VECTOR_RIGHT_BOUND);
				break;
			case West:
			case NorthWest:
				setVector(REFLECTION_VECTOR_LEFT_BOUND);
				break;
			case SouthEast:
			case South:
			case SouthWest:
				break;
			}
		}
	}

}
