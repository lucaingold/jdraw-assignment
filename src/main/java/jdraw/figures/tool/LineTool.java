package jdraw.figures.tool;

import jdraw.figures.figure.Line;
import jdraw.figures.figure.Oval;
import jdraw.framework.DrawContext;
import jdraw.framework.Figure;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;

public class LineTool extends AbstractTool {

    public LineTool(DrawContext context, String icon, String name) {
        super(context, icon, name);
    }

    @Override
    protected Figure getNewFigure(Point p){
        return new Line(getAnchor().x, getAnchor().y, p.x, p.y);
    }
}
