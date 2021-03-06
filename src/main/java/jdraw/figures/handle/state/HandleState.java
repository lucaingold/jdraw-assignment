package jdraw.figures.handle.state;

import jdraw.figures.figure.AbstractFigure;
import jdraw.framework.DrawView;
import jdraw.framework.Figure;

import java.awt.*;
import java.awt.event.MouseEvent;

public interface HandleState {

    Figure getOwner();

    void setOwner(AbstractFigure f);

    Color getColor();

    void setColor(Color color);

    Point getLocation();

    Cursor getCursor();

    void startInteraction();

    void stopInteraction(int x, int y);

    void dragInteraction(int x, int y, MouseEvent e, DrawView v);

    HandleState getHorizontalOppositeState();

    HandleState getVerticalOppositeState();
}
