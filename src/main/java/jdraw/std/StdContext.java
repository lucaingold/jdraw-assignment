/*
 * Copyright (c) 2017 Fachhochschule Nordwestschweiz (FHNW)
 * All Rights Reserved.
 */
package jdraw.std;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileFilter;

import jdraw.commands.GroupFigureCommand;
import jdraw.decorators.BorderDecorator;
import jdraw.decorators.BundleDecorator;
import jdraw.decorators.LogDecorator;
import jdraw.decorators.SimpleGreenDecorator;
import jdraw.figures.figure.Group;
import jdraw.figures.tool.ImageTool;
import jdraw.figures.tool.LineTool;
import jdraw.figures.tool.OvalTool;
import jdraw.figures.tool.RectTool;
import jdraw.framework.*;
import jdraw.grids.FixedGrid;
import jdraw.grids.SimpleGrid;
import jdraw.grids.SnapGrid;

/**
 * Standard implementation of interface DrawContext.
 *
 * @author Dominik Gruntz & Christoph Denzler
 * @version 2.6, 24.09.09
 * @see DrawView
 */
public class StdContext extends AbstractContext {

    /**
     * Constructs a standard context with a default set of drawing tools.
     *
     * @param view the view that is displaying the actual drawing.
     */
    public StdContext(DrawView view) {
        super(view, null);
    }

    /**
     * Constructs a standard context. The drawing tools available can be parameterized using <code>toolFactories</code>.
     *
     * @param view          the view that is displaying the actual drawing.
     * @param toolFactories a list of DrawToolFactories that are available to the user
     */
    public StdContext(DrawView view, List<DrawToolFactory> toolFactories) {
        super(view, toolFactories);
    }


    /**
     * Saves current clipboard in own data structure
     */
    private ClipBoard clipboard = new ClipBoard();


    /**
     * Creates and initializes the "Edit" menu.
     *
     * @return the new "Edit" menu.
     */
    @Override
    protected JMenu createEditMenu() {
        JMenu editMenu = new JMenu("Edit");
        final JMenuItem undo = new JMenuItem("Undo");
        undo.setAccelerator(KeyStroke.getKeyStroke("control Z"));
        editMenu.add(undo);
        undo.addActionListener(e -> {
                    final DrawCommandHandler h = getModel().getDrawCommandHandler();
                    if (h.undoPossible()) {
                        h.undo();
                    }
                }
        );

        final JMenuItem redo = new JMenuItem("Redo");
        redo.setAccelerator(KeyStroke.getKeyStroke("control Y"));
        editMenu.add(redo);
        redo.addActionListener(e -> {
                    final DrawCommandHandler h = getModel().getDrawCommandHandler();
                    if (h.redoPossible()) {
                        h.redo();
                    }
                }
        );
        editMenu.addSeparator();

        JMenuItem sa = new JMenuItem("SelectAll");
        sa.setAccelerator(KeyStroke.getKeyStroke("control A"));
        editMenu.add(sa);
        sa.addActionListener(e -> {
                    for (Figure f : getModel().getFigures()) {
                        getView().addToSelection(f);
                    }
                    getView().repaint();
                }
        );

        editMenu.addSeparator();


        JMenuItem cut = new JMenuItem("Cut ˆX");
        cut.addActionListener(e -> {
            List<Figure> selection = getView().getSelection();
            clipboard.setSelection(selection);
            selection.forEach(f -> getModel().removeFigure(f));
        });
        cut.setAccelerator(KeyStroke.getKeyStroke("control X"));
        editMenu.add(cut).setEnabled(true);


        JMenuItem copy = new JMenuItem("Copy ˆC");
        copy.addActionListener(e -> {
            List<Figure> selection = new LinkedList<>();
            getView().getSelection().forEach(f -> selection.add(f.clone()));
            clipboard.setSelection(selection);

        });
        copy.setAccelerator(KeyStroke.getKeyStroke("control C"));
        editMenu.add(copy).setEnabled(true);

        JMenuItem paste = new JMenuItem("Paste ˆV");
        paste.addActionListener(e -> {
            if(!clipboard.isEmpty()) {
                List<Figure> selection = clipboard.getSelection();
                selection.forEach(f -> {
                    getModel().addFigure(f);
                    getView().addToSelection(f);
                });
//                clipboard.reset();
            }
        });
        paste.setAccelerator(KeyStroke.getKeyStroke("control V"));
        editMenu.add(paste).setEnabled(true);

        editMenu.addSeparator();
        JMenuItem clear = new JMenuItem("Clear");
        editMenu.add(clear);
        clear.addActionListener(e -> {
            getModel().removeAllFigures();
        });

        editMenu.addSeparator();
        JMenuItem group = new JMenuItem("Group");
        group.addActionListener(e -> {
            List<Figure> selection = getView().getSelection();
            Group g = new Group(selection, getModel());
            getModel().addFigure(g);
            selection.forEach(f -> getModel().removeFigure(f));
            getView().addToSelection(g);
            //GroupCommand
            this.getModel().getDrawCommandHandler().addCommand(new GroupFigureCommand(this.getModel(), g, true));
        });

        group.setEnabled(true);
        editMenu.add(group);

        JMenuItem ungroup = new JMenuItem("Ungroup");

        ungroup.addActionListener(e -> {
            List<Figure> selection = getView().getSelection();
            for (Figure f: selection ) {
//                if(f instanceof FigureGroup){
                if(f.isInstanceOf(FigureGroup.class)){
                    //GroupCommand
                    this.getModel().getDrawCommandHandler().addCommand(new GroupFigureCommand(this.getModel(), (FigureGroup) f, false));
                    for (Figure member: f.getInstanceOf(FigureGroup.class).getFigureParts()) {
//                   for (Figure member:((FigureGroup) f).getFigureParts()) {
                        getModel().addFigure(member);
                        getView().addToSelection(f);
                    }
                    getModel().removeFigure(f);
                }
            }
        });



        ungroup.setEnabled(true);
        editMenu.add(ungroup);

        editMenu.addSeparator();

        JMenu orderMenu = new JMenu("Order...");
        JMenuItem frontItem = new JMenuItem("Bring To Front");
        frontItem.addActionListener(e -> {
            bringToFront(getView().getModel(), getView().getSelection());
        });
        orderMenu.add(frontItem);
        JMenuItem backItem = new JMenuItem("Send To Back");
        backItem.addActionListener(e -> {
            sendToBack(getView().getModel(), getView().getSelection());
        });
        orderMenu.add(backItem);
        editMenu.add(orderMenu);

        JMenu grid = new JMenu("Grid");

        //No Grid
        JMenuItem noGrid = new JMenuItem("No Grid");
        noGrid.addActionListener(e -> getView().setConstrainer(null));
        grid.add(noGrid);

        //Simple Grid Menu
        JMenu simpleGridMenu = new JMenu("Simple Grid");

        JMenuItem simpleGrid1 = new JMenuItem("Step 1 Grid");
        simpleGrid1.addActionListener(e -> getView().setConstrainer(new SimpleGrid(1)));
        simpleGridMenu.add(simpleGrid1);

        JMenuItem simpleGrid50 = new JMenuItem("Step 50 Grid");
        simpleGrid50.addActionListener(e -> getView().setConstrainer(new SimpleGrid(50)));
        simpleGridMenu.add(simpleGrid50);

        grid.add(simpleGridMenu);

        //Fixed Grid Menu
        JMenu fixedGridMenu = new JMenu("Fixed Grid");

        JMenuItem fixedGrid30 = new JMenuItem("30x30 Grid");
        fixedGrid30.addActionListener(e -> getView().setConstrainer(new FixedGrid(30)));
        fixedGridMenu.add(fixedGrid30);

        JMenuItem fixedGrid50 = new JMenuItem("50x50 Grid");
        fixedGrid50.addActionListener(e -> getView().setConstrainer(new FixedGrid(50)));
        fixedGridMenu.add(fixedGrid50);

        grid.add(fixedGridMenu);

        //Snap Grid
        JMenuItem snapGrid = new JMenuItem("Snap Grid");
        snapGrid.addActionListener(e -> getView().setConstrainer(new SnapGrid(50, getView())));
        grid.add(snapGrid);



        JMenu decoratorMenu = new JMenu("Decorators...");
        JMenuItem greenDecorator = new JMenuItem("Toggle Green Decorator");
        decoratorMenu.add(greenDecorator);
        greenDecorator.addActionListener(e -> {
                    List<Figure> s = getView().getSelection();
                    getView().clearSelection();
                    for (Figure f : s) {
                        Figure f2 = null;
                        if (f instanceof SimpleGreenDecorator) {
                            f2 = ((SimpleGreenDecorator) f).getInner();
                        } else {
                            f2 = new SimpleGreenDecorator(f);
                        }
                        getModel().removeFigure(f);
                        getModel().addFigure(f2);
                        getView().addToSelection(f2);
                    }
                }
        );

        JMenuItem borderDecorator = new JMenuItem("Toggle Border Decorator");
        decoratorMenu.add(borderDecorator);
        borderDecorator.addActionListener( e -> {
            List<Figure> s = getView().getSelection();
            getView().clearSelection();
            for (Figure f : s) {
                BorderDecorator dec = new BorderDecorator(f);
                getModel().removeFigure(f);
                getModel().addFigure(dec);
                getView().addToSelection(dec);
            }
        });

        JMenuItem bundleDecorator = new JMenuItem("Toggle Bundle Decorator");
        decoratorMenu.add(bundleDecorator);
        bundleDecorator.addActionListener(e -> {
                    List<Figure> s = getView().getSelection();
                    getView().clearSelection();
                    for (Figure f : s) {
                        Figure f2 = null;
                        if (f instanceof BundleDecorator) {
                            f2 = ((BundleDecorator) f).getInner();
                        } else {
                            f2 = new BundleDecorator(f);
                        }
                        getModel().removeFigure(f);
                        getModel().addFigure(f2);
                        getView().addToSelection(f2);
                    }
                }
        );


        JMenuItem logDecorator = new JMenuItem("Toggle Log Decorator");
        decoratorMenu.add(logDecorator);
        logDecorator.addActionListener(e -> {
                    List<Figure> s = getView().getSelection();
                    getView().clearSelection();
                    for (Figure f : s) {
                        Figure f2 = null;
                        if (f instanceof LogDecorator) {
                            f2 = ((LogDecorator) f).getInner();
                        } else {
                            f2 = new LogDecorator(f);
                        }
                        getModel().removeFigure(f);
                        getModel().addFigure(f2);
                        getView().addToSelection(f2);
                    }
                }
        );


        decoratorMenu.add(backItem);
        editMenu.add(decoratorMenu);

        editMenu.add(grid);

        return editMenu;
    }

    /**
     * Creates and initializes items in the file menu.
     *
     * @return the new "File" menu.
     */
    @Override
    protected JMenu createFileMenu() {
        JMenu fileMenu = new JMenu("File");
        JMenuItem open = new JMenuItem("Open");
        fileMenu.add(open);
        open.setAccelerator(KeyStroke.getKeyStroke("control O"));
        open.addActionListener(e -> doOpen());

        JMenuItem save = new JMenuItem("Save");
        save.setAccelerator(KeyStroke.getKeyStroke("control S"));
        fileMenu.add(save);
        save.addActionListener(e -> doSave());

        JMenuItem exit = new JMenuItem("Exit");
        fileMenu.add(exit);
        exit.addActionListener(e -> System.exit(0));

        return fileMenu;
    }

    @Override
    protected void doRegisterDrawTools() {
        DrawTool rectangleTool = new RectTool(this, "rectangle.png", "Rectangle");
        DrawTool lineTool = new LineTool(this, "line.png", "Line");
        DrawTool ovalTool = new OvalTool(this, "oval.png", "Oval");
        DrawTool imageTool = new ImageTool(this, "image.png", "Image");
        addTool(rectangleTool);
        addTool(lineTool);
        addTool(ovalTool);
        addTool(imageTool);
    }

    /**
     * Changes the order of figures and moves the figures in the selection
     * to the front, i.e. moves them to the end of the list of figures.
     *
     * @param model     model in which the order has to be changed
     * @param selection selection which is moved to front
     */
    public void bringToFront(DrawModel model, List<Figure> selection) {
        // the figures in the selection are ordered according to the order in
        // the model
        List<Figure> orderedSelection = new LinkedList<Figure>();
        int pos = 0;
        for (Figure f : model.getFigures()) {
            pos++;
            if (selection.contains(f)) {
                orderedSelection.add(0, f);
            }
        }
        for (Figure f : orderedSelection) {
            model.setFigureIndex(f, --pos);
        }
    }

    /**
     * Changes the order of figures and moves the figures in the selection
     * to the back, i.e. moves them to the front of the list of figures.
     *
     * @param model     model in which the order has to be changed
     * @param selection selection which is moved to the back
     */
    public void sendToBack(DrawModel model, List<Figure> selection) {
        // the figures in the selection are ordered according to the order in
        // the model
        List<Figure> orderedSelection = new LinkedList<Figure>();
        for (Figure f : model.getFigures()) {
            if (selection.contains(f)) {
                orderedSelection.add(f);
            }
        }
        int pos = 0;
        for (Figure f : orderedSelection) {
            model.setFigureIndex(f, pos++);
        }
    }

    /**
     * Handles the saving of a drawing to a file.
     */
    private void doSave() {
        JFileChooser chooser = new JFileChooser(getClass().getResource("")
                .getFile());
        chooser.setDialogTitle("Save Graphic");
        chooser.setDialogType(JFileChooser.SAVE_DIALOG);
        FileFilter filter = new FileFilter() {
            @Override
            public String getDescription() {
                return "JDraw Graphic (*.draw)";
            }

            @Override
            public boolean accept(File f) {
                return f.getName().endsWith(".draw");
            }
        };
        chooser.setFileFilter(filter);
        int res = chooser.showSaveDialog(this);

        if (res == JFileChooser.APPROVE_OPTION) {
            // save graphic
            File file = chooser.getSelectedFile();
            if (chooser.getFileFilter() == filter && !filter.accept(file)) {
                file = new File(chooser.getCurrentDirectory(), file.getName() + ".draw");
            }
            System.out.println("save current graphic to file " + file.getName());
        }
    }

    /**
     * Handles the opening of a new drawing from a file.
     */
    private void doOpen() {
        JFileChooser chooser = new JFileChooser(getClass().getResource("")
                .getFile());
        chooser.setDialogTitle("Open Graphic");
        chooser.setDialogType(JFileChooser.OPEN_DIALOG);
        chooser.setFileFilter(new javax.swing.filechooser.FileFilter() {
            @Override
            public String getDescription() {
                return "JDraw Graphic (*.draw)";
            }

            @Override
            public boolean accept(File f) {
                return f.isDirectory() || f.getName().endsWith(".draw");
            }
        });
        int res = chooser.showOpenDialog(this);

        if (res == JFileChooser.APPROVE_OPTION) {
            // read jdraw graphic
            System.out.println("read file "
                    + chooser.getSelectedFile().getName());
        }
    }

    private class ClipBoard{
        private List<Figure> selection;

        protected List<Figure> getSelection() {
            return selection;
        }

        protected void setSelection(List<Figure> selection) {
            this.selection = selection;
        }

        protected void reset(){
            this.selection.clear();
        }

        protected boolean isEmpty(){
            return selection.isEmpty();
        }
    }
}

