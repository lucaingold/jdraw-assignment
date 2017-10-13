package jdraw.figures.figure;

import jdraw.figures.handles.EastHandle;
import jdraw.figures.handles.NorthHandle;
import jdraw.figures.handles.SouthHandle;
import jdraw.figures.handles.WestHandle;
import jdraw.framework.DrawContext;
import jdraw.framework.Figure;
import jdraw.framework.FigureEvent;
import jdraw.framework.FigureHandle;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.LinkedList;
import java.util.List;

public class Oval extends AbstractRectangularFigure {

    public Oval(int x, int y, int w, int h) {
        super(new Point(x, y), w,h);
    }

    @Override
    public void draw(Graphics g) {
        Rectangle rectangle = this.getBounds();
        Graphics2D g2 = (Graphics2D) g;
        java.awt.geom.Ellipse2D circle = new Ellipse2D.Float(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        g2.setPaint(Color.YELLOW);
        g2.fill(circle);
        g2.setPaint(Color.BLACK);
        g2.draw(circle);
    }

    @Override
    public java.util.List<FigureHandle> getHandles() {
        List<FigureHandle> handles = new LinkedList<>();
        handles.add(new NorthHandle(this));
        handles.add(new SouthHandle(this));
        handles.add(new EastHandle(this));
        handles.add(new WestHandle(this));
        return handles;
    }

    @Override
    public Figure clone() {
        Rectangle circle = getBounds();
        return new Oval((int) circle.getX(), (int) circle.getY(), (int) circle.getWidth(), (int) circle.getHeight());
    }
}
