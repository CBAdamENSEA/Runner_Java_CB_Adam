import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;


public class Hellodemo extends Application{
        public void start(Stage primaryStage){
            primaryStage.setTitle("Runner Game");

            Group root2 = new Group();

            WelcomeScene ws= new WelcomeScene(root2, primaryStage);





            primaryStage.setScene(ws);
            primaryStage.show();

        }
        public static void main(String[] args) {
            launch(args);


        }
    }
