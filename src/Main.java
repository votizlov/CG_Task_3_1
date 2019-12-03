

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.LinkedList;

public class Main {

    static class DrawPanel extends JPanel implements MouseMotionListener, MouseListener, MouseWheelListener {
        private DDALineDrawer ld;
        private ScreenConverter sc;
        private ImageBufferPixelDrawer pd;
        private Line l;
        private Intersector in;
        private boolean isMouseClicked = false;
        private Triangle selectedTriangle = null;
        private LinkedList<Triangle> figuresList = new LinkedList<>();

        public DrawPanel() {
            super();
            sc = new ScreenConverter(-2, 2, 4, 4, 500, 500);
            figuresList.add(new Triangle(
                    new Section(new RealPoint(-1, -1), new RealPoint(1, 2)),
                    new Section(new RealPoint(1, 2), new RealPoint(1, 0)),
                    new Section(new RealPoint(1, 0), new RealPoint(-1, -1))));
            figuresList.add(new Triangle(
                    new Section(new RealPoint(-1, 0), new RealPoint(1, 1.5)),
                    new Section(new RealPoint(1, 1.5), new RealPoint(0, 1)),
                    new Section(new RealPoint(0, 1), new RealPoint(-1, 0))));
            in = new Intersector();
        }

        @Override
        public void paint(Graphics g) {
            BufferedImage bi = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
            Graphics2D gr = (Graphics2D) bi.getGraphics();
            gr.setColor(Color.BLACK);
            gr.fillRect(0,0,getWidth(),getHeight());

            sc.setHs(getHeight());
            sc.setWs(getWidth());
            pd = new ImageBufferPixelDrawer(bi);
            ld = new DDALineDrawer(pd);
            ld.drawLine(getWidth() / 2, 0, getWidth() / 2, getHeight(), Color.CYAN);//todo make constr for points
            ld.drawLine(0, getHeight() / 2, getWidth(), getHeight() / 2, Color.CYAN);

            for (Triangle t : figuresList
            ) {
                t.draw(ld, sc, Color.gray);
            }

            in.findIntersectionContour(figuresList.get(0), figuresList.get(1)).draw(ld, sc, Color.RED);
            //in.fillIntersectionContour(bi,Color.gray,Color.BLUE);

            g.drawImage(bi, 0, 0, null);
        }

        private ScreenPoint last = null;

        @Override
        public void mouseDragged(MouseEvent mouseEvent) {
            if (last != null) {

            }
        }

        @Override
        public void mouseMoved(MouseEvent mouseEvent) {
            //ScreenPoint screenPoint = new ScreenPoint();
            //l.setP2(sc.screenToReal(screenPoint));
            repaint();
        }

        @Override
        public void mouseClicked(MouseEvent mouseEvent) {

        }

        @Override
        public void mousePressed(MouseEvent mouseEvent) {
            isMouseClicked = !isMouseClicked;
        }

        @Override
        public void mouseReleased(MouseEvent mouseEvent) {
            isMouseClicked = !isMouseClicked;
        }

        @Override
        public void mouseEntered(MouseEvent mouseEvent) {

        }

        @Override
        public void mouseExited(MouseEvent mouseEvent) {

        }

        @Override
        public void mouseWheelMoved(MouseWheelEvent e) {
            e.getScrollAmount();
        }

        public void setCurrentTriangle(Triangle triangle) {
            selectedTriangle = triangle;
        }
    }

    public static void main(String... args) {
        DrawPanel myPanel = new DrawPanel();
        JFrame drawFrame = new JFrame();
        JPanel funcPanel = new JPanel();
        JFrame frame = new JFrame();
        HashMap<JRadioButton, Triangle> buttonTriangleHashMap = new HashMap<>();


        funcPanel.setSize(200, 500);
        frame.setSize(200, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(funcPanel);
        frame.setVisible(true);
        frame.setTitle("Triangle chooser");
        funcPanel.setVisible(true);
        funcPanel.setLayout(new GridLayout(1, 2));
        boolean flag = true;
        JRadioButton btn;
        for (int i = 0; i < 16; i++) {
            if (flag) {
                btn = new JRadioButton();
                buttonTriangleHashMap.put(btn, new Triangle(
                        new Section(new RealPoint(0, 0), new RealPoint(1, 1)),
                        new Section(new RealPoint(1, 1), new RealPoint(1, 2)),
                        new Section(new RealPoint(1, 2), new RealPoint(0, 0))));
                btn.setAction(new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        myPanel.setCurrentTriangle(buttonTriangleHashMap.get(e.getSource()));
                    }
                });
                funcPanel.add(btn);
            } else
                funcPanel.add(new JTextField(i));
            flag = !flag;
        }

        myPanel.setSize(800, 600);
        drawFrame.setSize(800, 600);
        drawFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        drawFrame.setContentPane(myPanel);
        drawFrame.setVisible(true);
        myPanel.setVisible(true);
    }
}
