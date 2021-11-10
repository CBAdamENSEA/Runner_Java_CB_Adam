import javafx.animation.AnimationTimer;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;


public class GameScene extends Scene {
    private Camera camera;
    private staticThing left;
    private staticThing right;
    private staticThing hearts;
    private staticThing gameOver;
    private Button startAgainButton;
    private Button homeButton;
    Text score;
    int scoreTotal;
    private ArrayList<Foe> foeList=null;
    //private Foe foe;
    private Hero myHero;
    private int numberOfLives;
    private int numberOfFoes;
    private long lastTime=0;
    private int ON=0;
    private boolean test=false;
    public HighScoreScene highScoreScene;
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

    public int getScoreTotal() {
        return scoreTotal;
    }

    public GameScene(Group g,Button saButton, Button hButton,HighScoreScene hs) {
        super(g,1400,706);
        highScoreScene=hs;
        startAgainButton=saButton;
        homeButton=hButton;


        scoreTotal=0;
        score= new Text();
        score.setX(100);
        score.setText("score= "+scoreTotal);
        score.setY(20);
        score.setFill(Color.WHITE);
        score.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
        numberOfLives=3;
        //left = new staticThing("C:\\Users\\cheik\\IdeaProjects\\Project_runner_java\\img\\desert.png",800,400);
        //right = new staticThing("C:\\Users\\cheik\\IdeaProjects\\Project_runner_java\\img\\desert.png",800,400);
        //myHero=new Hero(800,250,0,"C:\\Users\\cheik\\IdeaProjects\\Project_runner_java\\img\\heros.png"); 2800
        left = new staticThing("C:\\Users\\cheik\\IdeaProjects\\Project_runner_java\\img\\background.png",2800,706);
        right = new staticThing("C:\\Users\\cheik\\IdeaProjects\\Project_runner_java\\img\\background.png",2800,706);
        myHero=new Hero(800,300,0,"C:\\Users\\cheik\\IdeaProjects\\Project_runner_java\\img\\ENSEA_Hero2.png");
        gameOver = new staticThing("C:\\Users\\cheik\\IdeaProjects\\Project_runner_java\\img\\GAME_OVER.png",340,340);
        gameOver.getBackView().setX(530);
        gameOver.getBackView().setY(183);
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
        g.getChildren().add(gameOver.getBackView());
        g.getChildren().add(startAgainButton);
        g.getChildren().add(homeButton);
        startAgainButton.setVisible(false);
        homeButton.setVisible(false);
        gameOver.hideImage();
        //g.getChildren().add(foe.getAnimatedView());
        camera = new Camera(1200,0,myHero);
        timer.start();
        long time=0;
        render(time);




    }
    public void writeFile(String filename, int text)
    {
        try {
            File myObj = new File(filename);
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        try {
            FileWriter myWriter = new FileWriter("C:\\Users\\cheik\\IdeaProjects\\Project_runner_java\\img\\high_score.txt");
            myWriter.write(""+text);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
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
            System.out.println("test= "+test);
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
                if (((numberOfLives==0)&(Math.abs(camera.getVx())<0.01)&(camera.getX()< myHero.getX()))) {
                    timer.stop();
                    highScoreScene.update();
                }
                writeFile("C:\\Users\\cheik\\IdeaProjects\\Project_runner_java\\img\\high_score.txt",scoreTotal);

                numberOfLives=0;
                System.out.println("Game Over");
                myHero.cry();
                gameOver.showImage();


                startAgainButton.setOnAction(e -> {
                    System.out.println("Start Again");
                    myHero.startAgain(); //A changer (mêmes obstacles)
                    timer.start();
                    camera.startAgain();
                    startAgainButton.setVisible(false);
                    homeButton.setVisible(false);
                    gameOver.hideImage();
                    numberOfLives=3;
                });
                startAgainButton.setVisible(true);
                homeButton.setVisible(true);


                /*
                this.setOnKeyPressed(ev -> {
                    if (ev.getCode() == KeyCode.ENTER) {
                        System.out.println("Start Again");
                        myHero.startAgain(); //A changer (mêmes obstacles)
                        gameOver.hideImage();
                        numberOfLives=3;
                    }
                });
                */


            }

        }
        else {

        }
        scoreTotal=(int)((myHero.getX()-800)/100);
        score.setText("SCORE= "+scoreTotal);
        System.out.println(score.getText()+ " Lives="+numberOfLives);
        //foe.getAnimatedView().setX(foe.getX()-camera.getX());
        //foe.getAnimatedView().setY(foe.getY()-camera.getY());



    }
}
