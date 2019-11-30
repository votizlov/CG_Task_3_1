import java.awt.*;

public class Triangle implements IFIgure {

    private Section s1,s2,s3;

    public Triangle(Section s1, Section s2, Section s3) {
        this.s1 = s1;
        this.s2 = s2;
        this.s3 = s3;
    }

    public Section getS1() {
        return s1;
    }

    public Section getS2() {
        return s2;
    }

    public Section getS3() {
        return s3;
    }


    @Override
    public void draw(DDALineDrawer ld, ScreenConverter sc, Color c) {
        s1.draw(ld,sc,c);
        s2.draw(ld,sc,c);
        s3.draw(ld,sc,c);
    }

    public Section[] getSectionsAsArr() {
        return new Section[]{s1,s2,s3};
    }
}
