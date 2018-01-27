package jdraw.commands;

import jdraw.framework.DrawCommand;
import jdraw.framework.DrawModel;
import jdraw.framework.Figure;
import jdraw.framework.FigureGroup;

import java.awt.*;

public class GroupFigureCommand implements DrawCommand {
    private final DrawModel model;

    private final FigureGroup group;

    private final boolean insertGroup;

    public GroupFigureCommand(DrawModel model, FigureGroup group, boolean insertGroup) {
        this.model = model;
        this.group = group;
        //Indicates whether the current command instance is a group or an ungroup operation
        this.insertGroup = insertGroup;
    }

    @Override
    public void redo() {
        if (insertGroup) insertGroup();
        else removeGroup();
    }

    @Override
    public void undo() {
        if (insertGroup) removeGroup();
        else insertGroup();
    }

    private void insertGroup() {
        for (Figure f : group.getFigureParts()) {
            model.removeFigure(f);
        }
        model.addFigure((Figure) group);
    }

    private void removeGroup() {
        model.removeFigure((Figure) group);
        for (Figure f : group.getFigureParts()) {
            //Problem: the figures are added at the end of the figure list and are therefore probably not at the same position as before the grouping operation!
            model.addFigure(f);
        }
    }
}
