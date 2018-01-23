package jdraw.figures.factory;

import jdraw.figures.tool.LineTool;
import jdraw.framework.DrawContext;
import jdraw.framework.DrawTool;

public class LineToolFactory extends AbstractDrawToolFactory {
    @Override
    public DrawTool createTool(DrawContext c) {
        return new LineTool(c, getIconName(), getName());
    }
}