package de.shao.blueprints;

import java.awt.*;

public class FrameObject {
    protected int x;
    protected int y;

    protected Image objectImage;

    public void drawObject(Graphics2D g2d){
        g2d.drawImage(objectImage, x, y, null);
    }

    protected FrameObject(Image image){
        objectImage = image;
    }
}
