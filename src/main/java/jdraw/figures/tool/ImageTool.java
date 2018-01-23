/*
 * Copyright (c) 2017 Fachhochschule Nordwestschweiz (FHNW)
 * All Rights Reserved. 
 */

package jdraw.figures.tool;

import jdraw.figures.figure.Image;
import jdraw.figures.figure.Rect;
import jdraw.framework.DrawContext;
import jdraw.framework.Figure;

import java.awt.*;

/**
 * This tool defines a mode for drawing rectangles.
 *
 * @see Figure
 *
 * @author  Christoph Denzler
 */
public class ImageTool extends AbstractTool{

	private final String imagePath;

    public ImageTool(DrawContext context, String icon, String name) {
        super(context, icon, name);
        this.imagePath = getClass().getResource(IMAGES + "logo.png") + "";
    }

	public ImageTool(DrawContext context, String icon, String name, String path) {
		super(context, icon, name);
		this.imagePath = path;
	}


	@Override
	protected Figure getNewFigure(Point p){
    	return new Image(p.x, p.y, 0, 0, imagePath);
	}

}
