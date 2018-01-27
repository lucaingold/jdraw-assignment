package jdraw.decorators;

import jdraw.framework.DrawView;
import jdraw.framework.Figure;
import jdraw.framework.FigureHandle;
import jdraw.framework.FigureListener;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class AbstractDecorator implements Figure {

    private Figure inner;

    public AbstractDecorator(Figure inner) {
        this.inner = inner;
//        inner.setParent(this);
    }

    public Figure getInner() {
        return inner;
    }

    private List<FigureHandle> handles;

    public List<FigureHandle> getHandles() {
        //Problem: Handles refer to the wrong owner. As a consequence, the handles are not removed if the figure is removed.
        List<FigureHandle> handles = new LinkedList<>();
        for(FigureHandle h: inner.getHandles()) {
            //This handle-decorator returns the correct owner.
            handles.add(new HandleDecorator(h));
        }
        return Collections.unmodifiableList(handles);
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

//    @Override
//    public List<FigureHandle> getHandles() {
//        return inner.getHandles();
//    }

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

        try{
            AbstractDecorator f = (AbstractDecorator) super.clone();
            f.inner = (Figure) inner.clone();
            f.handles = null;
            return f;
        }
        catch (CloneNotSupportedException e){
            throw new InternalError();
        }
//        return inner.clone();
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

//    private Figure parent;
//
//    @Override
//    public final Figure getParent() { return parent; }
//
//    @Override
//    public void setParent(Figure parent) { this.parent = parent; }

    private final class HandleDecorator implements FigureHandle
    {
        private final FigureHandle inner;

        public HandleDecorator(FigureHandle handle) {
            this.inner = handle;
        }

        //Modified
        @Override
        public Figure getOwner() {
            //Class HandleDecorator is declared as inner class of class AbstractDecorator
            return AbstractDecorator.this;
        }

        //The remaining methods are all forwarded to the original figure handle...
        @Override
        public boolean contains(int x, int y) {
            return inner.contains(x, y);
        }

        @Override
        public void startInteraction(int x, int y, MouseEvent e, DrawView v) {
            inner.startInteraction(x,y,e,v);
        }

        @Override
        public void dragInteraction(int x, int y, MouseEvent e, DrawView v) {
            inner.dragInteraction(x,y,e,v);
        }

        @Override
        public void stopInteraction(int x, int y, MouseEvent e, DrawView v) {
            inner.stopInteraction(x,y,e,v);
        }

        @Override
        public Point getLocation() {
            return inner.getLocation();
        }

        @Override
        public void draw(Graphics g) {
            inner.draw(g);
        }

        @Override
        public Cursor getCursor() {
            return inner.getCursor();
        }

    }
}

