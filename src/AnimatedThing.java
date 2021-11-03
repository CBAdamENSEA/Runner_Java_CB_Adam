import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

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
    private Rectangle rectangle;


    public AnimatedThing(double x, double y, double dt, String filename) {

        this.x = x;
        this.y = y;
        this.animatedView =new ImageView(new Image(filename));
        rectangle=new Rectangle();
        //animatedView.setViewport(new Rectangle2D(20,2,60,97));
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
        g=2*150/(Math.pow(400/vx,2));

        vy+=g*elapsedTime;
        y+=vy*elapsedTime;
        if (y>=300)
        {
            y=300;
            vy=0;
            att=1;
        }

        rectangle.setX(x);
        rectangle.setY(y);
        rectangle.setWidth(animatedView.getImage().getWidth());
        rectangle.setHeight(animatedView.getImage().getHeight());

        index= (int) (((time/100000000))%12);
        animatedView.setViewport(new Rectangle2D(index*offs,0,w_size,317));
        //animatedView.setViewport(new Rectangle2D(4+index*offs,2,w_size,97));

        if (att==2) {


        }
        //System.out.println("ax="+ax+" vx="+vx+" x="+x+" g="+g+" vy="+vy);

    }

    public void jump() {
        this.att=2;

        if (y==300) this.vy=-Math.sqrt(2*150*this.g);
        //vy=-0.75;

    }
    Rectangle2D getHitBox(){
        double minx=0,miny=0,width=0,height=0;
        switch (index){
            case 0:
                minx=x+59;
                miny=y+21;
                width=128;
                height=296;
                break;
            case 1:
                minx=x+59;
                miny=y+21;
                width=108;
                height=296;
                break;
            case 2:
                minx=x+22;
                miny=10;
                width=219;
                height=256;
                break;
            case 3:
                minx=x+18;
                miny=y+5;
                width=114;
                height=263;
                break;
            case 4:
                minx=x+18;
                miny=y+9;
                width=210;
                height=286;
                break;
            case 5:
                minx=x+25;
                miny=y+18;
                width=175;
                height=299;
                break;
            case 6:
                minx=x+51;
                miny=y+22;
                width=123;
                height=296;
                break;
            case 7:
                minx=x+66;
                miny=y+21;
                width=85;
                height=296;
                break;
            case 8:
                minx=x+29;
                miny=y+12;
                width=183;
                height=261;
                break;
            case 9:
                minx=x+30;
                miny=y+5;
                width=206;
                height=262;
                break;
            case 10:
                minx=x+29;
                miny=y+9;
                width=189;
                height=285;
                break;
            case 11:
                minx=x+35;
                miny=y+18;
                width=141;
                height=299;
                break;
        }
        return (new Rectangle2D(minx,miny,width,height));


    }
}
