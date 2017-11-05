package jdraw.grids;

import jdraw.framework.DrawView;
import jdraw.framework.Figure;
import jdraw.framework.FigureHandle;
import jdraw.framework.PointConstrainer;
import jdraw.std.StdDrawModel;
import org.apache.log4j.Logger;

import java.awt.*;

public class SnapGrid implements PointConstrainer {

    private static final Logger logger = Logger.getLogger(StdDrawModel.class);

    private final int THRESHOLD;

    private final DrawView VIEW;

    public SnapGrid(int threshold, DrawView drawView) {
        this.THRESHOLD = threshold;
        this.VIEW = drawView;
    }

    @Override
    public Point constrainPoint(Point p) {
        Point location = getNearestHandle(p);
        return location != null ? location : p;
    }

    private Point getNearestHandle(Point p) {
        for (Figure figure : VIEW.getModel().getFigures()) {
            for (FigureHandle handle : figure.getHandles()) {
                Point location = handle.getLocation();
                if (THRESHOLD >= p.distance(location)) {
                    return location;
                }
            }
        }
        return null;
    }

    @Override
    public int getStepX(boolean right) {
        Point newPoint;
//        if (right)
//            newPoint = new Point(currentPosition.x + THRESHOLD, currentPosition.y);
//        else
//            newPoint = new Point(currentPosition.x - THRESHOLD, currentPosition.y);
//
//        Point location = getNearestHandle(newPoint);
//        newPoint = location != null ? location : newPoint;
//        if (location != null) {
//            int distance;
//            if (right)
//                distance = newPoint.x - currentPosition.x;
//            else
//                distance = currentPosition.x - newPoint.x;
//            currentPosition = newPoint;
//            return distance;
//        }
//        currentPosition = newPoint;
        return THRESHOLD;
    }

    @Override
    public int getStepY(boolean down) {
//        Point newPoint;
//        if (down)
//            newPoint = new Point(currentPosition.x, currentPosition.y + THRESHOLD);
//        else
//            newPoint = new Point(currentPosition.x, currentPosition.y - THRESHOLD);
//
//        Point location = getNearestHandle(newPoint);
//        newPoint = location != null ? location : newPoint;
//        if (location != null) {
//            int distance;
//            if (down)
//                distance = newPoint.y - currentPosition.y;
//            else
//                distance = currentPosition.y - newPoint.y;
//            currentPosition = newPoint;
//            return distance;
//        }
//        currentPosition = newPoint;
        return THRESHOLD;    }

    @Override
    public void activate() {
        logger.trace("SnapGrid" + THRESHOLD + ":activate()");
    }

    @Override
    public void deactivate() {
        logger.trace("SnapGrid" + THRESHOLD + ":deactivate()");
    }

    @Override
    public void mouseDown() {
        System.out.println("SnapGrid:mouseDown()");
    }

    @Override
    public void mouseUp() {
        System.out.println("SnapGrid:mouseUp()");
    }
}
