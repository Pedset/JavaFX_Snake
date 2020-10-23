import java.util.ArrayList;

import javafx.scene.layout.Pane;

public class Field extends Pane {
	private int w, h;
	ArrayList<Block> blocks = new ArrayList<>();
	
	
	public int getW() {
		return w;
	}

	public int getH() {
		return h;
	}
	
	private void addBlock(Block b) {

		getChildren().add(b);
		blocks.add(b);
	}
	
}