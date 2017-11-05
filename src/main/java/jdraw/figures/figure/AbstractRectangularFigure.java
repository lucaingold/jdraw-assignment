package jdraw.figures.figure;

import jdraw.framework.Figure;
import jdraw.framework.FigureEvent;

import java.awt.*;
import java.util.List;

public abstract class AbstractRectangularFigure extends AbstractFigure {

    private final Rectangle rectangle;

    protected AbstractRectangularFigure(Point p, int w, int h) {
        rectangle = new Rectangle(p.x, p.y, w, h);
    }

    @Override
    public abstract void draw(Graphics g);

    @Override
    public void setBounds(Point origin, Point corner) {
        Rectangle orig = new java.awt.Rectangle(rectangle);
        rectangle.setFrameFromDiagonal(origin, corner);
        if (!orig.equals(rectangle))
            notifyObservers(new FigureEvent(this));
    }

    @Override
    public Rectangle getBounds() { return rectangle; }

    @Override
    public boolean contains(int x, int y) {
        return getBounds().contains(x, y);
    }

    @Override
    public void move(int dx, int dy) {
        if (dx != 0 || dy != 0) {
            rectangle.setLocation(rectangle.x + dx, rectangle.y + dy);
            notifyObservers(new FigureEvent(this));
        }
    }
}
