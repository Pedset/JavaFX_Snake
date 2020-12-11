package Controller;

import Model.Block;
import Model.Field;
import Model.Snake;
import View.GameInterface;
import javafx.scene.input.KeyCode;

public class GameCtrls {
	static int nextUpdate;
	public static boolean hasNext = false;
	static boolean pause = false;
	static boolean changed = false;

	public static void ctrls() {

		GameInterface.getScene().setOnKeyPressed(e -> {
			Field localF = GameInterface.getField();
			int dir = localF.snake.getDirection();

			if (e.getCode().equals(KeyCode.ESCAPE)) {
				if (pause) {
					pause = false;
					GameTimer.startTheTimer();
				} else {
					pause = true;
					GameTimer.stopTheTimer();
				}
			} else if (e.getCode().equals(KeyCode.UP) && dir != Block.DOWN) {
				setDirection(localF.snake, Block.UP);
			} else if (e.getCode().equals(KeyCode.DOWN) && dir != Block.UP) {
				setDirection(localF.snake, Block.DOWN);
			} else if (e.getCode().equals(KeyCode.RIGHT) && dir != Block.LEFT) {
				setDirection(localF.snake, Block.RIGHT);
			} else if (e.getCode().equals(KeyCode.LEFT) && dir != Block.RIGHT) {
				setDirection(localF.snake, Block.LEFT);
			}
		});
	}

	public static void setDirection(Snake s, int d) {
		if (!changed) {
			s.setDirection(d);
			changed = true;
		} else {
			hasNext = true;
			nextUpdate = d;
		}
	}

	public static void setChanged(boolean changed) {
		GameCtrls.changed = changed;
	}

	public static void setHasNext(boolean hasNext) {
		GameCtrls.hasNext = hasNext;
	}

	public static int getNextUpdate() {
		return nextUpdate;
	}

	public static boolean isHasNext() {
		return hasNext;
	}

}
