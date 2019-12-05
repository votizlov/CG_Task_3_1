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
    public LinkedList<RealPoint> getPointsInsideTriangle(Triangle triangle, ScreenConverter sc) {

        LinkedList<RealPoint> answer = new LinkedList<>();
        // calculate dx & dy
        int dx = sc.realToScreen(p2).getI() - sc.realToScreen(p1).getI();
        int dy = sc.realToScreen(p2).getJ() - sc.realToScreen(p1).getJ();

        // calculate steps required for generating pixels
        int steps = abs(dx) > abs(dy) ? abs(dx) : abs(dy);

        // calculate increment in x & y for each steps
        double Xinc = dx / (double) steps;
        double Yinc = dy / (double) steps;

        // Put pixel for each step
        double X = sc.realToScreen(p1).getI();
        double Y = sc.realToScreen(p1).getJ();

        int x1 = sc.realToScreen(triangle.getS1().p1).getI();
        int y1 = sc.realToScreen(triangle.getS1().p1).getJ();
        int x2 = sc.realToScreen(triangle.getS1().p2).getI();
        int y2 = sc.realToScreen(triangle.getS1().p2).getJ();
        int x3 = sc.realToScreen(triangle.getS2().p2).getI();
        int y3 = sc.realToScreen(triangle.getS2().p2).getJ();

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
        return answer;
    }
}
