import java.util.ArrayList;

import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class Field extends Pane {
	private int w, h;
	ArrayList<Block> blocks = new ArrayList<>();
	
	ArrayList<Block> obsBlocks = new ArrayList<>();
	
	int score = 0;
	int randomX = 0;
	int randomY = 0;
	Food f;
	Snake snake;
	
	public int getW() {
		return w;
	}

	public int getH() {
		return h;
	}
	
	public void addSnake(Snake s) {
		snake = s;
		
		addFood();
		for(int x = 0; x < s.blocks.size(); x++) {
			addBlock(s.blocks.get(x));
		}
	}
	
	private void addBlock(Block b) {

		getChildren().add(b);
		blocks.add(b);
	}
	
	public void update() {
		for(int x = 0; x < blocks.size(); x++) {
			
			/// update block
		}
		
		if(isEaten(f)) {
			score += 50;
			addFood();
			
			// adding new block (waiting)
			
		}
	}
	
	
	public void addFood() {

		randomX = (int)(Math.random() * w);
		randomY = (int)(Math.random() * h);
		boolean conflict = true;
		
		// Makes sure that the food doesn't spawn on the obstacle.
		if (!obsBlocks.isEmpty()) {
			while(conflict) {
				conflict= false;
				 randomX = (int)(Math.random() * w);
				 randomY = (int)(Math.random() * h);
				
				for (Block b: obsBlocks) {
					if(randomX == b.posX || randomY == b.posY) {
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
	public boolean isEaten(Food f) {
		return f.getPosX() == snake.head.posX && f.getPosY() == snake.head.posY;
	}
	
	public Field(int width, int height) {
		w= width;
		h= height;
		//
		setMinSize(w * Main1.block_size, h * Main1.block_size);
		setBackground(new Background(new BackgroundFill(Color.BLACK,null, null)));
		setBorder(new Border(new BorderStroke(Color.WHITE,BorderStrokeStyle.SOLID, null, new BorderWidths(1))));
		
	}
	
	public void addnewBlock() {
		Block b = new Block(snake.tail.oldPosX, snake.tail.oldPosY, snake.tail, this);
		snake.tail = b;
		
			// fixes the issue with black box
			b.setFill((Color.TRANSPARENT));
		
		// ******
		addBlock(b);
	}
	
	public boolean isDead() {
		for (Block b: blocks) {
			if(b!= snake.head) {
				if(b.posX == snake.head.posX && b.posY == snake.head.posY) {
					// play death sound
					return true;
				}
			}
		}
		for (Block b: obsBlocks) {
				if(b.posX == snake.head.posX && b.posY == snake.head.posY) {
					// play death sound
					return true;
				}
		}
		return false;
	}
	
}