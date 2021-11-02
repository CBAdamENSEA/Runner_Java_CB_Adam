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
    private double vy=0;
    private double ay=0;
    private double vx=0;
    private double ax=0;
    private double g=0.0027;
    private final double f=0.002;


    public AnimatedThing(double x, double y, double dt, String filename) {

        this.x = x;
        this.y = y;
        this.animatedView =new ImageView(new Image(filename));
        animatedView.setViewport(new Rectangle2D(20,2,60,97));
        att=0; //still
        index=0;
        this.dt = dt;
        //indM=5;
        indM=11;
        //w_size= 80;
        w_size= 260;
        //offs = 84;
        offs = 260;
        //animatedView.setViewport(new Rectangle2D(4+index*offs,2,w_size,97));
        animatedView.setViewport(new Rectangle2D(index*offs,0,w_size,317));
    }
    void update(long time, double elapsedTime){
        //this.ax+=(0.000000001*elapsedTime);
        //this.vx+=this.ax*elapsedTime;
        if (x>4000) {
            this.ax = (int) (x / 4000);
        }
        this.vx=0.5+ax/100;
        this.x+=this.vx*elapsedTime;
        g=2*150/(Math.pow(200/vx,2));

        vy+=g*elapsedTime;
        y+=vy*elapsedTime;
        if (y>=300)
        {
            y=300;
            vy=0;
            att=1;
        }

        index= (int) (((time/100000000))%12);
        animatedView.setViewport(new Rectangle2D(index*offs,0,w_size,317));
        //animatedView.setViewport(new Rectangle2D(4+index*offs,2,w_size,97));

        if (att==2) {


        }
        System.out.println("ax="+ax+" vx="+vx+" x="+x+" g="+g+" vy="+vy);

    }

    public void jump() {
        this.att=2;

        if (y==300) this.vy=-Math.sqrt(2*150*this.g);
        //vy=-0.75;

    }
}
