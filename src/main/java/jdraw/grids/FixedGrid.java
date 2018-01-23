package jdraw.grids;

import jdraw.framework.PointConstrainer;
import jdraw.std.StdDrawModel;
import org.apache.log4j.Logger;

import java.awt.*;

public class FixedGrid implements PointConstrainer {

    private static final Logger logger = Logger.getLogger(StdDrawModel.class);

    private final int GRID_SIZE;

    public FixedGrid(int gridSize) {
        this.GRID_SIZE = gridSize;
    }

    @Override
    public Point constrainPoint(Point p) {
        p = new Point((p.x + GRID_SIZE / 2) / GRID_SIZE * GRID_SIZE, (p.y + GRID_SIZE / 2) / GRID_SIZE * GRID_SIZE);
        return p;
    }

    @Override
    public int getStepX(boolean right) {
        return GRID_SIZE;
    }

    @Override
    public int getStepY(boolean down) {
        return GRID_SIZE;
    }

    @Override
    public void activate() {
        logger.trace("FixedGrid" + GRID_SIZE + "x" + GRID_SIZE + ":activate()");
    }

    @Override
    public void deactivate() {
        logger.trace("FixedGrid" + GRID_SIZE + "x" + GRID_SIZE + ":deactivate()");
    }

    @Override
    public void mouseDown() {
        System.out.println("FixedGrid:mouseDown()");
    }

    @Override
    public void mouseUp() {
        System.out.println("FixedGrid:mouseUp()");
    }
}
