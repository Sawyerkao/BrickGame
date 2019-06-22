package gamestage;

import java.util.ArrayList;

import obj.Brick;
import obj.Vector2;

public class BrickBuilder {
	
	// Static Methods
	public static ArrayList<Brick> build(Vector2 origin, Vector2 size, Vector2 gapSize, int brickId) {
		ArrayList<Brick> bricks = new ArrayList<Brick>();
		for(int y = 0; y < size.getY(); y++) {
			for(int x = 0; x < size.getX(); x++) {
				bricks.add( new Brick(origin.getX() + x * (1 + gapSize.getX()), origin.getY() + y * (1 + gapSize.getY())) );
			}
		}
		return bricks;
	}
	
	public static ArrayList<Brick> buildChessBoard(Vector2 origin, Vector2 size, Vector2 gapSize, int brickId, boolean reverse) {
		int condition = reverse ? 1 : 0;
		ArrayList<Brick> bricks = new ArrayList<Brick>();
		for(int y = 0; y < size.getY(); y++) {
			for(int x = 0; x < size.getX(); x++) {
				if( (x+y)%2 == condition ) {
					bricks.add( new Brick(origin.getX() + x * (1 + gapSize.getX()), origin.getY() + y * (1 + gapSize.getY())) );
				}
			}
		}
		return bricks;
	}
}
