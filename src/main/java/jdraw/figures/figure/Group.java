package jdraw.figures.figure;

import jdraw.figures.handle.state.Handle;
import jdraw.figures.handle.state.states.*;
import jdraw.framework.*;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Group extends AbstractFigure implements FigureGroup {

    private List<Figure> parts = new ArrayList<>();

    private Rectangle bounds = new Rectangle();

    public Group(List<Figure> figures, DrawModel model) {
        if (parts != null && figures.size() > 0) {
            this.parts = new ArrayList<>(parts);
            for (Figure f : model.getFigures()) {
                if (figures.contains(f)) {
                    parts.add(f);
                }
            }
        }
        else throw new IllegalArgumentException();
    }

    private Group(List<Figure> figures) {
        if (parts != null && figures.size() > 0)
            this.parts = new ArrayList<Figure>(parts);
        else throw new IllegalArgumentException();
    }

    @Override
    public void draw(Graphics g) {
        parts.forEach(f -> f.draw(g));
    }

    @Override
    public void move(int dx, int dy) {
        if (dx != 0 || dy != 0)
            parts.forEach(f -> {
                f.move(dx, dy);
                notifyObservers(new FigureEvent(this));
            });
    }

    @Override
    public boolean contains(int x, int y) {
        return getBounds().contains(x, y);
    }


    @Override
    public void setBounds(Point origin, Point corner) {
        throw new NotImplementedException();
    }

    @Override
    public Rectangle getBounds() {
        bounds = new Rectangle();
        parts.forEach(f -> bounds.add(f.getBounds()));
//        for (Figure f : parts) {
//            if (bounds == null)
//                bounds = f.getBounds();
//            else
//                bounds.add(f.getBounds());
//        }
        return bounds;
    }

    @Override
    public List<FigureHandle> getHandles() {
        return null;
    }

    @Override
    public Iterable<Figure> getFigureParts() {
        return Collections.unmodifiableList(parts);
    }

    @Override
    public Group clone() {
        Group groupCopy = (Group) super.clone();
        groupCopy.parts = new LinkedList<>();
        this.parts.forEach(f -> groupCopy.parts.add(f.clone()));
        return groupCopy;
    }
}
