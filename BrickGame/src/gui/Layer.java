package gui;

import java.util.Timer;
import java.util.TimerTask;

import javafx.scene.Group;

public class Layer extends Group{
	
	// Constant
	public static final double DEFAULT_FRAME_RATE = 30;
	
	// Field
	private static int layerCount = 0;
	
	private int layerId;
	private String name;
	private Timer timer;
	private boolean running = false;
	private double frameRate = DEFAULT_FRAME_RATE;
	private long millisecondPerFrame = (long)(1000 / frameRate);
	
	// Constructor
	public	Layer() {
		setLayerId();
		setName("Layer_" + String.format("%4d", getLayerId()));
		this.timer = new Timer();
	}
	
	public	Layer(String name) {
		setLayerId();
		if(getName() == null || getName().equals("")) {
			setName("Layer_" + String.format("%4d", getLayerId()));
		}else {
			setName(name);
		}
		this.timer = new Timer();
	}
	
	// Setters and Getters
	private static int	getLayerCount()	{ return layerCount; }
	private static void	nextLayerCount(){ layerCount += 1; }
	
	public	int getLayerId() { return layerId; }
	private void setLayerId() {
		this.layerId = getLayerCount();
		nextLayerCount();
	}

	public	String getName() { return name; }
	public	void setName(String name) { this.name = name; }
	
	public	double getFrameRate() { return this.frameRate; }
	public	void setFrameRate(double frameRate) {
		this.frameRate = frameRate;
		this.millisecondPerFrame = (long)(1000 / this.frameRate);
	}
	
	private void setRunning(boolean running){ this.running = running; }
	public	boolean isRunning()				{ return this.running; }
	
	// Methods
	public void addRoutine(TimerTask task) {
		timer.schedule(task, 0, millisecondPerFrame);
		setRunning(true);
	}
	
	public void pause() {
		if(running) {
			timer.cancel();
			setRunning(false);
		} else {
			System.out.println("Timer is not running");
		}
	}
}
