package jdraw.grids;

import jdraw.framework.PointConstrainer;
import jdraw.std.StdDrawModel;
import org.apache.log4j.Logger;

import java.awt.*;

public class SimpleGrid implements PointConstrainer {

    private static final Logger logger = Logger.getLogger(StdDrawModel.class);

    private final int STEP_SIZE;

    public SimpleGrid(int stepSize){
        this.STEP_SIZE = stepSize;
    }

    @Override
    public Point constrainPoint(Point p) {
        return p;
    }

    @Override
    public int getStepX(boolean right) {
        return STEP_SIZE;
    }

    @Override
    public int getStepY(boolean down) {
        return STEP_SIZE;    }

    @Override
    public void activate() {
        logger.trace("SimpleGrid" + STEP_SIZE + ":activate()");
    }

    @Override
    public void deactivate() {
        logger.trace("SimpleGrid" + STEP_SIZE + ":deactivate()");
    }

    @Override
    public void mouseDown() {
        System.out.println("SimpleGrid:mouseDown()");
    }

    @Override
    public void mouseUp() {
        System.out.println("SimpleGrid:mouseUp()");
    }
}
