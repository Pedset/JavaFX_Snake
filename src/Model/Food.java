package Model;

import View.GameInterface;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class Food extends Rectangle {
	int posX, posY;

	// Getters for position x and y
	public int getPosX() {
		return posX;
	}

	public int getPosY() {
		return posY;
	}

	// Draws food at coordinates X and Y then adds an image to the block
	public Food(int x, int y) {
		super(GameInterface.block_size, GameInterface.block_size);

		posX = x;
		posY = y;
		setTranslateX(posX * GameInterface.block_size);
		setTranslateY(posY * GameInterface.block_size);
		setFill(new ImagePattern(new Image("file:Images/foodapple.png")));

	}

	public static boolean isEaten(Food f) {
		Field localF = GameInterface.getField();
		return f.getPosX() == localF.snake.head.posX && f.getPosY() == localF.snake.head.posY;
	}

}