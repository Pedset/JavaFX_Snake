import javafx.scene.shape.Rectangle;

public class Block extends Rectangle {
	final static int UP = 0, RIGHT = 1, DOWN = 2, LEFT = 3;
	int posX, posY, oldPosX, oldPosY;

	Block previous;

	int direction = LEFT;

	int maxX, maxY;
	
	
	
}