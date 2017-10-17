package jdraw.figures.handles.state.states;

import jdraw.figures.figure.AbstractFigure;
import jdraw.framework.DrawView;

import java.awt.*;
import java.awt.event.MouseEvent;

public class SouthWestHandleState extends AbstractHandleState {


    public SouthWestHandleState(AbstractFigure figure) {
        super(figure, Cursor.SW_RESIZE_CURSOR);
    }

    public SouthWestHandleState(AbstractFigure figure, Color color) {
        super(figure, Cursor.SW_RESIZE_CURSOR, color);
    }

    @Override
    public AbstractHandleState getHorizontalFlipState() {
        return new SouthEastHandleState(owner);
    }

    @Override
    public AbstractHandleState getVerticalFlipState() {
        return new NorthWestHandleState(owner);
    }


    @Override
    public Point getLocation() {
        Point p = owner.getBounds().getLocation();
        p.y += owner.getBounds().height;
        return p;
    }

    @Override
    public Point getCorner() {
        // NE
        Point p = owner.getBounds().getLocation();
        p.x += owner.getBounds().width;
        return p;
    }

    @Override
    public void dragInteraction(int x, int y, MouseEvent e, DrawView v) {
        Rectangle r = owner.getBounds();
        owner.setBounds(corner, new Point(x,y));

        if (x > r.x + r.width) {
            owner.flipHandlesHorizontal();
        }
        if (y < r.y) {
            owner.flipHandlesVertical();
        }
    }



}