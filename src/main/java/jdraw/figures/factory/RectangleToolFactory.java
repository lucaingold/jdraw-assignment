package jdraw.figures.factory;

import jdraw.figures.tool.RectTool;
import jdraw.framework.DrawContext;
import jdraw.framework.DrawTool;

public class RectangleToolFactory extends AbstractDrawToolFactory {
    @Override
    public DrawTool createTool(DrawContext c) {
        return new RectTool(c, getIconName(), getName());
    }
}