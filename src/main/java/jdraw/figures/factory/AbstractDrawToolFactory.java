package jdraw.figures.factory;

import jdraw.framework.DrawToolFactory;

public abstract class AbstractDrawToolFactory implements DrawToolFactory {

    private String name; // name of the tool

    private String icon; // name of the icon

    @Override   // > Invoked by spring
    public void setName(String name) { this.name = name; }

    @Override
    public String getName() { return name; }

    @Override   // > Invoked by spring
    public void setIconName(String name) { this.icon = name; }

    @Override
    public String getIconName() { return icon; }

}