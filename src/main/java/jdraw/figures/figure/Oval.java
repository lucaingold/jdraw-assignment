package jdraw.figures.figure;

import jdraw.framework.DrawContext;
import jdraw.framework.Figure;
import jdraw.framework.FigureEvent;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Oval extends AbstractFigure {

    java.awt.geom.Ellipse2D circle;

    public Oval(int x, int y, int w, int h) {
        circle = new Ellipse2D.Float(x, y, w, h);
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setPaint(Color.WHITE);
        g2.fill(circle);
        g2.setPaint(Color.BLACK);
        g2.draw(circle);
    }

    @Override
    public void move(int dx, int dy) {
        if (dx != 0 || dy != 0) {
            if (circle.getX() != dx && circle.getY() != dy) {
                circle.setFrame(circle.getX() + dx, circle.getY() + dy, circle.getWidth(), circle.getHeight());
                notifyObservers(new FigureEvent(this));
            }
        }
    }

    @Override
    public boolean contains(int x, int y) {
        return circle.contains(x, y);
    }

    @Override
    public void setBounds(Point origin, Point corner) {
        java.awt.geom.Ellipse2D orig = (Ellipse2D) circle.clone();
        circle.setFrameFromDiagonal(origin, corner);
        if (!orig.equals(circle))
            notifyObservers(new FigureEvent(this));

    }

    @Override
    public Rectangle getBounds() {
        return circle.getBounds();
    }

    @Override
    public Figure clone() {
        return new Oval((int) circle.getX(), (int) circle.getY(), (int) circle.getWidth(), (int) circle.getHeight());
    }
}
