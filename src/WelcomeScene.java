import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;


public class WelcomeScene extends Scene {
    Button startButton;
    public WelcomeScene(Group g, Stage primaryStage){
        super(g,1400,706);
        Button startButton=new Button("START");
        g.getChildren().add(startButton);
        startButton.setOnAction(e -> {
            Group root = new Group();
            GameScene gs = new GameScene(root);
            primaryStage.setScene(gs);
        });

    }
}
