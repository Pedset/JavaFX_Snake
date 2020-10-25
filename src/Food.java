import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class Food extends Rectangle {
	int posX, posY;

	public int getPosX() {
		return posX;
	}

	public int getPosY() {
		return posY;
	}

	public Food(int x, int y) {
		super(Main1.block_size, Main1.block_size);

		posX = x;
		posY = y;
		setTranslateX(posX * Main1.block_size);
		setTranslateY(posY * Main1.block_size);

		Image img = new Image("file:foodapple.png");
		setFill(new ImagePattern(img));

	}

}