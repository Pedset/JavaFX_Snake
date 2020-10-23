import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main1 extends Application {

    static int block_size = 20;
    int field_width = 70, field_height = 35;
    Field field;

    public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		
		VBox verticalBox = new VBox(10);
		verticalBox.setPadding(new Insets(5));
		
		//Creates field
		field = new Field(field_width, field_height);
		Label score = new Label("Score: 0");
		score.setFont(Font.font("tahoma", 20));
		verticalBox.getChildren().addAll(score, field);
		Scene scene = new Scene(verticalBox);
		
		stage.setResizable(false);
		stage.setScene(scene);
		stage.show();
	}
}
