package jdraw.figures.figure;

import jdraw.figures.handles.EastHandle;
import jdraw.figures.handles.NorthHandle;
import jdraw.figures.handles.SouthHandle;
import jdraw.figures.handles.WestHandle;
import jdraw.framework.Figure;
import jdraw.framework.FigureEvent;
import jdraw.framework.FigureHandle;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.LinkedList;
import java.util.List;

public class Line extends AbstractFigure {

    private final Line2D line;

    public Line(double p1X, double p1Y, double p2X, double p2Y) {
        line = new Line2D.Double(p1X, p1Y, p2X, p2Y);
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setPaint(Color.BLACK);
        g2.draw(line);
    }

    @Override
    public void move(int dx, int dy) {
        if (dx != 0 || dy != 0) {
            Point2D p1 = line.getP1();
            Point2D p2 = line.getP2();
            if (line.getX1() != dx && line.getX2() != dy) {
                p1.setLocation(p1.getX() + dx, p1.getY() + dy);
                p2.setLocation(p2.getX() + dx, p2.getY() + dy);
                line.setLine(p1, p2);
                notifyObservers(new FigureEvent(this));
            }
        }
    }

    @Override
    public java.util.List<FigureHandle> getHandles() {
        List<FigureHandle> handles = new LinkedList<>();
        handles.add(new NorthHandle(this));
        handles.add(new SouthHandle(this));
        return handles;
    }

    @Override
    public boolean contains(int x, int y) {
        return line.getBounds().contains(x, y);
    }

    @Override
    public void setBounds(Point origin, Point corner) {
        Line2D orig = (Line2D) line.clone();
        line.setLine(origin, corner);
        if (!orig.equals(line)){
            notifyObservers(new FigureEvent(this));}
    }

    @Override
    public Rectangle getBounds() {
        return line.getBounds();
    }

    @Override
    public Figure clone() {
        return new Line(line.getX1(), line.getY1(), line.getX2(), line.getY2());
    }
}