import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main1 extends Application {

	static int block_size = 20;
	int field_width = 70, field_height = 35;
	Field field;
	int il = 5;
	long then = System.nanoTime();

	boolean changed = false;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {

		VBox verticalBox = new VBox(10);
		verticalBox.setPadding(new Insets(5));

		// Creates field
		field = new Field(field_width, field_height);
		Label score = new Label("Score: 0");
		score.setFont(Font.font("tahoma", 20));
		verticalBox.getChildren().addAll(score, field);
		Scene scene = new Scene(verticalBox);

		AnimationTimer timer = new AnimationTimer() {

			@Override
			public void handle(long now) {
				if (now - then > 1000000000 / 8) {
					field.update();
					then = now;
					score.setText("Score: " + field.score);
					changed = false;

					if (field.isDead()) {
						stop();
//					stop sound
						Alert al = new Alert(AlertType.INFORMATION);
						al.setHeaderText("You Lost!");
						al.setContentText("Your Score is " + field.score);
						Platform.runLater(al::showAndWait);

						al.setOnHiding(e -> {
							verticalBox.getChildren().clear();
							field = new Field(field_width, field_height);

							score.setText("Score: 0");
							verticalBox.getChildren().addAll(score, field);
							start();
							// play sound
						});

					}
				}
			}
		};
		timer.start();

		Image image = new Image("file:bg2.jpg");
		ImageView img = new ImageView();
		img.setImage(image);
		img.fitWidthProperty().bind(stage.widthProperty());
		img.fitHeightProperty().bind(stage.heightProperty());
		verticalBox.setBackground(new Background(new BackgroundImage(image, BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
				new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, true, true))));

		scene.setOnKeyPressed(e -> {
			if (e.getCode().equals(KeyCode.UP) && field.snake.getDirection() != Block.DOWN) {
				setDirection(field.snake, Block.UP);
			} else if (e.getCode().equals(KeyCode.DOWN) && field.snake.getDirection() != Block.UP) {
				setDirection(field.snake, Block.DOWN);
			} else if (e.getCode().equals(KeyCode.RIGHT) && field.snake.getDirection() != Block.LEFT) {
				setDirection(field.snake, Block.RIGHT);
			} else if (e.getCode().equals(KeyCode.LEFT) && field.snake.getDirection() != Block.RIGHT) {
				setDirection(field.snake, Block.LEFT);
			}
		});

		stage.setResizable(false);
		stage.setScene(scene);
		stage.show();
	}

	public void setDirection(Snake s, int d) {
		if (!changed) {
			s.setDirection(d);
			changed = true;
		}
	}
}
