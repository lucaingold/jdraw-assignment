/*
 * Copyright (c) 2017 Fachhochschule Nordwestschweiz (FHNW)
 * All Rights Reserved. 
 */

package jdraw.std;

import java.util.*;

import jdraw.framework.*;
import org.apache.log4j.Logger;

/**
 * Provide a standard behavior for the drawing model. This class initially does not implement the methods
 * in a proper way.
 * It is part of the course assignments to do so.
 *
 * @author Luca Ingold
 */
public class StdDrawModel implements DrawModel, FigureListener {

    private static final Logger logger = Logger.getLogger(StdDrawModel.class);

    /**
     * List witch contains all figures in correct order
     */
    private final Stack<Figure> figures = new Stack<>();

    /**
     * Send changes to this listener.
     */
    private List<DrawModelListener> listeners = new LinkedList<>();

    @Override
    public void figureChanged(FigureEvent e) {
        invokeListener(e.getFigure(), DrawModelEvent.Type.FIGURE_CHANGED);
    }

    private void invokeListener(Figure f, DrawModelEvent.Type t) {
        DrawModelEvent e = new DrawModelEvent(this, f, t);
        listeners.forEach(l -> l.modelChanged(e));
    }

    @Override
    public void addFigure(Figure f) {
        if (f != null && !figures.contains(f)) {
            figures.add(f);
            f.addFigureListener(this);
            invokeListener(f, DrawModelEvent.Type.FIGURE_ADDED);
        }
    }

    @Override
    public Iterable<Figure> getFigures() {
//        logger.trace("getFigures");
        return Collections.unmodifiableList(figures);
    }

    @Override
    public void removeFigure(Figure f) {
        if (figures.remove(f)) {
            f.removeFigureListener(this);
            invokeListener(f, DrawModelEvent.Type.FIGURE_REMOVED);
        }
    }

    @Override
    public void addModelChangeListener(DrawModelListener listener) {
        if (listener != null && !listeners.contains(listener))
            listeners.add(listener);
    }

    @Override
    public void removeModelChangeListener(DrawModelListener listener) {
        listeners.remove(listener);
        logger.trace("removeModelChangeListener");
    }

    /**
     * The draw command handler. Initialized here with a dummy implementation.
     */
    // TODO initialize with your implementation of the undo/redo-assignment.
    private DrawCommandHandler handler = new EmptyDrawCommandHandler();

    /**
     * Retrieve the draw command handler in use.
     *
     * @return the draw command handler.
     */
    @Override
    public DrawCommandHandler getDrawCommandHandler() {
        return handler;
    }

    @Override
    public void setFigureIndex(Figure f, int index) {
        if (!figures.contains(f)) {
            throw new IllegalArgumentException();
        } else if (index >= figures.size() || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        figures.remove(f);
        figures.add(index, f);
        invokeListener(f, DrawModelEvent.Type.DRAWING_CHANGED);
        logger.trace("setFigureIndex");
    }

    @Override
    public void removeAllFigures() {
        new LinkedList<>(figures).forEach(f -> {
            this.removeFigure(f);
        });
        invokeListener(null, DrawModelEvent.Type.DRAWING_CLEARED);
        logger.trace("removeAllFigures");
    }

}
