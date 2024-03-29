import java.awt.*;
import java.util.LinkedList;

public class PolyLine2D implements IFIgure {//всегда замкнутая

    private LinkedList<Section> sections;

    public PolyLine2D(LinkedList<Section> sections) {
        this.sections = sections;
    }

    public PolyLine2D() {sections = new LinkedList<>();}

    @Override
    public void draw(DDALineDrawer ld, ScreenConverter sc, Color c) {
        for (Section s:sections
             ) {
            //s.draw(ld,sc,c);
            ld.drawLine(sc.realToScreen(s.getP1()).getI(),sc.realToScreen(s.getP1()).getJ(),sc.realToScreen(s.getP1()).getI(),sc.realToScreen(s.getP1()).getJ(),c);
            ld.drawLine(sc.realToScreen(s.getP2()).getI(),sc.realToScreen(s.getP2()).getJ(),sc.realToScreen(s.getP2()).getI(),sc.realToScreen(s.getP2()).getJ(),c);
        }
    }

    public void draw(DDALineDrawer ld, Color c) {
        for (Section s:sections
        ) {
            s.draw(ld,c);
            //ld.drawLine(s.getP1().getX(),s.getP1().getY(),s.getP1().getX(),s.getP1().getY(),c);
            //ld.drawLine(s.getP2().getX(),s.getP2().getY(),s.getP2().getX(),s.getP2().getY(),c);
        }
    }

    public LinkedList<Section> getSections() {
        return sections;
    }

    public void setSections(LinkedList<Section> sections) {
        this.sections = sections;
    }

    public void addSectionsFromPointsList(LinkedList<RealPoint> intersectionPoints) {
        RealPoint point0 = intersectionPoints.getFirst();
        RealPoint point1;
        for (int i = 1; i < intersectionPoints.size(); i++) {
            point1 = intersectionPoints.get(i);
            sections.add(new Section(point0,point1));
            point0 = point1;
        }
    }
}
