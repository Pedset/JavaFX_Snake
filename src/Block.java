import javafx.scene.shape.Rectangle;

public class Block extends Rectangle {
	final static int UP = 0, RIGHT = 1, DOWN = 2, LEFT = 3;
	int posX, posY, oldPosX, oldPosY;

	Block previous;

	int direction = LEFT;

	int maxX, maxY;
	
	public Block(int x, int y, Block p, Field f) {
		super(Main1.block_size, Main1.block_size);
		posX = x;
		posY = y;

		setTranslateX(posX * Main1.block_size);
		setTranslateY(posY * Main1.block_size);
		previous = p;
		maxX = f.getW();
		maxY = f.getH();
	}
	
	public void updatePosition() {
		setTranslateX(posX * Main1.block_size);
		setTranslateY(posY * Main1.block_size);
	}
	
	
	
}