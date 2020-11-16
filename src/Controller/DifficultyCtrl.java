package Controller;

import View.GameInterface;

public class DifficultyCtrl {

	private static short speed = 8;
	private static short difficulty = 8;
	private static short multiplier = 2;
	private static String lvl = "Medium";

	public static short getSpeed() {
		return speed;
	}

	public static String getLvl() {
		return lvl;
	}

	public static void setLvl(String lvl) {
		DifficultyCtrl.lvl = lvl;
		switch (lvl) {

		case "Easy": {
			DifficultyCtrl.multiplier = 4;
			DifficultyCtrl.difficulty = 5;
			break;
		}
		case "Medium": {
			DifficultyCtrl.multiplier = 3;
			DifficultyCtrl.difficulty = 8;
			break;
		}
		case "Hard": {
			DifficultyCtrl.multiplier = 2;
			DifficultyCtrl.difficulty = 12;
			break;
		}
		case "Expert": {
			DifficultyCtrl.multiplier = 1;
			DifficultyCtrl.difficulty = 16;
		}
		}
	}

	public static void checkSpeed() {
		DifficultyCtrl.speed = (short) ((GameInterface.getField().score / (100 * DifficultyCtrl.multiplier))
				+ DifficultyCtrl.difficulty);
	}

}
