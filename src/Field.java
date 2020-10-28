import java.util.ArrayList;

import javafx.scene.image.Image;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;

public class Field extends Pane {
	private int w, h;
	ArrayList<Block> blocks = new ArrayList<>();
	
	ArrayList<Block> obsBlocks = new ArrayList<>();
	
	int score = 0;
	
	

	int randomX = 0;
	int randomY = 0;
	Food f;
	Snake snake;
	
	public int getScore() {
		return score;
	}
	
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
	
	public void addObs(Obstacle obs) {
		
		for(Block b:obs.blocks) {
			addObsBlock(b);
		}
	}
	private void addObsBlock(Block b) {
		
		Image img = new Image("file:fire.gif");
		b.setFill(new ImagePattern(img));
		
		getChildren().add(b);
		obsBlocks.add(b);
	}
	
	public void update() {
		for(int x = 0; x < blocks.size(); x++) {
			blocks.get(x).update();
			if (x == 0){
				if(blocks.get(x).direction==0) {
					if(blocks.get(0).posY - randomY > -1 && blocks.get(0).posY - randomY < 3 && blocks.get(0).posX == randomX) {
						blocks.get(x).setFill(new ImagePattern(new Image("file:headupeat.png")));
					}else {
						blocks.get(x).setFill(new ImagePattern(new Image("file:headup.png")));
					}
				}else if(blocks.get(x).direction==1) {
					if(randomX - blocks.get(0).posX > -1 && randomX - blocks.get(0).posX < 3 && blocks.get(0).posY == randomY) {
						blocks.get(x).setFill(new ImagePattern(new Image("file:headrighteat.png")));
					}else {
						blocks.get(x).setFill(new ImagePattern(new Image("file:headright.png")));
					}
				}else if(blocks.get(x).direction==2) {
					if(randomY - blocks.get(0).posY > -1 && randomY - blocks.get(0).posY < 3 && blocks.get(0).posX == randomX) {
						blocks.get(x).setFill(new ImagePattern(new Image("file:headdowneat.png")));
					}else {
						blocks.get(x).setFill(new ImagePattern(new Image("file:headdown.png")));
					}
				}else if(blocks.get(x).direction==3) {
					if(blocks.get(0).posX - randomX > -1 && blocks.get(0).posX - randomX < 3 && blocks.get(0).posY == randomY) {
						blocks.get(x).setFill(new ImagePattern(new Image("file:headlefteat.png")));
					}else {
						blocks.get(x).setFill(new ImagePattern(new Image("file:headleft.png")));
					}
				}
			}else if(x != blocks.size()-1){
				if(blocks.get(x).posX < blocks.get(x).oldPosX || blocks.get(x).posX > blocks.get(x).oldPosX) {
					
					blocks.get(x).setFill(new ImagePattern(new Image("file:bodyhori.png")));
				}else {
					blocks.get(x).setFill(new ImagePattern(new Image("file:bodyvert.png")));
				}
				
				if((blocks.get(x-1).posX - blocks.get(x).posX ==1 &&  blocks.get(x).oldPosY -blocks.get(x).posY ==1)
							||
				  (blocks.get(x).oldPosX - blocks.get(x).posX ==1 && blocks.get(x-1).posY - blocks.get(x).posY ==1)
							||
				  (blocks.get(x).posX - blocks.get(x-1).posX > 10 && blocks.get(x).oldPosY - blocks.get(x).posY ==1 && blocks.get(x).posX == 69)
							||
				  (blocks.get(x).posX - blocks.get(x).oldPosX > 10 && blocks.get(x-1).posY - blocks.get(x).posY ==1 && blocks.get(x).posX == 69)
							||
				  (blocks.get(x).posY - blocks.get(x-1).posY > 10 && blocks.get(x).oldPosX - blocks.get(x).posX ==1 && blocks.get(x).posY == 34)
							||
				  (blocks.get(x).posY - blocks.get(x).oldPosY > 10 && blocks.get(x-1).posX - blocks.get(x).posX ==1 && blocks.get(x).posY == 34)
							||
				  (blocks.get(x).posX - blocks.get(x-1).posX > 10 && blocks.get(x).posY - blocks.get(x).oldPosY > 10 && blocks.get(x).posX == 69)
							||	
				  (blocks.get(x).posY - blocks.get(x-1).posY > 10 && blocks.get(x).posX - blocks.get(x).oldPosX > 10 && blocks.get(x).posX == 69)) {
					
						blocks.get(x).setFill(new ImagePattern(new Image("file:bodyrightdown.png")));
						
				}else if((blocks.get(x).posX - blocks.get(x).oldPosX ==1 &&  blocks.get(x-1).posY - blocks.get(x).posY ==1)
							||
						(blocks.get(x).posX - blocks.get(x-1).posX ==1 && blocks.get(x).oldPosY - blocks.get(x).posY ==1)						
							||
						(blocks.get(x-1).posX - blocks.get(x).posX > 10 && blocks.get(x).oldPosY - blocks.get(x).posY ==1 && blocks.get(x).posX == 0)
							||
						(blocks.get(x).oldPosX - blocks.get(x).posX > 10 && blocks.get(x-1).posY - blocks.get(x).posY ==1 && blocks.get(x).posX == 0)
							||
						(blocks.get(x).posY - blocks.get(x).oldPosY > 10 && blocks.get(x).posX - blocks.get(x-1).posX ==1 && blocks.get(x).posY == 34)
							||
						(blocks.get(x).posY - blocks.get(x-1).posY > 10 && blocks.get(x).posX - blocks.get(x).oldPosX ==1 && blocks.get(x).posY == 34)
						    ||
						(blocks.get(x-1).posX - blocks.get(x).posX > 10 && blocks.get(x).posY - blocks.get(x).oldPosY > 10 && blocks.get(x).posY == 34)
							||	
						(blocks.get(x).posY - blocks.get(x-1).posY > 10 && blocks.get(x).oldPosX - blocks.get(x).posX > 10 && blocks.get(x).posY == 34)) {
					
								blocks.get(x).setFill(new ImagePattern(new Image("file:bodyleftdown.png")));
								
						}else if((blocks.get(x).oldPosX - blocks.get(x).posX ==1 && blocks.get(x).posY - blocks.get(x-1).posY ==1)
									||
								 (blocks.get(x-1).posX - blocks.get(x).posX == 1&& blocks.get(x).posY - blocks.get(x).oldPosY ==1)						
								 	||
								 (blocks.get(x-1).posX - blocks.get(x).posX ==1 && blocks.get(x).oldPosY - blocks.get(x).posY > 10 && blocks.get(x).posY == 0)
								 	||
								 (blocks.get(x).oldPosX - blocks.get(x).posX ==1 && blocks.get(x-1).posY - blocks.get(x).posY >10 && blocks.get(x).posY == 0)
								 	||
								 (blocks.get(x).posY - blocks.get(x-1).posY ==1 && blocks.get(x).posX - blocks.get(x).oldPosX >10 && blocks.get(x).posX == 69)
								 	||
								 (blocks.get(x).posY - blocks.get(x).oldPosY ==1 && blocks.get(x).posX - blocks.get(x-1).posX >10 && blocks.get(x).posX == 69)
								 	||
								 (blocks.get(x).posX - blocks.get(x-1).posX > 10 && blocks.get(x).oldPosY - blocks.get(x).posY > 10 && blocks.get(x).posX == 69)
								 	||	
								 (blocks.get(x-1).posY - blocks.get(x).posY > 10 && blocks.get(x).posX - blocks.get(x).oldPosX > 10 && blocks.get(x).posX == 69)) {
							
										blocks.get(x).setFill(new ImagePattern(new Image("file:bodyrightup.png")));
										
							}else if((blocks.get(x).posX - blocks.get(x-1).posX == 1 && blocks.get(x).posY - blocks.get(x).oldPosY ==1)
										||
									 (blocks.get(x).posY - blocks.get(x-1).posY == 1 && blocks.get(x).posX - blocks.get(x).oldPosX ==1)						
									 	||
									 (blocks.get(x).oldPosX - blocks.get(x).posX >10 && blocks.get(x).posY - blocks.get(x-1).posY ==1 && blocks.get(x).posX == 0)
									 	||
									 (blocks.get(x-1).posX - blocks.get(x).posX >10 && blocks.get(x).posY - blocks.get(x).oldPosY ==1 && blocks.get(x).posX == 0)
									 	||
							 		 (blocks.get(x).oldPosY - blocks.get(x).posY > 10 && blocks.get(x).posX - blocks.get(x-1).posX ==1 && blocks.get(x).posY == 0)
							 		 	||
					 		 		 (blocks.get(x-1).posY - blocks.get(x).posY >10 && blocks.get(x).posX - blocks.get(x).oldPosX ==1 && blocks.get(x).posY == 0)
					 		 		 	||
				 		 		 	 (blocks.get(x-1).posX - blocks.get(x).posX > 10 && blocks.get(x).oldPosY - blocks.get(x).posY > 10 && blocks.get(x).posX == 0)
				 		 		 	 	||	
				 		 		 	 (blocks.get(x-1).posY - blocks.get(x).posY > 10 && blocks.get(x).oldPosX - blocks.get(x).posX > 10 && blocks.get(x).posX == 0)) {
								
											blocks.get(x).setFill(new ImagePattern(new Image("file:bodyleftup.png")));
								}
						
						
			}else {
				if(blocks.get(x).posX - blocks.get(x-1).posX ==1 || (blocks.get(x-1).posX- blocks.get(x).posX > 10 && blocks.get(x).posX == 0)) {
					blocks.get(x).setFill(new ImagePattern(new Image("file:tailleft.png")));
				}else if(blocks.get(x-1).posX - blocks.get(x).posX ==1 || (blocks.get(x).posX - blocks.get(x-1).posX > 10 && blocks.get(x).posX == 69)){
					blocks.get(x).setFill(new ImagePattern(new Image("file:tailright.png")));
				 }else if( blocks.get(x-1).posY - blocks.get(x).posY ==1 || (blocks.get(x).posY - blocks.get(x-1).posY > 10 && blocks.get(x).posY == 34)){
					blocks.get(x).setFill(new ImagePattern(new Image("file:taildown.png")));
				  }else if(blocks.get(x).posY - blocks.get(x-1).posY ==1 || (blocks.get(x-1).posY - blocks.get(x).posY > 10 && blocks.get(x).posY == 0) ){
					blocks.get(x).setFill(new ImagePattern(new Image("file:tailup.png")));
				  }
			 }
		}
		
		if(isEaten(f)) {
			Sound.biteSound();
			score += 50;
			addFood();
			addnewBlock();
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
					Sound.playDeathSound();
					return true;
				}
			}
		}
		for (Block b: obsBlocks) {
				if(b.posX == snake.head.posX && b.posY == snake.head.posY) {
					Sound.playDeathSound();
					return true;
				}
		}
		return false;
	}
	
}