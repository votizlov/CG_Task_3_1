import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;

import static java.lang.Math.*;

public class Section implements IFIgure {
    private RealPoint p1;
    private static final double e = 1;

    public Section(RealPoint p1, RealPoint p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    public RealPoint getP1() {
        return p1;
    }

    public void setP1(RealPoint p1) {
        this.p1 = p1;
    }

    public RealPoint getP2() {
        return p2;
    }

    public void setP2(RealPoint p2) {
        this.p2 = p2;
    }

    private RealPoint p2;

    public double length() {
        return sqrt(pow(p1.getX() - p2.getX(), 2) + pow(p1.getY() - p2.getY(), 2));
    }

    @Override
    public void draw(DDALineDrawer ld, ScreenConverter sc, Color c) {
        ld.drawLine(sc.realToScreen(p1).getI(), sc.realToScreen(p1).getJ(), sc.realToScreen(p2).getI(), sc.realToScreen(p2).getJ(), c);
    }

    public void draw(DDALineDrawer ld, Color c) {
        ld.drawLine(p1.getX(), p1.getY(), p2.getX(), p2.getY(), c);
    }

    public LinkedList<RealPoint> getPointsInsideTriangle(Triangle triangle) {

        LinkedList<RealPoint> answer = new LinkedList<>();
        // calculate dx & dy
        double dx = p2.getX() - p1.getX();
        double dy = p2.getY() - p1.getY();

        // calculate steps required for generating pixels
        double steps =(abs(dx) > abs(dy) ? abs(dx) : abs(dy))*100;

        // calculate increment in x & y for each steps
        double Xinc = dx /  steps;
        double Yinc = dy /  steps;

        // Put pixel for each step
        double X = p1.getX();
        double Y = p1.getY();

        double x1 = triangle.getS1().p1.getX();
        double y1 = triangle.getS1().p1.getY();
        double x2 = triangle.getS1().p2.getX();
        double y2 = triangle.getS1().p2.getY();
        double x3 = triangle.getS2().p2.getX();
        double y3 = triangle.getS2().p2.getY();
        for (int i = 0; i <= steps; i += 1) {
            double k = (x1 - X) * (y2 - y1) - (x2 - x1) * (y1 - Y);
            double m = (x2 - X) * (y3 - y2) - (x3 - x2) * (y2 - Y);
            double n = (x3 - X) * (y1 - y3) - (x1 - x3) * (y3 - Y);
            if ((k >= 0 && m >= 0 && n >= 0) || (k <= 0 && m <= 0 && n <= 0)) {
                answer.add(new RealPoint(X, Y));
            }

            X += Xinc;           // increment in x at each step
            Y += Yinc;           // increment in y at each step
        }
        System.out.println(answer.size());
        return answer;
    }
}
