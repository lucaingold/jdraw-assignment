package jdraw.figures.handle.state.states;

import jdraw.figures.figure.AbstractFigure;
import jdraw.framework.DrawView;

import java.awt.*;
import java.awt.event.MouseEvent;

public class NorthWestHandleState extends AbstractHandleState {

    public NorthWestHandleState(AbstractFigure figure) {
        super(figure, Cursor.NW_RESIZE_CURSOR);
    }

    public NorthWestHandleState(AbstractFigure figure, Color color) {
        super(figure, Cursor.NW_RESIZE_CURSOR, color);
    }

    @Override
    public AbstractHandleState getHorizontalFlipState() {
        return new NorthEastHandleState(owner);
    }

    @Override
    public AbstractHandleState getVerticalFlipState() {
        return new SouthWestHandleState(owner);
    }

    @Override
    public Point getLocation() {
        return owner.getBounds().getLocation();
    }

    @Override
    public Point getCorner() {
        //SW
        Point p = owner.getBounds().getLocation();
        p.x += owner.getBounds().width;
        p.y += owner.getBounds().height;
        return p;
    }

    @Override
    public void dragInteraction(int x, int y, MouseEvent e, DrawView v) {
        Rectangle r = owner.getBounds();
        owner.setBounds(new Point(x,y), corner);

        if (x > r.x + r.width) {
            owner.flipHandlesHorizontal();
        }
        if (y > r.y + r.height) {
            owner.flipHandlesVertical();
        }

    }



}