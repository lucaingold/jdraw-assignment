package jdraw.decorators;

import jdraw.framework.Figure;
import jdraw.framework.FigureHandle;

import java.util.List;

public class BundleDecorator extends AbstractDecorator {
    public BundleDecorator(Figure inner) {
        super(inner);
    }

    @Override
    public List<FigureHandle> getHandles() {
       return null;
    }
}

