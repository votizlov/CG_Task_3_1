import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

public class Intersector {

    public PolyLine2D findIntersectionContour(Triangle triangle, Triangle triangle1) {
        LinkedList<RealPoint> t = null;
        LinkedList<RealPoint> intersectionPoints = new LinkedList<>();

        for (Section s : triangle.getSectionsAsArr()) {
            t = s.getPointsInsideTriangle(triangle1);
            if (t.size()!=0)
                intersectionPoints.addAll(t);
        }

        for (Section s : triangle1.getSectionsAsArr()) {
            t = s.getPointsInsideTriangle(triangle);
            if (t.size()!=0)
                intersectionPoints.addAll(t);
        }

        PolyLine2D polyLine2D = new PolyLine2D();
        if (intersectionPoints.size() != 0) {
            polyLine2D.addSectionsFromPointsList(intersectionPoints);
            polyLine2D.getSections().add(new Section(intersectionPoints.getLast(), intersectionPoints.getFirst()));
        }
        return polyLine2D;
    }
}
