package jdraw.figures.handle.state.states;

import jdraw.decorators.AbstractDecorator;
import jdraw.figures.figure.AbstractFigure;
import jdraw.figures.handle.state.HandleState;
import jdraw.framework.Figure;

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

    protected abstract Point getCorner();

    @Override
    public Cursor getCursor() {
        return Cursor.getPredefinedCursor(resizeCursor);
    }

    @Override
    public void setColor(Color color){
        this.color = color;
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public void setOwner(AbstractFigure owner) {
        this.owner = owner;
    }

    @Override
    public Figure getOwner() {
//        if(owner.getParent()!=null){
//            return (Figure) owner.getParent();
//        }
        return (Figure) owner;
    }

    @Override
    public void startInteraction() {
        corner = getCorner();
    }

    @Override
    public void stopInteraction(int x, int y) {
        corner = null;
    }

}
