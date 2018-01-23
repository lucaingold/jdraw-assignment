package jdraw.figures.handle.inheritance;

import jdraw.framework.DrawView;
import jdraw.framework.Figure;

import java.awt.*;
import java.awt.event.MouseEvent;

public class NorthHandle extends AbstractHandle {
    public NorthHandle(Figure figure) {
        super(figure, Cursor.N_RESIZE_CURSOR);
    }

    @Override
    public Point getLocation() {
        Rectangle r = getOwner().getBounds();
        return new Point(r.x + r.width / 2, r.y);
    }

    @Override
    public void dragInteraction(int x, int y, MouseEvent e, DrawView v) {
        Rectangle r = this.getOwner().getBounds();
        getOwner().setBounds(new Point(getCorner().x,y), new Point(r.x+r.width, r.y+r.height));
    }

    @Override
    public void startInteraction(int x, int y, MouseEvent e, DrawView v) {
        Rectangle r = this.getOwner().getBounds();
        this.setCorner(new Point(r.x,r.y));
    }
}