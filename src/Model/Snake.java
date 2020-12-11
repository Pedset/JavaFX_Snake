package Model;

import java.util.ArrayList;

import View.GameInterface;

public class Snake {
	ArrayList<Block> blocks = new ArrayList<>();
	
	Block head;
	Block tail;
	public Snake(int initial_length,Field f) {
		
		// Start point
		int initial_pos_X = f.getW() / 2;
		int initial_pos_Y = f.getH() / 2;
		
		// create head block
		head = new Block(initial_pos_X, initial_pos_Y, null, f);
		blocks.add(head);
		tail = head;
		// create body block (tail)
		for(int i = 1; i< initial_length; i++) {
			Block b = new Block(initial_pos_X + i, initial_pos_Y, tail, f);
			blocks.add(b);
			tail = b;
		}
	}
	// sets direction to head
	public void setDirection(int d) {
		head.direction = d;
	}
	
	
	// getter for direction
	public int getDirection() {
		return head.direction;
	}
	
	public static boolean isDead() {
		// snake body arraylist
		Field f = GameInterface.getField();
		for (int x = 0; x < Math.max(f.obsBlocks.size(),f.blocks.size()); x++) {
			if (x < f.obsBlocks.size()){
				if (f.obsBlocks.get(x).posX == f.snake.head.posX && f.obsBlocks.get(x).posY == f.snake.head.posY) {
					
					return true;
				}
			}
			if (x < f.blocks.size()){
				if (f.blocks.get(x) != f.snake.head) {
					if (f.blocks.get(x).posX ==f.snake.head.posX && f.blocks.get(x).posY == f.snake.head.posY) {
						
						return true;
						
					}
				}
			}
		}
		return false;

	}
	
}
