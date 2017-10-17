package jdraw.figures.handles.state.states;

import jdraw.figures.figure.AbstractFigure;
import jdraw.framework.DrawView;

import java.awt.*;
import java.awt.event.MouseEvent;

public class EastHandleState extends AbstractHandleState {

    public EastHandleState(AbstractFigure figure) {
        super(figure, Cursor.E_RESIZE_CURSOR);
    }

    public EastHandleState(AbstractFigure figure, Color color) {
        super(figure, Cursor.E_RESIZE_CURSOR, color);
    }

    @Override
    public AbstractHandleState getHorizontalFlipState() {
        return new WestHandleState(owner);
    }

    @Override
    public AbstractHandleState getVerticalFlipState() {
        return this;
    }

    @Override
    public Point getLocation() {
        Point p = owner.getBounds().getLocation();
        p.x += owner.getBounds().width;
        p.y += owner.getBounds().height / 2;
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
        owner.setBounds(corner, new Point(x,r.y+r.height));

        if (x < r.x) {
            owner.flipHandlesHorizontal();
        }
    }
}