package Model;

import View.GameInterface;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class Block extends Rectangle {
	public final static int UP = 0, RIGHT = 1, DOWN = 2, LEFT = 3;
	int posX, posY, oldPosX, oldPosY;

	Block previous;
	Paint model;
	Paint prevModel;

	int direction = LEFT;

	int maxX, maxY;

	// Draws the block at coordinates X and Y then sets the block as previous . sets
	// max X and max Y
	public Block(int x, int y, Block p, Field f) {
		super(GameInterface.block_size, GameInterface.block_size);
		posX = x;
		posY = y;
		setTranslateX(posX * GameInterface.block_size);
		setTranslateY(posY * GameInterface.block_size);
		previous = p;
		maxX = f.getW();
		maxY = f.getH();
	}

	// Update the position/direction if we don't have the previous
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

	// Depending on which direction we are heading we change the coordinates
	private void moveLeft() {
		posX--;
		// Goes thru the wall
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

		setTranslateX(posX * GameInterface.block_size);
		setTranslateY(posY * GameInterface.block_size);
	}

}