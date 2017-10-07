package jdraw.figures.tool;

import jdraw.figures.figure.Rect;
import jdraw.framework.DrawContext;
import jdraw.framework.DrawTool;
import jdraw.framework.DrawView;
import jdraw.framework.Figure;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public abstract class AbstractTool implements DrawTool {
    /**
     * The context we use for drawing.
     */
    protected DrawContext context;


    /**
     * the image resource path.
     */
    protected static final String IMAGES = "/images/";


    /**
     * The context's view. This variable can be used as a shortcut, i.e.
     * instead of calling context.getView().
     */
    protected DrawView view;

    /**
     * Temporary variable. During rectangle creation (during a
     * mouse down - mouse drag - mouse up cycle) this variable refers
     * to the new rectangle that is inserted.
     */
    protected Figure newFigure = null;

    /**
     * Temporary variable.
     * During rectangle creation this variable refers to the point the
     * mouse was first pressed.
     */
    protected Point anchor = null;


    /**
     * Create a new rectangle tool for the given context.
     * @param context a context to use this tool in.
     */
    public AbstractTool(DrawContext context) {
        this.context = context;
        this.view = context.getView();
    }

    /**
     * Initializes a new Rectangle object by setting an anchor
     * point where the mouse was pressed. A new Rectangle is then
     * added to the model.
     *
     * @param x x-coordinate of mouse
     * @param y y-coordinate of mouse
     * @param e event containing additional information about which keys were pressed.
     * @see jdraw.framework.DrawTool#mouseDown(int, int, MouseEvent)
     */

    public abstract void mouseDown(int x, int y, MouseEvent e);

    /**
     * During a mouse drag, the Rectangle will be resized according to the mouse
     * position. The status bar shows the current size.
     *
     * @param x x-coordinate of mouse
     * @param y y-coordinate of mouse
     * @param e event containing additional information about which keys were
     *          pressed.
     * @see jdraw.framework.DrawTool#mouseDrag(int, int, MouseEvent)
     */
    @Override
    public abstract void mouseDrag(int x, int y, MouseEvent e);

    /**
     * When the user releases the mouse, the Rectangle object is updated
     * according to the color and fill status settings.
     *
     * @param x x-coordinate of mouse
     * @param y y-coordinate of mouse
     * @param e event containing additional information about which keys were
     *          pressed.
     * @see jdraw.framework.DrawTool#mouseUp(int, int, MouseEvent)
     */
    @Override
    public abstract void mouseUp(int x, int y, MouseEvent e);

    @Override
    public Cursor getCursor() {
        return Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR);
    }

    @Override
    public abstract Icon getIcon();

    @Override
    public abstract String getName();

    /**
     * Deactivates the current mode by resetting the cursor
     * and clearing the status bar.
     *
     * @see jdraw.framework.DrawTool#deactivate()
     */
    @Override
    public void deactivate() {
        this.context.showStatusText("");
    }

    /**
     * Activates the Rectangle Mode. There will be a
     * specific menu added to the menu bar that provides settings for
     * Rectangle attributes
     */
    @Override
    public void activate() {
        this.context.showStatusText(getName() + " Mode");
    }


}
