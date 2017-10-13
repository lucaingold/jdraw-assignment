package jdraw.figures.handles;

import jdraw.framework.DrawView;
import jdraw.framework.Figure;
import jdraw.framework.FigureHandle;

import java.awt.*;
import java.awt.event.MouseEvent;

public class SouthHandle extends AbstractHandle {

    public SouthHandle(Figure figure) {
        super(figure, Cursor.S_RESIZE_CURSOR);
    }

    @Override
    public Point getLocation() {
        Rectangle r = getOwner().getBounds();
        return new Point(r.x + r.width / 2, r.y + r.height);
    }

    @Override
    public void dragInteraction(int x, int y, MouseEvent e, DrawView v) {
        Rectangle r = this.getOwner().getBounds();
        getOwner().setBounds(new Point(r.x, r.y), new Point(getCorner().x,y));
    }

    @Override
    public void startInteraction(int x, int y, MouseEvent e, DrawView v) {
        Rectangle r = this.getOwner().getBounds();
        this.setCorner(new Point(r.x+r.width,r.y + r.height));
    }
}