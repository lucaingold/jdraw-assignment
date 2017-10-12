package jdraw.figures.tool;

import jdraw.figures.figure.Line;
import jdraw.figures.figure.Oval;
import jdraw.figures.figure.Rect;
import jdraw.framework.DrawContext;
import jdraw.framework.Figure;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class OvalTool extends AbstractTool {

    public OvalTool(DrawContext context, String icon, String name) { super(context, icon, name); }

    @Override
    protected Figure getNewFigure(Point p){
        return new Oval(p.x, p.y, 0, 0);
    }
}
