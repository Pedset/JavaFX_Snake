package Controller;

import Model.Snake;
import Model.Sound;
import View.GameInterface;

import javafx.animation.AnimationTimer;

public class GameTimer {

	static long then = System.nanoTime();
	static AnimationTimer timer;

	public static void tick() {
		DifficultyCtrl.checkSpeed();
		timer = new AnimationTimer() {
			@Override
			public void handle(long now) {
				if (now - then > 1000000000 / DifficultyCtrl.getSpeed()) {
					GameInterface.getField().update();
					then = now;
					GameCtrls.setChanged(false);

					if (GameCtrls.isHasNext()) {
						GameCtrls.setDirection(GameInterface.getField().snake, GameCtrls.getNextUpdate());
						GameCtrls.setHasNext(false);
					}
					if (Snake.isDead()) {
						Sound.playDeathSound();
						stop();
						Sound.stopAudio();
						GameInterface.afterDeath();
					}
				}
			}
		};
		timer.start();
	}

	public static void startTheTimer() {
		timer.start();
	}

	public static void stopTheTimer() {
		timer.stop();
	}
}
