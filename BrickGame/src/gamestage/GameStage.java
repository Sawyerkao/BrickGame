package gamestage;

import java.util.ArrayList;

import com.BrickGame;

import gui.Layer;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import obj.Ball;
import obj.Brick;
import obj.Plate;

public class GameStage {
	// Constant
		
	// Field
	private int stageId;
	private String stageName;
	private double originX, originY, width, height;
	private Paint background;
	private Layer stageLayer;
	private ObservableList<Node> objectContainer;
	private ObjectRoutine objectRoutine;
	private ArrayList<Brick> bricks;
	private Ball mainBall;
	private Plate mainPlate;
	
	// Constructor
	public GameStage() {
		setOrigin(0, 0);
		setSize(BrickGame.WINDOW_WIDTH, BrickGame.WINDOW_HEIGHT);
		stageLayer = new Layer("LayerOf" + getStageName());
		objectContainer = stageLayer.getChildren();
		objectRoutine = new ObjectRoutine(stageLayer);
		bricks = new ArrayList<Brick>();
	}
	
	public GameStage(int id, String name, Paint background) {
		setStageId(id);
		setStageName(name);
		setBackground(background);
		setOrigin(0, 0);
		setSize(BrickGame.WINDOW_WIDTH, BrickGame.WINDOW_HEIGHT);
		stageLayer = new Layer("LayerOf" + getStageName());
		objectContainer = stageLayer.getChildren();
		objectRoutine = new ObjectRoutine(stageLayer);
		bricks = new ArrayList<Brick>();
	}
	
	// Setters and Getters
	public void	setStageId(int id)	{ this.stageId = id; }
	public int	getStageId()		{ return stageId;	}

	public String getStageName()			{ return stageName;	}
	public void setStageName(String name)	{ this.stageName = name; }
	
	public double getOriginX()	{ return this.originX; }
	public double getOriginY()	{ return this.originY; }
	public void setOrigin(double x, double y) { this.originX = x; this.originY = y;}
	
	public double getWidth()	{ return this.width; }
	public double setHeight()	{ return this.height; }
	public void setSize(double width, double height) { this.width = width; this.height = height; }

	public Paint getBackground()				{ return background; }
	public void setBackground(Paint background)	{ this.background = background; }
	
	public Layer getStageLayer() { return this.stageLayer; }

	// Methods
	private void initial() {
		// Build background
		Rectangle bgCanvas = new Rectangle(0, 0, this.width, this.height);
		bgCanvas.setFill(background);
		addNode(0, bgCanvas);
	}
	
	public void start() {
		initial();
		stageLayer.addRoutine(objectRoutine);
	}
	
	public void stop() {
		stageLayer.pause();
	}
	
	public void setMousePosition(double x, double y) {
		objectRoutine.setMousePosition(x, y);
	}
	
	public void addPlate(Plate plate) {
		this.mainPlate = plate;
		addNode(mainPlate);
	}
	
	public void addBall(Ball ball) {
		this.mainBall = ball;
		addNode(mainBall);
	}
	
	public void addBrick(Brick brick) {
		this.bricks.add(brick);
		addNode(brick);
	}
	public void addBrick(ArrayList<Brick> bricks) {
		for(Brick brick : bricks) {
			this.bricks.add(brick);
			addNode(brick);
		}
	}
	
	private void addNode(Node node)	{ objectContainer.add(node); }
	private void addNode(int index, Node node)	{ objectContainer.add(index, node); }
	
}
