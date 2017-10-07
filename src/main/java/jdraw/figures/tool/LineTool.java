package jdraw.figures.tool;

import jdraw.figures.figure.Line;
import jdraw.figures.figure.Oval;
import jdraw.framework.DrawContext;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;

public class LineTool extends AbstractTool {

    public LineTool(DrawContext context) {
        super(context);
    }


    @Override
    public void mouseDown(int x, int y, MouseEvent e) {
        if (newFigure != null) {
            throw new IllegalStateException();
        }
        anchor = new Point(x, y);
        newFigure = new Line(anchor.x, anchor.y, x, y);
        view.getModel().addFigure(newFigure);

    }

    @Override
    public void mouseDrag(int x, int y, MouseEvent e) {
        ((Line)newFigure).moveEndpoint(x,y);
        newFigure.setBounds(anchor, new Point(x, y));
        Rectangle l = newFigure.getBounds();
        this.context.showStatusText("w: " + l.width + ", h: " + l.height);
    }

    @Override
    public void mouseUp(int x, int y, MouseEvent e) {
        newFigure = null;
        this.context.showStatusText("Line Mode");

        anchor = null;
    }

    @Override
    public Icon getIcon() {
        return new ImageIcon(getClass().getResource(IMAGES + "line.png"));
    }

    @Override
    public String getName() {
        return "Line";
    }
}
