package jdraw.figures.handles;

import jdraw.framework.DrawView;
import jdraw.framework.Figure;

import java.awt.*;
import java.awt.event.MouseEvent;

public class NorthWestHandle extends AbstractHandle {

    public NorthWestHandle(Figure figure) {
        super(figure, Cursor.NW_RESIZE_CURSOR);
    }

    @Override
    public Point getLocation() {
        return getOwner().getBounds().getLocation();
    }

    public void startInteraction(int x, int y, MouseEvent e, DrawView v) {
        Rectangle r = this.getOwner().getBounds();
        this.setCorner(new Point(r.x + r.width, r.y + r.height));
    }
}