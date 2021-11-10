import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class HighScoreScene extends Scene {
    private staticThing bg;
    private Text text1=new Text();
    private ArrayList<Integer> high_scores = new ArrayList<Integer>(Arrays.asList(0, 0, 0,0,0));
    private ArrayList<Text> scores= new ArrayList<Text>(Arrays.asList(text1));

    public HighScoreScene(Group g){
        super(g,1400,706);
        bg=new staticThing("C:\\Users\\cheik\\IdeaProjects\\Project_runner_java\\img\\high_score.png",1400,706);
        g.getChildren().add(0, bg.getBackView());

        //high_scores.add(readFile("C:\\Users\\cheik\\IdeaProjects\\Project_runner_java\\img\\high_score.txt"));
        int j=0;
        Text k=new Text();
        for (int i = 0; i < 5; i++)
        {
            j=i+1;
            k=new Text();
            k.setX(990);
            k.setY(95+i*100);
            k.setText("n°"+j+":        "+high_scores.get(i));
            k.setFill(Color.BLACK);
            k.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 30));
            scores.add(i,k);
            g.getChildren().add(k);
        }

    }
    public void update(){
        high_scores.add(readFile("C:\\Users\\cheik\\IdeaProjects\\Project_runner_java\\img\\high_score.txt"));
        System.out.println(readFile("C:\\Users\\cheik\\IdeaProjects\\Project_runner_java\\img\\high_score.txt"));

        Collections.sort(high_scores, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        });

        int j=0;
        for (Text i:scores )
        {
            j++;
            i.setText("n°"+j+":        "+high_scores.get(j-1));
        }

    }
    public int readFile(String filename){
        int dat=0;
        try {
            File myObj = new File(filename);

            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                dat=Integer.parseInt(data);
                System.out.println(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return dat;
    }
}
