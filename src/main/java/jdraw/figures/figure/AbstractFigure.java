package jdraw.figures.figure;

import jdraw.framework.Figure;
import jdraw.framework.FigureEvent;
import jdraw.framework.FigureHandle;
import jdraw.framework.FigureListener;
import java.util.LinkedList;
import java.util.List;

public abstract class AbstractFigure implements Figure{

    private final List<FigureListener> listeners = new LinkedList<>();

    /**
     * Returns a list of 8 handles for this Rectangle.
     * @return all handles that are attached to the targeted figure.
     * @see jdraw.framework.Figure#getHandles()
     */
    @Override
    public List<FigureHandle> getHandles() {
        return null;
    }

    @Override
    public void addFigureListener(FigureListener listener) {
        if(!listeners.contains(listener) && listener != null)
            listeners.add(listener);
    }

    @Override
    public void removeFigureListener(FigureListener listener) {
        listeners.remove(listener);
    }

    public void notifyObservers(FigureEvent e){
        new LinkedList<>(listeners).forEach(l -> {
            l.figureChanged(e);
        });
    }

    public abstract Figure clone();
}
