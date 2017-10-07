/*
 * Copyright (c) 2017 Fachhochschule Nordwestschweiz (FHNW)
 * All Rights Reserved. 
 */

package jdraw.figures.tool;

import java.awt.*;
import java.awt.event.MouseEvent;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import jdraw.figures.figure.Rect;
import jdraw.framework.DrawContext;
import jdraw.framework.DrawTool;
import jdraw.framework.DrawView;

/**
 * This tool defines a mode for drawing rectangles.
 *
 * @see jdraw.framework.Figure
 *
 * @author  Christoph Denzler
 */
public class RectTool extends AbstractTool{

	public RectTool(DrawContext context){
		super(context);
	}

	/**
	 * Initializes a new Rectangle object by setting an anchor
	 * point where the mouse was pressed. A new Rectangle is then
	 * added to the model.
	 * @param x x-coordinate of mouse
	 * @param y y-coordinate of mouse
	 * @param e event containing additional information about which keys were pressed.
	 * 
	 * @see DrawTool#mouseDown(int, int, MouseEvent)
	 */
	@Override
	public void mouseDown(int x, int y, MouseEvent e) {
		if (newFigure != null) {
			throw new IllegalStateException();
		}
		anchor = new Point(x, y);
		newFigure = new Rect(x, y, 0, 0);
		view.getModel().addFigure(newFigure);
	}

	/**
	 * During a mouse drag, the Rectangle will be resized according to the mouse
	 * position. The status bar shows the current size.
	 * 
	 * @param x   x-coordinate of mouse
	 * @param y   y-coordinate of mouse
	 * @param e   event containing additional information about which keys were
	 *            pressed.
	 * 
	 * @see DrawTool#mouseDrag(int, int, MouseEvent)
	 */
	@Override
	public void mouseDrag(int x, int y, MouseEvent e) {
		newFigure.setBounds(anchor, new Point(x, y));
		Rectangle r = newFigure.getBounds();
		this.context.showStatusText("w: " + r.width + ", h: " + r.height);
	}

	/**
	 * When the user releases the mouse, the Rectangle object is updated
	 * according to the color and fill status settings.
	 * 
	 * @param x   x-coordinate of mouse
	 * @param y   y-coordinate of mouse
	 * @param e   event containing additional information about which keys were
	 *            pressed.
	 * 
	 * @see jdraw.framework.DrawTool#mouseUp(int, int, MouseEvent)
	 */
	@Override
	public void mouseUp(int x, int y, MouseEvent e) {
		newFigure = null;
		anchor = null;
		this.context.showStatusText("Rectangle Mode");
	}

	
	@Override
	public Icon getIcon() {
		return new ImageIcon(getClass().getResource(IMAGES + "rectangle.png"));
	}

	@Override
	public String getName() {
		return "Rectangle";
	}

}
