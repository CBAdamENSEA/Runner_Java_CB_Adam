import javafx.animation.AnimationTimer;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;


public class GameScene extends Scene {
    private Camera camera;
    private staticThing left;
    private staticThing right;
    private staticThing hearts;
    Text score;
    private ArrayList<Foe> foeList=null;
    //private Foe foe;
    private Hero myHero;
    private int numberOfLives;
    private int numberOfFoes;
    private long lastTime=0;
    private int ON=0;
    private boolean test=false;
    AnimationTimer timer = new AnimationTimer()
    {
        @Override
        public void handle(long time){
            double elapsedTime=(double) (time-lastTime)/1000000;
            if (elapsedTime>100) elapsedTime=0;

            myHero.update(time,elapsedTime);

            camera.update(elapsedTime);
            lastTime=time;
            render(time);

        }
    };
    static void update(long time){

    }

    public GameScene(Group g) {
        super(g,1400,706);
        score= new Text();
        score.setX(100);
        score.setText("score= 0");
        score.setY(20);
        score.setFill(Color.WHITE);
        score.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
        numberOfLives=3;
        //left = new staticThing("C:\\Users\\cheik\\IdeaProjects\\Project_runner_java\\img\\desert.png",800,400);
        //right = new staticThing("C:\\Users\\cheik\\IdeaProjects\\Project_runner_java\\img\\desert.png",800,400);
        //myHero=new Hero(800,250,0,"C:\\Users\\cheik\\IdeaProjects\\Project_runner_java\\img\\heros.png"); 2800
        left = new staticThing("C:\\Users\\cheik\\IdeaProjects\\Project_runner_java\\img\\background.png",2800,706);
        right = new staticThing("C:\\Users\\cheik\\IdeaProjects\\Project_runner_java\\img\\background.png",2800,706);
        myHero=new Hero(800,300,0,"C:\\Users\\cheik\\IdeaProjects\\Project_runner_java\\img\\ENSEA_Hero.png");

        Random r = new Random();
        numberOfFoes= r.nextInt(500-100) + 100;
        foeList=new ArrayList<Foe>();
        int min_dist=1000;
        for (int i=0;i<numberOfFoes;i++)
        {
            min_dist+=r.nextInt(3000-800) + 800;
            Foe newFoe= new Foe(min_dist,520,"C:\\Users\\cheik\\IdeaProjects\\Project_runner_java\\img\\obstacle1.png");
            foeList.add(newFoe);
        }

        hearts= new staticThing("C:\\Users\\cheik\\IdeaProjects\\Project_runner_java\\img\\hearts.png",81,27);
        hearts.getBackView().setX(5);
        //foe= new Foe(1900,520,0,"C:\\Users\\cheik\\IdeaProjects\\Project_runner_java\\img\\obstacle1.png");
        g.getChildren().add(left.getBackView());
        g.getChildren().add(right.getBackView());
        g.getChildren().add(hearts.getBackView());
        for (Foe sfoe: foeList)

        {
            g.getChildren().add(sfoe.getAnimatedView());
        }

        g.getChildren().add(myHero.getAnimatedView());
        g.getChildren().add(score);
        //g.getChildren().add(foe.getAnimatedView());
        camera = new Camera(1200,0,myHero);
        timer.start();
        long time=0;
        render(time);




    }

    void render(long time) {

        double offset = (camera.getX())%left.getW();
        left.getBackView().setViewport(new Rectangle2D(offset, 0, left.getW()-offset, left.getL()));
        right.getBackView().setX(right.getW()-offset);
        hearts.getBackView().setViewport(new Rectangle2D(0,0 , (numberOfLives*27)+1,27 ));
        //hearts.getBackView().setX();
        if (myHero.getAtt()==0) {
            this.setOnKeyPressed(ev -> {
                if (ev.getCode() == KeyCode.ENTER) {
                    System.out.println("RUN");
                    myHero.run();
                    ON = 1;
                }
            });
        }
        if (myHero.getAtt()==1) {
            this.setOnKeyPressed(ev -> {
                if (ev.getCode() == KeyCode.SPACE) {
                    System.out.println("JUMP");
                    myHero.jump();
                }
            });
        }
        myHero.getAnimatedView().setX(myHero.getX()-camera.getX());
        myHero.getAnimatedView().setY(myHero.getY()-camera.getY());
        //boolean test=false;
        Foe hitFoe=null;
        long now=0;

            for (Foe ufoe : foeList) {
                ufoe.update(camera);
                if (myHero.getHitBox().intersects(ufoe.getHitBox())) {
                    test = true;
                    hitFoe = ufoe;
                    now = time;


                }
                //System.out.println("test= "+test);
                //System.out.println("Hero  minx="+myHero.getHitBox().getMinX()+" miny="+myHero.getHitBox().getMinY()+" width="+myHero.getHitBox().getWidth()+" height="+myHero.getHitBox().getHeight());
            }

        long i=0;
        if (test)
        {
            myHero.stop();

            if (numberOfLives>1)
            {

                    test = false;
                    myHero.resumeGame(hitFoe);
                    numberOfLives -= 1;



            }
            else
            {
                numberOfLives=0;
                System.out.println("Game Over");
                this.setOnKeyPressed(ev -> {
                    if (ev.getCode() == KeyCode.ENTER) {
                        System.out.println("Start Again");
                        myHero.startAgain(); //A changer (mêmes obstacles)
                        numberOfLives=3;
                    }
                });


            }

        }
        else {

        }
        score.setText("SCORE= "+(int)((myHero.getX()-800)/100));
        System.out.println(score.getText());
        //foe.getAnimatedView().setX(foe.getX()-camera.getX());
        //foe.getAnimatedView().setY(foe.getY()-camera.getY());



    }
}
