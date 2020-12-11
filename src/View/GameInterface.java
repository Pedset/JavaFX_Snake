package View;

import Controller.DifficultyCtrl;
import Controller.GameCtrls;
import Controller.GameTimer;
import Controller.MenuCtrls;
import Model.Field;
import Model.Obstacle;
import Model.Snake;
import Model.Sound;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class GameInterface {
	public static int block_size = 20;
	static int field_width = 70;
	static int field_height = 35;
	static int il = 5;
	static Stage gameStage;
	static Field field;
	static Label score;
	static Image image = new Image("file:Images/bg2.jpg");
	static VBox verticalBox;
	static Scene scene;

	public static Scene getScene() {
		return scene;
	}

	public static void setScore() {
		score.setText("Difficulty level: " + DifficultyCtrl.getLvl() + "    Speed: " + DifficultyCtrl.getSpeed()
				+ "km h\u207b\u00B9" + "    Score: " + field.score);
	}

	public static void startingGameInterface(Stage gS) {

		gameStage = gS;

		field = new Field(field_width, field_height);
		field.addObs(new Obstacle(field));
		field.addSnake(new Snake(il, field));

		score = new Label();
		score.setStyle("-fx-text-fill: #ffffff");
		score.setFont(Font.font("tahoma", 20));

		ImageView img = new ImageView(image);
		img.setFitWidth(1408);
		img.setFitWidth(744);

		verticalBox = new VBox(10);
		verticalBox.setPadding(new Insets(5));
		verticalBox.getChildren().addAll(score, field);
		verticalBox.setBackground(new Background(new BackgroundImage(image, BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
				new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, true, true))));

		scene = new Scene(verticalBox);

		setScore();
		Sound.playSound();
		GameCtrls.ctrls();
		gameStage.setScene(scene);
		GameTimer.tick();
	}

	public static Field getField() {
		return field;
	}

	public static void afterDeath() {

		verticalBox.getChildren().clear();
		verticalBox.setBackground(Background.EMPTY);
		MenuInterface.addBackground();
		Sound.menuDeathSound();
		MenuInterface.getScene().setRoot(MenuInterface.createContent(MenuCtrls.getMenuDataDeath(),
				"    You Died \nScore Points: " + field.score, 78));
		MenuInterface.getPrimaryStage().setScene(MenuInterface.getScene());

	}

}
