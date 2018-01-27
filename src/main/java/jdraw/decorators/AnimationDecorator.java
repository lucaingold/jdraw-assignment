package jdraw.decorators;

import jdraw.framework.Figure;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.text.AttributedCharacterIterator;

public class AnimationDecorator extends AbstractDecorator  implements Runnable{

    public AnimationDecorator(Figure inner) {
        super(inner);
        Thread t = new Thread(this);
        t.start();
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            move(2, 2);
//            draw(g);
        }
    }
}

