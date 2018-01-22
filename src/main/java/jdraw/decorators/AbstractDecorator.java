package jdraw.decorators;

import jdraw.framework.Figure;
import jdraw.framework.FigureHandle;
import jdraw.framework.FigureListener;

import java.awt.*;
import java.util.List;

public class AbstractDecorator implements Figure {

    private final Figure inner;

    public AbstractDecorator(Figure inner) {
        this.inner = inner;
    }

    public Figure getInner() {
        return inner;
    }

    @Override
    public void draw(Graphics g) {
        inner.draw(g);
    }

    @Override
    public void move(int dx, int dy) {
        inner.move(dx, dy);
    }

    @Override
    public boolean contains(int x, int y) {
        return inner.contains(x, y);
    }

    @Override
    public void setBounds(Point origin, Point corner) {
        inner.setBounds(origin, corner);
    }

    @Override
    public Rectangle getBounds() {
        return inner.getBounds();
    }

    @Override
    public List<FigureHandle> getHandles() {
        return inner.getHandles();
    }

    @Override
    public void addFigureListener(FigureListener listener) {
        inner.addFigureListener(listener);
    }

    @Override
    public void removeFigureListener(FigureListener listener) {
        inner.removeFigureListener(listener);
    }

    @Override
    public Figure clone() {
        return inner.clone();
    }

    //### Decorator Helper-Methods
    @Override
    public final <T> T getInstanceOf(Class<T> type) {
        if(type.isAssignableFrom(this.getClass())){
            return type.cast(this); // checked version of (T)this
        } else {
            return inner.getInstanceOf(type);
        }
    }

    @Override
    public final boolean isInstanceOf(Class<?> type) {
        return type.isAssignableFrom(this.getClass()) || inner.isInstanceOf(type);
    }

}
