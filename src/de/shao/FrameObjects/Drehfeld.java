package de.shao.FrameObjects;

import de.shao.blueprints.FrameObject;

import java.awt.*;

public class Drehfeld extends FrameObject {

    private int starthoehe;
    private final int PIXEL_PER_RUN = 10;

    Drehfeld(Image image, int x, int y,  int starthoehe){
        super(image);
        this.starthoehe = starthoehe;
        this.x = x;
        this.y = y;
    }

    @Override
    public void drawObject(Graphics2D g2d) {
        int wiederholungen = 60;
        new Thread(new Runnable() {
            @Override
            public void run() {
                g2d.drawImage(objectImage, x, y+starthoehe, null);
            }
        }).start();
    }
}
