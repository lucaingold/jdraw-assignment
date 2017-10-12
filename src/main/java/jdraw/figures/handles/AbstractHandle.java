package jdraw.figures.handles;

import jdraw.framework.DrawView;
import jdraw.framework.Figure;
import jdraw.framework.FigureHandle;

import java.awt.*;
import java.awt.event.MouseEvent;

public abstract class AbstractHandle implements FigureHandle {

    private Figure owner;

    private Point corner;

    public int resizeCursor;

    public void setResizeCursor(int resizeCursor) {
        this.resizeCursor = resizeCursor;
    }

    public AbstractHandle(Figure figure) {

        this.owner = figure;
    }

    public Point getCorner() {
        return corner;
    }

    public void setCorner(Point corner) {
        this.corner = corner;
    }


    @Override
    public Figure getOwner() {
        return owner;
    }

    @Override
    public void draw(Graphics g) {
        Point loc = getLocation();
        g.setColor(Color.WHITE);
        g.fillRect(loc.x - 3, loc.y - 3, 6, 6);
        g.setColor(Color.BLACK);
        g.drawRect(loc.x - 3, loc.y - 3, 6, 6);
    }

    @Override
    public Cursor getCursor() {
        return Cursor.getPredefinedCursor(resizeCursor);
    }

    @Override
    public boolean contains(int x, int y) {
        Point loc = getLocation();
        Rectangle rect = new Rectangle(loc.x - 3, loc.y - 3, 6, 6);
        return rect.contains(new Point(x, y));
    }

    @Override
    public abstract void startInteraction(int x, int y, MouseEvent e, DrawView v);

    @Override
    public void dragInteraction(int x, int y, MouseEvent e, DrawView v) {
        owner.setBounds(new Point(x, y), corner);
    }

    @Override
    public void stopInteraction(int x, int y, MouseEvent e, DrawView v) {
        corner = null;
    }

}
