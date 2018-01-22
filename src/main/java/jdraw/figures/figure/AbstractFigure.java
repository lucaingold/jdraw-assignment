package jdraw.figures.figure;

import jdraw.figures.handle.state.Handle;
import jdraw.framework.Figure;
import jdraw.framework.FigureEvent;
import jdraw.framework.FigureHandle;
import jdraw.framework.FigureListener;

import java.util.LinkedList;
import java.util.List;

public abstract class AbstractFigure implements Figure {

//    private final List<FigureListener> listeners = new LinkedList<>();
    private List<FigureListener> listeners = new LinkedList<>();

    @Override
    public void addFigureListener(FigureListener listener) {
        if (!listeners.contains(listener) && listener != null)
            listeners.add(listener);
    }

    @Override
    public void removeFigureListener(FigureListener listener) {
        listeners.remove(listener);
    }

    protected void notifyObservers(FigureEvent e) {
        new LinkedList<>(listeners).forEach(l -> {
            l.figureChanged(e);
        });
    }

    @Override
    public Figure clone(){
        try {
            AbstractFigure abstractFigureCopy = (AbstractFigure) super.clone();
            abstractFigureCopy.listeners = new LinkedList<>();
            abstractFigureCopy.handles = null;
            return abstractFigureCopy;
        } catch (CloneNotSupportedException e) {
            throw new InternalError();
        }
    }

    //###### Handles State Pattern #####

    protected List<FigureHandle> handles = new LinkedList<>();

    public void flipHandlesHorizontal() {
        for (FigureHandle handle: handles) {
            ((Handle) handle).flipHandleHorizontal();
        }
    }

    public void flipHandlesVertical() {
        for (FigureHandle handle: handles) {
            ((Handle) handle).flipHandleVertical();
        }
    }

    //Decorator-Helper
    @Override
    public boolean isInstanceOf(Class<?> type) {
        return type.isAssignableFrom(this.getClass());
    }

    @Override
    public <T> T getInstanceOf(Class<T> type) {
        return type.cast(this); // checked version of (T)this
    }


}