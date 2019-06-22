package gamestage;

import java.util.TimerTask;

import gui.Layer;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import obj.Ball;
import obj.Brick;
import obj.IHittable;
import obj.IHitter;
import obj.IMovable;
import obj.Plate;

public class ObjectRoutine extends TimerTask{
	
	// Field
	private Layer layer;
	private ObservableList<Node> children;
	private double mouseX, mouseY;
	
	// Constructor
	public ObjectRoutine() {}
	
	public ObjectRoutine(Layer layer) {
		setLayer(layer);
	}
	
	// Methods
	public void setLayer(Layer layer) {
		this.layer = layer;
		children = layer.getChildren();
	}
	
	public void setMousePosition(double x, double y) {
		this.mouseX = x;
		this.mouseY = y;
	}

	@Override
	public void run() {
		for(Node node : children) {
			if(node instanceof IMovable) {
				IMovable movable = (IMovable)node;
				movable.move();
			}
			if(node instanceof IHitter) {
				if(node instanceof Ball) {
					Ball ball = (Ball)node;
					for(Node _node : children) {
						if(_node instanceof Brick) {
							Brick brick = (Brick)_node;
							if(	brick.getState() != Brick.State.Dead && ball.distanceToBrick(brick) <= ball.getRadius()) {
								brick.hitBy(ball);
								ball.hit(brick);
							}
						}else if(_node instanceof Plate) {
							Plate plate = (Plate)_node;
							plate.setPosition(mouseX - ( 0.5 * plate.getWidth() ), plate.getY());
							
							if( ball.distanceToPlate(plate) <= ball.getRadius()) {
								plate.hitBy(ball);
								ball.hit(plate);
							}
						}
					}
				}
			}
			if(node instanceof IHittable) {
				
			}
		}
	}

}
