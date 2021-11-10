import javafx.scene.Group;
import javafx.scene.Scene;

public class InstructionsScene extends Scene {
    private staticThing bg;
    public InstructionsScene(Group g){
        super(g,1400,706);
        bg=new staticThing("C:\\Users\\cheik\\IdeaProjects\\Project_runner_java\\img\\instructions.png",1400,706);
        g.getChildren().add(bg.getBackView());
    }
}
