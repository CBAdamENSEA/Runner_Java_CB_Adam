import javafx.animation.AnimationTimer;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;


public class GameScene extends Scene {
    private Camera camera;
    private staticThing left;
    private staticThing right;
    private staticThing hearts;
    //private Foe foe;
    private Hero myHero;
    private int numberOfLives;
    private long lastTime=0;
    AnimationTimer timer = new AnimationTimer()
    {
        @Override
        public void handle(long time){
            double elapsedTime=(double) (time-lastTime)/1000000;
            if (elapsedTime>100) elapsedTime=0;
            myHero.update(time,elapsedTime);
            camera.update(elapsedTime);
            lastTime=time;
            render();

        }
    };
    static void update(long time){

    }

    public GameScene(Group g) {
        super(g,1400,706);

        numberOfLives=3;
        //left = new staticThing("C:\\Users\\cheik\\IdeaProjects\\Project_runner_java\\img\\desert.png",800,400);
        //right = new staticThing("C:\\Users\\cheik\\IdeaProjects\\Project_runner_java\\img\\desert.png",800,400);
        //myHero=new Hero(800,250,0,"C:\\Users\\cheik\\IdeaProjects\\Project_runner_java\\img\\heros.png"); 2800
        left = new staticThing("C:\\Users\\cheik\\IdeaProjects\\Project_runner_java\\img\\background.png",2800,706);
        right = new staticThing("C:\\Users\\cheik\\IdeaProjects\\Project_runner_java\\img\\background.png",2800,706);
        myHero=new Hero(800,300,0,"C:\\Users\\cheik\\IdeaProjects\\Project_runner_java\\img\\ENSEA_Hero.png");

        hearts= new staticThing("C:\\Users\\cheik\\IdeaProjects\\Project_runner_java\\img\\hearts.png",81,27);
        //foe= new Foe(1900,520,0,"C:\\Users\\cheik\\IdeaProjects\\Project_runner_java\\img\\obstacle1.png");
        g.getChildren().add(left.getBackView());
        g.getChildren().add(right.getBackView());
        g.getChildren().add(myHero.getAnimatedView());
        g.getChildren().add(hearts.getBackView());
        //g.getChildren().add(foe.getAnimatedView());
        camera = new Camera(1200,0,myHero);
        timer.start();
        render();



    }

    void render(){

        double offset = (camera.getX())%left.getW();
        left.getBackView().setViewport(new Rectangle2D(offset, 0, left.getW()-offset, left.getL()));
        right.getBackView().setX(right.getW()-offset);
        hearts.getBackView().setViewport(new Rectangle2D(0,0 , (numberOfLives*27)+1,27 ));
        //hearts.getBackView().setX();
        this.setOnKeyPressed(ev -> {
            if (ev.getCode() == KeyCode.SPACE) {
                System.out.println("JUMP");
                myHero.jump();
            }
        });
        myHero.getAnimatedView().setX(myHero.getX()-camera.getX());
        myHero.getAnimatedView().setY(myHero.getY()-camera.getY());
        //foe.getAnimatedView().setX(foe.getX()-camera.getX());
        //foe.getAnimatedView().setY(foe.getY()-camera.getY());



    }
}
