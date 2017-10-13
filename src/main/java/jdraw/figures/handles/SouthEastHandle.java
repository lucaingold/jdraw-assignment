package jdraw.figures.handles;

import jdraw.framework.DrawView;
import jdraw.framework.Figure;
import jdraw.framework.FigureHandle;

import java.awt.*;
import java.awt.event.MouseEvent;

public class SouthEastHandle extends AbstractHandle {

    public SouthEastHandle(Figure figure) {
        super(figure, Cursor.SE_RESIZE_CURSOR);
    }

    @Override
    public Point getLocation() {
        Rectangle r = getOwner().getBounds();
        return new Point(r.x + r.width, r.y + r.height );
    }

    @Override
    public void startInteraction(int x, int y, MouseEvent e, DrawView v) {
        Rectangle r = this.getOwner().getBounds();
        this.setCorner(getOwner().getBounds().getLocation());
    }
}