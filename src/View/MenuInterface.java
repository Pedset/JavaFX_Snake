package View;

import javafx.animation.*;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.util.Pair;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import java.util.List;

import Controller.MenuCtrls;
import Controller.VolumeCtrl;
import Model.MenuSnakeMenuItem;
import Model.Sound;

public class MenuInterface extends Application {

	private static final int WIDTH = 1408;
	private static final int HEIGHT = 744;
	static ImageView imageView;
	static Scene scene;

	public static Scene getScene() {
		return scene;
	}

	static Stage primaryStage;

	public static Stage getPrimaryStage() {
		return primaryStage;
	}

	static double lineX = WIDTH / 2 - 100;
	static double lineY = HEIGHT / 3 + 50;

	private static Pane root = new Pane();
	private static VBox menuBox = new VBox(-5);
	private static Line line;

	public static void startingMenu(String[] args) {
		launch(args);

	}

	public static void removeList(int index) {
		int x = menuBox.getChildren().size();
		for (int i = 0; i < x; i++) {
			menuBox.getChildren().remove(menuBox.getChildren().get(0));
		}
		int y = root.getChildren().size();
		for (int i = index; i < y; i++) {
			root.getChildren().remove(root.getChildren().get(index));
		}
	}

	public static void changeTitle(String str) {

		root.getChildren().remove(root.getChildren().size() - 1);
		addTitle(str);
	}

	public static void addSlider() {
		Slider sl1 = new Slider();
		sl1.setMin(0);
		sl1.setMax(100);
		sl1.setValue(VolumeCtrl.getVolume());
		sl1.setShowTickLabels(true);
		sl1.setShowTickMarks(true);
		sl1.setBlockIncrement(5);
		sl1.setStyle("-fx-background-color: rgb(0,255,0, 0.35);");

		menuBox.getChildren().add(sl1);
		sl1.valueProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observableValue, Number oldValue, Number newValue) {
				VolumeCtrl.setVolume(newValue.byteValue());
				changeTitle("Sound Settings\n Volume: " + newValue.byteValue() + "%");
				Sound.playMouseHover();
				Sound.changeGainValumeMenu();
			}
		});
	}

	public static Parent createContent(List<Pair<String, Runnable>> menu, String title, int length) {

		addLine(lineX, lineY, length);
		addMenu(lineX + 5, lineY + 5, menu);
		addTitle(title);
		startAnimation();

		return root;
	}

	public static void addBackground() {

		imageView.setFitWidth(WIDTH);
		imageView.setFitHeight(HEIGHT);
		root.getChildren().add(imageView);
	}

	private static void addTitle(String txt) {
		Model.MenuSnakeTitle title = new Model.MenuSnakeTitle(txt);
		title.setTranslateX(WIDTH / 2 - title.getTitleWidth() / 2);
		title.setTranslateY(HEIGHT / 3);

		root.getChildren().add(title);
	}

	private static void addLine(double x, double y, int length) {
		line = new Line(x, y, x, y + length);
		line.setStrokeWidth(3);
		line.setStyle("-fx-stroke: rgb(66,255,37, 0.65);");
		line.setEffect(new DropShadow(5, Color.BLACK));
		line.setScaleY(0);
		root.getChildren().add(line);
	}

	private static void startAnimation() {
		ScaleTransition st = new ScaleTransition(Duration.seconds(0.5), line);
		st.setToY(1);
		st.setOnFinished(e -> {
			for (int i = 0; i < menuBox.getChildren().size(); i++) {
				Node n = menuBox.getChildren().get(i);

				TranslateTransition tt = new TranslateTransition(Duration.seconds(1 + i * 0.15), n);
				tt.setToX(0);
				tt.setOnFinished(e2 -> n.setClip(null));
				tt.play();
			}
		});
		st.play();
	}

	private static void addMenu(double x, double y, List<Pair<String, Runnable>> menu) {
		menuBox.setTranslateX(x);
		menuBox.setTranslateY(y);
		menu.forEach(data -> {
			MenuSnakeMenuItem item = new MenuSnakeMenuItem(data.getKey());
			item.setOnAction(data.getValue());
			item.setTranslateX(-300);
			Rectangle clip = new Rectangle(300, 30);
			clip.translateXProperty().bind(item.translateXProperty().negate());
			item.setClip(clip);
			menuBox.getChildren().addAll(item);
		});
		root.getChildren().add(menuBox);
	}

	@Override
	public void start(Stage pS) throws Exception {
		imageView = new ImageView(new Image(("File:Images/MenuBG.gif")));
		addBackground();
		scene = new Scene(createContent(MenuCtrls.getMenuData(), "Snake X: Road To VG\n      Main Menu", 154));
		primaryStage = pS;
		primaryStage.setTitle("Snake X: Road To VG [Version 2.0]");
		primaryStage.setScene(scene);
		primaryStage.getIcons().add(new Image("file:Images/snakeicon.png"));
		primaryStage.setResizable(false);
		primaryStage.show();
		Sound.playMenuSound();
	}

}
