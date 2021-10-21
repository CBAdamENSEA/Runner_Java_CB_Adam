import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class staticThing {
    private double w;
    private double l;
    private ImageView backView;

    public double getW() {
        return w;
    }

    public double getL() {
        return l;
    }

    public staticThing(String fileName, double w, double l) {
        this.w=w;
        this.l=l;
        this.backView=new ImageView(new Image(fileName));
    }

    public ImageView getBackView() {
        return backView;
    }
}
