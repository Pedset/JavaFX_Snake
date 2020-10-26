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

	public void update() {
		oldPosX = posX;
		oldPosY = posY;
		if (previous == null) {
			switch (direction) {
			case UP: {
				moveUp();
				break;
			}
			case RIGHT: {
				moveRight();
				break;
			}
			case DOWN: {
				moveDown();
				break;
			}
			case LEFT: {
				moveLeft();
			}
			}
		} else {
			posX = previous.oldPosX;
			posY = previous.oldPosY;
		}
		updatePosition();
	}

	private void moveLeft() {
		posX--;
		if (posX < 0) {
			posX = maxX - 1;
		}
	}

	private void moveDown() {
		posY++;
		if (posY >= maxY) {
			posY = 0;
		}
	}

	private void moveUp() {
		posY--;
		if (posY < 0) {
			posY = maxY - 1;
		}
	}

	private void moveRight() {
		posX++;
		if (posX >= maxX) {
			posX = 0;
		}
	}

	public void updatePosition() {
		setTranslateX(posX * Main1.block_size);
		setTranslateY(posY * Main1.block_size);
	}

}