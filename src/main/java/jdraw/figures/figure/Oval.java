package jdraw.figures.figure;

import jdraw.framework.DrawContext;
import jdraw.framework.Figure;
import jdraw.framework.FigureEvent;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Oval extends AbstractRectangularFigure {

    public Oval(int x, int y, int w, int h) {
        super(new Point(x, y), w,h);
    }

    @Override
    public void draw(Graphics g) {
        Rectangle rectangle = getBounds();
        Graphics2D g2 = (Graphics2D) g;
        java.awt.geom.Ellipse2D circle = new Ellipse2D.Float(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        g2.setPaint(Color.YELLOW);
        g2.fill(circle);
        g2.setPaint(Color.BLACK);
        g2.draw(circle);
    }

    @Override
    public Figure clone() {
        Rectangle circle = getBounds();
        return new Oval((int) circle.getX(), (int) circle.getY(), (int) circle.getWidth(), (int) circle.getHeight());
    }
}
