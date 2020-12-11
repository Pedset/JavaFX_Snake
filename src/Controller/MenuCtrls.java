package Controller;

import java.util.Arrays;
import java.util.List;
import Model.Sound;
import View.GameInterface;
import View.MenuInterface;
import javafx.application.Platform;
import javafx.util.Pair;

public class MenuCtrls {
	
	public static List<Pair<String, Runnable>> getMenuDataVol() {
		return menuDataVol;
	}

	public static List<Pair<String, Runnable>> getMenuDataDif() {
		return menuDataDif;
	}

	public static List<Pair<String, Runnable>> getMenuData() {
		return menuData;
	}
	
	public static List<Pair<String, Runnable>> getMenuDataDeath() {
		return menuDataDeath;
	}

	private static List<Pair<String, Runnable>> menuDataDif = Arrays.asList(
			
            new Pair<String, Runnable>("Back", () -> {
            	MenuInterface.removeList(1);
            	MenuInterface.getScene().setRoot(MenuInterface.createContent(getMenuData(),"Snake X: Road To VG\n      Main Menu", 154));
            }),
            new Pair<String, Runnable>("Easy", () -> {
            	DifficultyCtrl.setLvl("Easy");
            	MenuInterface.changeTitle("Difficulty Level: Easy");
            	
            }),
            new Pair<String, Runnable>("Medium", () -> {
            	DifficultyCtrl.setLvl("Medium");
            	MenuInterface.changeTitle("Difficulty Level: Medium");
            	
            }),
            new Pair<String, Runnable>("Hard", () -> {
            	DifficultyCtrl.setLvl("Hard");
            	MenuInterface.changeTitle("Difficulty Level: Hard");
            	
            }),
            new Pair<String, Runnable>("Expert", () -> {
            	DifficultyCtrl.setLvl("Expert");
            	MenuInterface.changeTitle("Difficulty Level: Expert");
            })
    );

	private static List<Pair<String, Runnable>> menuDataVol = Arrays.asList(
			
            new Pair<String, Runnable>("Back", () -> {
            	MenuInterface.removeList(1);
            	MenuInterface.getScene().setRoot(MenuInterface.createContent(getMenuData(),"Snake X: Road To VG\n      Main Menu", 154));
            })
    );
	
	private static List<Pair<String, Runnable>> menuDataDeath = Arrays.asList(
			
            new Pair<String, Runnable>("Try Again", () -> {
            	MenuInterface.removeList(0);
            	Sound.stopMenuAudio();
            	Sound.stopMenuDeathAudio();
            	GameInterface.startingGameInterface(MenuInterface.getPrimaryStage());
            }),
            new Pair<String, Runnable>("Main Menu",  () -> {
            	Sound.stopMenuDeathAudio();
            	Sound.playMenuSound();
            	MenuInterface.removeList(1);
            	MenuInterface.getScene().setRoot(MenuInterface.createContent(getMenuData(),"Snake X: Road To VG\n      Main Menu", 154));
            })
	);
	
	private static List<Pair<String, Runnable>> menuData = Arrays.asList(
			
            new Pair<String, Runnable>("Start Game", () -> {
            	MenuInterface.removeList(0);
            	Sound.stopMenuAudio();
            	GameInterface.startingGameInterface(MenuInterface.getPrimaryStage());
            }),
            new Pair<String, Runnable>("Difficulty Level", () -> {
            	MenuInterface.removeList(1);
            	MenuInterface.getScene().setRoot(MenuInterface.createContent(getMenuDataDif(), "Difficulty Level: " + DifficultyCtrl.getLvl(), 193));
            }),
            new Pair<String, Runnable>("Sound Settings", () -> {
            	MenuInterface.removeList(1);
            	MenuInterface.getScene().setRoot(MenuInterface.createContent(getMenuDataVol(), "Sound Settings\n Volume:" + VolumeCtrl.getVolume() + "%", 84));
            	MenuInterface.addSlider();
            }),
            new Pair<String, Runnable>("Rage Quit", Platform::exit)
			);
}
