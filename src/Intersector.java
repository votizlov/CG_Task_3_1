import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

public class Intersector {

    public PolyLine2D findIntersectionContour(Triangle triangle, Triangle triangle1) {
        LinkedList<RealPoint> t = null;
        LinkedList<RealPoint> intersectionPoints = new LinkedList<>();

        for (Section i : triangle.getSectionsAsArr()
        ) {
            for (Section j : triangle1.getSectionsAsArr()
            ) {
                t = i.getIntersection(j);
                if (t != null)
                    intersectionPoints.addAll(t);
            }
        }
        System.out.println(intersectionPoints.size());
        PolyLine2D polyLine2D = new PolyLine2D();
        if (intersectionPoints.size() != 0) {
            polyLine2D.addSectionsFromPointsList(intersectionPoints);
            polyLine2D.getSections().add(new Section(intersectionPoints.getLast(),intersectionPoints.getFirst()));
        }
        return polyLine2D;
    }
}
