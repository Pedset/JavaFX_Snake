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
	
	public void update() {
		for(int x = 0; x < blocks.size(); x++) {
			
			/// update block
		}
		
		// check if food is Eaten
	}
	
	public Field(int width, int height) {
		w= width;
		h= height;
		//
		setMinSize(w * Main1.block_size, h * Main1.block_size);
		setBackground(new Background(new BackgroundFill(Color.BLACK,null, null)));
		setBorder(new Border(new BorderStroke(Color.WHITE,BorderStrokeStyle.SOLID, null, new BorderWidths(1))));
		
	}
	
}