package Model;

import javafx.beans.binding.Bindings;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Text;

	public class MenuSnakeMenuItem extends Pane{
		private Text text;
	    private Effect shadow = new DropShadow(5, Color.WHITE);
	    private Effect blur = new BoxBlur(1, 1, 2);

	    public MenuSnakeMenuItem(String name) {
	        Polygon bg = new Polygon(
	                
	                0,0,
	                200, 0,
	                220, 15,
	                200, 30,
	                0, 30
	        );
	        bg.setStroke(Color.color(1, 1, 1, 0.75));
	        bg.setEffect(new GaussianBlur());

	        bg.fillProperty().bind(
	                Bindings.when(pressedProperty())
	                        .then(Color.rgb(0, 255, 0, 0.25))
	                        .otherwise(Color.rgb(1,1,1, .50))
	        );
	        bg.setOnMouseClicked(e->{
	        	Sound.playMouseClick();
	        });
	        bg.setOnMouseEntered(e->{
	        	Sound.playMouseHover();
	        });
	       

	        text = new Text(name);
	        text.setTranslateX(5);
	        text.setTranslateY(20);
	        text.setFill(Color.rgb(66,255,37, 0.99));

	        text.effectProperty().bind(
	                Bindings.when(hoverProperty())
	                        .then(shadow)
	                        .otherwise(blur)
	        );
	        text.setOnMouseClicked(e->{
	        	Sound.playMouseClick();
	        });

	        getChildren().addAll(bg, text);
	    }

	    public void setOnAction(Runnable action) {
	        setOnMouseClicked(e -> action.run());
	    }
	
	
}
