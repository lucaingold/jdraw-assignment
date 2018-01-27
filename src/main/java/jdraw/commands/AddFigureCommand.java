package jdraw.commands;

import jdraw.framework.DrawCommand;
import jdraw.framework.DrawModel;
import jdraw.framework.Figure;

public class AddFigureCommand implements DrawCommand{
    private final DrawModel model;
    private final Figure figure;

    public AddFigureCommand(DrawModel model, Figure figure) {
        this.model = model;
        this.figure = figure;
    }

    @Override
    public void redo() {
        model.addFigure(figure);
    }

    @Override
    public void undo() {
        model.removeFigure(figure);
    }


    //A new AddFigureCommand is added when a new figure has been created, e.g. in method mouseUp of the draw-tool:
    // context.getModel().getDrawCommandHandler().addCommand(new AddFigureCommand(context.getModel(), figure));
}