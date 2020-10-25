import java.util.ArrayList;

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
	
		
}