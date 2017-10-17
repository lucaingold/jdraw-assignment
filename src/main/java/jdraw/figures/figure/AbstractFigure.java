package jdraw.figures.figure;

import jdraw.figures.handle.state.Handle;
import jdraw.framework.Figure;
import jdraw.framework.FigureEvent;
import jdraw.framework.FigureHandle;
import jdraw.framework.FigureListener;

import java.util.LinkedList;
import java.util.List;

public abstract class AbstractFigure implements Figure {

    private final List<FigureListener> listeners = new LinkedList<>();

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

    public abstract Figure clone();

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


}