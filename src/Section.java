import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;

import static java.lang.Math.*;

public class Section implements IFIgure {
    private RealPoint p1;
    private static final double e = 0;

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

    /*
        public LinkedList<RealPoint> getIntersection(Section j) {
            LinkedList<RealPoint> list = new LinkedList<>();
            double a1 = 1 / (getP2().getX() - getP1().getX());
            double b1 = 1 / (getP2().getY() - getP1().getY());
            double c1 = getP1().getX() / (getP2().getX() - getP1().getX()) - getP1().getY() / (getP2().getY() - getP1().getY());

            double a2 = 1 / (j.getP2().getX() - j.getP1().getX());
            double b2 = 1 / (j.getP2().getY() - j.getP1().getY());
            double c2 = j.getP1().getX() / (j.getP2().getX() - j.getP1().getX()) - j.getP1().getY() / (j.getP2().getY() - j.getP1().getY());


            if (a1 / -b1 == a2 / -b2 && c1 / -b1 == c2 / -b2) {
                System.out.println("fk");
                if (this.length() > j.length()) { //здесь обрабатывается случай, когда одна сторона лежит на другой
                    list.add(j.p1);
                    list.add(j.p2);
                    return list;
                } else {
                    list.add(p1);
                    list.add(p2);
                    return list;
                }
            } else {
                double x1 = min(p1.getX(), p2.getX());
                double x2 = min(j.p1.getX(), j.p2.getX());
                for (double i = x1; i < max(p1.getX(), p2.getX()); i += e) {
                    for (double k = x2; k < min(p1.getX(), p2.getX()); k += e) {
                        if (abs(a1 * i / (-b1) + c1 / (-b1) - a2 * k / (-b2) + c2 / (-b2)) < e) {
                            list.add(new RealPoint(i, k));
                            return list;
                        }
                    }
                }
            }


    //проверим существование потенциального интервала для точки пересечения отрезков

            if (p2.getX() < j.p1.getX()) {

                return null; //ибо у отрезков нету взаимной абсциссы

            }

    //оба отрезка невертикальные

            double A1 = (p1.getY() - p2.getY()) / (p1.getX() - p2.getX());

            double A2 = (j.p1.getY() - j.p2.getY()) / (j.p1.getX() - j.p2.getX());

            b1 = p1.getY() - A1 * p1.getX();

            b2 = j.p1.getY() - A2 * j.p1.getX();

            if (A1 == A2) {

                // return false; //отрезки параллельны

            }

    //Xa - абсцисса точки пересечения двух прямых

            double Xa = (b2 - b1) / (A1 - A2);
            double Ya = A1 * Xa + b1;
            System.out.println(Xa);

            if ((Xa < Math.max(p1.getX(), j.p1.getX())) || (Xa > Math.min(p2.getX(), j.p2.getX()))) {

                //return false; //точка Xa находится вне пересечения проекций отрезков на ось X

            } else {
                list.add(new RealPoint(Xa, Ya));
                return list;

            }
            return null;
        }
    */
    public LinkedList<RealPoint> getPointsInsideTriangle(Triangle triangle) {
        LinkedList<RealPoint> answer = new LinkedList<>();
        // calculate dx & dy
        double dx = p2.getX() - p1.getX();
        double dy = p2.getY() - p1.getY();

        // calculate steps required for generating pixels
        int steps = (int) (abs(dx) > abs(dy) ? abs(dx) : abs(dy));
        System.out.println(steps);

        // calculate increment in x & y for each steps
        double Xinc = dx / steps;
        double Yinc = dy / steps;

        // Put pixel for each step
        double X = p1.getX();
        double Y = p1.getY();

        double x1 = triangle.getS1().p1.getX();
        double y1 = triangle.getS1().p1.getY();
        double x2 = triangle.getS1().p2.getX();
        double y2 = triangle.getS1().p2.getY();
        double x3 = triangle.getS2().p2.getX();
        double y3 = triangle.getS2().p2.getY();

        for (int i = 0; i <= steps; i++) {
            double k= (x1 - X) * (y2 - y1) - (x2 - x1) * (y1 - Y);
            double m= (x2 - X) * (y3 - y2) - (x3 - x2) * (y2 - Y);
            double n= (x3 - X) * (y1 - y3) - (x1 - x3) * (y3 - Y);
            if((k>=0 && m>=0 && n>=0) || (k<=0 && m<=0 && n<=0)){ answer.add(new RealPoint(X,Y));}

            X += Xinc;           // increment in x at each step
            Y += Yinc;           // increment in y at each step
        }
        return answer;
    }
}
