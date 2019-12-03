import java.awt.*;
import java.util.LinkedList;

import static java.lang.Math.*;

public class Section implements IFIgure {
    private RealPoint p1;

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

    public double length (){
        return sqrt(pow(p1.getX()-p2.getX(),2)+pow(p1.getY()-p2.getY(),2));
    }

    @Override
    public void draw(DDALineDrawer ld, ScreenConverter sc, Color c) {
        ld.drawLine(sc.realToScreen(p1).getI(), sc.realToScreen(p1).getJ(), sc.realToScreen(p2).getI(), sc.realToScreen(p2).getJ(), c);
    }

    public LinkedList<RealPoint> getIntersection(Section j) {
        LinkedList<RealPoint> list = new LinkedList<>();
        double a1 = 1 / (getP2().getX() - getP1().getX());
        double b1 = 1 / (getP2().getY() - getP1().getY());
        double c1 = getP1().getX() / (getP2().getX() - getP1().getX()) - getP1().getY() / (getP2().getY() - getP1().getY());

        double a2 = 1 / (j.getP2().getX() - j.getP1().getX());
        double b2 = 1 / (j.getP2().getY() - j.getP1().getY());
        double c2 = j.getP1().getX() / (j.getP2().getX() - j.getP1().getX()) - j.getP1().getY() / (j.getP2().getY() - j.getP1().getY());


        if (a1 / -b1 == a2 / -b2 && c1 / -b1 == c2 / -b2) {
            System.out.println("k");
            if(this.length() >j.length()) {
                list.add(j.p1);
                list.add(j.p2);
                return list;
            } else {
                list.add(p1);
                list.add(p2);
                return list;
            }
        } else {
            double x = (-c1 / (-b1) - c2) / (a2 + a1 / (-b1));
            double y = a1 * x / (-b1) + c1 / (-b1);
            System.out.println(x + " " + y);
            if (a1 * x + b1 * y + c1 < 0.1
                    && a2 * x + b2 * y + c2 < 0.1
                    //&& x > min(min(p1.getX(),p2.getX()),min(j.p1.getX(),j.p2.getX()))
                    //&& x < max(max(p1.getX(),p2.getX()),max(j.p1.getX(),j.p2.getX()))
            ) {
                list.add(new RealPoint(x, y));
                System.out.println("point");
                return list;
            }
        }
        return null;
    }

    public LinkedList<RealPoint> getPointsInsideTriangle(Triangle triangle) {
        return null;
    }
}
