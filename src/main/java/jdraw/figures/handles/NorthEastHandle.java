package jdraw.figures.handles;

import jdraw.framework.DrawView;
import jdraw.framework.Figure;
import jdraw.framework.FigureHandle;

import java.awt.*;
import java.awt.event.MouseEvent;

public class NorthEastHandle extends AbstractHandle {

    public NorthEastHandle(Figure figure) {
        super(figure);
        this.setResizeCursor(Cursor.NE_RESIZE_CURSOR);
    }

    @Override
    public Point getLocation() {
        return null;
    }

    @Override
    public void startInteraction(int x, int y, MouseEvent e, DrawView v) {

    }
}