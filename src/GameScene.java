import javafx.animation.AnimationTimer;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class GameScene extends Scene {
    private Camera camera;
    private staticThing left;
    private staticThing right;
    private staticThing hearts;
    private Hero myHero;
    private int numberOfLives;
    private long lastTime=0;

    AnimationTimer timer = new AnimationTimer()
    {
        @Override
        public void handle(long time){
            double elapsedTime=(time-lastTime)/1000000000;
            myHero.update(time,elapsedTime);
            //camera.update(elapsedTime);
            render();
            lastTime=time;
        }
    };
    static void update(long time){

    }

    public GameScene(Group g) {
        super(g);

        numberOfLives=2;
        left = new staticThing("C:\\Users\\cheik\\IdeaProjects\\Project_runner_java\\img\\desert.png",800,400);
        right = new staticThing("C:\\Users\\cheik\\IdeaProjects\\Project_runner_java\\img\\desert.png",800,400);
        myHero=new Hero(1350,250,0,"C:\\Users\\cheik\\IdeaProjects\\Project_runner_java\\img\\heros.png");

        hearts= new staticThing("C:\\Users\\cheik\\IdeaProjects\\Project_runner_java\\img\\hearts.png",81,27);
        g.getChildren().add(left.getBackView());
        g.getChildren().add(right.getBackView());
        g.getChildren().add(myHero.getAnimatedView());
        g.getChildren().add(hearts.getBackView());
        camera = new Camera(1200,0,myHero);
        render();
        timer.start();
    }

    void render(){

        double offset = camera.getX()%left.getW();
        left.getBackView().setViewport(new Rectangle2D(offset, 0, left.getW()-offset, left.getL()));
        right.getBackView().setX(right.getW()-offset);
        hearts.getBackView().setViewport(new Rectangle2D(0,0 , (numberOfLives*27)+1,27 ));
        //hearts.getBackView().setX();
        myHero.getAnimatedView().setX(myHero.getX()-camera.getX());
        myHero.getAnimatedView().setY(myHero.getY()-camera.getY());

    }
}
