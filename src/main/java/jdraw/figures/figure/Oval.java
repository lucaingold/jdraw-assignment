package jdraw.figures.figure;

import jdraw.figures.handle.state.Handle;
import jdraw.figures.handle.state.states.*;
import jdraw.framework.Figure;
import jdraw.framework.FigureHandle;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.LinkedList;

public class Oval extends AbstractRectangularFigure {

    public Oval(int x, int y, int w, int h) {
        super(new Point(x, y), w,h);
    }

    @Override
    public void draw(Graphics g) {
        Rectangle rectangle = this.getBounds();
        Graphics2D g2 = (Graphics2D) g;
        java.awt.geom.Ellipse2D circle = new Ellipse2D.Float(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        g2.setPaint(Color.YELLOW);
        g2.fill(circle);
        g2.setPaint(Color.BLACK);
        g2.draw(circle);
    }

    @Override
    public java.util.List<FigureHandle> getHandles() {
        handles = new LinkedList<>();

//        handles.add(new NorthHandle(this));
//        handles.add(new SouthHandle(this));
//        handles.add(new EastHandle(this));
//        handles.add(new WestHandle(this));

        handles.add(new Handle(new NorthHandleState(this)));
        handles.add(new Handle(new SouthHandleState(this)));
        handles.add(new Handle(new EastHandleState(this)));
        handles.add(new Handle(new WestHandleState(this)));

        return handles;
    }

    @Override
    public Oval clone() {
        return (Oval) super.clone();
    }
}
