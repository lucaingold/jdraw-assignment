package jdraw.figures.handle.inheritance;

import jdraw.framework.DrawView;
import jdraw.framework.Figure;

import java.awt.*;
import java.awt.event.MouseEvent;

public class SouthWestHandle extends AbstractHandle {

    public SouthWestHandle(Figure figure) {
        super(figure, Cursor.SW_RESIZE_CURSOR);
    }

    @Override
    public Point getLocation() {
        Rectangle r = getOwner().getBounds();
        return new Point(r.x, r.y + r.height);    }

    @Override
    public void startInteraction(int x, int y, MouseEvent e, DrawView v) {
        Rectangle r = this.getOwner().getBounds();
        this.setCorner(new Point(r.x + r.width, r. y));
    }
}
