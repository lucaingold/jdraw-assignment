package jdraw.figures.factory;

import jdraw.figures.tool.OvalTool;
import jdraw.framework.DrawContext;
import jdraw.framework.DrawTool;

public class OvalToolFactory extends AbstractDrawToolFactory {
    @Override
    public DrawTool createTool(DrawContext c) {
        return new OvalTool(c, getIconName(), getName());
    }
}