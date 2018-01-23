/*
 * Copyright (c) 2017 Fachhochschule Nordwestschweiz (FHNW)
 * All Rights Reserved. 
 */

package jdraw.figures.tool;

import java.awt.*;
import java.awt.event.MouseEvent;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import jdraw.figures.figure.Oval;
import jdraw.figures.figure.Rect;
import jdraw.framework.DrawContext;
import jdraw.framework.DrawTool;
import jdraw.framework.DrawView;
import jdraw.framework.Figure;

/**
 * This tool defines a mode for drawing rectangles.
 *
 * @see jdraw.framework.Figure
 *
 * @author  Christoph Denzler
 */
public class RectTool extends AbstractTool{

    public RectTool(DrawContext context, String icon, String name) {
        super(context, icon, name);
    }

	@Override
	protected Figure getNewFigure(Point p){
		return new Rect(p.x, p.y, 0, 0);
	}

}
