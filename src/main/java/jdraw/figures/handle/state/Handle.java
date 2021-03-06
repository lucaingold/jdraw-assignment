package jdraw.figures.handle.state;

import jdraw.figures.figure.AbstractFigure;
import jdraw.framework.DrawView;
import jdraw.framework.Figure;
import jdraw.framework.FigureHandle;

import java.awt.*;
import java.awt.event.MouseEvent;

public class Handle implements FigureHandle {

    private static final int HANDLE_SIZE = 6;

    //** State Pattern **
    private HandleState state;

    public Handle(HandleState state) {
        this.state = state;
    }

    public void setState(HandleState s) {
        state = s;
    }

    //** FigureHandler **
    @Override
    public Figure getOwner() {
        return state.getOwner();
    }

    @Override
    public Point getLocation() {
        return state.getLocation();
    }

    @Override
    public Cursor getCursor() {
        return state.getCursor();
    }

    @Override
    public void draw(Graphics g) {
        Point loc = getLocation();
        g.setColor(state.getColor());
        g.fillOval(loc.x - HANDLE_SIZE / 2, loc.y - HANDLE_SIZE / 2, HANDLE_SIZE, HANDLE_SIZE);
        g.setColor(Color.BLACK);
        g.drawOval(loc.x - HANDLE_SIZE / 2, loc.y - HANDLE_SIZE / 2, HANDLE_SIZE, HANDLE_SIZE);
    }

    @Override
    public boolean contains(int x, int y) {
        Point loc = getLocation();
        Rectangle rect = new Rectangle(loc.x - HANDLE_SIZE / 2, loc.y - HANDLE_SIZE / 2, HANDLE_SIZE, HANDLE_SIZE);
        return rect.contains(new Point(x, y));
    }

    @Override
    public void startInteraction(int x, int y, MouseEvent e, DrawView v) {
        state.startInteraction();
    }

    @Override
    public void dragInteraction(int x, int y, MouseEvent e, DrawView v) {
        state.dragInteraction(x, y, e, v);
    }

    @Override
    public void stopInteraction(int x, int y, MouseEvent e, DrawView v) {
        state.stopInteraction(x, y);
    }

    //** Switch Handles **
    public void flipHandleHorizontal() {
        state = state.getHorizontalOppositeState();
        System.out.println(state.getOwner());
        state.setOwner((AbstractFigure) state.getOwner());
        System.out.println((AbstractFigure)state.getOwner());
        state.startInteraction();
    }

    public void flipHandleVertical() {
        state = state.getVerticalOppositeState();
        state.setOwner((AbstractFigure)state.getOwner());
        state.startInteraction();
    }


}
