package Model;

import java.util.ArrayList;

import Controller.DifficultyCtrl;
import View.GameInterface;
import javafx.scene.image.Image;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;

public class Field extends Pane {

	final static Paint headupeat = new ImagePattern(new Image("file:Images/headupeat.png"));
	final static Paint headup = new ImagePattern(new Image("file:Images/headup.png"));

	final static Paint headrighteat = new ImagePattern(new Image("file:Images/headrighteat.png"));
	final static Paint headright = new ImagePattern(new Image("file:Images/headright.png"));

	final static Paint headdowneat = new ImagePattern(new Image("file:Images/headdowneat.png"));
	final static Paint headdown = new ImagePattern(new Image("file:Images/headdown.png"));

	final static Paint headlefteat = new ImagePattern(new Image("file:Images/headlefteat.png"));
	final static Paint headleft = new ImagePattern(new Image("file:Images/headleft.png"));

	final static Paint bodyhori = new ImagePattern(new Image("file:Images/bodyhori.png"));
	final static Paint bodyvert = new ImagePattern(new Image("file:Images/bodyvert.png"));

	final static Paint bodyrightdown = new ImagePattern(new Image("file:Images/bodyrightdown.png"));
	final static Paint bodyleftdown = new ImagePattern(new Image("file:Images/bodyleftdown.png"));
	final static Paint bodyrightup = new ImagePattern(new Image("file:Images/bodyrightup.png"));
	final static Paint bodyleftup = new ImagePattern(new Image("file:Images/bodyleftup.png"));

	final static Paint tailleft = new ImagePattern(new Image("file:Images/tailleft.png"));
	final static Paint tailright = new ImagePattern(new Image("file:Images/tailright.png"));
	final static Paint taildown = new ImagePattern(new Image("file:Images/taildown.png"));
	final static Paint tailup = new ImagePattern(new Image("file:Images/tailup.png"));

	final static Paint fireWall = new ImagePattern(new Image("file:Images/fire.gif"));

	private int w, h;

	public int getW() {
		return w;
	}

	public int getH() {
		return h;
	}

	ArrayList<Block> blocks = new ArrayList<>();
	ArrayList<Block> obsBlocks = new ArrayList<>();

	public int score = 0;
	public Snake snake;

	int randomX = 0;
	int randomY = 0;
	Food f;

	public void addSnake(Snake s) {
		snake = s;
		addFood();
		for (int x = 0; x < s.blocks.size(); x++) {
			addBlock(s.blocks.get(x));
			if (x == 0) {
				s.blocks.get(x).setFill(headleft);
			} else if (x > 0 && x < s.blocks.size() - 1) {
				s.blocks.get(x).model = (bodyhori);
				s.blocks.get(x).setFill(s.blocks.get(x).model);
			} else {
				s.blocks.get(x).setFill(tailleft);
			}
		}
	}

	private void addBlock(Block b) {
		getChildren().add(b);
		blocks.add(b);
	}

	public void addnewBlock() {
		Block b = new Block(snake.tail.oldPosX, snake.tail.oldPosY, snake.tail, this);
		snake.tail = b;
		b.setFill((Color.TRANSPARENT));
		addBlock(b);
	}

	public void addObs(Obstacle obs) {
		for (Block b : obs.blocks) {
			addObsBlock(b);
		}
	}

	private void addObsBlock(Block b) {
		b.setFill(fireWall);
		getChildren().add(b);
		obsBlocks.add(b);
	}

	public void addFood() {

		boolean conflict = true;

		// Makes sure that the food doesn't spawn on the obstacle.
		if (!obsBlocks.isEmpty()) {
			while (conflict) {
				conflict = false;
				randomX = (int) (Math.random() * w);
				randomY = (int) (Math.random() * h);

				for (Block b : obsBlocks) {
					if (randomX == b.posX && randomY == b.posY) {
						conflict = true;
					}
				}
			}
		}
		Food food = new Food(randomX, randomY);
		getChildren().add(food);
		getChildren().remove(f);
		f = food;
	}

	public void update() {
		for (int x = 0; x < blocks.size(); x++) {

			blocks.get(x).update();
			Block currentB = blocks.get(x);
			short currentPosX = (short) currentB.posX;
			short currentPosY = (short) currentB.posY;
			short oldPosX = (short) currentB.oldPosX;
			short oldPosY = (short) currentB.oldPosY;

			short newPosX = 0;
			short newPosY = 0;
			if (x != 0) {
				newPosX = (short) blocks.get(x - 1).posX;
				newPosY = (short) blocks.get(x - 1).posY;
			}

			if (x == 0) {
				if (currentB.direction == 0) {
					if (currentPosY - randomY > -1 && currentPosY - randomY < 3 && currentPosX == randomX) {

						currentB.setFill(headupeat);
					} else {
						currentB.setFill(headup);
					}
				} else if (currentB.direction == 1) {
					if (randomX - currentPosX > -1 && randomX - currentPosX < 3 && currentPosY == randomY) {

						currentB.setFill(headrighteat);
					} else {
						currentB.setFill(headright);
					}
				} else if (currentB.direction == 2) {
					if (randomY - currentPosY > -1 && randomY - currentPosY < 3 && currentPosX == randomX) {

						currentB.setFill(headdowneat);
					} else {
						currentB.setFill(headdown);
					}
				} else if (currentB.direction == 3) {
					if (currentPosX - randomX > -1 && currentPosX - randomX < 3 && currentPosY == randomY) {

						currentB.setFill(headlefteat);
					} else {
						currentB.setFill(headleft);
					}
				}
			} else if (x == 1) {
				if (currentPosX < oldPosX || currentPosX > oldPosX) {

					currentB.setFill(bodyhori);
				} else {
					currentB.setFill(bodyvert);
				}

				if ((newPosX - currentPosX == 1 && oldPosY - currentPosY == 1)
						|| (oldPosX - currentPosX == 1 && newPosY - currentPosY == 1)
						|| (currentPosX - newPosX > 10 && oldPosY - currentPosY == 1 && currentPosX == 69)
						|| (currentPosX - oldPosX > 10 && newPosY - currentPosY == 1 && currentPosX == 69)
						|| (currentPosY - newPosY > 10 && oldPosX - currentPosX == 1 && currentPosY == 34)
						|| (currentPosY - oldPosY > 10 && newPosX - currentPosX == 1 && currentPosY == 34)
						|| (currentPosX - newPosX > 10 && currentPosY - oldPosY > 10 && currentPosX == 69)
						|| (currentPosY - newPosY > 10 && currentPosX - oldPosX > 10 && currentPosX == 69)) {

					currentB.setFill(bodyrightdown);

				} else if ((currentPosX - oldPosX == 1 && newPosY - currentPosY == 1)
						|| (currentPosX - newPosX == 1 && oldPosY - currentPosY == 1)
						|| (newPosX - currentPosX > 10 && oldPosY - currentPosY == 1 && currentPosX == 0)
						|| (oldPosX - currentPosX > 10 && newPosY - currentPosY == 1 && currentPosX == 0)
						|| (currentPosY - oldPosY > 10 && currentPosX - newPosX == 1 && currentPosY == 34)
						|| (currentPosY - newPosY > 10 && currentPosX - oldPosX == 1 && currentPosY == 34)
						|| (newPosX - currentPosX > 10 && currentPosY - oldPosY > 10 && currentPosY == 34)
						|| (currentPosY - newPosY > 10 && oldPosX - currentPosX > 10 && currentPosY == 34)) {

					currentB.setFill(bodyleftdown);

				} else if ((oldPosX - currentPosX == 1 && currentPosY - newPosY == 1)
						|| (newPosX - currentPosX == 1 && currentPosY - oldPosY == 1)
						|| (newPosX - currentPosX == 1 && oldPosY - currentPosY > 10 && currentPosY == 0)
						|| (oldPosX - currentPosX == 1 && newPosY - currentPosY > 10 && currentPosY == 0)
						|| (currentPosY - newPosY == 1 && currentPosX - oldPosX > 10 && currentPosX == 69)
						|| (currentPosY - oldPosY == 1 && currentPosX - newPosX > 10 && currentPosX == 69)
						|| (currentPosX - newPosX > 10 && oldPosY - currentPosY > 10 && currentPosX == 69)
						|| (newPosY - currentPosY > 10 && currentPosX - oldPosX > 10 && currentPosX == 69)) {

					currentB.setFill(bodyrightup);

				} else if ((currentPosX - newPosX == 1 && currentPosY - oldPosY == 1)
						|| (currentPosY - newPosY == 1 && currentPosX - oldPosX == 1)
						|| (oldPosX - currentPosX > 10 && currentPosY - newPosY == 1 && currentPosX == 0)
						|| (newPosX - currentPosX > 10 && currentPosY - oldPosY == 1 && currentPosX == 0)
						|| (oldPosY - currentPosY > 10 && currentPosX - newPosX == 1 && currentPosY == 0)
						|| (newPosY - currentPosY > 10 && currentPosX - oldPosX == 1 && currentPosY == 0)
						|| (newPosX - currentPosX > 10 && oldPosY - currentPosY > 10 && currentPosX == 0)
						|| (newPosY - currentPosY > 10 && oldPosX - currentPosX > 10 && currentPosX == 0)) {

					currentB.setFill(bodyleftup);
				}

				currentB.prevModel = currentB.model;
				currentB.model = currentB.getFill();

			} else if (x > 1 && x < blocks.size() - 1) {

				currentB.setFill(blocks.get(x - 1).prevModel);
				currentB.prevModel = currentB.model;
				currentB.model = currentB.getFill();
			}

			else {
				if (currentPosX - newPosX == 1 || (newPosX - currentPosX > 10 && currentPosX == 0)) {
					currentB.setFill(tailleft);
				} else if (newPosX - currentPosX == 1 || (currentPosX - newPosX > 10 && currentPosX == 69)) {
					currentB.setFill(tailright);
				} else if (newPosY - currentPosY == 1 || (currentPosY - newPosY > 10 && currentPosY == 34)) {
					currentB.setFill(taildown);
				} else if (currentPosY - newPosY == 1 || (newPosY - currentPosY > 10 && currentPosY == 0)) {
					currentB.setFill(tailup);
				}
			}
		}

		if (Food.isEaten(f)) {
			Sound.biteSound();
			score += 50;
			addFood();
			addnewBlock();
			DifficultyCtrl.checkSpeed();
			GameInterface.setScore();
		}
	}

	public Field(int width, int height) {
		w = width;
		h = height;
		//
		setMinSize(w * GameInterface.block_size, h * GameInterface.block_size);
		setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, null, new BorderWidths(1))));
	}

}