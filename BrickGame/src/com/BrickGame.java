package com;

import gamestage.GameStage;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import obj.*;

public class BrickGame extends Application{
	
	// Constant
	public static final	int	FRAME_PER_SECOND = 30;
	public static final	long TIME_PER_FRAME  = 1000 / FRAME_PER_SECOND;
	public static final	int	WINDOW_WIDTH	= 1200;
	public static final	int	WINDOW_HEIGHT	= 800;
	
	// Field
	public GameStage gameStage;
	
	@Override
	public void init() {
		
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		System.out.println("javafx start...");
		
		gameStage = new GameStage(1, "Test", Color.DARKGREEN);

		// scene prepare
		Group root = new Group();
		Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
		//root.getChildren().add(new Rectangle(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT));
		
		// Set MouseEventHandler
		scene.addEventHandler(MouseEvent.MOUSE_MOVED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				gameStage.setMousePosition(e.getSceneX(), e.getSceneY());
			}
		});
		
		// build bricks, ball, and plate
		Ball mainBall = new Ball(100, 500);
		Plate mainPlate = new Plate(500, 750);
		mainBall.setMovingEdge(0, WINDOW_WIDTH, WINDOW_HEIGHT, 0);
		gameStage.addBall(mainBall);
		gameStage.addPlate(mainPlate);
		gameStage.addBrick(gamestage.BrickBuilder.build(new Vector2(0.5, 0.5), new Vector2(20, 6), new Vector2(0.5, 0.5), 0));
		root.getChildren().add(gameStage.getStageLayer());
		/*
		for(Brick brick : BrickGenerator(0, 1, 20, 8, 0.5, 0.5)) {
			root.getChildren().add(brick);
		}
		*/
		
		// stage prepare
		stage.setResizable(false);
		stage.setTitle("Brick Game");
		stage.setScene(scene);
		stage.sizeToScene();
		stage.show();
		
		gameStage.start();
	}
	
	@Override
	public void stop() {
		System.out.println("javafx close.");
		gameStage.stop();
	}
	
	// Methods	
	public static void main(String[] args) {
		System.out.println("Application launch...");
		Application.launch(args);
		System.out.println("Application close.");
	}
}
