/*
 * Copyright (c) 2017 Fachhochschule Nordwestschweiz (FHNW)
 * All Rights Reserved. 
 */

package jdraw.figures.figure;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.LinkedList;

import jdraw.figures.handle.state.Handle;
import jdraw.figures.handle.state.states.*;
import jdraw.framework.Figure;
import jdraw.framework.FigureHandle;

/**
 * Represents rectangles in JDraw.
 *
 * @author Christoph Denzler
 */
public class Rect extends AbstractRectangularFigure{
    /**
     * Create a new rectangle of the given dimension.
     *
     * @param x the x-coordinate of the upper left corner of the rectangle
     * @param y the y-coordinate of the upper left corner of the rectangle
     * @param w the rectangle's width
     * @param h the rectangle's height
     */
    public Rect(int x, int y, int w, int h) {
        super(new Point(x, y), w,h);
    }

    private final Color color = new Color((int)(Math.random() * 0x1000000));

    @Override
    public java.util.List<FigureHandle> getHandles() {
        handles = new LinkedList<>();

//        handles.add(new NorthHandle(this));
//        handles.add(new SouthHandle(this));
//        handles.add(new EastHandle(this));
//        handles.add(new WestHandle(this));
//        handles.add(new NorthEastHandle(this));
//        handles.add(new NorthWestHandle(this));
//        handles.add(new SouthWestHandle(this));
//        handles.add(new SouthEastHandle(this));

        handles.add(new Handle(new NorthHandleState(this, Color.BLUE)));
        handles.add(new Handle(new SouthHandleState(this, Color.RED)));
        handles.add(new Handle(new EastHandleState(this, Color.GREEN)));
        handles.add(new Handle(new WestHandleState(this, Color.YELLOW)));
        handles.add(new Handle(new NorthEastHandleState(this, Color.CYAN)));
        handles.add(new Handle(new NorthWestHandleState(this, Color.MAGENTA)));
        handles.add(new Handle(new SouthWestHandleState(this, Color.ORANGE)));
        handles.add(new Handle(new SouthEastHandleState(this, Color.PINK)));

        return handles;
    }

    /**
     * Draw the rectangle to the given graphics context.
     *
     * @param g the graphics context to use for drawing.
     */
    @Override
    public void draw(Graphics g) {
        Rectangle rectangle = getBounds();
        g.setColor(color);
        g.fillRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        g.setColor(Color.BLACK);
        g.drawRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
    }

    @Override
    public Figure clone() {
        Rectangle rectangle = getBounds();
        return new Rect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
    }
}
