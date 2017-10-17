package jdraw.figures.handle.state.states;

import jdraw.figures.figure.AbstractFigure;
import jdraw.figures.handle.state.HandleState;

import java.awt.*;

public abstract class AbstractHandleState implements HandleState {

    protected AbstractFigure owner;
    protected Point corner;
    private final int resizeCursor;
    private Color color;

    protected AbstractHandleState(AbstractFigure figure, int resizeCursor) {
        this.color = Color.WHITE;
        this.owner = figure;
        this.resizeCursor = resizeCursor;
    }

    protected AbstractHandleState(AbstractFigure figure, int resizeCursor, Color color) {
        this(figure,resizeCursor);
        this.color = color;
    }

    public Cursor getCursor() {
        return Cursor.getPredefinedCursor(resizeCursor);
    }

    protected abstract Point getCorner();

    public void setColor(Color color){
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public void setOwner(AbstractFigure figure) {
        this.owner = figure;
    }

    public AbstractFigure getOwner() {
        return owner;
    }

    public void startInteraction() {
        corner = getCorner();
    }

    public void stopInteraction(int x, int y) {
        corner = null;
    }
}
