package jdraw.figures.handle.inheritance;

import jdraw.framework.DrawView;
import jdraw.framework.Figure;
import jdraw.framework.FigureHandle;

import java.awt.*;
import java.awt.event.MouseEvent;

public abstract class AbstractHandle implements FigureHandle {
    private Figure owner;

    private Point corner;

    private static final int HANDLE_SIZE = 6;

    private final int resizeCursor;

    protected AbstractHandle(Figure figure, int resizeCursor) {
        this.owner = figure;
        this.resizeCursor = resizeCursor;
    }

    protected Point getCorner() {
        return corner;
    }

    protected void setCorner(Point corner) {
        this.corner = corner;
    }

    @Override
    public Figure getOwner() {
        return owner;
    }

    @Override
    public void draw(Graphics g) {
        Point loc = getLocation();
        g.setColor(Color.RED);
        g.fillRect(loc.x - HANDLE_SIZE / 2, loc.y - HANDLE_SIZE / 2, HANDLE_SIZE, HANDLE_SIZE);
        g.setColor(Color.BLACK);
        g.drawRect(loc.x - HANDLE_SIZE / 2, loc.y - HANDLE_SIZE / 2, HANDLE_SIZE, HANDLE_SIZE);
    }

    @Override
    public Cursor getCursor() {
        return Cursor.getPredefinedCursor(resizeCursor);
    }

    @Override
    public boolean contains(int x, int y) {
        Point loc = getLocation();
        Rectangle rect = new Rectangle(loc.x - HANDLE_SIZE / 2, loc.y - HANDLE_SIZE / 2, HANDLE_SIZE, HANDLE_SIZE);
        return rect.contains(new Point(x, y));
    }

    @Override
    public void dragInteraction(int x, int y, MouseEvent e, DrawView v) {
        owner.setBounds(new Point(x, y), corner);
    }

    @Override
    public void stopInteraction(int x, int y, MouseEvent e, DrawView v) {
        corner = null;
    }
}
