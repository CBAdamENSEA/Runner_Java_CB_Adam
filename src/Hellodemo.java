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

            Group root2 = new Group();
            Group root3 =new Group();
            Group root4 =new Group();
            WelcomeScene ws= new WelcomeScene(root2);
            HighScoreScene hs=new HighScoreScene(root3);
            InstructionsScene is= new InstructionsScene(root4);



            Button startButton=newButton(1000,150,250,80,"START","b");
            Button hsButton=newButton(1000,280,250,80,"High Scores","b");
            Button instButton=newButton(1000,410,250,80,"Instructions","b");
            Button saButton=newButton(600,580,250,80,"Start Again","w");
            Button homeButton1=newButton(1000,580,250,80,"HOME","b");
            Button homeButton2=newButton(1000,580,250,80,"HOME","b");
            Button homeButton3=newButton(1000,580,250,80,"HOME","b");
            root3.getChildren().add(homeButton1);

            root4.getChildren().add(homeButton2);
            root2.getChildren().add(startButton);
            root2.getChildren().add(hsButton);
            root2.getChildren().add(instButton);

            instButton.setOnAction(e -> {
                primaryStage.setScene(is);
            });

            homeButton1.setOnAction(e -> {
                primaryStage.setScene(ws);
            });
            homeButton2.setOnAction(e -> {
                primaryStage.setScene(ws);
            });
            homeButton3.setOnAction(e -> {
                primaryStage.setScene(ws);
            });
            hsButton.setOnAction(e -> {
                primaryStage.setScene(hs);
                //hs.update();
            });

            startButton.setOnAction(e -> {
                Group root = new Group();
                GameScene gs = new GameScene(root,saButton,homeButton3,hs);
                primaryStage.setScene(gs);
            });






            primaryStage.setScene(ws);
            primaryStage.show();

        }
        public static void main(String[] args) {
            launch(args);


        }
        public Button newButton(double x, double y, double w, double h,String f,String c)
        {
            Button b = new Button(f);
            b.setFont(Font.font("Arial", FontWeight.MEDIUM,30));
            if (c=="w") b.setStyle("-fx-background-color: #ffffff; -fx-border-color: #000000;-fx-text-fill: #000000;");
            else b.setStyle("-fx-background-color: #000000; -fx-border-color: #ffffff;-fx-text-fill: #ffffff;");
            b.setLayoutX(x);
            b.setLayoutY(y);
            b.setPrefWidth(w);
            b.setPrefHeight(h);
            return b;
        }
    }
