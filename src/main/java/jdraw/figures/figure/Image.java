/*
 * Copyright (c) 2017 Fachhochschule Nordwestschweiz (FHNW)
 * All Rights Reserved. 
 */

package jdraw.figures.figure;

import jdraw.figures.handle.state.Handle;
import jdraw.figures.handle.state.states.*;
import jdraw.framework.Figure;
import jdraw.framework.FigureHandle;


import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

/**
 * Represents rectangles in JDraw.
 *
 * @author Christoph Denzler
 */
public class Image extends AbstractRectangularFigure{
    /**
     * Create a new rectangle of the given dimension.
     *
     * @param x the x-coordinate of the upper left corner of the rectangle
     * @param y the y-coordinate of the upper left corner of the rectangle
     * @param w the rectangle's width
     * @param h the rectangle's height
     */
    public Image(int x, int y, int w, int h, String path) {
        super(new Point(x, y), w,h);
    }

    @Override
    public java.util.List<FigureHandle> getHandles() {
        handles = new LinkedList<>();

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

        java.awt.Image img;
        File f= new File("strawberry.jpg");
        img = new ImageIcon(f.toString()).getImage();

//        g.drawImage(img, 10,10);
//
        Rectangle rectangle = getBounds();
//        g.setColor(color);
        g.fillRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        g.setColor(Color.BLACK);
        g.drawRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
    }
}
