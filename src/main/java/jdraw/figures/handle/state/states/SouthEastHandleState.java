package jdraw.figures.handle.state.states;

import jdraw.figures.figure.AbstractFigure;
import jdraw.framework.DrawView;

import java.awt.*;
import java.awt.event.MouseEvent;

public class SouthEastHandleState extends AbstractHandleState {

    public SouthEastHandleState(AbstractFigure figure) {
        super(figure, Cursor.SE_RESIZE_CURSOR);
    }

    public SouthEastHandleState(AbstractFigure figure, Color color) {
        super(figure, Cursor.SE_RESIZE_CURSOR, color);
    }

    @Override
    public AbstractHandleState getHorizontalOppositeState() {
        return new SouthWestHandleState(owner);
    }

    @Override
    public AbstractHandleState getVerticalOppositeState() {
        return new NorthWestHandleState(owner);
    }

    @Override
    public Point getLocation() {
        Point p = owner.getBounds().getLocation();
        p.x += owner.getBounds().width;
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
        owner.setBounds(corner, new Point(x,y));

        if (x < r.x) {
            owner.flipHandlesHorizontal();
        }
        if (y < r.y) {
            owner.flipHandlesVertical();
        }
    }


}