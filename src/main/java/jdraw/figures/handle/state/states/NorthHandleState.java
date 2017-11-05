package jdraw.figures.handle.state.states;

import jdraw.figures.figure.AbstractFigure;
import jdraw.framework.DrawView;

import java.awt.*;
import java.awt.event.MouseEvent;

public class NorthHandleState extends AbstractHandleState {

    public NorthHandleState(AbstractFigure figure) {
        super(figure, Cursor.N_RESIZE_CURSOR);
    }

    public NorthHandleState(AbstractFigure figure, Color color) {
        super(figure, Cursor.N_RESIZE_CURSOR, color);
    }

    @Override
    public AbstractHandleState getHorizontalOppositeState() {
        return this;
    }

    @Override
    public AbstractHandleState getVerticalOppositeState() {
        return new SouthHandleState(owner);
    }

    @Override
    public Point getLocation() {
        Point p = owner.getBounds().getLocation();
        p.x += owner.getBounds().width / 2;
        return p;
    }

    @Override
    public Point getCorner() {
        // SW
        Point p = owner.getBounds().getLocation();
        p.y += owner.getBounds().height;
        return p;
    }

    @Override
    public void dragInteraction(int x, int y, MouseEvent e, DrawView v) {
        Rectangle r = owner.getBounds();
        owner.setBounds(corner, new Point(corner.x+r.width,y));

        if (y > r.y + r.height) {
            owner.flipHandlesVertical();
        }
    }


}