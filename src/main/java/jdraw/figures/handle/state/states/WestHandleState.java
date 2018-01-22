package jdraw.figures.handle.state.states;

import jdraw.figures.figure.AbstractFigure;
import jdraw.framework.DrawView;
import jdraw.framework.Figure;

import java.awt.*;
import java.awt.event.MouseEvent;

public class WestHandleState extends AbstractHandleState {

    public WestHandleState(Figure figure) {
        super(figure, Cursor.W_RESIZE_CURSOR);
    }

    public WestHandleState(Figure figure, Color color) {
        super(figure, Cursor.W_RESIZE_CURSOR, color);
    }

    @Override
    public AbstractHandleState getHorizontalOppositeState() {
        return new EastHandleState(owner);
    }

    @Override
    public AbstractHandleState getVerticalOppositeState() {
        return this;
    }


    @Override
    public Point getLocation() {
        Point p = owner.getBounds().getLocation();
        p.y += owner.getBounds().height / 2;
        return p;
    }

    @Override
    public Point getCorner() {
        // SE
        Point p = owner.getBounds().getLocation();
        p.x += owner.getBounds().width;
        p.y += owner.getBounds().height;
        return p;
    }

    @Override
    public void dragInteraction(int x, int y, MouseEvent e, DrawView v) {
        Rectangle r = owner.getBounds();
        owner.setBounds(new Point(x,r.y), corner);

        if (x > r.x + r.width) {
            getAbstractInstance(owner).flipHandlesHorizontal();
        }

    }


}