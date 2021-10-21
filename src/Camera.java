public class Camera {
    private double x;
    private double y;
    private double vx;
    private double vy;
    private double ax;
    private double ay;
    private Hero center;
    private final double k=1;

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

    public void update(double time){
        ax=(center.getX()-x)*k;
        vx=vx+ax*time;
        x=x+vx*time;

    }
    void update(long time){

    }

    @Override
    public String toString(){
        return this.x+","+this.y;
    }
}
