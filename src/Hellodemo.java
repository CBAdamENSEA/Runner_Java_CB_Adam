import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;


public class Hellodemo extends Application{
        public void start(Stage primaryStage){
            primaryStage.setTitle("Runner Game");
            Group root = new Group();
            Group root2 = new Group();
            Group root3 =new Group();
            Group root4 =new Group();
            WelcomeScene ws= new WelcomeScene(root2);
            GameScene gs = new GameScene(root);

            Button startButton=newButton(1000,150,250,80,"START");
            Button hsButton=newButton(1000,280,250,80,"High Scores");
            Button instButton=newButton(1000,410,250,80,"Instructions");
            root2.getChildren().add(startButton);
            root2.getChildren().add(hsButton);
            root2.getChildren().add(instButton);
            startButton.setOnAction(e -> {
                primaryStage.setScene(gs);
            });






            primaryStage.setScene(ws);
            primaryStage.show();

        }
        public static void main(String[] args) {
            launch(args);


        }
        public Button newButton(double x, double y, double w, double h,String f)
        {
            Button b = new Button(f);
            b.setFont(Font.font("Arial", FontWeight.MEDIUM,30));
            b.setStyle("-fx-background-color: #000000; -fx-border-color: #ffffff;-fx-text-fill: #ffffff;");
            b.setLayoutX(x);
            b.setLayoutY(y);
            b.setPrefWidth(w);
            b.setPrefHeight(h);
            return b;
        }
    }
