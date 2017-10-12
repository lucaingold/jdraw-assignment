/*
 * Copyright (c) 2017 Fachhochschule Nordwestschweiz (FHNW)
 * All Rights Reserved. 
 */

package jdraw.figures.figure;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import jdraw.framework.Figure;
import jdraw.framework.FigureEvent;

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

    /**
     * Draw the rectangle to the given graphics context.
     *
     * @param g the graphics context to use for drawing.
     */
    @Override
    public void draw(Graphics g) {
        Rectangle rectangle = getBounds();
        g.setColor(Color.orange);
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
