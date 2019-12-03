import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class Vector2 {
    private double x;

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    private double y;

    public Vector2(RealPoint point, RealPoint point1) {
        this.x = point1.getX() - point.getX();
        this.y = point1.getY() - point.getY();
    }

    public double dot(Vector2 line2) {
        double[] vals1 = new double[]{x, y};
        double[] vals2 = new double[]{line2.getX(), line2.getY()};
        double r = 0;
        for (double i : vals1
        ) {
            for (double j : vals2
            ) {
                r += i * j;
            }
        }
        return r;
    }

    public double length() {
        return sqrt(pow(x, 2) + pow(y, 2));
    }
}
