public class Camera {
    private double x;
    private double y;
    private double vx;
    private double vy;
    private double ax;
    private double ay;
    private Hero center;
    private final double k=0.00001;
    private final double f=0.002;
    private long now=0;

    public double getVx() {
        return vx;
    }

    public Camera(double x, double y, Hero center) {
        this.x = x;
        this.y = y;
        this.center=center;
        vx=0;
        vy=0;
        ay=0;
        ax=0;

    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
    public void startAgain(){
        x=1200;
    }

    public void update(double time){
        ax=(center.getX()-x)*k-(f*vx);
        vx=vx+ax*time;
        x=x+vx*time;
        System.out.println("Camera pos : "+x +" vx : "+vx+" ax : "+ax+" time ="+time);

    }

    @Override
    public String toString(){
        return this.x+","+this.y;
    }
}
