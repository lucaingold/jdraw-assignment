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
    private DrawContext context;

    /**
     * the image resource path.
     */
    protected static final String IMAGES = "/images/";

    /**
     * the icon for the tool.
     */
    private final String icon;

    /**
     * the name of the tool.
     */
    private final String name;

    /**
     * The context's view. This variable can be used as a shortcut, i.e.
     * instead of calling context.getView().
     */
    protected final DrawView view;

    /**
     * Temporary variable. During rectangle creation (during a
     * mouse down - mouse drag - mouse up cycle) this variable refers
     * to the new rectangle that is inserted.
     */
    private Figure newFigure = null;

    /**
     * Temporary variable.
     * During rectangle creation this variable refers to the point the
     * mouse was first pressed.
     */
    private Point anchor = null;

    /**
     * Create a new rectangle tool for the given context.
     * @param context a context to use this tool in.
     */
    public AbstractTool(DrawContext context, String icon, String name) {
        this.context = context;
        this.view = context.getView();
        this.name = name;
        this.icon = icon;
    }

    /**
     * Initializes a new Rectangle object by setting an corner
     * point where the mouse was pressed. A new Rectangle is then
     * added to the model.
     *
     * @param x x-coordinate of mouse
     * @param y y-coordinate of mouse
     * @param e event containing additional information about which keys were pressed.
     * @see jdraw.framework.DrawTool#mouseDown(int, int, MouseEvent)
     */

    @Override
    public void mouseDown(int x, int y, MouseEvent e) {
        if (newFigure != null) {
            throw new IllegalStateException();
        }
        anchor = new Point(x, y);
        newFigure = this.getNewFigure(anchor);
        view.getModel().addFigure(newFigure);
    }

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
    public void mouseDrag(int x, int y, MouseEvent e) {
        newFigure.setBounds(anchor, new Point(x, y));
        Rectangle r = newFigure.getBounds();
        this.context.showStatusText("w: " + r.width + ", h: " + r.height);
    }


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
    public void mouseUp(int x, int y, MouseEvent e) {
        Rectangle r = newFigure.getBounds();
        if (r.width == 0 && r.height == 0) {
            context.getModel().removeFigure(newFigure);
        }
        newFigure = null;
        this.context.showStatusText(name + " Mode");
        anchor = null;
    }


    @Override
    public Cursor getCursor() {
        return Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR);
    }


    @Override
    public Icon getIcon() {
        return new ImageIcon(getClass().getResource(IMAGES + icon));
    }

    @Override
    public String getName() {
        return name;
    }


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

    protected abstract Figure getNewFigure(Point p);

    protected Point getAnchor() {
        return anchor;
    }
}
