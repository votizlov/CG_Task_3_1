import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageBufferPixelDrawer implements PixelDrawer {
    private BufferedImage bi;

    public ImageBufferPixelDrawer(BufferedImage bi) {
        this.bi = bi;
    }

    @Override
    public void drawPixel(int x, int y, Color c) {
        if (x < bi.getWidth() && x > 0 && y > 0 && y < bi.getHeight())
            bi.setRGB(x, y, c.getRGB());
    }


}
