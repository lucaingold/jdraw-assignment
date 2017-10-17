package jdraw.figures.handle.state.states;

import jdraw.figures.figure.AbstractFigure;
import jdraw.framework.DrawView;

import java.awt.*;
import java.awt.event.MouseEvent;

public class SouthHandleState extends AbstractHandleState {

    public SouthHandleState(AbstractFigure figure) {
        super(figure, Cursor.S_RESIZE_CURSOR);
    }

    public SouthHandleState(AbstractFigure figure, Color color) {
        super(figure, Cursor.S_RESIZE_CURSOR, color);
    }

    @Override
    public AbstractHandleState getHorizontalFlipState() {
        return this;
    }

    @Override
    public AbstractHandleState getVerticalFlipState() {
        return new NorthHandleState(owner);
    }

    @Override
    public Point getLocation() {
        Point p = owner.getBounds().getLocation();
        p.x += owner.getBounds().width / 2;
        p.y += owner.getBounds().height;
        return p;
    }

    @Override
    public Point getCorner() {
        // NW
        return owner.getBounds().getLocation();
    }

    @Override
    public void dragInteraction(int x, int y, MouseEvent e, DrawView v) {
        Rectangle r = owner.getBounds();
        owner.setBounds(corner, new Point(corner.x+r.width,y));

        if (y < r.y) {
            owner.flipHandlesVertical();
        }
    }




}