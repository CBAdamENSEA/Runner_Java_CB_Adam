import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;


public class Hellodemo extends Application{
        public void start(Stage primaryStage){
            primaryStage.setTitle("Runner Game");
            Group root = new Group();

            GameScene gs = new GameScene(root);

            primaryStage.setScene(gs);
            primaryStage.show();

        }
        public static void main(String[] args) {
            launch(args);


        }
    }
