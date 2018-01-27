package jdraw.commands;

import jdraw.framework.DrawCommand;
import jdraw.framework.DrawCommandHandler;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Stack;

public class MyDrawCommandHandler implements DrawCommandHandler{

    private Stack<DrawCommand> undoStack = new Stack<>();
    private Stack<DrawCommand> redoStack = new Stack<>();
    private Script script = null;

    @Override
    public void addCommand(DrawCommand cmd) {
        //Clear the redoStack whenever a new command is added
        redoStack.clear();
        //A new command is either added on the undo-stack or to an open script;
        // this implementation does not support recursive scripts!
        if(script == null) {
            undoStack.push(cmd);
        } else {
            script.commands.add(cmd);
        }
    }

    @Override
    public void undo() {
        if(undoPossible()) {
            DrawCommand d = undoStack.pop();
            redoStack.push(d);
            d.undo();
        }
    }

    @Override
    public void redo() {
        if (redoPossible()) {
            DrawCommand d = redoStack.pop();
            undoStack.push(d);
            d.redo();
        }
    }

    @Override
    public boolean undoPossible() {
        return undoStack.size() > 0;
    }

    @Override
    public boolean redoPossible() {
        return redoStack.size() > 0;
    }

    @Override
    public void beginScript() {
        if (script != null) throw new IllegalStateException();
        //As the script is no longer null, new commands are added to the script
        script = new Script();
    }

    @Override
    public void endScript() {
        if (script == null) throw new IllegalStateException();
        Script tmp = script;
        //Script reference has to be cleared before script-command is added with addCommand (otherwise it would be added to itself)
        script = null;
        if (tmp.commands.size() > 0) {
            addCommand(tmp);
        }
    }

    @Override
    public void clearHistory() {
        undoStack.clear();
        redoStack.clear();
        script = null;
    }

    private static final class Script implements DrawCommand {
        private List<DrawCommand> commands = new LinkedList<>();

        public void redo() {
            ListIterator<DrawCommand> it = commands.listIterator();
            while (it.hasNext()) { it.next().redo(); }
        }

        public void undo() {
            int size = commands.size();
            ListIterator<DrawCommand> it = commands.listIterator(size);
            //Undo has to traverse the list backwards
            while (it.hasPrevious()) { it.previous().undo(); }
        }
    }
}

