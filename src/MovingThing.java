import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

public class MovingThing {
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    private double x;
    private double y;
    private Rectangle rectangle;

    public ImageView getAnimatedView() {
        return animatedView;
    }

    private ImageView animatedView;
    void update(Camera camera){
        getAnimatedView().setX(x-camera.getX());
        getAnimatedView().setY(y-camera.getY());


    }
    public MovingThing(double x, double y, String filename){
        this.x = x;
        this.y = y;
        this.animatedView =new ImageView(new Image(filename));
        rectangle=new Rectangle();

    }
    Rectangle2D getHitBox(){
        return (new Rectangle2D(x,y,animatedView.getImage().getWidth(),animatedView.getImage().getHeight()));
    }
}
