import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;


public class WelcomeScene extends Scene {
    private staticThing bg;
    public WelcomeScene(Group g){
        super(g,1400,706);
        bg = new staticThing("C:\\Users\\cheik\\IdeaProjects\\Project_runner_java\\img\\welcome_scene.png",1400,706);
        g.getChildren().add(bg.getBackView());
    }
}
