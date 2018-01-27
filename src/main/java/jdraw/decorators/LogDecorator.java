package jdraw.decorators;

import jdraw.framework.Figure;
import jdraw.framework.FigureHandle;
import jdraw.framework.FigureListener;

import java.awt.*;
import java.util.List;

public class LogDecorator extends AbstractDecorator {

    public LogDecorator(Figure inner) {
        super(inner);
    }

    public Figure getInner() {
        return super.getInner();
    }


    @Override
    public void draw(Graphics g) {
        System.out.println("draw...");
        super.draw(g);
    }

    @Override
    public void move(int dx, int dy) {
        System.out.println("move ( " + dx + " / " + dy + ")...");
        super.move(dx, dy);
    }

    @Override
    public boolean contains(int x, int y) {
        boolean result = super.contains(x, y);
        System.out.println("contains ( " + x + " / " + y + ")..." + " = " + result);
        return result;
    }

    @Override
    public void setBounds(Point origin, Point corner) {
        System.out.println("setBounds ( " + origin + " / " + corner + ")...");
        super.setBounds(origin, corner);
    }

    @Override
    public Rectangle getBounds() {
        System.out.println("getBounds...");
        return super.getBounds();
    }

    @Override
    public List<FigureHandle> getHandles() {
        System.out.println("getHandles...");
        return super.getHandles();
    }

    @Override
    public void addFigureListener(FigureListener listener) {
        System.out.println("addFigureListener...");
        super.addFigureListener(listener);
    }

    @Override
    public void removeFigureListener(FigureListener listener) {
        System.out.println("removeFigureListener...");
        super.removeFigureListener(listener);
    }

    @Override
    public Figure clone() {
        System.out.println("clone...");
        return super.clone();
    }

}
