package jdraw.figures.handles.state;

import jdraw.framework.DrawView;

import java.awt.*;
import java.awt.event.MouseEvent;

public interface HandleState {
    Point getAnchor();
    Cursor getCursor();
    void dragInteraction(int x, int y, MouseEvent e, DrawView v);
}
