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
            s.draw(ld,sc,c);
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
