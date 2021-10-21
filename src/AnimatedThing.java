import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

abstract public class AnimatedThing {
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    private double x;
    private double y;

    public ImageView getAnimatedView() {
        return animatedView;
    }

    private ImageView animatedView;
    private int att;
    private int index;
    private double dt;
    private int indM;
    private int w_size;
    private int offs;


    public AnimatedThing(double x, double y, double dt, String filename) {

        this.x = x;
        this.y = y;
        this.animatedView =new ImageView(new Image(filename));
        animatedView.setViewport(new Rectangle2D(20,2,60,97));
        att=0; //running
        index=0;
        this.dt = dt;
        indM=5;
        w_size= 80;
        offs = 84;
        animatedView.setViewport(new Rectangle2D(4+index*offs,2,w_size,97));
    }
    void update(long time, double elapsedTime){
        index= (int) (((time/100000000))%6);
        animatedView.setViewport(new Rectangle2D(4+index*offs,2,w_size,97));

    }
}
