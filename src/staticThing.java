import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class staticThing {
    private double w;
    private double l;
    private Image staticImage;
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
        this.staticImage=new Image(fileName);
        this.backView=new ImageView(staticImage);
    }
    public void showImage(){
        backView.setImage(staticImage);
    }
    public void hideImage(){
        backView.setImage(null);
    }

    public ImageView getBackView() {
        return backView;
    }
}
