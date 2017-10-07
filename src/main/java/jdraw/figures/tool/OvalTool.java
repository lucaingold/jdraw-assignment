package jdraw.figures.tool;

import jdraw.figures.figure.Oval;
import jdraw.figures.figure.Rect;
import jdraw.framework.DrawContext;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class OvalTool extends AbstractTool {

    public OvalTool(DrawContext context) {
        super(context);
    }

    @Override
    public void mouseDown(int x, int y, MouseEvent e) {
        if (newFigure != null) {
            throw new IllegalStateException();
        }
        anchor = new Point(x, y);
        newFigure = new Oval(x, y, 0, 0);
        view.getModel().addFigure(newFigure);

    }

    @Override
    public void mouseDrag(int x, int y, MouseEvent e) {
        newFigure.setBounds(anchor, new Point(x, y));
        Rectangle r = newFigure.getBounds();
        this.context.showStatusText("w: " + r.width + ", h: " + r.height);
    }

    @Override
    public void mouseUp(int x, int y, MouseEvent e) {
        newFigure = null;
        anchor = null;
        this.context.showStatusText("Rectangle Mode");
    }

    @Override
    public Icon getIcon() {
        return new ImageIcon(getClass().getResource(IMAGES + "oval.png"));
    }

    @Override
    public String getName() {
        return "Oval";
    }
}
